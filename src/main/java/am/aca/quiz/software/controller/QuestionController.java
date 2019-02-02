package am.aca.quiz.software.controller;

import am.aca.quiz.software.service.dto.AnswerDto;
import am.aca.quiz.software.service.dto.QuestionDto;
import am.aca.quiz.software.service.dto.TopicDto;
import am.aca.quiz.software.service.implementations.AnswerServiceImp;
import am.aca.quiz.software.service.implementations.QuestionServiceImp;
import am.aca.quiz.software.service.implementations.TopicServiceImp;
import am.aca.quiz.software.service.mapper.QuestionMapper;
import am.aca.quiz.software.service.mapper.TopicMapper;

import org.springframework.http.MediaType;
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

    @PostMapping(value = "/add",consumes = MediaType.APPLICATION_JSON_VALUE)
//    public ModelAndView postQuestion(@RequestParam Map<String, String> formData) {
    public String postQuestion(@RequestBody QuestionDto question) {
        try {
        String questionBody = question.getQuestion();
        String level = question.getLevel();
        int points = question.getPoints();
        int corr_answer_count = question.getCorrect_amount();


            Long topicId = question.getTopicId();

            questionServiceImp.addQuestion(questionBody, level, corr_answer_count, points, topicId);
            Long questionId =  questionServiceImp.getQuestionEntityByQuestion(questionBody).getId();
            List<AnswerDto> answerList = question.getAnswerDtoList();

            for(AnswerDto answer : answerList) {
                String answerText = answer.getAnswer();
                String description = answer.getDescription();
                boolean isCorrect = answer.isCorrect();
                answerServiceImp.addAnswer(answerText,description,isCorrect,questionId);
            }

            List<TopicDto> topicDtos = topicMapper.mapEntitiesToDto(topicServiceImp.getAll());
            //modelAndView.addObject("topics", topicDtos);
            return null;
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return "redirect?/add";
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

