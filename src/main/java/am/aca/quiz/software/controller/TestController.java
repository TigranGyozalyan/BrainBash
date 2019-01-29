package am.aca.quiz.software.controller;

import am.aca.quiz.software.entity.TopicEntity;
import am.aca.quiz.software.service.dto.QuestionDto;
import am.aca.quiz.software.service.dto.TestDto;
import am.aca.quiz.software.service.dto.TopicDto;
import am.aca.quiz.software.service.implementations.QuestionServiceImp;
import am.aca.quiz.software.service.implementations.TestServiceImp;
import am.aca.quiz.software.service.implementations.TopicServiceImp;
import am.aca.quiz.software.service.mapper.QuestionMapper;
import am.aca.quiz.software.service.mapper.TestMapper;
import am.aca.quiz.software.service.mapper.TopicMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/test")
public class TestController {

    private final TestServiceImp testServiceImp;
    private final TestMapper testMapper;
    private final TopicServiceImp topicServiceImp;
    private final TopicMapper topicMapper;
    private final QuestionServiceImp questionServiceImp;
    private final QuestionMapper questionMapper;
    private static final String EMPTY = "empty";


    public TestController(TestServiceImp testServiceImp, TestMapper testMapper, TopicServiceImp topicServiceImp, TopicMapper topicMapper, QuestionServiceImp questionServiceImp, QuestionMapper questionMapper) {
        this.testServiceImp = testServiceImp;
        this.testMapper = testMapper;
        this.topicServiceImp = topicServiceImp;
        this.topicMapper = topicMapper;
        this.questionServiceImp = questionServiceImp;
        this.questionMapper = questionMapper;
    }

    @GetMapping
    @RequestMapping("/add")
    public ModelAndView addTest() {

        ModelAndView modelAndView = new ModelAndView("test_topic");
        try {
            List<TopicDto> topicDtos = topicMapper.mapEntitiesToDto(topicServiceImp.getAll());
            modelAndView.addObject("topicList", topicDtos);
            return modelAndView;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @GetMapping("/{id}")
    public ResponseEntity<TestDto> getById(@PathVariable("id") Long id) {
        try {
            return ResponseEntity.ok(testMapper.mapEntityToDto(testServiceImp.getById(id)));
        } catch (SQLException e) {
            return ResponseEntity.noContent().build();
        }
    }

    @GetMapping
    public ResponseEntity<List<TestDto>> getAllTests() {
        try {
            return ResponseEntity.ok(testMapper.mapEntitiesToDto(testServiceImp.getAll()));
        } catch (SQLException e) {
            return ResponseEntity.noContent().build();
        }
    }

    @PostMapping("/add")
    @ResponseBody
    public ModelAndView postTest(Map<String, String> formData) {
        ModelAndView modelAndView = new ModelAndView("test_questions");

        List<TopicEntity> topics = new ArrayList<>();

        for (int i = 0; i < 5; i++) {
            String topicNameValue = formData.get("topic" + (i + 1));
            if (!topicNameValue.equals(EMPTY)) {
                topics.add(topicServiceImp.getByTopicName(topicNameValue));
            }

            //toDO finish the methods

//            modelAndView.addObject("topicList");
//        }
//            Map<TopicDto, List<QuestionDto>> testMap = new HashMap<>();
//
//            modelAndView.addObject("topics", topicDtos);
//
//            modelAndView.addObject("questions", questionsDtos);
//            return modelAndView;
//        }

        }
        return null;
    }

//    @PostMapping("/add/questions")
//    public ModelAndView postTestQuestions() {
//        return null;
//    }






}
