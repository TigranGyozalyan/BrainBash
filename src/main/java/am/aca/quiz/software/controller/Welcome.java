package am.aca.quiz.software.controller;


import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.jws.WebParam;

@RestController
@RequestMapping("/")
public class Welcome {

    @GetMapping
    public ModelAndView welcomePage(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        ModelAndView modelAndView=new ModelAndView("welcome");
        if(authentication instanceof AnonymousAuthenticationToken){
            return modelAndView;
        }
        return new ModelAndView("redirect:/notfound");

    }

    @GetMapping(value = "notfound")
    public ModelAndView notFound(){
        return new ModelAndView("notFound");
    }

}
