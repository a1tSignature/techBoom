package com.a1tSign.techBoom.data.mapper;

import com.a1tSign.techBoom.data.dto.item.ItemDTO;
import com.a1tSign.techBoom.data.entity.Category;
import com.a1tSign.techBoom.data.entity.Item;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import java.util.ArrayList;
import java.util.List;

@Mapper (componentModel = "spring")
public interface ItemMapper {

    @Mappings ({
            @Mapping (target = "title", source = "item.title"),
            @Mapping (target = "cost", source = "item.cost"),
            @Mapping (target = "categories", source = "categories")})
    ItemDTO toItemDTO(Item item, List<String> categories);

    @Mappings ({
            @Mapping(target = "title", source = "itemDTO.title"),
            @Mapping(target = "cost", source = "itemDTO.cost"),
            @Mapping(target = "categories", source = "categories"),
    })
    Item toItemEntity(ItemDTO itemDTO, List<Category> categories);

    default Item toItemEntity(ItemDTO itemDTO) {
        Item item = new Item();
        item.setTitle(itemDTO.getTitle());
        item.setCost(itemDTO.getCost());

        List<Category> categories = new ArrayList<>();
        for (String str : itemDTO.getCategories()) {
            Category category = new Category();
            category.setName(str);

            categories.add(category);
        }

        item.setCategories(categories);

        return item;
    }
}
