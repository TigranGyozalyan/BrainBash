package am.aca.quiz.software.controller;


import am.aca.quiz.software.entity.UserEntity;
import am.aca.quiz.software.entity.enums.Role;
import am.aca.quiz.software.service.dto.UserDto;
import am.aca.quiz.software.service.implementations.UserServiceImp;
import am.aca.quiz.software.service.mapper.UserMapper;
import com.sun.org.apache.xpath.internal.operations.Mod;
import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.jws.WebParam;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.security.Principal;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;


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
            UserEntity dbUser = userServiceImp.findByEmail(email);
            if (dbUser == null) {
                try {

                    userServiceImp.addUser(name, lastName, nickname, email, password, password2);
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                modelAndView.addObject("message2", "Check Your Email");
            } else {
                modelAndView.addObject("message", "User exists");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return modelAndView;
    }

    @GetMapping(value = "/profile")
    public ModelAndView profilePage(Principal principal) {
        ModelAndView modelAndView = new ModelAndView("userProfile");
        String email = principal.getName();
        try {
            UserEntity user = userServiceImp.findByEmail(email);
            UserDto userDto = userMapper.mapEntityToDto(user);
            modelAndView.addObject("user", userDto);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return modelAndView;
    }

    @PostMapping("/delete")
    public ModelAndView deleteAccount(@RequestParam Map<String, String> formData ) {
        ModelAndView modelAndView = new ModelAndView("redirect:/");
        String email = formData.get("delete");
        try {
            UserEntity userEntity = userServiceImp.findByEmail(email);
            userServiceImp.removeByUserEntity(userEntity);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return modelAndView;
    }

    @GetMapping("/userList")
    public ModelAndView getAllUsers() {
        ModelAndView modelAndView = new ModelAndView("userList");
        try {
            List<UserDto> userDtos = userMapper.mapEntitiesToDto(userServiceImp.getAll());


            modelAndView.addObject("userList", userDtos);


        } catch (SQLException e) {
            e.printStackTrace();
        }

        return modelAndView;
    }
    @GetMapping(value = "/{user}")
    public ModelAndView updateUserRole(@PathVariable Long user){

        ModelAndView modelAndView=new ModelAndView("userEdit");
        UserDto userDto= null;
        try {
            userDto = userMapper.mapEntityToDto(userServiceImp.getById(user));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        modelAndView.addObject("user",userDto);
        modelAndView.addObject("roles", Role.values());


        return modelAndView;
    }

    @PostMapping
    public ModelAndView postUserRole(@RequestParam("userId") String userId,
                                     @RequestParam Map<String, String> formData ){

        ModelAndView modelAndView=new ModelAndView("redirect:/user/userList");

        try {
            UserEntity userEntity=userServiceImp.getById(Long.parseLong(userId));
            /*
            Map Role Values to String set for checking checkbox's values
             */

            Set<String> roles= Arrays
                    .stream(Role.values())
                    .map(Role::name)
                    .collect(Collectors.toSet());


            userEntity.getRoles().clear();

            /*
            Iterating throw Map Keys  and checking out if selected Role is Present in keySet().
             */
            for(String key :formData.keySet()){
                if(roles.contains(key)){
                    userEntity.getRoles().add(Role.valueOf(key));
                }
            }
            userServiceImp.updateUser(userEntity);

        } catch (SQLException e) {
            e.printStackTrace();
        }

       return modelAndView;

    }



}