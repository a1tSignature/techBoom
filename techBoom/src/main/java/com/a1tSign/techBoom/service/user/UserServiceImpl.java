package com.a1tSign.techBoom.service.user;

import com.a1tSign.techBoom.data.dto.security.RegisterUserDTO;
import com.a1tSign.techBoom.data.entity.Cart;
import com.a1tSign.techBoom.data.entity.Role;
import com.a1tSign.techBoom.data.entity.User;
import com.a1tSign.techBoom.data.mapper.UserMapper;
import com.a1tSign.techBoom.data.repository.CartRepository;
import com.a1tSign.techBoom.data.repository.RoleRepository;
import com.a1tSign.techBoom.data.repository.UserRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

    public UserServiceImpl(UserRepository userRepository, RoleRepository roleRepository,
                           UserMapper userMapper, CartRepository cartRepository,
                           PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.userMapper = userMapper;
        this.cartRepository = cartRepository;
        this.passwordEncoder = passwordEncoder;
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

        if(user.isPresent()) {
            if(passwordEncoder.matches(password, user.get().getPassword()))
                return user.get();
        }

        return null;
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
