package am.aca.quiz.software.controller;

import am.aca.quiz.software.entity.HistoryEntity;
import am.aca.quiz.software.entity.UserEntity;
import am.aca.quiz.software.entity.enums.Status;
import am.aca.quiz.software.service.dto.HistoryDto;
import am.aca.quiz.software.service.dto.TestDto;
import am.aca.quiz.software.service.implementations.HistoryServiceImp;
import am.aca.quiz.software.service.implementations.TestServiceImp;
import am.aca.quiz.software.service.implementations.UserServiceImp;
import am.aca.quiz.software.service.mapper.HistoryMapper;
import am.aca.quiz.software.service.mapper.TestMapper;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.security.Principal;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/history")
public class HistoryController {
    private final HistoryServiceImp historyServiceImp;
    private final HistoryMapper historyMapper;
    private final UserServiceImp userServiceImp;
    private final TestServiceImp testServiceImp;
    private final TestMapper testMapper;

    public HistoryController(HistoryServiceImp historyServiceImp, HistoryMapper historyMapper, UserServiceImp userServiceImp, TestServiceImp testServiceImp, TestMapper testMapper) {
        this.historyServiceImp = historyServiceImp;
        this.historyMapper = historyMapper;
        this.userServiceImp = userServiceImp;
        this.testServiceImp = testServiceImp;
        this.testMapper = testMapper;
    }

    @GetMapping("/future")
    public ModelAndView futureHistory(Principal principal) {

        String email = principal.getName();
        UserEntity userEntity = null;
        try {
            userEntity = userServiceImp.findByEmail(email);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        List<HistoryEntity> historyEntityList = historyServiceImp.findAllByUserId(userEntity.getId());
        ModelAndView modelAndView = new ModelAndView("futureHistory");


        List<HistoryDto> historyDtos = historyMapper.mapEntitiesToDto(historyEntityList.stream()
                .filter(i -> i.getStatus().equals(Status.UPCOMING))
                .collect(Collectors.toList()));

        List<TestDto> testDtoList = new ArrayList<>();

        historyDtos.stream()
                .forEach(i -> {
                    try {
                        testDtoList.add(testMapper.mapEntityToDto(testServiceImp.getById(i.getTestId())));
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                });
        modelAndView.addObject("testList", testDtoList);
        modelAndView.addObject("historyList", historyDtos);

        return modelAndView;

    }

    @GetMapping("/pastList")
    public ModelAndView pastHistoryList(Principal principal) {
        ModelAndView modelAndView = new ModelAndView("pastHistory");

        try {
            Long userId = userServiceImp.findByEmail(principal.getName()).getId();

            List<HistoryDto> historyDtos = historyMapper.mapEntitiesToDto(historyServiceImp.findAllByStatus(userId, Status.COMPLETED));

            modelAndView.addObject("historyList", historyDtos);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return modelAndView;
    }

    @GetMapping("/allHistory")
    public ModelAndView userAllHistory(Principal principal) {
        ModelAndView modelAndView = new ModelAndView("allHistory");

        try {
            Long userId = userServiceImp.findByEmail(principal.getName()).getId();
            modelAndView.addObject("historyList", historyMapper.mapEntitiesToDto(historyServiceImp.findAllByUserId(userId)));
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return modelAndView;
    }
}
