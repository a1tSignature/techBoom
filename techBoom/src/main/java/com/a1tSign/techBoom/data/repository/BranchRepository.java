package com.a1tSign.techBoom.data.repository;

import com.a1tSign.techBoom.data.entity.Branch;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BranchRepository extends PagingAndSortingRepository<Branch, Long> {
    Branch findByIdentifier(String identifier);
}
