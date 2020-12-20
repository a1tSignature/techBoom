package com.a1tSign.techBoom.controller;

import com.a1tSign.techBoom.data.entity.Role;
import com.a1tSign.techBoom.data.repository.RoleRepository;
import com.a1tSign.techBoom.service.admin.AdminService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/admin")
public class AdminController {

    private final AdminService adminService;

    public AdminController(AdminService adminService) {
        this.adminService = adminService;
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

}
