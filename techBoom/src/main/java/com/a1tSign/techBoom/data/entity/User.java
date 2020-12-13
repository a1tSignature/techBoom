package com.a1tSign.techBoom.data.entity;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Table (name = "users", schema = "public")
@Getter
@Setter
@EqualsAndHashCode
public class User {

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Long id;

    private String username;
    private String password;

    private double budget;

    @Column(name = "x_user_coordinate")
    private double xUserCoordinate;

    @Column(name = "y_user_coordinate")
    private double yUserCoordinate;


    @ManyToMany
    @JoinTable (
            name = "user_roles",
            joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "role_id", referencedColumnName = "id")
    )
    private List<Role> roles;
}
