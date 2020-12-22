package com.a1tSign.techBoom.service.item;

import com.a1tSign.techBoom.data.dto.branch.BranchDTO;
import com.a1tSign.techBoom.data.dto.item.ItemDTO;
import com.a1tSign.techBoom.data.dto.item.ItemIdDTO;
import com.a1tSign.techBoom.data.entity.Item;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.annotation.Secured;

import java.util.Collection;
import java.util.List;

public interface ItemService {
    ItemDTO findOne(long id);
    @Secured("ROLE_ADMIN")
    Item findOneEntity(long id);
    Page<ItemDTO> findAll(Pageable pageable);
    @Secured ("ROLE_ADMIN")
    ItemDTO createItem(ItemDTO itemDTO);
    @Secured("ROLE_ADMIN")
    boolean deleteItem(long id);
    @Secured("ROLE_ADMIN")
    ItemDTO updateItem(long id, ItemDTO updItem);
    @Secured("ROLE_ADMIN")
    void testCreateCategory(String name);
    Page<ItemDTO> findAllItemsInBranch(long branchId, Pageable pageable);
    @Secured("ROLE_ADMIN")
    void addItemInBranch(long branchId, ItemIdDTO itemIdDTO);
    Page<ItemDTO> findByUserAndInRangeOfCosts(String username, double minValue, double maxValue, Pageable pageable);
    void transferItemToUserCart(String username, long itemId);

    List<ItemDTO> findAllItemsInBranch(Long id);
}
