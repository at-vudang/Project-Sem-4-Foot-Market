package com.aptech.foodmarket.food_market.service.ImplService;

import com.aptech.foodmarket.food_market.EntityNotFoundException;
import com.aptech.foodmarket.food_market.builder.ItemVOBuilder;
import com.aptech.foodmarket.food_market.builder.PromotionItemVOBuilder;
import com.aptech.foodmarket.food_market.builder.SupplierVOBuilder;
import com.aptech.foodmarket.food_market.builder.UnitVOBuilder;
import com.aptech.foodmarket.food_market.model.*;
import com.aptech.foodmarket.food_market.service.ItemService;
import com.aptech.foodmarket.food_market.vo.*;
import com.aptech.foodmarket.food_market.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.ArrayList;
import java.util.List;

@Service
public class ItemServiceImpl implements ItemService {
    @PersistenceContext
    private EntityManager entityManager;
    @Autowired
    private CategoryRepository categoryRepository;
    @Autowired
    private ItemRepository itemRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private SupplierRepository supplierRepository;
    @Autowired
    private UnitRepository unitRepository;

    private OrderRepository orderRepository;


    public List<ItemVO> defaultJson(List<Item> items) {
        List<ItemVO> itemVOS = new ArrayList<>();
        items.stream().forEach(item -> {
            UnitVO unitVO = new UnitVO();
            unitVO = UnitVOBuilder.anUnitVO().withId(item.getUnit().getId())
                                            .withName(item.getUnit().getName())
                                            .withSyntax(item.getUnit().getSyntax()).build();
            SupplierVO supplierVO = SupplierVOBuilder.aSupplierVO().withId(item.getSupplier().getId())
                                                                    .build();
            List<PromotionItemVO> promotionItemVOS = new ArrayList<>();
            for (PromotionItem promotionItem: item.getPromotionItems()
                    ) {
                PromotionItemVO promotionItemVO = PromotionItemVOBuilder.aPromotionItemVO()
                        .withId(promotionItem.getId())
                        .withPercent(promotionItem.getPercent()).build();
                promotionItemVOS.add(promotionItemVO);
            }
            itemVOS.add(ItemVOBuilder.anItemVO().withId(item.getId())
                    .withName(item.getName())
                    .withPrice(item.getPrice())
                    .withAvatar(item.getAvatar())
                    .withQuantity(item.getQuantity())
                    .withDescription(item.getDescription())
//                    .withCreatedAt(item.getCreatedAt())
//                    .withEditedAt(item.getEditedAt())
//                    .withSupplier(supplierVO)
//                    .withUnit(unitVO)
                    .withPromotions(promotionItemVOS)
//                    .withImageItems(item.getImageItems())
//                    .withOrderItems(item.getOrderItems())
//                    .withCategory(item.getCategories())
                    .build());
        });
        return itemVOS;

    }

    public ItemVO convertVO(Item item) {
        List<PromotionItemVO> promotionItemVOS = new ArrayList<>();
        for (PromotionItem promotionItem: item.getPromotionItems()
                ) {
            PromotionItemVO promotionItemVO = PromotionItemVOBuilder.aPromotionItemVO()
                    .withId(promotionItem.getId())
                    .withPercent(promotionItem.getPercent()).build();
            promotionItemVOS.add(promotionItemVO);
        }
        ItemVO itemVO = ItemVOBuilder.anItemVO().withId(item.getId())
                .withName(item.getName())
                .withPrice(item.getPrice())
                .withAvatar(item.getAvatar())
                .withDescription(item.getDescription())
                .withQuantity(item.getQuantity())
                .withPromotions(promotionItemVOS)
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
//        List<Item> items = itemRepository.findAllByCategories(cate);
//        return this.defaultJson(items);
        return null;
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
    public List<ItemVO> getItemTool(int quantity) {
        Category category = new Category();
        category = categoryRepository.findOne(44);
        return this.defaultJson(itemRepository.findAllByCategories(category)).subList(0,quantity);
    }

    @Override
    public Page<ItemVO> search(String key,int page, int size) {
        Page<Item> items = itemRepository.search(key, new PageRequest(page, size));
        ItemServiceImpl itemService = new ItemServiceImpl();
        Page<ItemVO> itemsVOs = items.map(new Converter<Item, ItemVO>() {
            @Override
            public ItemVO convert(Item entity) {
                return itemService.convertVO(entity);
            }
        });
        return itemsVOs;
    }

    @Override
    public List<ItemVO> searchWithCategory(int cate_id, String key) {
        return this.defaultJson(itemRepository.searchWithCategory(cate_id, key));
    }

//    @Override
//    public ItemVO create(ItemVO itemVO) {
//        Item item = new Item();
//        item.setName(itemVO.getName());
//        item.setAvatar(itemVO.getAvatar());
//        item.setDescription(itemVO.getDescription());
//        item.setPrice(itemVO.getPrice());
//        item.setQuantity(itemVO.getQuantity());
//        item.setStatus(true);
//        item.setUnit(unitRepository.findOne(itemVO.getUnit().getId()));
//        item.setActive(true);
//        List<Category> list =itemVO.getCategory();
//        item.setCategories(list);
//        item.setSupplier(supplierRepository.findOne(itemVO.getId()));
//        item = itemRepository.save(item);
//        for (ImageItem imageItem:itemVO.getImageItems()) {
//            ImageItem imageItem1 = new ImageItem();
//            imageItem1.setItem(item);
//            imageItem1.setImage(imageItem.getImage());
//            imageItem1.setActive(true);
//            imageRepository.save(imageItem1);
//        }
//        return this.convertVO(item);
//    }

    @Override
    public List<ItemVO> getItemNew(int quantity) {
        return this.defaultJson(itemRepository.findAllByOrderByIdDesc()).subList(0,quantity);
    }


    @Override
    public ItemVO getItemById( int id) {
        Item item = itemRepository.findOne(id);
        return convertVO(item);
    }

    @Override
    public Page<ItemVO> findPaginated(int page, int size, String sort) {
        String direction = sort.substring(0,1);
        String keySort = sort.substring(1,sort.length());
        Page<Item> items = itemRepository.findAll(new PageRequest(page, size,
                direction.equals("-") ? Sort.Direction.DESC : Sort.Direction.ASC,
                keySort));
        Page<ItemVO> itemsVOs = items.map(new Converter<Item, ItemVO>() {
            @Override
            public ItemVO convert(Item entity) {
                return convertVO(entity);
            }
        });
        return itemsVOs;
    }

    @Override
    public List<ItemVO> getCart(List<Integer> itemIds) {
        List<ItemVO> itemVOS = this.defaultJson(itemRepository.findByIdIn(itemIds));
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
    private ImageRepository imageRepository;


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
    @Override
    public List<CategoryVO> getCategory(Integer id){
        Item item = itemRepository.findOne(id);
        CategoryServiceImpl categoryService = new CategoryServiceImpl();
        return categoryService.defaultJson(item.getCategories());
    }

    @Override
    public Item createItem(Item item) {
        item.setActive(true);
        item = itemRepository.save(item);
        for (ImageItem imageItem: item.getImageItems()
             ) {
            ImageItem newImageItem = new ImageItem();
            newImageItem.setItem(item);
            newImageItem.setImage("/" + imageItem.getImage());
            newImageItem.setActive(true);
            imageRepository.save(newImageItem);
        }
        item = itemRepository.findOne(item.getId());
        return item;
    }
}

