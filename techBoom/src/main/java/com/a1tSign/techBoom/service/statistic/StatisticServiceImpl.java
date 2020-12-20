package com.a1tSign.techBoom.service.statistic;

import com.a1tSign.techBoom.data.dto.statistic.StatisticDTO;
import com.a1tSign.techBoom.data.mapper.ItemMapper;
import com.a1tSign.techBoom.data.mapper.StatisticMapper;
import com.a1tSign.techBoom.data.mapper.UserMapper;
import com.a1tSign.techBoom.data.repository.BranchRepository;
import com.a1tSign.techBoom.data.repository.ItemRepository;
import com.a1tSign.techBoom.data.repository.StatisticRepository;
import com.a1tSign.techBoom.data.repository.UserRepository;
import com.a1tSign.techBoom.filters.Comparison;
import com.a1tSign.techBoom.filters.SearchCriteria;
import com.a1tSign.techBoom.filters.specification.StatisticSpecification;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Service
public class StatisticServiceImpl implements StatisticService {

    private final StatisticRepository statisticRepository;
    private final UserRepository userRepository;
    private final BranchRepository branchRepository;
    private final ItemRepository itemRepository;
    private final ItemMapper itemMapper;
    private final UserMapper userMapper;
    private final StatisticMapper statisticMapper;

    public StatisticServiceImpl(StatisticRepository statisticRepository, UserRepository userRepository,
                                BranchRepository branchRepository, ItemRepository itemRepository,
                                ItemMapper itemMapper, UserMapper userMapper,
                                StatisticMapper statisticMapper) {
        this.statisticRepository = statisticRepository;
        this.userRepository = userRepository;
        this.branchRepository = branchRepository;
        this.itemRepository = itemRepository;
        this.itemMapper = itemMapper;
        this.userMapper = userMapper;
        this.statisticMapper = statisticMapper;
    }

    @Override
    public Page<StatisticDTO> getFullStatistic(Pageable pageable) {
        return statisticRepository.findAll(pageable)
                .map((e) -> statisticMapper.toStatisticDTO(e,
                        e.getBranch(),
                        itemMapper.toItemStatisticDTO(e.getItem()),
                        userMapper.toUserStatisticDTO(e.getUser())));
    }

    @Override
    public Page<StatisticDTO> getStatisticForDays(int days, Pageable pageable) {
        StatisticSpecification ssTime = ssTime(days);

        return statisticRepository.findAll(ssTime, pageable)
                .map((e) -> statisticMapper.toStatisticDTO(e,
                        e.getBranch(),
                        itemMapper.toItemStatisticDTO(e.getItem()),
                        userMapper.toUserStatisticDTO(e.getUser())));
    }

    @Override
    public Page<StatisticDTO> getStatisticOfUser(String username, Pageable pageable) {
        var user = userRepository.findByUsername(username);

        StatisticSpecification ssUsername = new StatisticSpecification();
        ssUsername.addCriteria(new SearchCriteria("user",
                user.orElse(null), Comparison.EQUAL_OBJECT));

        return statisticRepository.findAll(ssUsername, pageable)
                .map((e) -> statisticMapper.toStatisticDTO(e,
                        e.getBranch(),
                        itemMapper.toItemStatisticDTO(e.getItem()),
                        userMapper.toUserStatisticDTO(e.getUser())));
    }

    @Override
    public Page<StatisticDTO> getStatisticOfUserForDays(String username, int days, Pageable pageable) {
        var user = userRepository.findByUsername(username);

        StatisticSpecification ssUsername = new StatisticSpecification();
        ssUsername.addCriteria(new SearchCriteria("user",
                user.orElse(null), Comparison.EQUAL_OBJECT));

        StatisticSpecification ssTime = ssTime(days);

        return statisticRepository.findAll(Specification.where(ssUsername).and(ssTime), pageable)
                .map((e) -> statisticMapper.toStatisticDTO(e,
                        e.getBranch(),
                        itemMapper.toItemStatisticDTO(e.getItem()),
                        userMapper.toUserStatisticDTO(e.getUser())));
    }

    @Override
    public Page<StatisticDTO> getStatisticOfBranch(String identifier, Pageable pageable) {
        var branch = branchRepository.findByIdentifier(identifier);

        StatisticSpecification ssIdentifier = new StatisticSpecification();
        ssIdentifier.addCriteria(new SearchCriteria("branch", branch, Comparison.EQUAL_OBJECT));

        return statisticRepository.findAll(ssIdentifier, pageable)
                .map((e) -> statisticMapper.toStatisticDTO(e,
                        e.getBranch(),
                        itemMapper.toItemStatisticDTO(e.getItem()),
                        userMapper.toUserStatisticDTO(e.getUser())));
    }

    @Override
    public Page<StatisticDTO> getStatisticOfBranchForDays(String identifier, int days, Pageable pageable) {
        var branch = branchRepository.findByIdentifier(identifier);

        StatisticSpecification ssIdentifier = new StatisticSpecification();
        ssIdentifier.addCriteria(new SearchCriteria("branch", branch, Comparison.EQUAL_OBJECT));

        StatisticSpecification ssTime = ssTime(days);

        return statisticRepository.findAll(Specification.where(ssIdentifier).and(ssTime), pageable)
                .map((e) -> statisticMapper.toStatisticDTO(e,
                        e.getBranch(),
                        itemMapper.toItemStatisticDTO(e.getItem()),
                        userMapper.toUserStatisticDTO(e.getUser())));
    }

    @Override
    public Page<StatisticDTO> getStatisticOfItem(String title, Pageable pageable) {
        var item = itemRepository.findByTitle(title);

        StatisticSpecification ssItem = new StatisticSpecification();
        ssItem.addCriteria(new SearchCriteria("item", item, Comparison.EQUAL_OBJECT));

        return statisticRepository.findAll(ssItem, pageable)
                .map((e) -> statisticMapper.toStatisticDTO(e,
                        e.getBranch(),
                        itemMapper.toItemStatisticDTO(e.getItem()),
                        userMapper.toUserStatisticDTO(e.getUser())));
    }

    @Override
    public Page<StatisticDTO> getStatisticOfItemForDays(String title, int days, Pageable pageable) {
        var item = itemRepository.findByTitle(title);

        StatisticSpecification ssItem = new StatisticSpecification();
        ssItem.addCriteria(new SearchCriteria("item", item, Comparison.EQUAL_OBJECT));

        StatisticSpecification ssTime = ssTime(days);

        return statisticRepository.findAll(Specification.where(ssItem).and(ssTime), pageable)
                .map((e) -> statisticMapper.toStatisticDTO(e,
                        e.getBranch(),
                        itemMapper.toItemStatisticDTO(e.getItem()),
                        userMapper.toUserStatisticDTO(e.getUser())));
    }

    @Override
    public BigDecimal getRevenueForDays(int days) {
        LocalDateTime timeLimit = LocalDateTime.now().minusDays(days);

        //return statisticRepository.sumSalary(timeLimit);
        return null;
    }



    private StatisticSpecification ssTime(int days) {
        LocalDateTime timeLimit = LocalDateTime.now().minusDays(days);
        StatisticSpecification ssTime = new StatisticSpecification();
        ssTime.addCriteria(new SearchCriteria("date", timeLimit, Comparison.GREATER_THAN_EQUAL));
        return ssTime;
    }
}
