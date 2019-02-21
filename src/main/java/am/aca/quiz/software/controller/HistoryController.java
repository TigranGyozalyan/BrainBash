package am.aca.quiz.software.controller;

import am.aca.quiz.software.entity.UserEntity;
import am.aca.quiz.software.entity.enums.Status;
import am.aca.quiz.software.service.dto.HistoryDto;
import am.aca.quiz.software.service.dto.TimerDto;
import am.aca.quiz.software.service.implementations.HistoryServiceImp;
import am.aca.quiz.software.service.implementations.TestServiceImp;
import am.aca.quiz.software.service.implementations.UserServiceImp;
import am.aca.quiz.software.service.mapper.HistoryMapper;
import am.aca.quiz.software.service.mapper.TestMapper;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.security.Principal;
import java.sql.SQLException;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/history")
public class HistoryController {
    private final HistoryServiceImp historyServiceImp;
    private final HistoryMapper historyMapper;
    private final UserServiceImp userServiceImp;
    private final TestServiceImp testServiceImp;
    private final TestMapper testMapper;
    private final TestController testController;

    public HistoryController(HistoryServiceImp historyServiceImp, HistoryMapper historyMapper, UserServiceImp userServiceImp, TestServiceImp testServiceImp, TestMapper testMapper, TestController testController) {
        this.historyServiceImp = historyServiceImp;
        this.historyMapper = historyMapper;
        this.userServiceImp = userServiceImp;
        this.testServiceImp = testServiceImp;
        this.testMapper = testMapper;
        this.testController = testController;
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

        List<am.aca.quiz.software.entity.HistoryEntity> historyEntityList = historyServiceImp.findAllByUserId(userEntity.getId());
        ModelAndView modelAndView = new ModelAndView("futureHistory");


        List<HistoryDto> historyDtos = historyMapper.mapEntitiesToDto(historyEntityList.stream()
            .filter(i -> i.getStatus().equals(Status.UPCOMING))
            .collect(Collectors.toList()));

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


    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("/history")
    public ModelAndView history() {

        return new ModelAndView("history");
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("/history/all")
    public ModelAndView modelAndView() {
        ModelAndView modelAndView = new ModelAndView("allStory");

        try {
            modelAndView.addObject("historyList", historyMapper.mapEntitiesToDto(historyServiceImp.getAll()));
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return modelAndView;
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("/history/past")
    public ModelAndView pastStory() {
        ModelAndView modelAndView = new ModelAndView("pastStory");

        List<HistoryDto> historyDtos = historyMapper.mapEntitiesToDto(historyServiceImp.findAllByStatus(Status.COMPLETED));

        modelAndView.addObject("historyList", historyDtos);


        return modelAndView;
    }


    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("/history/future")
    public ModelAndView futureStory() {
        ModelAndView modelAndView = new ModelAndView("futureStory");

        List<HistoryDto> historyDtos = historyMapper.mapEntitiesToDto(historyServiceImp.findAllByStatus(Status.UPCOMING));

        modelAndView.addObject("historyList", historyDtos);


        return modelAndView;
    }


    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("/history/inprogress")
    public ModelAndView inProgressStory() {
        ModelAndView modelAndView = new ModelAndView("inprogressStory");

        List<HistoryDto> historyDtos = historyMapper.mapEntitiesToDto(historyServiceImp.findAllByStatus(Status.INPROGRESS));

        modelAndView.addObject("historyList", historyDtos);


        return modelAndView;
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @PostMapping("/history/byemail")
    public ModelAndView historyByEmail(@RequestParam Map<String, String> formDate) {
        ModelAndView modelAndView = new ModelAndView("searchHistoryByEmail");

        String user = formDate.get("user");


        List<HistoryDto> historyDtos = null;
        if (!user.isEmpty()) {

            if (!historyServiceImp.findByEmail(user).isEmpty()) {
                historyDtos = historyMapper.mapEntitiesToDto(historyServiceImp.findByEmail(user));
            } else if (!historyServiceImp.findByName(user).isEmpty()) {
                historyDtos = historyMapper.mapEntitiesToDto(historyServiceImp.findByName(user));
            } else if (!historyServiceImp.findBySurname(user).isEmpty()) {
                historyDtos = historyMapper.mapEntitiesToDto(historyServiceImp.findBySurname(user));
            } else {
                historyDtos = historyMapper.mapEntitiesToDto(historyServiceImp.findByNickname(user));
            }

        }

        modelAndView.addObject("historyList", historyDtos);
        return modelAndView;
    }


    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("/history/search")
    public ModelAndView searchHistory() {
        return new ModelAndView("searchHistory");
    }


    @PreAuthorize("hasAuthority('ADMIN')")
    @PostMapping(value = "/test")
    public ModelAndView createTest(@RequestParam("testId") Long testId, @RequestParam Map<String, String> formData) {

        ModelAndView modelAndView = new ModelAndView("redirect:/test/organize");

        for (String key : formData.keySet()) {
            System.out.println(formData.get(key));
        }


        return modelAndView;
    }

    @PostMapping(value = "/test/update", consumes = MediaType.APPLICATION_JSON_VALUE)
    public void updateUserHistory(Principal principal, @RequestBody TimerDto timerDto) {

        try {
            double score = testController.getScore().getKey();

            UserEntity userEntity = userServiceImp.findByEmail(principal.getName());
            am.aca.quiz.software.entity.HistoryEntity history = historyServiceImp.findHistoryByUserIdAndTetId(userEntity.getId(), testController.getTestId(), "INPROGRESS");

            if (history != null) {
                if (history.getSessionId() != null) {

                    LocalDateTime endTime =
                        LocalDateTime.ofInstant(Instant.ofEpochMilli(timerDto.getEndTime()), ZoneId.systemDefault());

                    history.setScore(score);
                    history.setEndTime(endTime);

                    history.setStatus(Status.COMPLETED);
                    history.setSessionId(null);

                    historyServiceImp.addHistory(history);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @PostMapping("/user/{userId}/{testId}")
    public ResponseEntity<TimerDto> sendHistory(@PathVariable("userId") Long userId, @PathVariable("testId") Long testId) {

        HistoryDto historyDto = historyMapper.mapEntityToDto(historyServiceImp.findHistoryByUserIdAndTetId(userId, testId, "UPCOMING"));
        TimerDto timerDto = new TimerDto();
        timerDto.setCurrentTime(System.currentTimeMillis());
        timerDto.setStartTime(historyDto.getStartTime().atZone(ZoneId.systemDefault()).toInstant().toEpochMilli());
        return ResponseEntity.ok(timerDto);

    }
}
