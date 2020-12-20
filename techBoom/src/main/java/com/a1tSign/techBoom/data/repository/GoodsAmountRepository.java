package com.a1tSign.techBoom.data.repository;

import com.a1tSign.techBoom.data.entity.GoodsAmount;
import com.a1tSign.techBoom.data.entity.Item;
import com.a1tSign.techBoom.data.entity.StoreHouse;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GoodsAmountRepository extends JpaRepository<GoodsAmount, Long> {
    List<GoodsAmount> findByStore(StoreHouse storeHouse, Pageable pageable);
    GoodsAmount findByStoreAndItem(StoreHouse storeHouse, Item item);
}
