package am.aca.quiz.software.controller;

import am.aca.quiz.software.entity.QuestionEntity;
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
    private List<QuestionDto> filteredQuestions=null;
    private String description,duration,test_name,question;
    private List<QuestionDto> questionDtos=new ArrayList<>();


    public TestController(TestServiceImp testServiceImp, TestMapper testMapper, TopicServiceImp topicServiceImp, TopicMapper topicMapper, QuestionServiceImp questionServiceImp, QuestionMapper questionMapper) {
        this.testServiceImp = testServiceImp;
        this.testMapper = testMapper;
        this.topicServiceImp = topicServiceImp;
        this.topicMapper = topicMapper;
        this.questionServiceImp = questionServiceImp;
        this.questionMapper = questionMapper;
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

    @GetMapping
    @RequestMapping("/add")
    public ModelAndView addTest() {

        ModelAndView modelAndView = new ModelAndView("test_topic");
        try {
            List<TopicDto> topicDtos = topicMapper.mapEntitiesToDto(topicServiceImp.getAll());
            modelAndView.addObject("topicList", topicDtos);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return modelAndView;
    }

    @PostMapping("/add")
    @ResponseBody
    public ModelAndView postTest(@RequestParam Map<String, String> formData) {

        questionDtos.clear();

         List<TopicEntity> topics = new ArrayList<>();
         description=formData.get("description");
         duration=formData.get("duration");
         test_name=formData.get("test_name");

        for (int i = 0; i < 5; i++) {
            String topicNameValue = formData.get("topicList" + (i + 1));
            if (!topicNameValue.equals(EMPTY)) {
                try {
                    topics.add(topicServiceImp.getByTopicName(topicNameValue));
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        List<QuestionEntity> allQuestions=new ArrayList<>();

        for(int i=0;i<topics.size();i++){
            List<QuestionEntity> questionEntities=questionServiceImp.getQuestionEntityByTopic(topics.get(i));

            questionEntities.forEach(question->allQuestions.add(question));
        }

       filteredQuestions=questionMapper.mapEntitiesToDto(allQuestions);
        return addTest();
    }

    @GetMapping("/add/question")
    public ModelAndView addTestQuestions(@RequestParam Map<String, String> formData) {

        ModelAndView modelAndView=new ModelAndView("test_questions");
        modelAndView.addObject("questionList",filteredQuestions);

        return modelAndView;
    }
    @PostMapping(value = "/add/question")
    public ModelAndView postTestQuestions(@RequestParam Map<String, String> formData){

        ModelAndView modelAndView=new ModelAndView("test_questions");
        question=formData.get("Questions");

        QuestionDto questionDto=questionMapper
                    .mapEntityToDto(questionServiceImp.getQuestionEntityByQuestion(question));

        questionDtos.add(questionDto);

        try {
            testServiceImp.addTest(test_name,description,Long.parseLong(duration),questionDtos);
        } catch (SQLException e) {
            e.printStackTrace();
        }


        filteredQuestions.remove(questionMapper.mapEntityToDto(questionServiceImp.getQuestionEntityByQuestion(question)));



        modelAndView.addObject("questionList",filteredQuestions);

        return modelAndView;
    }







}
