package am.aca.quiz.software.controller;

import am.aca.quiz.software.service.dto.TestDto;
import am.aca.quiz.software.service.implementations.QuestionServiceImp;
import am.aca.quiz.software.service.implementations.TestServiceImp;
import am.aca.quiz.software.service.implementations.TopicServiceImp;
import am.aca.quiz.software.service.mapper.QuestionMapper;
import am.aca.quiz.software.service.mapper.TestMapper;
import am.aca.quiz.software.service.mapper.TopicMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.sql.SQLException;
import java.util.List;

@RestController
@RequestMapping("/test")
public class TestController {

    private final TestServiceImp testServiceImp;
    private final TestMapper testMapper;
    private final TopicServiceImp topicServiceImp;
    private final TopicMapper topicMapper;
    private final QuestionServiceImp questionServiceImp;
    private final QuestionMapper questionMapper;


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
        return new ModelAndView("test");
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

//    @PostMapping("/add")
//    public ModelAndView postTest(@RequestParam("name") String name, @RequestParam("description") String description, @RequestParam("duretion") String duretion) {
////        ModelAndView modelAndView = new ModelAndView("view/test");
////
////        Map<TopicDto, List<QuestionDto>> testMap = new HashMap<>();
////
////        List<TopicDto> topicDtos = new ArrayList<>();
////        List<QuestionDto> questionsDtos = new ArrayList<>();
////        try {
////            topicDtos.add()
////            questionsDtos =
////        } catch (SQLException e) {
////            e.printStackTrace();
////        }
//
//
//        modelAndView.addObject("topics", topicDtos);
//
//        modelAndView.addObject("questions", questionsDtos);
//        return modelAndView;
//    }
}