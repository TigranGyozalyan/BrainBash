package am.aca.quiz.software.controller;

import am.aca.quiz.software.entity.HistoryEntity;
import am.aca.quiz.software.entity.QuestionEntity;
import am.aca.quiz.software.entity.enums.Status;
import am.aca.quiz.software.service.dto.*;
import am.aca.quiz.software.service.implementations.*;
import am.aca.quiz.software.service.implementations.score.ScorePair;
import am.aca.quiz.software.service.mapper.QuestionMapper;
import am.aca.quiz.software.service.mapper.TestMapper;
import am.aca.quiz.software.service.mapper.TopicMapper;
import am.aca.quiz.software.service.mapper.UserMapper;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.math.BigInteger;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.*;

@RestController
@RequestMapping("/test")
public class TestController {

    private final TestServiceImp testServiceImp;
    private final TestMapper testMapper;
    private final TopicServiceImp topicServiceImp;
    private final TopicMapper topicMapper;
    private final QuestionServiceImp questionServiceImp;
    private final QuestionMapper questionMapper;
    private final UserMapper userMapper;
    private final UserServiceImp userServiceImp;
    private final QuestionController questionController;
    private final HistoryServiceImp historyServiceImp;
    private ScorePair<Double, Double> score;


    public TestController(TestServiceImp testServiceImp, TestMapper testMapper, TopicServiceImp topicServiceImp, TopicMapper topicMapper, QuestionServiceImp questionServiceImp, QuestionMapper questionMapper, UserMapper user, UserMapper userMapper, UserServiceImp userServiceImp, QuestionController questionController, HistoryServiceImp historyServiceImp) {
        this.testServiceImp = testServiceImp;
        this.testMapper = testMapper;
        this.topicServiceImp = topicServiceImp;
        this.topicMapper = topicMapper;
        this.questionServiceImp = questionServiceImp;
        this.questionMapper = questionMapper;
        this.userMapper = userMapper;
        this.userServiceImp = userServiceImp;
        this.questionController = questionController;
        this.historyServiceImp = historyServiceImp;
    }

//    @GetMapping("/{id}")
//    public ResponseEntity<TestDto> getById(@PathVariable("id") Long id) {
//        try {
//            if (testServiceImp.getById(id) != null) {
//                return ResponseEntity.ok(testMapper.mapEntityToDto(testServiceImp.getById(id)));
//            }
//            return ResponseEntity.noContent().build();
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//        return null;
//    }

    @GetMapping
    public ResponseEntity<List<TestDto>> getAll() {
        try {
            if (testServiceImp.getAll().isEmpty()) {
                return ResponseEntity.noContent().build();
            }
            return ResponseEntity.ok(testMapper.mapEntitiesToDto(testServiceImp.getAll()));
        } catch (SQLException e) {
        }
        return null;
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("/add")
    public ModelAndView addTest() {

        return new ModelAndView("test");
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @PostMapping("/add")
    public ModelAndView postTest(@RequestBody TestDto test) {

        String test_name = test.getTest_name();
        String description = test.getDescription();
        long duration = test.getDuration();

        List<Long> questionIds = test.getQuestionIds();
        List<QuestionEntity> questions = new ArrayList<>();

        try {
            for (Long questionId : questionIds) {
                QuestionEntity questionEntity = questionServiceImp.getById(questionId);
                questions.add(questionEntity);
            }
            testServiceImp.addTest(test_name, description, duration, questions);

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return new ModelAndView("test");
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("/list")
    public ModelAndView testList() {
        ModelAndView modelAndView = new ModelAndView("testList");
        try {
            modelAndView.addObject("testList", testMapper.mapEntitiesToDto(testServiceImp.getAll()));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return modelAndView;
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("/delete/{id}")
    public ModelAndView deleteTest(@PathVariable("id") Long id) {

        try {
            testServiceImp.removeById(id);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return testList();
    }

    @GetMapping("/solve/{id}")
    public ModelAndView loadTest(@PathVariable("id") Long id) {


        return new ModelAndView("testSolution");
    }


    @PostMapping("/process")
    public ModelAndView deleteTest(@RequestBody List<SubmitQuestionDto> submitQuestionDtos) {

        questionController.getTestID().clear();
        questionController.getQuestionEntityList().clear();

        testServiceImp.checkTest(submitQuestionDtos);

        return new ModelAndView("testSolution");
    }


    @GetMapping("/scorepage")
    public ModelAndView scorePage() {
        System.out.println(score);

        return new ModelAndView("testScore");
    }


    @GetMapping("/menu")
    public ModelAndView loadMenu() {
        ModelAndView modelAndView = new ModelAndView("testMenu");

        return modelAndView;
    }

    @GetMapping("/menu/{id}")
    public ModelAndView loadTestById(@PathVariable("id") Long id) {
        ModelAndView modelAndView = new ModelAndView("testByTopic");

        Set<BigInteger> testId = testServiceImp.findTestIdByTopicId(id);
        Set<TestDto> testDtos = new HashSet<>();
        testId.stream()
                .forEach(i -> {
                    try {
                        testDtos.add(testMapper.mapEntityToDto(testServiceImp.getById(i.longValue())));
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                });
        modelAndView.addObject("testList", testDtos);

        return modelAndView;
    }


    @GetMapping("/organize")
    public ModelAndView selectTest() {
        ModelAndView modelAndView = new ModelAndView("selectTest");
        try {
            List<TestDto> testDtos = testMapper.mapEntitiesToDto(testServiceImp.getAll());
            modelAndView.addObject("testList", testDtos);
        } catch (SQLException e) {
            e.printStackTrace();
        }


        return modelAndView;
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("/selectUser/{id}")
    public ModelAndView selectUsers(@PathVariable("id") Long id) {
        ModelAndView modelAndView = new ModelAndView("organizeTest");


        List<UserDto> userDtos = null;
        TestDto testDto = null;

        try {
            testDto = testMapper.mapEntityToDto(testServiceImp.getById(id));
            userDtos = userMapper.mapEntitiesToDto(userServiceImp.getAll());
        } catch (SQLException e) {
            e.printStackTrace();
        }


        modelAndView.addObject("userList", userDtos);
        modelAndView.addObject("test", testDto);


        return modelAndView;

    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @PostMapping("/selectUser")
    public ModelAndView saveHistory(@RequestBody TestUsersDto testUsersDto) {

        ModelAndView modelAndView = new ModelAndView("organizeTest");

        List<HistoryEntity> historyEntities = new ArrayList<>();
        List<UserDto> userDtos = null;
        TestDto testDto = null;

        try {
            testDto = testMapper.mapEntityToDto(testServiceImp.getById(testUsersDto.getTestId()));
            userDtos = userMapper.mapEntitiesToDto(userServiceImp.getAll());
        } catch (SQLException e) {
            e.printStackTrace();
        }


        List<UserDto> finalUserDtos = userDtos;

        userDtos
                .stream()
                .forEach(i -> {
                    HistoryEntity historyEntity = new HistoryEntity();
                    try {
                        historyEntity.setUserEntity(userServiceImp.getById(i.getId()));
                        historyEntity.setTestEntity(testServiceImp.getById(testUsersDto.getTestId()));
                        historyEntity.setStatus(Status.UPCOMING);
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                    historyEntity.setStartTime(LocalDateTime.parse(testUsersDto.getStartTime()));
                    System.out.println(testUsersDto.getStartTime());
                    historyEntity.setScore(0);
                    historyServiceImp.add(historyEntity);
                });

        return modelAndView;

    }


    @PreAuthorize("hasAuthority('ADMIN')")
    @PostMapping("/selectUser/{id}")
    public ModelAndView filterUser(@PathVariable("id") Long id, @RequestParam Map<String, String> formDate) {
        ModelAndView modelAndView = new ModelAndView("organizeTest");
        String user = formDate.get("search");

        List<UserDto> userDtos = new ArrayList<>();

        try {
            TestDto testDto = testMapper.mapEntityToDto(testServiceImp.getById(id));

            if (userServiceImp.findByEmail(user) != null) {

                userDtos.add(userMapper.mapEntityToDto(userServiceImp.findByEmail(user)));

            } else if (!userServiceImp.findByNameLike(user).isEmpty()) {

                userDtos = userMapper.mapEntitiesToDto(userServiceImp.findByNameLike(user));

            } else if (!userServiceImp.findBySurnameLike(user).isEmpty()) {

                userDtos = userMapper.mapEntitiesToDto(userServiceImp.findBySurnameLike(user));

            } else if (!userServiceImp.findByNiknameLike(user).isEmpty()) {

                userDtos = userMapper.mapEntitiesToDto(userServiceImp.findByNiknameLike(user));

            }
            modelAndView.addObject("userList", userDtos);
            modelAndView.addObject("test", testDto);

        } catch (SQLException e) {
            e.printStackTrace();
        }


        return modelAndView;
    }


    @PreAuthorize("hasAuthority('ADMIN')")
    @PostMapping(value = "/notify", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ModelAndView notify(@RequestBody TestUsersDto testUsersDto) {
        System.out.println(testUsersDto.getTestId() + " " + testUsersDto.getUsersId() + testUsersDto.getStartTime());


        //TODO
        return new ModelAndView("redirect:/test/organize");
//        return selectTest();
    }

}
