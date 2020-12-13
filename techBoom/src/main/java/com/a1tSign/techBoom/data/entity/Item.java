package com.a1tSign.techBoom.data.entity;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.List;

@Entity
@Table (name = "item", schema = "public")
@Getter
@Setter
@EqualsAndHashCode
public class Item {

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    private BigDecimal cost;

    @ManyToMany
    @JoinTable (
            name = "item_category",
            joinColumns = @JoinColumn(name = "item_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "category_id", referencedColumnName = "id")
    )
    private List<Category> categories;

    @OneToMany(mappedBy = "item")
    private List<GoodsAmount> amounts;

}
