package am.aca.quiz.software.controller;

import am.aca.quiz.software.entity.QuestionEntity;
import am.aca.quiz.software.service.dto.AnswerDto;
import am.aca.quiz.software.service.dto.QuestionDto;
import am.aca.quiz.software.service.dto.TopicDto;
import am.aca.quiz.software.service.implementations.AnswerServiceImp;
import am.aca.quiz.software.service.implementations.QuestionServiceImp;
import am.aca.quiz.software.service.implementations.TopicServiceImp;
import am.aca.quiz.software.service.mapper.QuestionMapper;
import am.aca.quiz.software.service.mapper.TopicMapper;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/question")
public class QuestionController {

    private final QuestionServiceImp questionServiceImp;
    private final TopicServiceImp topicServiceImp;
    private final QuestionMapper questionMapper;
    private final TopicMapper topicMapper;
    private final AnswerServiceImp answerServiceImp;


    public QuestionController(QuestionServiceImp questionServiceImp, TopicServiceImp topicServiceImp, QuestionMapper questionMapper, TopicMapper topicMapper, AnswerServiceImp answerServiceImp) {
        this.questionServiceImp = questionServiceImp;
        this.topicServiceImp = topicServiceImp;
        this.questionMapper = questionMapper;
        this.topicMapper = topicMapper;
        this.answerServiceImp = answerServiceImp;
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<QuestionDto> getById(@PathVariable("id") Long id) {
        try {
            return ResponseEntity.ok(questionMapper.mapEntityToDto(questionServiceImp.getById(id)));
        } catch (SQLException e) {
            return ResponseEntity.noContent().build();
        }
    }


    @GetMapping
    public ResponseEntity<List<QuestionDto>> getAllQuestionsByTopicId(@RequestParam("topicId") int topicId) {
        try {
            List<QuestionEntity> questionList = questionServiceImp.getAll().stream()
                    .filter(i -> i.getTopicEntity().getId() == topicId)
                    .collect(Collectors.toList());
            return new ResponseEntity<>(questionMapper.mapEntitiesToDto(questionList), HttpStatus.OK);
        } catch (SQLException e) {
            return ResponseEntity.noContent().build();
        }
    }

    @GetMapping(value = "/add")
    public ModelAndView addQuestion() {
//        ModelAndView modelAndView = ;
//
//        try {
//            List<TopicDto> topicDtos = topicMapper.mapEntitiesToDto(topicServiceImp.getAll());
//            modelAndView.addObject("topics", topicDtos);
//            return modelAndView;
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }

        return new ModelAndView("question");
    }

    @PostMapping(value = "/add", consumes = MediaType.APPLICATION_JSON_VALUE)
    public String postQuestion(@RequestBody QuestionDto question) {
        try {
            String questionBody = question.getQuestion();
            String level = question.getLevel();
            int points = question.getPoints();
            int corr_answer_count = question.getCorrect_amount();


            Long topicId = question.getTopicId();

            questionServiceImp.addQuestion(questionBody, level, corr_answer_count, points, topicId);
            Long questionId = questionServiceImp.getQuestionEntityByQuestion(questionBody).getId();
            List<AnswerDto> answerList = question.getAnswerDtoList();

            for (AnswerDto answer : answerList) {
                String answerText = answer.getAnswer();
                String description = answer.getDescription();
                boolean isCorrect = answer.isCorrect();
                answerServiceImp.addAnswer(answerText, description, isCorrect, questionId);
            }

            List<TopicDto> topicDtos = topicMapper.mapEntitiesToDto(topicServiceImp.getAll());
            //modelAndView.addObject("topics", topicDtos);
            return null;
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return "redirect?/add";
    }

    @GetMapping(value = "/update")
    public ModelAndView updateQuestion() {
        ModelAndView modelAndView = new ModelAndView("questionUpdate");
        List<TopicDto> topicDtos = null;
        List<QuestionDto> questionDtos = null;

        try {
            topicDtos = topicMapper.mapEntitiesToDto(questionServiceImp.getTopicServiceImp().getAll());
            questionDtos = questionMapper.mapEntitiesToDto(questionServiceImp.getAll());

//            modelAndView.addObject("empty", EMPTY);
            modelAndView.addObject("topics", topicDtos);
            modelAndView.addObject("question", questionDtos.get(0));

        } catch (SQLException e) {
            e.printStackTrace();
        }


        return modelAndView;
    }

    @PostMapping(value = "/update")
    public ModelAndView updateQuestion(@RequestParam Map<String, String> formDate) {
        ModelAndView modelAndView = new ModelAndView("questionUpdate");
        String topicName = formDate.get("topicList");
        String questionBody = formDate.get("questionText");
        String level = formDate.get("level");

        int points = Integer.parseInt(formDate.get("points"));
        int corr_answer_count = Integer.parseInt(formDate.get("corr_answer_count"));


        return modelAndView;
    }


}

