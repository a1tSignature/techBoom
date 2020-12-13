package com.a1tSign.techBoom.data.dto.item;

import com.a1tSign.techBoom.data.entity.Category;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.List;

@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
public class ItemDTO {
    String title;
    List<String> categories;
    BigDecimal cost;
}
