package com.a1tSign.techBoom.data.dto.user;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
@ToString
public class UserStatisticDTO {
    String username;

    double xCoordinate;
    double yCoordinate;
}
