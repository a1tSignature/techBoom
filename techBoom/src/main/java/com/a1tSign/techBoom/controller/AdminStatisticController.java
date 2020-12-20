package com.a1tSign.techBoom.controller;

import com.a1tSign.techBoom.data.dto.statistic.StatisticDTO;
import com.a1tSign.techBoom.service.statistic.StatisticService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping ("api/v1/statistic")
public class AdminStatisticController {

    private final StatisticService statisticService;

    public AdminStatisticController(StatisticService statisticService) {
        this.statisticService = statisticService;
    }

    @GetMapping ("/all")
    public Page<StatisticDTO> getAllStatistic(
            @PageableDefault (sort = {"id"}, direction = Sort.Direction.DESC) Pageable pageable) {
        return statisticService.getFullStatistic(pageable);
    }

    @GetMapping (value = "/all/days", params = "days")
    public Page<StatisticDTO> getAllForDays
            (@RequestParam int days, @PageableDefault (sort = {"date"},
                    direction = Sort.Direction.DESC) Pageable pageable) {
        return statisticService.getStatisticForDays(days, pageable);
    }

    @GetMapping (value = "/user", params = "username")
    public Page<StatisticDTO> getAllUserStatistic(@PageableDefault (sort = {"amount"},
            direction = Sort.Direction.ASC) Pageable pageable, @RequestParam String username) {
        return statisticService.getStatisticOfUser(username, pageable);
    }

    @GetMapping (value = "/user/days", params = {"username", "days"})
    public Page<StatisticDTO> getUserStatisticForDays
            (@RequestParam String username, @RequestParam int days,
             @PageableDefault (sort = {"amount", "cost"}, direction = Sort.Direction.ASC) Pageable pageable) {
        return statisticService.getStatisticOfUserForDays(username, days, pageable);
    }

    @GetMapping (value = "/branch", params = "identifier")
    public Page<StatisticDTO> getAllBranchStatistic
            (@RequestParam String identifier,
             @PageableDefault (sort = {"id"}, direction = Sort.Direction.DESC, size = 5) Pageable pageable) {
        return statisticService.getStatisticOfBranch(identifier, pageable);
    }

    @GetMapping (value = "/branch/days", params = {"identifier", "days"})
    public Page<StatisticDTO> getBranchStatisticForDays
            (@RequestParam String identifier, @RequestParam int days,
             @PageableDefault (sort = {"date"}, direction = Sort.Direction.DESC, size = 8) Pageable pageable) {
        return statisticService.getStatisticOfBranchForDays(identifier, days, pageable);
    }

    @GetMapping (value = "/item", params = "title")
    public Page<StatisticDTO> getAllItemStatistic
            (@RequestParam String title,
             @PageableDefault (sort = {"date"}, direction = Sort.Direction.ASC) Pageable pageable) {
        return statisticService.getStatisticOfItem(title, pageable);
    }

    @GetMapping (value = "/item/days", params = {"title", "days"})
    public Page<StatisticDTO> getItemStatisticForDays
            (@RequestParam String title, @RequestParam int days,
             @PageableDefault (sort = {"amount"}, direction = Sort.Direction.DESC) Pageable pageable) {
        return statisticService.getStatisticOfItemForDays(title, days, pageable);
    }

    @GetMapping("/branches/count")
    public long countAllBranches() {
        return statisticService.countAllBranches();
    }

    //repair method that returns revenue, it doesn't work
    @GetMapping(value = "revenue/days", params = "days")
    public double getRevenuePerRangeOfDays(@RequestParam int days) {
        return statisticService.getRevenueForDays(days);
    }
}
