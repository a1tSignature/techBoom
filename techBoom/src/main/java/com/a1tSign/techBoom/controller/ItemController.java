package com.a1tSign.techBoom.controller;

import com.a1tSign.techBoom.data.dto.branch.BranchDTO;
import com.a1tSign.techBoom.data.dto.branch.NearestBranchDTO;
import com.a1tSign.techBoom.data.dto.item.ItemDTO;
import com.a1tSign.techBoom.data.dto.item.ItemIdDTO;
import com.a1tSign.techBoom.data.entity.Branch;
import com.a1tSign.techBoom.security.CustomUserDetails;
import com.a1tSign.techBoom.service.branch.BranchService;
import com.a1tSign.techBoom.service.item.ItemService;
import org.springframework.data.domain.*;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static java.lang.Math.pow;
import static java.lang.Math.sqrt;

@RestController
@RequestMapping ("/api/v1/items")
@SuppressWarnings ("unused")
public class ItemController {

    private final ItemService itemService;

    public ItemController(ItemService itemService) {
        this.itemService = itemService;
    }

    @GetMapping ("/{id}")
    ItemDTO getOne(@PathVariable ("id") long id) {
        return itemService.findOne(id);
    }

    @GetMapping
    Page<ItemDTO> getAll(@PageableDefault(sort = {"id"}) Pageable pageable) {
        return itemService.findAll(pageable);
    }

    @PostMapping ("/create")
    @ResponseStatus (HttpStatus.CREATED)
    ItemDTO createItem(@RequestBody ItemDTO itemDTO) {
        itemService.createItem(itemDTO);

        return itemDTO;
    }

    @DeleteMapping ("/{id}/delete")
    @ResponseStatus (HttpStatus.OK)
    void deleteItem(@PathVariable ("id") long id) {
        itemService.deleteItem(id);
    }

    @PutMapping ("/{id}/update")
    ItemDTO updateUser(@PathVariable ("id") long id, @RequestBody ItemDTO itemDTO) {
        itemService.updateItem(id, itemDTO);

        return itemDTO;
    }

    @PostMapping ("/category")
    @ResponseStatus (HttpStatus.OK)
    void createCategory(@RequestParam String category) {
        itemService.testCreateCategory(category);
    }

    @GetMapping ("/{branchId}/all")
    List<ItemDTO> findAllItemsInBranch(@PathVariable ("branchId") long branchId) {
        return itemService.findAllItemsInBranch(branchId);
    }

    @PostMapping ("/{branchId}/add")
    @ResponseStatus (HttpStatus.ACCEPTED)
    void addItemInBranch(@PathVariable ("branchId") long branchId, @RequestBody ItemIdDTO itemIdDTO) {
        itemService.addItemInBranch(branchId, itemIdDTO);
    }

    @PostMapping ("/{id}/transfer")
    @ResponseStatus (HttpStatus.CREATED)
    public void transferItemToUserCart(@PathVariable ("id") long id, Authentication authentication) {
        var details = (CustomUserDetails) authentication.getPrincipal();
        var username = details.getUsername();

        itemService.transferItemToUserCart(username, id);
    }


}
