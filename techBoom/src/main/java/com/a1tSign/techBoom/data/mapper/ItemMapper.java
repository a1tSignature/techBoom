package com.a1tSign.techBoom.data.mapper;

import com.a1tSign.techBoom.data.dto.item.ItemDTO;
import com.a1tSign.techBoom.data.dto.item.ItemStatisticDTO;
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
            @Mapping (target = "id", source = "item.id"),
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

    @Mappings({
            @Mapping (target = "title", source = "item.title"),
            @Mapping (target = "cost", source = "item.cost")
    })
    ItemStatisticDTO toItemStatisticDTO(Item item);
}
