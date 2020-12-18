package com.a1tSign.techBoom.data.repository;

import com.a1tSign.techBoom.data.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
    Optional<Role> findByName(String name);
    @Transactional
    void deleteByName(String name);
}
