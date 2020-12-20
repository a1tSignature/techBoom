package com.a1tSign.techBoom.data.mapper;

import com.a1tSign.techBoom.data.dto.branch.BranchDTO;
import com.a1tSign.techBoom.data.entity.Branch;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

//Исправляет ли компонент то, что спринг не видит маппер в бинах? Да, исправляет
@Mapper(componentModel = "spring")
public interface BranchMapper {

    @Mappings ({
            @Mapping (target = "identifier", source = "identifier"),
            @Mapping (target = "XCoordinate", source = "XCoordinate"),
            @Mapping (target = "YCoordinate", source = "YCoordinate")})
    Branch toBranchEntity(BranchDTO branchDTO);

    @Mappings ({
            @Mapping (target = "identifier", source = "identifier"),
            @Mapping (target = "XCoordinate", source = "XCoordinate"),
            @Mapping (target = "YCoordinate", source = "YCoordinate")})
    BranchDTO toBranchDTO(Branch branch);
}
