package com.a1tSign.techBoom.controller;

import com.a1tSign.techBoom.data.entity.Role;
import com.a1tSign.techBoom.data.repository.RoleRepository;
import com.a1tSign.techBoom.service.branch.BranchService;
import com.a1tSign.techBoom.service.item.ItemService;
import com.a1tSign.techBoom.service.user.UserService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/admin")
public class AdminController {

    private final UserService userService;
    private final ItemService itemService;
    private final BranchService branchService;
    private final PasswordEncoder passwordEncoder;
    private final RoleRepository roleRepository;

    public AdminController(UserService userService, ItemService itemService,
                           BranchService branchService, PasswordEncoder passwordEncoder,
                           RoleRepository roleRepository) {
        this.userService = userService;
        this.itemService = itemService;
        this.branchService = branchService;
        this.passwordEncoder = passwordEncoder;
        this.roleRepository = roleRepository;
    }

    @PostMapping(
            value = "/role/create",
            params = "role"
    )
    public void createNewRole(@RequestParam String role) {
        Role roleEntity = new Role();
        roleEntity.setName(role);

        roleRepository.save(roleEntity);
    }

    @PostMapping(
            value = "role/delete",
            params = "role"
    )
    public void deleteRole(@RequestParam String role) {
            roleRepository.deleteByName(role);
    }

}
