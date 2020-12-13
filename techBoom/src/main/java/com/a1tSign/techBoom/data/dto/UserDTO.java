package com.a1tSign.techBoom.data.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@EqualsAndHashCode
@ToString
public class UserDTO {

    private String username;

    private double budget;
    private double xUserCoordinate;
    private double yUserCoordinate;
}
