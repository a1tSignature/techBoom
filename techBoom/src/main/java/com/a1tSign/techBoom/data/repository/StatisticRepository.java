package com.a1tSign.techBoom.data.repository;

import com.a1tSign.techBoom.data.entity.Statistic;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Repository
public interface StatisticRepository extends PagingAndSortingRepository<Statistic, Long>,
        JpaSpecificationExecutor<Statistic> {

    @Query ("SELECT SUM(s.cost) FROM Statistic s WHERE s.date >= :date")
    double sumSalary(LocalDateTime date);
}
