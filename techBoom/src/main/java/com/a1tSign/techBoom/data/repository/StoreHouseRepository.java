package com.a1tSign.techBoom.data.repository;

import com.a1tSign.techBoom.data.entity.StoreHouse;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StoreHouseRepository extends PagingAndSortingRepository<StoreHouse, Long> {
    StoreHouse findByBranch_Id(long branchId);
}
