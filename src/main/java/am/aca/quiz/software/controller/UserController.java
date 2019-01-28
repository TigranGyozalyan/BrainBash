package am.aca.quiz.software.controller;


import am.aca.quiz.software.service.implementations.UserServiceImp;
import am.aca.quiz.software.service.mapper.UserMapper;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.sql.SQLException;
import java.util.Map;


@RestController
@RequestMapping("/user")
public class UserController {
    private final UserServiceImp userServiceImp;
    private final UserMapper userMapper;

    public UserController(UserServiceImp userServiceImp, UserMapper userMapper) {
        this.userServiceImp = userServiceImp;
        this.userMapper = userMapper;
    }

    @GetMapping(value = "/register")
    public ModelAndView registrationPage() {
        ModelAndView modelAndView = new ModelAndView("userRegistration");
        return modelAndView;
    }

    @PostMapping(value = "/register", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public void registerUser(@RequestParam Map<String, String> formData) {
        String name = formData.get("name");
        String lastName = formData.get("name2");
        String nickname = formData.get("nickname");
        String email = formData.get("email");
        String password = formData.get("password");
        String password2 = formData.get("password2");
        try {

            userServiceImp.addUser(name, lastName, nickname, email, password, password2);
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

}