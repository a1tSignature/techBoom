package com.a1tSign.techBoom.controller;

import com.a1tSign.techBoom.data.dto.item.ItemDTO;
import com.a1tSign.techBoom.data.dto.item.ItemIdDTO;
import com.a1tSign.techBoom.service.item.ItemService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/items")
@SuppressWarnings("unused")
public class ItemController {

    private final ItemService itemService;

    public ItemController(ItemService itemService) {
        this.itemService = itemService;
    }

    @GetMapping("/{id}")
    ItemDTO getOne(@PathVariable("id") long id) {
        return itemService.findOne(id);
    }

    @GetMapping
    Iterable<ItemDTO> getAll() {
        return itemService.findAll();
    }

    @PostMapping("/create")
    @ResponseStatus(HttpStatus.CREATED)
    ItemDTO createItem(@RequestBody ItemDTO itemDTO) {
        itemService.createItem(itemDTO);

        return itemDTO;
    }

    @DeleteMapping("/{id}/delete")
    @ResponseStatus(HttpStatus.OK)
    void deleteItem(@PathVariable("id") long id) {
        itemService.deleteItem(id);
    }

    @PutMapping("/{id}/update")
    ItemDTO updateUser(@PathVariable("id") long id, @RequestBody ItemDTO itemDTO) {
        itemService.updateItem(id, itemDTO);

        return itemDTO;
    }

    @PostMapping("/category")
    @ResponseStatus(HttpStatus.OK)
    void createCategory(@RequestParam String category) {
        itemService.testCreateCategory(category);
    }

    @GetMapping("/{branchId}/all")
    List<ItemDTO> findAllItemsInBranch(@PathVariable("branchId") long branchId) {
        return itemService.findAllItemsInBranch(branchId);
    }

    @PostMapping("/{branchId}/add")
    @ResponseStatus(HttpStatus.ACCEPTED)
    void addItemInBranch(@PathVariable("branchId") long branchId, @RequestBody ItemIdDTO itemIdDTO) {
        itemService.addItemInBranch(branchId, itemIdDTO);
    }


}
