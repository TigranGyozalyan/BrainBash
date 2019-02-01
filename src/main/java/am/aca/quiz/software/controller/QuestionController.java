package am.aca.quiz.software.controller;


import am.aca.quiz.software.entity.TopicEntity;
import am.aca.quiz.software.service.dto.QuestionDto;
import am.aca.quiz.software.service.dto.TopicDto;
import am.aca.quiz.software.service.implementations.AnswerServiceImp;
import am.aca.quiz.software.service.implementations.QuestionServiceImp;
import am.aca.quiz.software.service.implementations.TopicServiceImp;
import am.aca.quiz.software.service.mapper.QuestionMapper;
import am.aca.quiz.software.service.mapper.TopicMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/question")
public class QuestionController {

    private final QuestionServiceImp questionServiceImp;
    private final TopicServiceImp topicServiceImp;
    private final QuestionMapper questionMapper;
    private final TopicMapper topicMapper;
    private final AnswerServiceImp answerServiceImp;

    private final int ANSWER_COUNT = 6;
    private final String EMPTY = "empty";

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
    public ResponseEntity<List<QuestionDto>> getAllQuestions() {
        try {
            return ResponseEntity.ok(questionMapper.mapEntitiesToDto(questionServiceImp.getAll()));
        } catch (SQLException e) {
            return ResponseEntity.noContent().build();
        }
    }

    @GetMapping(value = "/add")
    public ModelAndView addQuestion() {
        ModelAndView modelAndView = new ModelAndView("question");

        try {
            List<TopicDto> topicDtos = topicMapper.mapEntitiesToDto(topicServiceImp.getAll());
            modelAndView.addObject("topics", topicDtos);
            return modelAndView;
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    @PostMapping(value = "/add")
    public ModelAndView postQuestion(@RequestParam Map<String, String> formData) {
        ModelAndView modelAndView = new ModelAndView("question");
        String questionBody = formData.get("questionText");
        String level = formData.get("level");
        int points = Integer.parseInt(formData.get("points"));
        int corr_answer_count = Integer.parseInt(formData.get("correct_answer_count"));
        String topicName = formData.get("topic");

        try {
            TopicEntity topicEntity = topicServiceImp.getByTopicName(topicName);
            Long topicId = topicEntity.getId();

            questionServiceImp.addQuestion(questionBody, level, corr_answer_count, points, topicId);

            QuestionDto newQuestionDto = questionMapper.mapEntityToDto(questionServiceImp.getQuestionEntityByQuestion(questionBody));

            Long questionId = newQuestionDto.getId();

            //todo


            List<TopicDto> topicDtos = topicMapper.mapEntitiesToDto(topicServiceImp.getAll());
            modelAndView.addObject("topics", topicDtos);
            return modelAndView;
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return modelAndView;
    }

    //toDO
//    @PostMapping(value = "/add")
//    @ResponseBody
//    public ModelAndView postQuestion(@RequestParam Map<String, String> formData) {
//        System.out.println("Add button was pressed");
//        String text = formData.get("questionText").;
//        String level = formData.get("level");
//
//        int points = Integer.parseInt(formData.get("points"));
//        int corr_answer = Integer.parseInt(formData.get("correct_answer_count"));
//        String topicName = formData.get("topic");
//        try {
//            TopicEntity topicEntity = topicServiceImp.getByTopicName(topicName);
//            Long topicId = topicEntity.getId();
//            questionServiceImp.addQuestion(text, level, corr_answer, points, topicId);
//            QuestionEntity newQuestion = questionServiceImp.getQuestionEntityByQuestion(text);
//            Long questionId = newQuestion.getId();
//
//            List<String> answerTextList = formData.get("answer");
//            List<String> descriptionList = formData.get("description");
//            List<String> isTrueList = formData.get("isTrue");
//            for (int i = 0; i < answerTextList.size(); i++) {
//                String answerText = answerTextList.get(i);
//                String description = ((char)(97 + i)) + ")" + descriptionList.get(i);
//                boolean isTrue = Boolean.parseBoolean(isTrueList.get(i));
//                answerServiceImp.addAnswer(answerText, description, isTrue, questionId);
//            }
//            List<TopicDto> topicDtos = topicMapper.mapEntitiesToDto(topicServiceImp.getAll());
//            ModelAndView modelAndView = new ModelAndView("question");
//            modelAndView.addObject("topics", topicDtos);
//            return modelAndView;
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//
//
//        return null;
//
//    }

    @GetMapping(value = "/update")
    public ModelAndView updateQuestion() {
        ModelAndView modelAndView = new ModelAndView("questionUpdate");
        List<TopicDto> topicDtos = null;
        List<QuestionDto> questionDtos = null;

        try {
            topicDtos = topicMapper.mapEntitiesToDto(questionServiceImp.getTopicServiceImp().getAll());
            questionDtos = questionMapper.mapEntitiesToDto(questionServiceImp.getAll());

            modelAndView.addObject("empty", EMPTY);
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
