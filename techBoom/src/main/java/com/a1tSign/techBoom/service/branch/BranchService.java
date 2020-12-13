package com.a1tSign.techBoom.service.branch;

import com.a1tSign.techBoom.data.dto.branch.BranchDTO;
import com.a1tSign.techBoom.data.entity.Branch;

import java.util.List;

public interface BranchService {
    BranchDTO updateBranch(long id, BranchDTO branchDTO);
    boolean deleteBranch(long id);
    BranchDTO createBranch(BranchDTO branchDTO);
    Iterable<BranchDTO> findAll();
    BranchDTO findOne(long branchId);
    long countAllBranches();
    Branch findByIdentifier(String identifier);
    //void offerForSale(long branchId, long itemId, int count);

}
