package com.a1tSign.techBoom.service.item;

import com.a1tSign.techBoom.data.dto.item.ItemDTO;
import com.a1tSign.techBoom.data.dto.item.ItemIdDTO;
import com.a1tSign.techBoom.data.entity.Cart;
import com.a1tSign.techBoom.data.entity.Category;
import com.a1tSign.techBoom.data.entity.GoodsAmount;
import com.a1tSign.techBoom.data.entity.Item;
import com.a1tSign.techBoom.data.mapper.ItemMapper;
import com.a1tSign.techBoom.data.repository.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ItemServiceImpl implements ItemService {

    private final ItemRepository itemRepository;
    private final ItemMapper itemMapper;
    private final CategoryRepository categoryRepository;
    private final StoreHouseRepository storeHouseRepository;
    private final GoodsAmountRepository goodsAmountRepository;
    private final CartRepository cartRepository;
    private final UserRepository userRepository;

    public ItemServiceImpl(ItemRepository itemRepository, ItemMapper itemMapper,
                           CategoryRepository categoryRepository, StoreHouseRepository storeHouseRepository,
                           GoodsAmountRepository goodsAmountRepository, CartRepository cartRepository,
                           UserRepository userRepository) {
        this.itemRepository = itemRepository;
        this.itemMapper = itemMapper;
        this.categoryRepository = categoryRepository;
        this.storeHouseRepository = storeHouseRepository;
        this.goodsAmountRepository = goodsAmountRepository;
        this.cartRepository = cartRepository;
        this.userRepository = userRepository;
    }

    @Override
    //may produces null, catch
    public ItemDTO findOne(long id) {
        var itemEntity = itemRepository.findById(id);
        return itemEntity.map(item -> itemMapper.toItemDTO(item,
                categoryUnExtractor(item))).orElse(null);
    }

    @Override
    public Iterable<ItemDTO> findAll() {
        List<Item> items = new ArrayList<>();

        itemRepository.findAll(Sort.by("title")).forEach(items::add);
        return items.stream().map((item -> itemMapper.toItemDTO(item, categoryUnExtractor(item))))
                .collect(Collectors.toList());

    }

    @Override
    public ItemDTO createItem(ItemDTO itemDTO) {
        var categories = categoryExtractor(itemDTO);
        itemRepository.save(itemMapper.toItemEntity(itemDTO, categories));

        return itemDTO;
    }

    @Override
    public boolean deleteItem(long id) {
        var item = itemRepository.findById(id);
        item.ifPresent(itemRepository::delete);

        return true;
    }

    @Override
    @Transactional
    public ItemDTO updateItem(long id, ItemDTO updItem) {
        List<Category> categories = categoryExtractor(updItem);

        Item item = itemRepository.findById(id).get();
        item.setTitle(updItem.getTitle());
        item.setCost(updItem.getCost());
        item.setCategories(categories);

        itemRepository.save(item);

        return updItem;
    }

    @Override
    public void testCreateCategory(String name) {
        Category category = new Category();
        category.setName(name);

        categoryRepository.save(category);
    }

    @Override
    @Transactional
    public void addItemInBranch(long branchId, ItemIdDTO itemIdDTO) {
        var store = storeHouseRepository.findByBranch_Id(branchId);
        var item = itemRepository.findById(itemIdDTO.getItemId());

        GoodsAmount goodsAmount = new GoodsAmount();
        item.ifPresent(goodsAmount::setItem);
        goodsAmount.setStore(store);
        goodsAmount.setCount(itemIdDTO.getCount());

        goodsAmountRepository.save(goodsAmount);
    }

    @Override
    @Transactional
    public Page<ItemDTO> findItemsByUserId(long userId) {
        var user = userRepository.findById(userId);
        var cart = cartRepository.findByUser(user.orElse(null));
        List<ItemDTO> items = cart.getItems().stream()
                .map((item -> itemMapper.toItemDTO(item, categoryUnExtractor(item))))
                .collect(Collectors.toList());

        //TODO: there is not a pagination, do real one
        return (Page) items;
    }

    @Override
    //may produces null, catch
    public List<ItemDTO> findAllItemsInBranch(long branchId) {
        var store = storeHouseRepository.findByBranch_Id(branchId);
        List<GoodsAmount> goods = goodsAmountRepository.findByStore(store);

        List<ItemDTO> allItems = new ArrayList<>();

        for (GoodsAmount good : goods) {
            var item = itemRepository.findById(good.getItem().getId());
            ItemDTO itemDTO = itemMapper
                    .toItemDTO(item.get(), categoryUnExtractor(item.get()));
            allItems.add(itemDTO);
        }

        return allItems;
    }

    @Override
    //may produces null, catch
    public Item findOneEntity(long id) {
        return itemRepository.findById(id).orElse(null);
    }

    private List<Category> categoryExtractor(ItemDTO itemDTO) {
        List<Category> categories = new ArrayList<>();

        for (String str : itemDTO.getCategories()) {
            var category = categoryRepository.findByName(str);
            category.ifPresent(categories::add);
        }

        return categories;
    }

    private List<String> categoryUnExtractor(Item item) {
        return item.getCategories().stream().map(Category::getName).collect(Collectors.toList());
    }

    @Override
    @Transactional
    public void transferItemToUserCart(String username, long itemId) {
        var user = userRepository.findByUsername(username);
        var item = itemRepository.findById(itemId);
        var cart = cartRepository.findByUser(user.orElse(null));

        List<Item> list = cart.getItems();
        list.add(item.orElse(null));

        cart.setItems(list);
    }
}
