package com.a1tSign.techBoom.data.repository;

import com.a1tSign.techBoom.data.entity.Cart;
import com.a1tSign.techBoom.data.entity.User;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CartRepository extends PagingAndSortingRepository<Cart, Long> {
    Cart findByUser(User user);
}
