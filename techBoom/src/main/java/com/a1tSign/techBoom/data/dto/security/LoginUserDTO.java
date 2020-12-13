package com.a1tSign.techBoom.data.dto.security;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@EqualsAndHashCode
@ToString
public class LoginUserDTO {

    private String username;
    private String password;
}
