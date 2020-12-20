package com.a1tSign.techBoom.service.statistic;

import com.a1tSign.techBoom.data.dto.statistic.StatisticDTO;
import com.a1tSign.techBoom.data.entity.Statistic;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.math.BigDecimal;

public interface StatisticService {
    Page<StatisticDTO> getFullStatistic(Pageable pageable);
    Page<StatisticDTO> getStatisticForDays(int days, Pageable pageable);
    Page<StatisticDTO> getStatisticOfUser(String username, Pageable pageable);
    Page<StatisticDTO> getStatisticOfUserForDays(String username, int days, Pageable pageable);
    Page<StatisticDTO> getStatisticOfBranch(String identifier, Pageable pageable);
    Page<StatisticDTO> getStatisticOfBranchForDays(String identifier, int days, Pageable pageable);
    Page<StatisticDTO> getStatisticOfItem(String title, Pageable pageable);
    Page<StatisticDTO> getStatisticOfItemForDays(String title, int days, Pageable pageable);
    double getRevenueForDays(int days);
    long countAllBranches();
}
