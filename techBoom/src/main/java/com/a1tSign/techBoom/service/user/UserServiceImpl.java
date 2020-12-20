package com.a1tSign.techBoom.service.user;

import com.a1tSign.techBoom.data.dto.security.RegisterUserDTO;
import com.a1tSign.techBoom.data.dto.user.UserDTO;
import com.a1tSign.techBoom.data.entity.*;
import com.a1tSign.techBoom.data.mapper.StatisticMapper;
import com.a1tSign.techBoom.data.mapper.UserMapper;
import com.a1tSign.techBoom.data.repository.*;
import com.a1tSign.techBoom.filters.Comparison;
import com.a1tSign.techBoom.filters.SearchCriteria;
import com.a1tSign.techBoom.filters.specification.UserSpecification;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final UserMapper userMapper;
    private final CartRepository cartRepository;
    private final PasswordEncoder passwordEncoder;
    private final StatisticRepository statisticRepository;
    private final ItemRepository itemRepository;
    private final BranchRepository branchRepository;
    private final StoreHouseRepository storeHouseRepository;
    private final GoodsAmountRepository goodsAmountRepository;
    private final StatisticMapper statisticMapper;

    public UserServiceImpl(UserRepository userRepository, RoleRepository roleRepository,
                           UserMapper userMapper, CartRepository cartRepository,
                           PasswordEncoder passwordEncoder, StatisticRepository statisticRepository,
                           ItemRepository itemRepository, BranchRepository branchRepository,
                           StoreHouseRepository storeHouseRepository, GoodsAmountRepository goodsAmountRepository,
                           StatisticMapper statisticMapper) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.userMapper = userMapper;
        this.cartRepository = cartRepository;
        this.passwordEncoder = passwordEncoder;
        this.statisticRepository = statisticRepository;
        this.itemRepository = itemRepository;
        this.branchRepository = branchRepository;
        this.storeHouseRepository = storeHouseRepository;
        this.goodsAmountRepository = goodsAmountRepository;
        this.statisticMapper = statisticMapper;
    }

    @Override
    public User findByUsername(String username) {
        var user = userRepository.findByUsername(username);

        return user.orElse(null);
    }

    @Override
    public User findById(Long id) {
        var user = userRepository.findById(id);

        return user.orElse(null);
    }

    @Override
    public Page<User> findAll(Pageable pageable) {
        var users = userRepository.findAll(pageable);

        return !users.isEmpty() ? users : Page.empty();
    }

    @Override
    @Transactional
    public void createNewUser(RegisterUserDTO registerUserDTO) {
        var roles = roleExtractor(registerUserDTO);
        String password = passwordEncoder.encode(registerUserDTO.getPassword());

        User user = userMapper.toUserEntity(registerUserDTO, roles, password);

        userRepository.save(user);

        Cart cart = new Cart();
        cart.setUser(user);

        cartRepository.save(cart);
    }

    @Override
    public User findByUsernameAndPassword(String username, String password) {
        var user = userRepository.findByUsername(username);

        if (user.isPresent()) {
            if (passwordEncoder.matches(password, user.get().getPassword()))
                return user.get();
        }

        return null;
    }

    @Override
    @Transactional
    public void buyOne(long itemId, String username, int amount, long branchId) {
        var item = itemRepository.findById(itemId);
        var user = userRepository.findByUsername(username);
        var branch = branchRepository.findById(branchId);
        var cart = cartRepository.findByUser(user.orElse(null));
        var store = storeHouseRepository.findByBranch_Id(branchId);

        if (item.isPresent() && user.isPresent() && branch.isPresent()) {
            double budget = user.get().getBudget();
            double fullCost = item.get().getCost().doubleValue() * amount;
            GoodsAmount goodsAmount = goodsAmountRepository.findByStoreAndItem(store, item.get());

            if (goodsAmount.getCount() >= amount && fullCost <= budget) {
                user.get().setBudget(budget - fullCost);
                goodsAmount.setCount(goodsAmount.getCount() - amount);

                if (goodsAmount.getCount() == 0) goodsAmountRepository.delete(goodsAmount);
                cart.getItems().remove(item.get());

                Statistic statistic = statisticMapper.newStatistic(amount, branch.get(), fullCost,
                        LocalDateTime.now(), item.get(), user.get());

                statisticRepository.save(statistic);
            }
        }
    }

    @Override
    public Page<UserDTO> findOnlyUsersWithGreaterBudget(double budget, Pageable pageable) {
        var role = roleRepository.findByName("ROLE_USER");

        UserSpecification usRole = new UserSpecification(new ArrayList<>());
        usRole.addCriteria(new SearchCriteria("roles", role.orElse(null), Comparison.IN));

        UserSpecification usBudget = new UserSpecification(new ArrayList<>());
        usBudget.addCriteria(new SearchCriteria("budget", budget, Comparison.GREATER_THAN));

        return userRepository.findAll(Specification.where(usRole).and(usBudget), pageable)
                .map((e) -> userMapper.toUserDTO(e, roleUnExtractor(e)));
    }

    private List<Role> roleExtractor(RegisterUserDTO userDTO) {
        List<Role> roles = new ArrayList<>();

        for (String str : userDTO.getRoles()) {
            var role = roleRepository.findByName(str);
            role.ifPresent(roles::add);
        }

        return roles;
    }

    private List<String> roleUnExtractor(User user) {
        return user.getRoles().stream().map(Role::getName).collect(Collectors.toList());
    }

}
