package com.a1tSign.techBoom.service.user;

import com.a1tSign.techBoom.data.entity.User;
import com.a1tSign.techBoom.data.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Override
    public User findByUsernameAndPassword(String username, String password) {
        return null;
    }

    @Override
    public User findById(Long id) {
        return null;
    }
}
