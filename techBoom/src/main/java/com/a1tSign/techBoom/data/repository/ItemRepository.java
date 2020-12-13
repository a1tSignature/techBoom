package com.a1tSign.techBoom.data.repository;

import com.a1tSign.techBoom.data.entity.Item;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ItemRepository extends PagingAndSortingRepository<Item, Long> {
}
