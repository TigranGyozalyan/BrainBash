package am.aca.quiz.software.controller;


import am.aca.quiz.software.service.implementations.UserServiceImp;
import am.aca.quiz.software.service.mapper.UserMapper;
import org.hibernate.query.criteria.internal.predicate.MemberOfPredicate;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.sql.SQLException;


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
    public ModelAndView registrationPage(){
        ModelAndView modelAndView=new ModelAndView("userRegistration");
        return modelAndView;
    }
    @PostMapping(value = "/register")
    public ModelAndView registerUser(@RequestParam("name") String name,
                                     @RequestParam("name2") String lastName,
                                     @RequestParam("email") String email,
                                     @RequestParam("password") String password,
                                     @RequestParam("password2")String password2,
                                     @RequestParam("nickname") String nickname) {

        ModelAndView modelAndView=new ModelAndView("userRegistration");
        try {

            userServiceImp.addUser(name,lastName,nickname,email,password,password2);
        } catch (SQLException e) {
            e.printStackTrace();
        }


        return modelAndView;
    }

}
