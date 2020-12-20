package com.a1tSign.techBoom.data.dto.branch;

import lombok.*;

@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
@ToString
public class TransferBranchDTO {

    String identifier;

    double xCoordinate;
    double yCoordinate;
}
