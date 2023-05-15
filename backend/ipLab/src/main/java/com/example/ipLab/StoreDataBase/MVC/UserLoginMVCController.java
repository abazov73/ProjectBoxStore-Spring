package com.example.ipLab.StoreDataBase.MVC;

import com.example.ipLab.StoreDataBase.DTO.LoginDTO;
import com.example.ipLab.StoreDataBase.Model.User;
import com.example.ipLab.StoreDataBase.Model.UserRole;
import com.example.ipLab.StoreDataBase.Service.UserService;
import com.example.ipLab.StoreDataBase.util.validation.ValidationException;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(UserLoginMVCController.SIGNUP_URL)
public class UserLoginMVCController {
    public static final String SIGNUP_URL = "/signup";

    private final UserService userService;

    public UserLoginMVCController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public String showSignupForm(Model model) {
        model.addAttribute("userDto", new LoginDTO());
        return "signup";
    }

    @PostMapping
    public String signup(@ModelAttribute("userDto") @Valid LoginDTO userSignupDto,
                         BindingResult bindingResult,
                         Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("errors", bindingResult.getAllErrors());
            return "signup";
        }
        try {
            final User user = userService.addUser(
                    userSignupDto.getLogin(), userSignupDto.getPassword(), userSignupDto.getPasswordConfirm(), UserRole.USER, Long.parseLong("1"));
            return "redirect:/login?created=" + user.getLogin();
        } catch (ValidationException e) {
            model.addAttribute("errors", e.getMessage());
            return "signup";
        }
    }
}
