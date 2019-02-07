package am.aca.quiz.software.controller;


import am.aca.quiz.software.entity.UserEntity;
import am.aca.quiz.software.entity.enums.Role;
import am.aca.quiz.software.service.dto.UserDto;
import am.aca.quiz.software.service.implementations.UserServiceImp;
import am.aca.quiz.software.service.mapper.UserMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

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
    private String email;
    private boolean message;


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
            if (message) {
                modelAndView.addObject("message", "Email Invalid");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return modelAndView;
    }

    @PostMapping(value = "/profile")
    public ModelAndView redirectToProfile() {
        return new ModelAndView("redirect:/user/profile");
    }

    @PostMapping(value = "/question")
    public ModelAndView modelAndView(Principal principal, @RequestParam Map<String, String> formData) {
        ModelAndView modelAndView = null;
        email = formData.get("delete");
        String checker = principal.getName();
        if (email.equals(checker)) {
            message = false;
            modelAndView = new ModelAndView("userDelete");

        } else {
            message = true;
            return profilePage(principal);

        }


        return modelAndView;
    }


    @PostMapping("/delete")
    public ModelAndView deleteAccount(@RequestParam Map<String, String> formData) {
        ModelAndView modelAndView = new ModelAndView("redirect:/logout");
        try {
            UserEntity userEntity = userServiceImp.findByEmail(email);
            userServiceImp.removeByUserEntity(userEntity);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return modelAndView;
    }

    @PreAuthorize("hasAuthority('ADMIN')")
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

    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping(value = "/{user}")
    public ModelAndView updateUserRole(@PathVariable Long user) {

        ModelAndView modelAndView = new ModelAndView("userEdit");
        UserDto userDto = null;
        try {
            userDto = userMapper.mapEntityToDto(userServiceImp.getById(user));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        modelAndView.addObject("user", userDto);
        modelAndView.addObject("roles", Role.values());


        return modelAndView;
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @PostMapping
    public ModelAndView postUserRole(@RequestParam("userId") String userId,
                                     @RequestParam Map<String, String> formData) {

        ModelAndView modelAndView = new ModelAndView("redirect:/user/userList");

        try {
            UserEntity userEntity = userServiceImp.getById(Long.parseLong(userId));
            /*
            Map Role Values to String set for checking checkbox's values
             */

            Set<String> roles = Arrays
                    .stream(Role.values())
                    .map(Role::name)
                    .collect(Collectors.toSet());


            userEntity.getRoles().clear();

            /*
            Iterating throw Map Keys  and checking out if selected Role is Present in keySet().
             */
            for (String key : formData.keySet()) {
                if (roles.contains(key)) {
                    userEntity.getRoles().add(Role.valueOf(key));
                }
            }
            userServiceImp.updateUser(userEntity);

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return modelAndView;

    }

    @PostMapping("/username")
    public ModelAndView updateUsername(Principal principal, @RequestParam Map<String, String> formDate) {
        ModelAndView modelAndView = new ModelAndView("redirect:/user/profile");

        String username = formDate.get("name");

        try {
            UserEntity userEntity = userServiceImp.findByEmail(principal.getName());

            userEntity.setName(username);

            userServiceImp.updateUser(userEntity);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return modelAndView;
    }

    @PostMapping("/surname")
    public ModelAndView updateSurname(Principal principal, @RequestParam Map<String, String> formDate) {
        ModelAndView modelAndView = new ModelAndView("redirect:/user/profile");

        String surname = formDate.get("surname");

        try {
            UserEntity userEntity = userServiceImp.findByEmail(principal.getName());

            userEntity.setSurname(surname);

            userServiceImp.updateUser(userEntity);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return modelAndView;
    }

    @PostMapping("/nickname")
    public ModelAndView updateNickname(Principal principal, @RequestParam Map<String, String> formDate) {
        ModelAndView modelAndView = new ModelAndView("redirect:/user/profile");

        String nickname = formDate.get("nickname");

        try {
            UserEntity userEntity = userServiceImp.findByEmail(principal.getName());

            userEntity.setNickname(nickname);

            userServiceImp.updateUser(userEntity);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return modelAndView;
    }

    @PostMapping("/password")
    public ModelAndView updatePassword(Principal principal, @RequestParam Map<String, String> formDate) {
        ModelAndView modelAndView = new ModelAndView("redirect:/user/profile");

        String password = formDate.get("password");

        try {
            UserEntity userEntity = userServiceImp.findByEmail(principal.getName());

            userEntity.setPassword(password);

            userServiceImp.updateUserPassword(userEntity);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return modelAndView;
    }

    @PostMapping("/email")
    public ModelAndView updateEmail(Principal principal, @RequestParam Map<String, String> formDate) {
        ModelAndView modelAndView = new ModelAndView("redirect:/logout");

        String email = formDate.get("email");

        try {
            UserEntity userEntity = userServiceImp.findByEmail(principal.getName());

            userEntity.setEmail(email);

            userServiceImp.updateUser(userEntity);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return modelAndView;
    }

    @GetMapping("/activate/{code}")
    public ModelAndView activate(@PathVariable String code) {
        ModelAndView modelAndView = new ModelAndView("redirect:/login");
        boolean isActivated = userServiceImp.activateUser(code);

        if (!isActivated) {
            return new ModelAndView("redirect:/");
        }

        return modelAndView;
    }

    @PostMapping("/role")
    public ResponseEntity<UserDto> getUserRole(Principal principal) {
        try {
            if (userServiceImp.findByEmail(principal.getName()) == null) {
                return ResponseEntity.noContent().build();
            }
            return ResponseEntity.ok(userMapper.mapEntityToDto(userServiceImp.findByEmail(principal.getName())));
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    @GetMapping("/admin")
    public ModelAndView adminPage() {
        return new ModelAndView("adminProfile");
    }

}



