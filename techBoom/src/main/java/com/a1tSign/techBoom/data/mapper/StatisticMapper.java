package com.a1tSign.techBoom.data.mapper;

import com.a1tSign.techBoom.data.dto.item.ItemStatisticDTO;
import com.a1tSign.techBoom.data.dto.statistic.StatisticDTO;
import com.a1tSign.techBoom.data.dto.user.UserStatisticDTO;
import com.a1tSign.techBoom.data.entity.Branch;
import com.a1tSign.techBoom.data.entity.Item;
import com.a1tSign.techBoom.data.entity.Statistic;
import com.a1tSign.techBoom.data.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import java.time.LocalDateTime;

@Mapper(componentModel = "spring")
public interface StatisticMapper {

    @Mappings({
            @Mapping (target = "id", source = "statistic.id"),
            @Mapping (target = "amount", source = "statistic.amount"),
            @Mapping (target = "cost", source = "statistic.cost"),
            @Mapping (target = "date", source = "statistic.date"),
            @Mapping (target = "user", source = "user"),
            @Mapping (target = "branch", source = "branch"),
            @Mapping (target = "item", source = "item"),
    })
    StatisticDTO toStatisticDTO(Statistic statistic, Branch branch, ItemStatisticDTO item,
                                UserStatisticDTO user);

    @Mappings({
            @Mapping(target = "amount", source = "amount"),
            @Mapping(target = "branch", source = "branch"),
            @Mapping(target = "fullCost", source = "fullCost"),
            @Mapping(target = "date", source = "dateTime"),
            @Mapping(target = "item", source = "item"),
            @Mapping(target = "user", source = "user"),
            @Mapping(target = "amount", source = "amount")
    })
    Statistic newStatistic(int amount, Branch branch, double fullCost,
                           LocalDateTime dateTime, Item item, User user);
}
