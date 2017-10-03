package com.aptech.foodmarket.food_market.controller;

import com.aptech.foodmarket.food_market.model.User;
import com.aptech.foodmarket.food_market.service.ImplService.UserServiceImpl;
import com.aptech.foodmarket.food_market.service.UserService;
import com.aptech.foodmarket.food_market.vo.ItemVO;
import com.aptech.foodmarket.food_market.vo.UserVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;
    /**
     * GET /create  --> Create a new user and save it in the database.
     */
//    @RequestMapping("/create")
//    @ResponseBody
//    public String create(String username, String password) {
//        String userId = "";
//        try {
//            User user = new User();
//            user.setUsername(username);
//            user.setPassword(password);
//            user.setActive(true);
//            userService.save(user);
//            userId = String.valueOf(user.getId());
//        }
//        catch (Exception ex) {
//            return "Error creating the user: " + ex.toString();
//        }
//        return "User succesfully created with id = " + userId;
//    }
//
//    /**
//     * GET /delete  --> Delete the user having the passed id.
//     */
//    @RequestMapping("/delete")
//    @ResponseBody
//    public String delete(int id) {
//        try {
//            User user = new User();
//            user.setId(id);
//            userRepository.delete(user);
//        }
//        catch (Exception ex) {
//            return "Error deleting the user:" + ex.toString();
//        }
//        return "User succesfully deleted!";
//    }

    /**
     * GET /get-by-email  --> Return the id for the user having the passed
     * email.
     */
//    @PreAuthorize("#oauth2.hasScope('foo')")
//    @RequestMapping("/get-by-user")
//    @ResponseBody
//    public String getByUser(String username) {
//        String userId = "";
//        try {
//            User user = userRepository.findByUsername(username);
//            userId = String.valueOf(user.getId());
//        }
//        catch (Exception ex) {
//            return "User not found";
//        }
//        return "The user id is: " + userId;
//    }

    /**
     * GET /update  --> Update the email and the name for the user in the
     * database having the passed id.
     */
//    @RequestMapping("/update")
//    @ResponseBody
//    public String updateUser(long id, String email, String name) {
//        try {
//            User user = userRepository.findOne(id);
//            user.setEmail(email);
//            user.setName(name);
//            userRepository.save(user);
//        }
//        catch (Exception ex) {
//            return "Error updating the user: " + ex.toString();
//        }
//        return "User succesfully updated!";
//    }

    // Private fields
    @PreAuthorize("hasRole('ADMIN')")
    @RequestMapping(method = RequestMethod.POST, value = "/createUser")
    public ResponseEntity<UserVO> createUser(@RequestBody UserVO userVO) {
        return new ResponseEntity<UserVO>(userService.createUser(userVO), HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.PUT, value = "/update")
    public ResponseEntity<UserVO> updateUser(@RequestHeader(value="Access-token") String user_token, @RequestBody UserVO userVO) {
        return new ResponseEntity<UserVO>(userService.update(user_token, userVO), HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.PUT, value = "/updateByAdmin")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<UserVO> updateUserByAdmin(@RequestBody UserVO userVO) {
        return new ResponseEntity<UserVO>(userService.updateUserByAdmin(userVO), HttpStatus.OK);
    }

    @RequestMapping(value = "/getUserDetail")
    @ResponseBody
    public UserVO getDetailUser(@RequestHeader(value="Authorization") String user_token){
        return userService.getDetailUser(user_token);
    }

    @RequestMapping(value = "/getUserById/{id}")
    @ResponseBody
    public UserVO getUserById(@PathVariable Integer id){
        return userService.getUserById(id);
    }

    @RequestMapping(value = "/getUserByToken/{token}")
    @ResponseBody
    public UserVO getUserById(@PathVariable String user_token){
        return userService.getDetailUser(user_token);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    @ResponseBody
    public UserVO deleteUser(@PathVariable Integer id){
        return userService.delete(id);
    }

    @RequestMapping(value = "/getUsersByAuthority/{id}", params = {"page", "size","sort" })
    @ResponseBody
    public Page<UserVO> search(@PathVariable Integer id,
                               @RequestParam int page,
                               @RequestParam int size,
                               @RequestParam String sort) {
        return userService.getUserByAuthority(id, page, size, sort);
    }

    @RequestMapping(method = RequestMethod.PUT, value = "/changePassword/{id}", params = {"newPassword"})
    public ResponseEntity<UserVO> updateUser( @PathVariable Integer id, @RequestParam String newPassword) {
        return new ResponseEntity<UserVO>(userService.updatePassword(id, newPassword), HttpStatus.OK);
    }
}
