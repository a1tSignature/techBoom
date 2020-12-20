package com.a1tSign.techBoom.controller;

import com.a1tSign.techBoom.data.dto.branch.BranchDTO;
import com.a1tSign.techBoom.data.dto.branch.NearestBranchDTO;
import com.a1tSign.techBoom.data.dto.branch.TransferBranchDTO;
import com.a1tSign.techBoom.data.dto.item.ItemDTO;
import com.a1tSign.techBoom.service.branch.BranchService;
import com.a1tSign.techBoom.service.item.ItemService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static java.lang.Math.pow;
import static java.lang.Math.sqrt;

@RestController
@RequestMapping ("/api/v1/branches")
@SuppressWarnings ("unused")
public class BranchController {

    private final BranchService branchService;
    private final ItemService itemService;

    public BranchController(BranchService branchService, ItemService itemService) {
        this.branchService = branchService;
        this.itemService = itemService;
    }

    @GetMapping ("/{id}")
    TransferBranchDTO getOne(@PathVariable ("id") long id) {
        return null;
    }

    @GetMapping
    List<TransferBranchDTO> getAll() {
        return null;
    }

    @PostMapping ("/create")
    @ResponseStatus(HttpStatus.OK)
    void createBranch(@RequestBody BranchDTO branchDTO) {
        branchService.createBranch(branchDTO);
    }

    @PutMapping ("/{id}/update")
    TransferBranchDTO updateBranch(@PathVariable ("id") long id, @RequestBody TransferBranchDTO branchDTO) {
        return null;
    }

    @DeleteMapping ("/{id}/delete")
    @ResponseStatus (HttpStatus.OK)
    void deleteBranch(@PathVariable ("id") long id) {
        boolean b = branchService.deleteBranch(id);
    }

    @PostMapping ("/having")
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
