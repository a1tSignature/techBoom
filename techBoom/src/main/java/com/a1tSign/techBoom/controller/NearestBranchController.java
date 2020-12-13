package com.a1tSign.techBoom.controller;

import com.a1tSign.techBoom.data.dto.branch.BranchDTO;
import com.a1tSign.techBoom.data.dto.branch.NearestBranchDTO;
import com.a1tSign.techBoom.data.dto.item.ItemDTO;
import com.a1tSign.techBoom.service.branch.BranchService;
import com.a1tSign.techBoom.service.item.ItemService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static java.lang.Math.pow;
import static java.lang.Math.sqrt;

@RestController
@RequestMapping ("/api/v1/search")
public class NearestBranchController {

    private final BranchService branchService;
    private final ItemService itemService;

    public NearestBranchController(BranchService branchService, ItemService itemService) {
        this.branchService = branchService;
        this.itemService = itemService;
    }

    @GetMapping ("/having")
    BranchDTO findNearestBranchWithItem(@RequestBody NearestBranchDTO branchDTO) {
        Iterable<BranchDTO> branches = branchService.findAll();
        ItemDTO item = itemService.findOne(branchDTO.getItemId());

        BranchDTO nearestBranch = null;
        double minDistance = 0;
        for (BranchDTO branch : branches) {
            if (itemService.findAllItemsInBranch(branchService.findByIdentifier(branch.getIdentifier()).getId())
                    .contains(item))
                if (minDistance == 0 ||
                        minDistance > sqrt(pow(branchDTO.getXUserCoordinate() - branch.getXCoordinate(), 2) +
                                pow(branchDTO.getYUserCoordinate() - branch.getXCoordinate(), 2))) {
                    minDistance = sqrt(pow(branchDTO.getXUserCoordinate() - branch.getXCoordinate(), 2) +
                            pow(branchDTO.getYUserCoordinate() - branch.getXCoordinate(), 2));
                    nearestBranch = branch;
                }
        }
        return nearestBranch;
    }
}
