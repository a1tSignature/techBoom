package com.a1tSign.techBoom.data.entity;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Table (name = "store", schema = "public")
@Getter
@Setter
@EqualsAndHashCode
@NoArgsConstructor
public class StoreHouse {

    @Id
    private Long id;

    @OneToOne (fetch = FetchType.LAZY)
    @MapsId
    private Branch branch;

    @OneToMany (mappedBy = "store")
    private List<GoodsAmount> goodsAmounts;
}
