package com.aptech.foodmarket.food_market.controller;

import com.aptech.foodmarket.food_market.EntityNotFoundException;
import com.aptech.foodmarket.food_market.model.Item;
import com.aptech.foodmarket.food_market.model.Order;
import com.aptech.foodmarket.food_market.model.Supplier;
import com.aptech.foodmarket.food_market.repository.CategoryRepository;
import com.aptech.foodmarket.food_market.repository.ItemRepository;
import com.aptech.foodmarket.food_market.repository.OrderItemRepository;
import com.aptech.foodmarket.food_market.repository.SupplierRepository;
import com.aptech.foodmarket.food_market.service.CategoryService;
import com.aptech.foodmarket.food_market.service.ItemService;
import com.aptech.foodmarket.food_market.vo.CategoryVO;
import com.aptech.foodmarket.food_market.vo.ItemVO;
import com.aptech.foodmarket.food_market.vo.OrderVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.*;

import java.security.InvalidParameterException;
import java.util.List;
import java.util.Set;

@Controller
@RequestMapping("/item")
public class ItemController {
    @Autowired
    ItemRepository itemRepository;

    @Autowired
    OrderItemRepository orderItemRepository;

    @Autowired
    CategoryRepository categoryRepository;

    @Autowired
    ItemService itemService;
    @Autowired
    CategoryService categoryService;
    @Autowired
    SupplierRepository supplierRepository;

    @RequestMapping(value = "/all",
            method = RequestMethod.GET)
    @ResponseBody
    public Page<ItemVO> getAll(@RequestParam("page") int page,
                               @RequestParam("size") int size,
                               @RequestParam("sort") String sort,
                               @RequestParam(value = "category", required=false) String category
                               ) throws EntityNotFoundException {
        if (category == null) {
            Page<ItemVO> resultPage = itemService.findPaginated(page, size, sort);
            if (page > resultPage.getTotalPages()) {
                new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }
            return resultPage;
        } else {
            Integer categoryId = Integer.parseInt(category);
            Page<ItemVO> resultPage = categoryService.getItems(categoryId, page, size, sort);
            if (page > resultPage.getTotalPages()) {
                new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }
            return resultPage;
        }

    }
    @RequestMapping(method = RequestMethod.POST, value = "/getCart")
    @ResponseBody
    public ResponseEntity<?> getCart(@RequestBody List<Integer> itemIds) throws EntityNotFoundException {
        List<ItemVO> itemVOList = itemService.getCart(itemIds);
        return ResponseEntity.ok(itemVOList);
    }

    @RequestMapping("/getItemByName")
    @ResponseBody
    public List<ItemVO> getItemByName(String name) throws EntityNotFoundException {
        return itemService.getItemByName(name);
    }

    @RequestMapping("/getItemByCate")
    @ResponseBody
    public List<ItemVO> getItemByCate(int cate_id) throws EntityNotFoundException {
        return itemService.getItemByCategory(categoryRepository.findOne(cate_id));
    }

    @RequestMapping("/getItemById/{id}")
    @ResponseBody
    public ItemVO getItemByID(@PathVariable Integer id) throws EntityNotFoundException {
        return itemService.getItemById(id);
    }

    @RequestMapping("/getItemNew")
    @ResponseBody
    public List<ItemVO> getItemNew(int quantity) throws EntityNotFoundException {
        return itemService.getItemNew(quantity);
    }

    @RequestMapping("/getItemPromotion")
    @ResponseBody
    public List<ItemVO> getItemPromotion(int quantity) throws EntityNotFoundException {
        return itemService.getItemPromotion(quantity);
    }
    @RequestMapping("/getItemTrending")
    @ResponseBody
    public List<ItemVO> getItemTrending(int quantity) throws EntityNotFoundException {
        return itemService.getItemTrending(quantity);
    }

    @RequestMapping("/getItemTool")
    @ResponseBody
    public List<ItemVO> getItemTool(int quantity) throws EntityNotFoundException {
        return itemService.getItemTool(quantity);
    }

    @RequestMapping("/getItemBest")
    @ResponseBody
    public List<ItemVO> getItemBest() throws EntityNotFoundException {
        return itemService.getItemBestSeller(orderItemRepository.getIDBest());
    }

    @RequestMapping("/getCategory/{id}")
    @ResponseBody
    public Set<CategoryVO> getCategories(@PathVariable Integer id) throws EntityNotFoundException {
        return itemService.getCategory(id);
    }

    @RequestMapping(value = "/search", params = { "key", "page", "size" })
    @ResponseBody
    public Page<ItemVO> search(@RequestParam String key,
                               @RequestParam int page,
                               @RequestParam int size) throws EntityNotFoundException {
        return itemService.search(key, page, size);
    }

    @RequestMapping(value = "/search/supplier/{id}")
    @ResponseBody
    public List<ItemVO> searchItemBySupllierID(
            @PathVariable Integer id, @RequestParam String key) throws EntityNotFoundException {
        return itemService.searchWithSupplierId(id, key);
    }

    @RequestMapping("/searchWithCategory")
    @ResponseBody
    public List<ItemVO> searchWithCategory(@RequestParam int id, @RequestParam String key) throws EntityNotFoundException {
        return itemService.searchWithCategory(id,key);
    }

    @RequestMapping(method = RequestMethod.POST, value = "/create")
    public ResponseEntity<ItemVO> createItem(@RequestBody Item item) throws EntityNotFoundException {
        return new ResponseEntity<ItemVO>(itemService.createItem(item), HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.PUT, value = "/update")
    public ResponseEntity<ItemVO> updateItem(@RequestBody Item item) throws EntityNotFoundException {
        System.out.println(item.getCategories());
        return new ResponseEntity<ItemVO>(itemService.updateItem(item), HttpStatus.OK);
    }

    @RequestMapping(value = "/getItemBySupplier/{id}", params = {"page", "size"})
    @ResponseBody
    public Page<ItemVO> getgetItemBySupplier(@PathVariable Integer id,
                                             @RequestParam int page,
                                             @RequestParam int size) throws EntityNotFoundException {
        Supplier supplier = supplierRepository.findOne(id);
        if (supplier != null) {
            return itemService.getItemBySuplier(supplier, page, size);
        }
        return null;
    }
    @PreAuthorize("hasRole('ADMIN')")
    @RequestMapping(method = RequestMethod.DELETE, value = "/delete/{id}")
    public ItemVO deleteItem(@PathVariable Integer id) {
        return itemService.deleteItem(id);
    }

    @RequestMapping(value = "/getItemByStatus", params = {"status", "page", "size"})
    @ResponseBody
    public Page<ItemVO> getgetItemBySupplier(@RequestParam int status,
                                                @RequestParam int page,
                                             @RequestParam int size) throws EntityNotFoundException {
            return itemService.getItemByStatus(status, page, size);
    }
}
