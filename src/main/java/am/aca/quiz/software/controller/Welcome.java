package am.aca.quiz.software.controller;


import org.springframework.web.bind.annotation.GetMapping;
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
    @GetMapping("login")
    public ModelAndView loginPage(){
        ModelAndView modelAndView=new ModelAndView("login");
        return modelAndView;
    }
}
