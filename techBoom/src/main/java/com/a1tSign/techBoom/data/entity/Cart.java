package com.a1tSign.techBoom.data.entity;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.List;

@Entity
@Table (name = "cart")
@Getter
@Setter
@EqualsAndHashCode
@ToString
public class Cart {

    @Id
    private Long id;

    @ManyToMany
    @JoinTable (
            name = "cart_items",
            joinColumns = @JoinColumn(name = "cart_id", referencedColumnName = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "item_id", referencedColumnName = "id")
    )
    private List<Item> items;

    @OneToOne (fetch = FetchType.LAZY)
    @MapsId
    private User user;
}
