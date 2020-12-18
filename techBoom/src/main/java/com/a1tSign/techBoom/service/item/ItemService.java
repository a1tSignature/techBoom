package com.a1tSign.techBoom.service.item;

import com.a1tSign.techBoom.data.dto.branch.BranchDTO;
import com.a1tSign.techBoom.data.dto.item.ItemDTO;
import com.a1tSign.techBoom.data.dto.item.ItemIdDTO;
import com.a1tSign.techBoom.data.entity.Item;
import org.springframework.data.domain.Page;
import org.springframework.security.access.annotation.Secured;

import java.util.List;

public interface ItemService {
    ItemDTO findOne(long id);
    Item findOneEntity(long id);
    Iterable<ItemDTO> findAll();
    @Secured ("ROLE_ADMIN")
    ItemDTO createItem(ItemDTO itemDTO);
    @Secured("ROLE_ADMIN")
    boolean deleteItem(long id);
    @Secured("ROLE_ADMIN")
    ItemDTO updateItem(long id, ItemDTO updItem);
    void testCreateCategory(String name);
    List<ItemDTO> findAllItemsInBranch(long branchId);
    @Secured("ROLE_ADMIN")
    void addItemInBranch(long branchId, ItemIdDTO itemIdDTO);
    Page<ItemDTO> findItemsByUserId(long userId);
}
