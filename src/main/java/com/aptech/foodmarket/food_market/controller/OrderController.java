package com.aptech.foodmarket.food_market.controller;

import com.aptech.foodmarket.food_market.BaoKimPayment;
import com.aptech.foodmarket.food_market.Payment;
import com.aptech.foodmarket.food_market.model.Order;
import com.aptech.foodmarket.food_market.model.Promotion;
import com.aptech.foodmarket.food_market.model.Ship;
import com.aptech.foodmarket.food_market.model.User;
import com.aptech.foodmarket.food_market.repository.UserRepository;
import com.aptech.foodmarket.food_market.service.CategoryService;
import com.aptech.foodmarket.food_market.service.OrderService;
import com.aptech.foodmarket.food_market.vo.ItemVO;
import com.aptech.foodmarket.food_market.vo.OrderVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/order")
public class OrderController {
    @Autowired
    OrderService orderService;
    @Autowired
    UserRepository userRepository;
    @RequestMapping("/")
    @ResponseBody
    public String getAll() {
        return "hello";
    }
    @RequestMapping(method = RequestMethod.POST, value = "/create")
    public ResponseEntity<OrderVO> createOrder(@RequestBody OrderVO orderVO) throws Exception {
        return new ResponseEntity<OrderVO>(orderService.createOrder(orderVO), HttpStatus.OK);
    }
    @RequestMapping(method = RequestMethod.POST, value = "/getUrl")
    public ResponseEntity<String> getCart(@RequestBody Payment payment) {
        BaoKimPayment baoKimPayment = new BaoKimPayment();
        String url = baoKimPayment.createRequestUrl(payment.order_id,
                payment.business,
                payment.total_amount,
                payment.shipping_fee,
                payment.tax_fee,
                payment.order_description,
                payment.url_success,
                payment.url_cancel,
                payment.url_detail);
        System.out.println(url);
        return new ResponseEntity<String>(url, HttpStatus.OK);
    }
    @RequestMapping(value = "/getOrderByUser/{id}", params = {"page", "size" })
    @ResponseBody
    public Page<OrderVO> getOrderByUser(@PathVariable Integer id,
                               @RequestParam int page,
                               @RequestParam int size,
                                @RequestParam(value = "sort", required=false) String sort) {
        User user = userRepository.findOne(id);
        return orderService.getOrderByUser(user, page, size, sort);
    }
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    @ResponseBody
    public OrderVO getOrderById(@PathVariable Integer id) {
        return orderService.getByID(id);
    }
    @RequestMapping(value = "/getOrderByStatus", params = {"status", "page", "size" })
    @ResponseBody
    public Page<OrderVO> getOrderByStatus(@RequestParam byte status,
                                        @RequestParam int page,
                                        @RequestParam int size,
                                          @RequestParam(value = "sort", required=false) String sort) {
        return orderService.getOrderByStatus(status, page, size, sort);
    }
    @RequestMapping(method = RequestMethod.DELETE, value = "/delete/{id}")
    @ResponseBody
    public OrderVO deleteItem(@PathVariable Integer id) {
        return orderService.deleteItem(id);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @RequestMapping(method = RequestMethod.PUT, value = "/{id}/status/{status}")
    @ResponseBody
    public OrderVO updateStatus(@PathVariable Integer id,@PathVariable Byte status) {
       return orderService.updateStatus(id,status);
    }
}
