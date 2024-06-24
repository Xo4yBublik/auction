package org.example.auctionprofile.controllers;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import org.example.auctionprofile.dtos.LotRegisterDto;
import org.example.auctionprofile.dtos.UserRegisterDto;
import org.example.auctionprofile.entities.LotEntity;
import org.example.auctionprofile.entities.UserEntity;
import org.example.auctionprofile.services.LotService;
import org.example.auctionprofile.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@RestController
@AllArgsConstructor
public class RegistrationController {
    @Autowired
    private UserService userService;
    @Autowired
    private LotService lotService;

    @GetMapping("/")
    public ModelAndView redirectUrl() {
        return new ModelAndView("redirect:/auc/welcome");
    }

    @GetMapping("/register")
    public ModelAndView getRegister() {
        return new ModelAndView("register");
    }

    @PostMapping("/register")
    public ModelAndView registerUser(@RequestBody UserRegisterDto registerDto) {
        userService.save(registerDto);
        return new ModelAndView("registrationDone");
    }

    @GetMapping("/login")
    public ModelAndView loginUser() {
        return new ModelAndView("login");
    }

    @GetMapping("/logout")
    public ModelAndView logout(HttpServletRequest request, HttpServletResponse response) {
        SecurityContextLogoutHandler logoutHandler = new SecurityContextLogoutHandler();
        logoutHandler.logout(request, response, SecurityContextHolder.getContext().getAuthentication());
        return new ModelAndView("redirect:/login");
    }

    @GetMapping("/auc/welcome")
    public ModelAndView welcome(Model model) {
        List<LotEntity> lots = lotService.getAllLots();
        model.addAttribute("lots", lots);
        return new ModelAndView("welcome");
    }

    @GetMapping("/auc/users")
    public ModelAndView pageForUsers(Model model, HttpServletRequest request) {
        CsrfToken csrfToken = (CsrfToken) request.getAttribute(CsrfToken.class.getName());
        model.addAttribute("_csrf", csrfToken);
        List<UserEntity> users = userService.getAllUsers();
        model.addAttribute("users", users);
        return new ModelAndView("users");
    }

    @GetMapping("/auc/admins")
    public ModelAndView pageForAdmins() {
        return new ModelAndView("admins");
    }

    @GetMapping("/auc/register-lot")
    public ModelAndView getRegisterLot() {
        return new ModelAndView("lotAdd");
    }

    @PostMapping("/auc/register-lot")
    public ModelAndView registerLot(@RequestBody LotRegisterDto lotRegisterDto) {
        lotService.save(lotRegisterDto);
        return new ModelAndView("redirect:/auc/welcome");
    }
}
