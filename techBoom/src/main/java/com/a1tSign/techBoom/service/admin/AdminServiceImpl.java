package com.a1tSign.techBoom.service.admin;

import com.a1tSign.techBoom.data.entity.Role;
import com.a1tSign.techBoom.data.repository.RoleRepository;
import com.a1tSign.techBoom.data.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdminServiceImpl implements AdminService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;

    public AdminServiceImpl(UserRepository userRepository, RoleRepository roleRepository) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
    }

    @Override
    public void makeAdmin(String username) {
        var role = roleRepository.findByName("ROLE_ADMIN").orElse(null);
        addRole(role, username);
    }

    @Override
    public void makeUser(String username) {
        var role = roleRepository.findByName("ROLE_USER").orElse(null);
        addRole(role, username);
    }

    @Override
    public void createNewRole(String role) {
        Role roleEntity = new Role();
        roleEntity.setName(role);

        roleRepository.save(roleEntity);
    }

    @Override
    public void updateRole(String role, String newName) {
        var roleEntity = roleRepository.findByName(role);

        if(roleEntity.isPresent()) {
            roleEntity.get().setName(newName);
            roleRepository.save(roleEntity.get());
        }
    }

    @Override
    public void deleteRole(String role) {
        roleRepository.deleteByName(role);
    }

    private void addRole(Role role, String username) {
        var user = userRepository.findByUsername(username);

        if(user.isPresent()) {
            List<Role> roles = user.get().getRoles();
            roles.add(role);

            userRepository.save(user.get());
        }
    }
}
