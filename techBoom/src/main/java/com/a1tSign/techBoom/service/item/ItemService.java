package com.a1tSign.techBoom.service.item;

import com.a1tSign.techBoom.data.dto.branch.BranchDTO;
import com.a1tSign.techBoom.data.dto.item.ItemDTO;
import com.a1tSign.techBoom.data.dto.item.ItemIdDTO;
import com.a1tSign.techBoom.data.entity.Item;

import java.util.List;

public interface ItemService {
    ItemDTO findOne(long id);
    Item findOneEntity(long id);
    Iterable<ItemDTO> findAll();
    ItemDTO createItem(ItemDTO itemDTO);
    boolean deleteItem(long id);
    ItemDTO updateItem(long id, ItemDTO updItem);
    void testCreateCategory(String name);
    List<ItemDTO> findAllItemsInBranch(long branchId);
    void addItemInBranch(long branchId, ItemIdDTO itemIdDTO);
}
