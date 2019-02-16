package am.aca.quiz.software.controller;

import am.aca.quiz.software.entity.AnswerEntity;
import am.aca.quiz.software.entity.QuestionEntity;
import am.aca.quiz.software.service.dto.AnswerDto;
import am.aca.quiz.software.service.dto.QuestionDto;
import am.aca.quiz.software.service.implementations.AnswerServiceImp;
import am.aca.quiz.software.service.implementations.QuestionServiceImp;
import am.aca.quiz.software.service.implementations.TestServiceImp;
import am.aca.quiz.software.service.implementations.TopicServiceImp;
import am.aca.quiz.software.service.mapper.QuestionMapper;
import am.aca.quiz.software.service.mapper.TestMapper;
import am.aca.quiz.software.service.mapper.TopicMapper;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.sql.SQLException;
import java.util.*;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/question")
public class QuestionController {

    private final QuestionServiceImp questionServiceImp;
    private final TopicServiceImp topicServiceImp;
    private final QuestionMapper questionMapper;
    private final TopicMapper topicMapper;
    private final AnswerServiceImp answerServiceImp;
    private final TestServiceImp testServiceImp;
    private final TestMapper testMapper;
    private Set<Long> testID = new HashSet<>();

    public List<QuestionEntity> getQuestionEntityList() {
        return questionEntityList;
    }

    private List<QuestionEntity> questionEntityList;

    public Set<Long> getTestID() {
        return testID;
    }


    public QuestionController(QuestionServiceImp questionServiceImp, TopicServiceImp topicServiceImp, QuestionMapper questionMapper, TopicMapper topicMapper, AnswerServiceImp answerServiceImp, TestServiceImp testServiceImp, TestMapper testMapper) {
        this.questionServiceImp = questionServiceImp;
        this.topicServiceImp = topicServiceImp;
        this.questionMapper = questionMapper;
        this.topicMapper = topicMapper;
        this.answerServiceImp = answerServiceImp;
        this.testServiceImp = testServiceImp;
        this.testMapper = testMapper;

    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping(value = "/{id}")
    public ResponseEntity<QuestionDto> getById(@PathVariable("id") Long id) {
        try {
            return ResponseEntity.ok(questionMapper.mapEntityToDto(questionServiceImp.getById(id)));
        } catch (SQLException e) {
            return ResponseEntity.noContent().build();
        }
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping(value = "/list")
    public ModelAndView questionList() {
        ModelAndView modelAndView = new ModelAndView("questionList");

        try {
            modelAndView.addObject("questionList", questionMapper.mapEntitiesToDto(questionServiceImp.getAll()));
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return modelAndView;
    }

    @GetMapping("/all")
    public List<QuestionDto> getAllQuestions(){
        try {
            return questionMapper.mapEntitiesToDto(questionServiceImp.getAll());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @GetMapping
    public ResponseEntity<List<QuestionDto>> getAllQuestionsByTopicId(@RequestParam("topicId") Long topicId) {
        try {
            List<QuestionEntity> questionList = questionServiceImp.getAll().stream()
                    .filter(i -> i.getTopicEntity().getId().equals(topicId))
                    .collect(Collectors.toList());
            return ResponseEntity.ok(questionMapper.mapEntitiesToDto(questionList));
        } catch (SQLException e) {
            return ResponseEntity.noContent().build();
        }
    }

    @GetMapping("/test")
    public ResponseEntity<List<QuestionDto>> getAllQuestionsByTestId(@RequestParam("testId") long testId) {
        try {

            List<QuestionEntity> questionList = testServiceImp.getById(testId).getQuestionEntities();
            if (!testID.contains(testId)) {
                testID.add(testId);
                questionEntityList = questionList;
                Collections.shuffle(questionList);
            } else {
                questionList = questionEntityList;
            }

            return ResponseEntity.ok(questionMapper.mapEntitiesToDto(questionList));
        } catch (SQLException e) {
            return ResponseEntity.noContent().build();
        }
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping(value = "/add")
    public ModelAndView addQuestion() {
        return new ModelAndView("question");
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @PostMapping(value = "/add", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ModelAndView postQuestion(@RequestBody QuestionDto question) {
        try {

            String questionBody = question.getQuestion();
            String level = question.getLevel();
            int points = question.getPoints();
            int corr_answer_count = 0;

            List<AnswerDto> answerList = question.getAnswerDtoList();

            for (AnswerDto answer : answerList) {
                corr_answer_count += answer.isCorrect() ? 1 : 0;
            }

            Long topicId = question.getTopicId();

            questionServiceImp.addQuestion(questionBody, level, corr_answer_count, points, topicId);
            Long questionId = questionServiceImp.getQuestionEntityByQuestion(questionBody).getId();

            for (AnswerDto answer : answerList) {
                String answerText = answer.getAnswer();
                String description = answer.getDescription();
                boolean isCorrect = answer.isCorrect();
                answerServiceImp.addAnswer(answerText, description, isCorrect, questionId);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return new ModelAndView("question");
    }


    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping(value = "/update/{id}")
    public ModelAndView updateQuestion(@PathVariable("id") int id) {
        return new ModelAndView("questionUpdate");
    }


    @SuppressWarnings("Duplicates")
    @PreAuthorize("hasAuthority('ADMIN')")
    @PostMapping(value = "/update/{id}")
    public ModelAndView updateQuestion(@PathVariable Long id ,@RequestBody QuestionDto questionDto) {

        String questionBody = questionDto.getQuestion();
        String level = questionDto.getLevel();
        int points = questionDto.getPoints();
        int corr_answer_count = 0;

        List<AnswerDto> answerList = questionDto.getAnswerDtoList();

        for (AnswerDto answer : answerList) {
            corr_answer_count += answer.isCorrect() ? 1 : 0;
        }

        Long topicId = questionDto.getTopicId();
        try {
            QuestionEntity updatedQuestionEntity = new QuestionEntity(questionBody,  points, level, corr_answer_count, topicServiceImp.getById(topicId));
            updatedQuestionEntity.setId(id);
            questionServiceImp.update(updatedQuestionEntity);

            List<AnswerEntity> initialAnswers = answerServiceImp.getAnswerEntitiesByQuestionId(id);

            for(AnswerEntity answerEntity : initialAnswers) {
                answerServiceImp.removeById(answerEntity.getId());
            }

            for (AnswerDto answer : answerList) {
                String answerText = answer.getAnswer();
                String description = answer.getDescription();
                boolean isCorrect = answer.isCorrect();
                answerServiceImp.addAnswer(answerText,description,isCorrect,updatedQuestionEntity.getId());
            }


        } catch (SQLException e) {
            e.printStackTrace();
        }
        return new ModelAndView("question");
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping(value = "/delete/{id}")
    public ModelAndView deleteQuestion(@PathVariable("id") Long id) {
        try {
            questionServiceImp.removeById(id);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return questionList();

    }

}

