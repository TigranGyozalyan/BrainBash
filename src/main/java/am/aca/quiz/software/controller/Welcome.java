package am.aca.quiz.software.controller;


import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
@RequestMapping("/")
public class Welcome {

    @GetMapping
    public ModelAndView welcomePage() {
        ModelAndView modelAndView = new ModelAndView("welcome");
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication instanceof AnonymousAuthenticationToken) {
            return modelAndView;
        }
        return new ModelAndView("redirect:/user/profile");

    }

    @GetMapping("registration")
    public ModelAndView registration() {
        ModelAndView modelAndView = new ModelAndView("userRegistration");
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication instanceof AnonymousAuthenticationToken) {
            return modelAndView;
        }
        return new ModelAndView("redirect:/user/profile");
    }

    @GetMapping("login")
    public ModelAndView login() {
        ModelAndView modelAndView = new ModelAndView("login");
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication instanceof AnonymousAuthenticationToken) {
            return modelAndView;
        }
        return new ModelAndView("redirect:/user/profile" +
                "");
    }

    @GetMapping("animation")
    public ModelAndView animation(){
        return new ModelAndView("animation");
    }

}
