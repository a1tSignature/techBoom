package com.a1tSign.techBoom.data.dto.branch;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@EqualsAndHashCode
public class NearestBranchDTO {

    double xUserCoordinate;
    double yUserCoordinate;

    int itemId;
}
