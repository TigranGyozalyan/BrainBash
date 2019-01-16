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

    private final QuestionRepository questionRepository;
    private final AnswerRepository answerRepository;
    private final TopicRepository topicRepository;

    @Autowired
    public QuestionServiceImp(QuestionRepository questionRepository, AnswerRepository answerRepository, TopicRepository topicRepository) {
        this.questionRepository = questionRepository;
        this.answerRepository = answerRepository;
        this.topicRepository = topicRepository;
    }

    public void addCategory(QuestionEntity question) throws SQLException {
        questionRepository.saveAndFlush(question);
        return true;
    }

    public List<QuestionEntity> getAll() throws SQLException {
        return questionRepository.findAll();
    }

    public void update(QuestionEntity question) throws SQLException {
        //toDO
    }

    public QuestionEntity remove(QuestionEntity question) throws SQLException {
        questionRepository.delete(question);
        return question;
    }
}
