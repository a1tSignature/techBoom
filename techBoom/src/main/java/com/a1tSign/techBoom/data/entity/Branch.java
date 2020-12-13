package com.a1tSign.techBoom.data.entity;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "branch", schema = "public")
@Getter
@Setter
@EqualsAndHashCode
public class Branch {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private double xCoordinate;
    private double yCoordinate;

    private String identifier;
}
