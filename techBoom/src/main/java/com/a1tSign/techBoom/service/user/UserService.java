package com.a1tSign.techBoom.service.user;

import com.a1tSign.techBoom.data.dto.security.RegisterUserDTO;
import com.a1tSign.techBoom.data.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface UserService {
    User findByUsername(String username);
    User findById(Long id);
    Page<User> findAll(Pageable pageable);
    void createNewUser(RegisterUserDTO registerUserDTO);
    User findByUsernameAndPassword(String username, String password);
    void buyOne(long itemId, String username, int amount, long branchId);
    void addItemToCart(long itemId, String username);
}
