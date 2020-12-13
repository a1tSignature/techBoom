package com.a1tSign.techBoom.data.entity;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "amount_of_goods")
@Getter
@Setter
@EqualsAndHashCode
public class GoodsAmount {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    private StoreHouse store;

    @ManyToOne(fetch = FetchType.LAZY)
    private Item item;

    private int count;
}
