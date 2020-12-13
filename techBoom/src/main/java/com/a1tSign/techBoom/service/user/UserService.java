package com.a1tSign.techBoom.service.user;

import com.a1tSign.techBoom.data.entity.User;

public interface UserService {
    User findByUsernameAndPassword(String username, String password);
    User findById(Long id);
}
