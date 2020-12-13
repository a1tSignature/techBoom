package com.a1tSign.techBoom.data.dto.security;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@EqualsAndHashCode
@ToString
public class RegisterUserDTO {

    private String username;
    private String password;

    private double budget;

    private double xUserCoordinate;
    private double yUserCoordinate;
}
