package com.a1tSign.techBoom.controller;

import com.a1tSign.techBoom.data.dto.branch.BranchDTO;
import com.a1tSign.techBoom.data.dto.branch.NewBranchDTO;
import com.a1tSign.techBoom.service.branch.BranchService;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping ("/api/v1/branches")
@SuppressWarnings ("unused")
public class BranchController {

    private final BranchService branchService;

    public BranchController(BranchService branchService) {
        this.branchService = branchService;
    }

    @GetMapping ("/{id}")
    NewBranchDTO getOne(@PathVariable ("id") long id) {
        return null;
    }

    @GetMapping
    List<NewBranchDTO> getAll() {
        return null;
    }

    @PostMapping ("/create")
    @ResponseStatus(HttpStatus.OK)
    void createBranch(@RequestBody BranchDTO branchDTO) {
        branchService.createBranch(branchDTO);
    }

    @PutMapping ("/{id}/update")
    NewBranchDTO updateBranch(@PathVariable ("id") long id, @RequestBody NewBranchDTO branchDTO) {
        return null;
    }

    @DeleteMapping ("/{id}/delete")
    @ResponseStatus (HttpStatus.OK)
    void deleteBranch(@PathVariable ("id") long id) {
        boolean b = branchService.deleteBranch(id);

    }
}
