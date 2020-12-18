package com.a1tSign.techBoom.filters;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
@ToString
public class SearchCriteria {
    private String key;
    private Object value;
    private Comparison comparison;

}
