package com.a1tSign.techBoom.data.dto.user;

import lombok.*;

import java.util.List;

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

    private List<String> roles;
}
