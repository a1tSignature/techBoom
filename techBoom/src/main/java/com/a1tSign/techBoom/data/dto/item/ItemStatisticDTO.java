package com.a1tSign.techBoom.data.dto.item;

import lombok.*;

import java.math.BigDecimal;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
@ToString
public class ItemStatisticDTO {
    String title;
    BigDecimal cost;
}
