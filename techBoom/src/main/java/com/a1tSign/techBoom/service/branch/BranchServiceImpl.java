package com.a1tSign.techBoom.service.branch;

import com.a1tSign.techBoom.data.dto.branch.BranchDTO;
import com.a1tSign.techBoom.data.entity.Branch;
import com.a1tSign.techBoom.data.entity.StoreHouse;
import com.a1tSign.techBoom.data.mapper.BranchMapper;
import com.a1tSign.techBoom.data.repository.BranchRepository;
import com.a1tSign.techBoom.data.repository.GoodsAmountRepository;
import com.a1tSign.techBoom.data.repository.ItemRepository;
import com.a1tSign.techBoom.data.repository.StoreHouseRepository;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class BranchServiceImpl implements BranchService {

    private final BranchRepository branchRepository;
    private final StoreHouseRepository storeHouseRepository;
    private final GoodsAmountRepository goodsAmountRepository;
    private final ItemRepository itemRepository;
    private final BranchMapper branchMapper;

    public BranchServiceImpl(BranchRepository branchRepository, StoreHouseRepository storeHouseRepository,
                             GoodsAmountRepository goodsAmountRepository, ItemRepository itemRepository,
                             BranchMapper branchMapper) {
        this.branchRepository = branchRepository;
        this.storeHouseRepository = storeHouseRepository;
        this.goodsAmountRepository = goodsAmountRepository;
        this.itemRepository = itemRepository;
        this.branchMapper = branchMapper;
    }

    @Override
    public BranchDTO updateBranch(long id, BranchDTO branchDTO) {
        Branch branch = branchRepository.findById(id).get();
        branch.setIdentifier(branchDTO.getIdentifier());
        branch.setXCoordinate(branchDTO.getXCoordinate());
        branch.setYCoordinate(branchDTO.getYCoordinate());

        branchRepository.save(branch);

        return branchDTO;
    }

    @Override
    @Transactional
    public boolean deleteBranch(long id) {
        storeHouseRepository.deleteById(id);
        var branch = branchRepository.findById(id);
        branch.ifPresent(branchRepository::delete);

        return true;
    }

    @Override
    @Transactional
    public BranchDTO createBranch(BranchDTO branchDTO) {
        Branch branch = branchMapper.toBranchEntity(branchDTO);
        branchRepository.save(branch);
        StoreHouse storeHouse = new StoreHouse();
        storeHouse.setBranch(branch);
        storeHouseRepository.save(storeHouse);

        return branchDTO;
    }

    @Override
    public Iterable<BranchDTO> findAll() {
        List<Branch> branches = new ArrayList<>();
        branchRepository.findAll(Sort.by("identifier")).forEach(branches::add);

        return branches.stream().map(branchMapper::toBranchDTO)
                .collect(Collectors.toList());
    }

    @Override
    public BranchDTO findOne(long branchId) {
        var branch = branchRepository.findById(branchId);
        return branchMapper.toBranchDTO(branch.get());
    }

    @Override
    public long countAllBranches() {
        return branchRepository.count();
    }

    @Override
    public Branch findByIdentifier(String identifier) {
        return branchRepository.findByIdentifier(identifier);
    }

    //    @Override
//    public void offerForSale(long branchId, long itemId, int count) {
//        StoreHouse store = storeHouseRepository.findByBranch_Id(branchId);
//        Item item = itemRepository.findById(itemId).get();
//
//        GoodsAmount goodsAmount = new GoodsAmount();
//        goodsAmount.setCount(count);
//        goodsAmount.setStore(store);
//        goodsAmount.setItem(item);
//
//        goodsAmountRepository.save(goodsAmount);
//    }
}
