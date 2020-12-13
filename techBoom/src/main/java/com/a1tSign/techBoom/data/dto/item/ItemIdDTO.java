package com.a1tSign.techBoom.data.dto.item;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@EqualsAndHashCode
public class ItemIdDTO {

    private long itemId;
    private int count;
}
