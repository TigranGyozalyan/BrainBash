package am.aca.quiz.software.controller;


import com.sun.org.apache.xpath.internal.operations.Mod;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
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
//
//    @PostMapping
//    public ModelAndView redirectWelcomePage(){
//        return welcomePage();
//    }

    @GetMapping(value = "redirect")
    public ModelAndView transferPage(){
        return new ModelAndView("transferPage");
    }

    @GetMapping("logout")
    public ModelAndView logout(){
        return new ModelAndView("logout");
    }

}
