package com.a1tSign.techBoom.controller;

import com.a1tSign.techBoom.data.dto.user.UserDTO;
import com.a1tSign.techBoom.service.admin.AdminService;
import com.a1tSign.techBoom.service.user.UserService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/admin")
public class AdminController {

    private final AdminService adminService;
    private final UserService userService;

    public AdminController(AdminService adminService, UserService userService) {
        this.adminService = adminService;
        this.userService = userService;
    }

    @PostMapping(
            value = "/role/create",
            params = "role"
    )
    public void createNewRole(@RequestParam String role) {
       adminService.createNewRole(role);
    }

    @PostMapping(
            value = "role/delete",
            params = "role"
    )
    public void deleteRole(@RequestParam String role) {
            adminService.deleteRole(role);
    }

    @PostMapping(value = "user/toAdmin",
    params = "username")
    public void makeAdmin(@RequestParam String username) {
        adminService.makeAdmin(username);
    }

    @PostMapping(value = "user/toUser",
    params = "username")
    public void makeUser(@RequestParam String username) {
        adminService.makeUser(username);
    }

    @PostMapping(value = "search/budget",
    params = "budget")
    public Page<UserDTO> findUsersWithGreaterBudget
            (@RequestParam double budget,
             @PageableDefault(sort = "budget", direction = Sort.Direction.ASC) Pageable pageable) {
        return userService.findOnlyUsersWithGreaterBudget(budget, pageable);
    }
}
