package com.a1tSign.techBoom.data.entity;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.time.Instant;
import java.time.LocalDateTime;

@Entity
@Table(name = "statistic")
@Getter
@Setter
@EqualsAndHashCode
@ToString
public class Statistic {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private Integer amount;
    private double cost;
    private LocalDateTime date;

    @ManyToOne(fetch = FetchType.LAZY)
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    private Branch branch;

    @ManyToOne(fetch = FetchType.LAZY)
    private Item item;

}
