package com.a1tSign.techBoom.data.repository;

import com.a1tSign.techBoom.data.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends PagingAndSortingRepository<User, Long>,
        JpaSpecificationExecutor<User> {
    //Page<User> allUsers(Pageable pageable);
    Optional<User> findByUsername(String username);
}
