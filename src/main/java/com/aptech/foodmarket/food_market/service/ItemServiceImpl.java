package com.aptech.foodmarket.food_market.service;

import com.aptech.foodmarket.food_market.builder.ItemVOBuilder;
import com.aptech.foodmarket.food_market.builder.SupplierVOBuilder;
import com.aptech.foodmarket.food_market.builder.UnitVOBuilder;
import com.aptech.foodmarket.food_market.model.*;
import com.aptech.foodmarket.food_market.repository.ItemRepository;
import com.aptech.foodmarket.food_market.repository.SupplierRepository;
import com.aptech.foodmarket.food_market.repository.UnitRepository;
import com.aptech.foodmarket.food_market.repository.UserRepository;
import com.aptech.foodmarket.food_market.vo.ItemVO;
import com.aptech.foodmarket.food_market.vo.SupplierVO;
import com.aptech.foodmarket.food_market.vo.UnitVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import javax.persistence.Convert;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.ArrayList;
import java.util.List;

@Service
public class ItemServiceImpl implements ItemService {
    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    private ItemRepository itemRepository;

    public List<ItemVO> defaultJson(List<Item> items) {
        List<ItemVO> itemVOS = new ArrayList<>();
        items.stream().forEach(item -> {
            UnitVO unitVO = new UnitVO();
            unitVO = UnitVOBuilder.anUnitVO().withId(item.getUnit().getId())
                                            .withName(item.getUnit().getName())
                                            .withSyntax(item.getUnit().getSyntax()).build();
            SupplierVO supplierVO = SupplierVOBuilder.aSupplierVO().withId(item.getSupplier().getId())
                                                                    .build();
            itemVOS.add(ItemVOBuilder.anItemVO().withId(item.getId())
                    .withName(item.getName())
                    .withPrice(item.getPrice())
                    .withAvatar(item.getAvatar())
                    .withQuantity(item.getQuantity())
//                    .withCreatedAt(item.getCreatedAt())
//                    .withEditedAt(item.getEditedAt())
//                    .withSupplier(supplierVO)
//                    .withUnit(unitVO)
//                    .withPromotions(item.getPromotionItems())
//                    .withImageItems(item.getImageItems())
//                    .withOrderItems(item.getOrderItems())
//                    .withCategory(item.getCategories())
                    .build());
        });
        return itemVOS;

    }

    public ItemVO convertVO(Item item) {
        ItemVO itemVO = ItemVOBuilder.anItemVO().withId(item.getId())
                .withName(item.getName())
                .withPrice(item.getPrice())
                .withAvatar(item.getAvatar())
                .withQuantity(item.getQuantity())
                .build();
        return itemVO;
    }

    @Override
    public List<ItemVO> getAll() {

        List<Item> items = itemRepository.findAll();
        return this.defaultJson(items);
    }

    @Override
    public List<ItemVO> getItemByName(String name) {
        List<Item> items = itemRepository.findByName(name);
        return this.defaultJson(items);
    }

    @Override
    public List<ItemVO> getItemByCategory(Category cate) {
        List<Item> items = itemRepository.findAllByCategories(cate);
        return this.defaultJson(items);
    }

    @Override
    public List<ItemVO> getItemByCategoryOrName(String key) {

        return null;
    }

    @Override
    public List<ItemVO> getItemBestSeller(List<Integer> ids) {
        return this.defaultJson(itemRepository.findByIdIn(ids));
    }

    @Override
    public List<ItemVO> getItemPromotion(int quantity) {
        return this.defaultJson(itemRepository.findAllByOrderByPromotionItemsDesc()).subList(0,quantity);
    }

    @Override
    public List<ItemVO> getItemNew(int quantity) {
        return this.defaultJson(itemRepository.findAllByOrderByIdDesc()).subList(0,quantity);
    }
    @Override
    public Page<ItemVO> findPaginated(int page, int size) {
        Page<Item> items = itemRepository.findAll(new PageRequest(page, size));
        Page<ItemVO> itemsVOItemVOS = items.map(new Converter<Item, ItemVO>() {
            @Override
            public ItemVO convert(Item entity) {
                return convertVO(entity);
            }
        });
        return itemsVOItemVOS;
    }

    @Override
    public List<ItemVO> getCart(List<Integer> itemIds) {
        List<ItemVO> itemVOS = this.defaultJson(itemRepository.findByIdIn(itemIds));
        System.out.println(itemVOS.get(1).getId());
        return itemVOS;
    }

    @Override
    public Item create(Item item) {
        Item newItem = new Item();
        newItem.setName(item.getName());
        newItem.setImageItems(item.getImageItems());
        newItem.setSupplier(item.getSupplier());
        newItem.setQuantity(item.getQuantity());
        newItem.setStatus(true);
        newItem.setDescription(item.getDescription());
        newItem.setCategories(item.getCategories());
        newItem.setPrice(item.getPrice());
        newItem.setAvatar(item.getAvatar());
        newItem.setUnit(item.getUnit());
        newItem = itemRepository.save(item);
        return newItem;
    }
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private SupplierRepository supplierRepository;

    @Autowired
    private UnitRepository unitRepository;

    @Override
    public void init() {
        try {
            User user = new User();
            user.setActive(true);
            user.setUsername("admin");

            userRepository.save(user);
            System.out.println(user.getId());
            Supplier supplier = new Supplier();
            supplier.setActive(true);
            supplier.setUser(user);
            //user.setSupplier(supplier);

            supplierRepository.save(supplier);
            Unit unit = new Unit();
            unit.setActive(true);
            unit.setSyntax("KG");
            unitRepository.save(unit);
            unit = new Unit();
            unit.setActive(true);
            unit.setSyntax("GOI");
            unitRepository.save(unit);
            //supplierRepository.save(supplier);
        } catch (Exception ex) {

        }

    }
}

