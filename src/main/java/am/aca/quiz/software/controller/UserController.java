package am.aca.quiz.software.controller;


import am.aca.quiz.software.entity.UserEntity;
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



    @PostMapping(value = "/register")
    public ModelAndView registerUser(@RequestParam Map<String, String> formData) {
        ModelAndView modelAndView = new ModelAndView("userRegistration");



        String name = formData.get("name");
        String lastName = formData.get("name2");
        String nickname = formData.get("nickname");
        String email = formData.get("email");
        String password = formData.get("password");
        String password2 = formData.get("password2");


        try {
            UserEntity dbUser=userServiceImp.findByEmail(email);
            if(dbUser==null){
                try {
                    userServiceImp.addUser(name, lastName, nickname, email, password, password2);
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            else{
                modelAndView.addObject("message","User exists");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return modelAndView;
    }
    @GetMapping(value = "/profile")
    public ModelAndView profilePage(){
        return new ModelAndView("userProfile");
    }

    @PostMapping(value = "")
    public ModelAndView deleteUser(@RequestParam Map<String, String> formDate) {
        ModelAndView modelAndView = new ModelAndView("");
        String email = formDate.get("email");

        try {
            userServiceImp.removeByUserEntity(userServiceImp.findByEmail(email));
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return modelAndView;
    }

}