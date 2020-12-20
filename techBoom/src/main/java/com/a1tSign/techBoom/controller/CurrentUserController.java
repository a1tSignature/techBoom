package com.a1tSign.techBoom.controller;

import com.a1tSign.techBoom.data.dto.user.BuyItemDTO;
import com.a1tSign.techBoom.security.CustomUserDetails;
import com.a1tSign.techBoom.service.user.UserService;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping ("api/v1/current")
public class CurrentUserController {

    private final UserService userService;

    public CurrentUserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping ("/buy/{itemId}")
    public void buyOne(@PathVariable("itemId") long itemId, @RequestBody BuyItemDTO buyItemDTO,
                       Authentication authentication) {
        var details = (CustomUserDetails) authentication.getPrincipal();
        String username = details.getUsername();

        userService.buyOne(itemId, username, buyItemDTO.getAmount(), buyItemDTO.getBranchId());
    }
}
