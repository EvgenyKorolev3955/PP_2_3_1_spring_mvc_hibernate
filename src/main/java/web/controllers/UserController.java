package web.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import web.models.User;
import web.service.UserService;


@Controller
@RequestMapping("/users")
public class UserController {

    private UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping()
    public String getAll(ModelMap model) {

        model.addAttribute("users", userService.getAll());
        return "usersPages/index";
    }

    @GetMapping(value = "/new")
    public String addUser(@ModelAttribute("user") User user, Model model) {
        model.addAttribute("msg", "Создать нового пользователя");
        return "usersPages/new";
    }

    @PostMapping()
    public String saveUser(@ModelAttribute("user") User user) {

        userService.saveUser(user);
        return "redirect: /users";
    }

    @GetMapping("/edit")
    public String edit(@RequestParam(value = "id") long id, Model model) {
        model.addAttribute("user", userService.getUserById(id));
        model.addAttribute("msg", "Изменить существующего пользователя");
        return "usersPages/new";
    }

    @PostMapping("/delete")
    public String delete(@RequestParam(value = "id") long id) {

        userService.deleteUser(id);
        return "redirect: /users";
    }


}
