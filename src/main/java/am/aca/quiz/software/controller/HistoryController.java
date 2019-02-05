package am.aca.quiz.software.controller;

import am.aca.quiz.software.entity.UserEntity;
import am.aca.quiz.software.entity.enums.Status;
import am.aca.quiz.software.service.implementations.HistoryServiceImp;
import am.aca.quiz.software.service.implementations.UserServiceImp;
import am.aca.quiz.software.service.mapper.HistoryMapper;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.security.Principal;
import java.sql.SQLException;

@RestController
@RequestMapping("/history")
public class HistoryController {
    private final HistoryServiceImp historyServiceImp;
    private final HistoryMapper historyMapper;
    private final UserServiceImp userServiceImp;

    public HistoryController(HistoryServiceImp historyServiceImp, HistoryMapper historyMapper, UserServiceImp userServiceImp) {
        this.historyServiceImp = historyServiceImp;
        this.historyMapper = historyMapper;
        this.userServiceImp = userServiceImp;
    }

    @GetMapping("/pastList")
    public ModelAndView pastHistoryList(Principal principal) {
        ModelAndView modelAndView = new ModelAndView("pastHistory");

        try {
            Long userId = userServiceImp.findByEmail(principal.getName()).getId();

            modelAndView.addObject("historyList", historyMapper.mapEntitiesToDto(historyServiceImp.findAllByStatus(userId, Status.COMPLETED)));

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return modelAndView;
    }
}
