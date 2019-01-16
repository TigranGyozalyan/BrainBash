package am.aca.quiz_software.service;


import am.aca.quiz_software.entity.AnswerEntity;
import am.aca.quiz_software.repository.AnswerRepository;
import am.aca.quiz_software.repository.QuestionRepository;
import am.aca.quiz_software.service.dto.AnswerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;

@Service
public  class AnswerServiceImp implements AnswerService {

    private final AnswerRepository answerRepository;
    private final QuestionRepository questionRepository;

    @Autowired
    public AnswerServiceImp(AnswerRepository answerRepository, QuestionRepository questionRepository) {
        this.answerRepository = answerRepository;
        this.questionRepository = questionRepository;
    }

    public void addCategory(AnswerEntity answer) throws SQLException {
        answerRepository.saveAndFlush(answer);
        return true;

    }

    public List<AnswerEntity> getAll() throws SQLException {
        return answerRepository.findAll();
    }

    public void update(AnswerEntity answer) throws SQLException {
        //toDO
    }

    public AnswerEntity remove(AnswerEntity answer) throws SQLException {
        answerRepository.delete(answer);
        return answer;
    }
    //TODO implement methods
}
