package com.a1tSign.techBoom.data.dto.statistic;

import com.a1tSign.techBoom.data.dto.item.ItemStatisticDTO;
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
    private long user;
    private long branch;
    private ItemStatisticDTO item;
}
