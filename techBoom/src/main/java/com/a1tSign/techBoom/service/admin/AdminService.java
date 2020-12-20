package com.a1tSign.techBoom.service.admin;

public interface AdminService {
    void makeAdmin(String username);
    void makeUser(String username);
    void createNewRole(String role);
    void updateRole(String role, String newName);
    void deleteRole(String role);
}
