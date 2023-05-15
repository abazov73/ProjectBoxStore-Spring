package com.example.ipLab.StoreDataBase.MVC;

import com.example.ipLab.StoreDataBase.DTO.ProductDTO;
import com.example.ipLab.StoreDataBase.DTO.UserDTO;
import com.example.ipLab.StoreDataBase.Model.UserRole;
import com.example.ipLab.StoreDataBase.Service.UserService;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/user")
public class UserMVCController {
    private final UserService userService;

    public UserMVCController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    @Secured({UserRole.AsString.ADMIN})
    public String getUsers(Model model){
        model.addAttribute("users",
                userService.getAllUsers().stream()
                        .map(UserDTO::new)
                        .toList());
        return "users";
    }


}
