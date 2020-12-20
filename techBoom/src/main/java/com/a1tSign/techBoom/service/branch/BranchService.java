package com.a1tSign.techBoom.service.branch;

import com.a1tSign.techBoom.data.dto.branch.BranchDTO;
import com.a1tSign.techBoom.data.entity.Branch;
import org.springframework.security.access.annotation.Secured;

import java.util.List;

public interface BranchService {
    @Secured("ROLE_ADMIN")
    BranchDTO updateBranch(long id, BranchDTO branchDTO);
    @Secured("ROLE_ADMIN")
    boolean deleteBranch(long id);
    @Secured("ROLE_ADMIN")
    BranchDTO createBranch(BranchDTO branchDTO);
    Iterable<BranchDTO> findAll();
    BranchDTO findOne(long branchId);
    @Secured("ROLE_ADMIN")
    long countAllBranches();
    Branch findByIdentifier(String identifier);
    //void offerForSale(long branchId, long itemId, int count);

}
