package am.aca.quiz.software.controller;


import com.sun.org.apache.xpath.internal.operations.Mod;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/")
public class Welcome {

    @GetMapping
    public ModelAndView welcomePage(){
        ModelAndView modelAndView=new ModelAndView("welcome");
        return modelAndView;
    }


    @GetMapping(value = "/logout")
    public ModelAndView logout(){
        return new ModelAndView("logout");
    }

}
