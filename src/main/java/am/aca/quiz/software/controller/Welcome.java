package am.aca.quiz.software.controller;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
@RequestMapping("/")
public class Welcome {

    @GetMapping
    public ModelAndView welcomePage(){
        ModelAndView modelAndView=new ModelAndView("welcome");
        return modelAndView;
    }
    @GetMapping(value = "login")
    public ModelAndView loginPage(){
        ModelAndView modelAndView=new ModelAndView("login");
        return modelAndView;
    }
    @GetMapping(value = "redirect")
    public ModelAndView transferPage(){
        return new ModelAndView("transferPage");
    }
    @GetMapping(value = "/user/profile")
    public ModelAndView profilePage(){
        return new ModelAndView("userProfile");
    }
}
