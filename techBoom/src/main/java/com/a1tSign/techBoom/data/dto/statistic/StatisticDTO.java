package com.a1tSign.techBoom.data.dto.statistic;

import com.a1tSign.techBoom.data.dto.item.ItemStatisticDTO;
import com.a1tSign.techBoom.data.dto.user.UserStatisticDTO;
import com.a1tSign.techBoom.data.entity.Branch;
import lombok.*;

import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
@ToString
public class StatisticDTO {
    private int id;
    private int amount;
    private double cost;
    private LocalDateTime date;
    private UserStatisticDTO user;
    private Branch branch;
    private ItemStatisticDTO item;
}
