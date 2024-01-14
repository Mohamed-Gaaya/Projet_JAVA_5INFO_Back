package org.example.controller;

import jakarta.annotation.security.PermitAll;
import org.example.model.User;
import org.example.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
@Controller
@PermitAll

public class UserAdminController {

        @Autowired
        private UserService service;

        @GetMapping("/usersa")
        public String showUserList(Model model) {
            List<User> listUsers = service.getUsers();
            model.addAttribute("listUsers", listUsers);

            return "users";
        }

        @GetMapping("/usersa/new")
        public String showNewForm(Model model) {
            model.addAttribute("user", new User());
            model.addAttribute("pageTitle", "Add New User");
            return "user_form";
        }

        @PostMapping("/usersa/save")
        public String saveUser(User user, RedirectAttributes ra) {
            service.registerUser(user);
            ra.addFlashAttribute("message", "The user has been  saved successfully.");
            return "redirect:/usersa";
        }

        @GetMapping("/usersa/edit/{id}")
        public String showEditForm(@PathVariable("id") Long id, Model model, RedirectAttributes ra) {
            try {
                User user = service.get(id);
                model.addAttribute("user", user);
                model.addAttribute("pageTitle", "Edit User (ID: " + id + ")");

                return "user_form";
            } catch (UsernameNotFoundException e) {
                ra.addFlashAttribute("message", e.getMessage());
                return "redirect:/usersa";
            }
        }

        @GetMapping("/usersa/delete/{id}")
        public String deleteUser(@PathVariable("id") Long id, RedirectAttributes ra) {
            try {
                service.deleteUserid(id);
                ra.addFlashAttribute("message", "The user ID " + id + " has been deleted.");
            } catch (UsernameNotFoundException e) {
                ra.addFlashAttribute("message", e.getMessage());
            }
            return "redirect:/usersa";
        }

}
