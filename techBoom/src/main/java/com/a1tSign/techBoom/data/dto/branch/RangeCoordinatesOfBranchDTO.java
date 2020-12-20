package com.a1tSign.techBoom.data.dto.branch;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
@ToString
public class RangeCoordinatesOfBranchDTO {
    double minX;
    double maxX;
    double minY;
    double maxY;
}
