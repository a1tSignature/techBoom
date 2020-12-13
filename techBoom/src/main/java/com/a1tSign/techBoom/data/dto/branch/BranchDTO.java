package com.a1tSign.techBoom.data.dto.branch;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
public class BranchDTO {

    String identifier;

    double xCoordinate;
    double yCoordinate;
}
