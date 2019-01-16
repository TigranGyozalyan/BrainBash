package am.aca.quiz_software.service;

import am.aca.quiz_software.entity.QuestionEntity;
import am.aca.quiz_software.repository.AnswerRepository;
import am.aca.quiz_software.repository.QuestionRepository;
import am.aca.quiz_software.repository.TopicRepository;
import am.aca.quiz_software.service.dto.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;


@Service
public class QuestionServiceImp implements QuestionService {

    @Autowired
    private  QuestionRepository questionRepository;

    public boolean addCategory(QuestionEntity question) throws SQLException {
        questionRepository.saveAndFlush(question);
        return true;
    }

    public List<QuestionEntity> getAll() throws SQLException {
        return questionRepository.findAll();
    }

    public boolean update(QuestionEntity question) throws SQLException {
        //toDO
        return false;
    }

    public QuestionEntity remove(QuestionEntity question) throws SQLException {
        questionRepository.delete(question);
        return question;
    }
}
