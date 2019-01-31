package am.aca.quiz.software.controller;

import am.aca.quiz.software.entity.AnswerEntity;
import am.aca.quiz.software.entity.QuestionEntity;
import am.aca.quiz.software.service.dto.AnswerDto;
import am.aca.quiz.software.service.dto.QuestionDto;
import am.aca.quiz.software.service.implementations.AnswerServiceImp;
import am.aca.quiz.software.service.implementations.QuestionServiceImp;
import am.aca.quiz.software.service.mapper.AnswerMapper;
import am.aca.quiz.software.service.mapper.QuestionMapper;
import com.sun.org.apache.xpath.internal.operations.Mod;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/answer")
public class AnswerController {

    private final AnswerServiceImp answerServiceImp;
    private final QuestionServiceImp questionServiceImp;
    private final QuestionMapper questionMapper;
    private final AnswerMapper answerMapper;
    private final String EMPTY="empty";

    public AnswerController(AnswerServiceImp answerServiceImp, QuestionServiceImp questionServiceImp, QuestionMapper questionMapper, AnswerMapper answerMapper) {
        this.answerServiceImp = answerServiceImp;
        this.questionServiceImp = questionServiceImp;
        this.questionMapper = questionMapper;
        this.answerMapper = answerMapper;
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<AnswerDto> getById(@PathVariable("id") Long id) {
        try {
            return ResponseEntity.ok(answerMapper.mapEntityToDto(answerServiceImp.getById(id)));
        } catch (SQLException e) {
            return ResponseEntity.noContent().build();
        }
    }

    @GetMapping
    public ResponseEntity<List<AnswerDto>> getAllQuestions() {
        try {
            return ResponseEntity.ok(answerMapper.mapEntitiesToDto(answerServiceImp.getAll()));
        } catch (SQLException e) {
            return ResponseEntity.noContent().build();
        }
    }

    @GetMapping(value = "/add")
    public ModelAndView addQuestion() {
        ModelAndView modelAndView = new ModelAndView("answer");

        try {
            List<QuestionDto> questionDtos = questionMapper.mapEntitiesToDto(questionServiceImp.getAll());
            modelAndView.addObject("questions", questionDtos);
            return modelAndView;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @PostMapping(value = "/add")
    @ResponseBody
    public ModelAndView postAnswer(@RequestParam Map<String, String> formDate) {
        ModelAndView modelAndView = new ModelAndView("answer");

        String answer = formDate.get("answer");
        String description = formDate.get("description");
        String isCorrect = formDate.get("isCorrect");
        String question = formDate.get("question");
        QuestionEntity questionEntity = questionServiceImp.getQuestionEntityByQuestion(question);

        boolean isTrue = Boolean.parseBoolean(isCorrect);

        try {
            answerServiceImp.addAnswer(answer, description, isTrue, questionEntity.getId());
        } catch (SQLException e) {
            e.printStackTrace();
        }

        List<QuestionDto> questionDtos = null;

        try {
            questionDtos = questionMapper.mapEntitiesToDto(questionServiceImp.getAll());
        } catch (SQLException e) {
            e.printStackTrace();
        }

        modelAndView.addObject("questions", questionDtos);

        return modelAndView;

    }
    @GetMapping(value = "/update")
    public ModelAndView update(){
        ModelAndView modelAndView=new ModelAndView("answerUpdate");
        List<AnswerDto> answerDtos=null;
        List<QuestionDto> questionDtos=null;

        try {
            answerDtos=answerMapper.mapEntitiesToDto(answerServiceImp.getAll());
            questionDtos=questionMapper.mapEntitiesToDto(questionServiceImp.getAll());

            modelAndView.addObject("questions",questionDtos);
            modelAndView.addObject("answers",answerDtos);
            modelAndView.addObject("empty",EMPTY);

        } catch (SQLException e) {
            e.printStackTrace();
        }


        return modelAndView;
    }



    @PostMapping(value = "/update")
    public ModelAndView updateAnswer(@RequestParam Map<String, String> formData){
        String selectedAnswer = formData.get("answerList");
        String question = formData.get("questionList");
        String newAnswer=formData.get("answer");
        String description=formData.get("description");
        String isCorrect = formData.get("isCorrect");

        if(newAnswer!=null){
            QuestionEntity questionEntity=null;
            if(!question.equals(EMPTY)){
                questionEntity=questionServiceImp.getQuestionEntityByQuestion(question);

            }else {
                try {
                    questionEntity=questionServiceImp.getById(answerServiceImp.getQuestionIdByAnswer(selectedAnswer));
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            AnswerEntity updatedAnswer=new AnswerEntity(newAnswer,description,Boolean.parseBoolean(isCorrect),questionEntity);

            AnswerEntity answerEntity=answerServiceImp.getAnswerByAnswerText(selectedAnswer);
            try {
                answerServiceImp.update(updatedAnswer,answerEntity);
            } catch (SQLException e) {
                e.printStackTrace();
            }

        }
        return update();
    }


}
