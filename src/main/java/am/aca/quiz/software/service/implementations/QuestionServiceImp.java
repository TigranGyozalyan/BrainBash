package am.aca.quiz.software.service.implementations;

import am.aca.quiz.software.repository.QuestionRepository;
import am.aca.quiz.software.service.intefaces.QuestionService;
import am.aca.quiz.software.entity.QuestionEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;


@Service
public class QuestionServiceImp implements QuestionService {

    @Autowired
    private QuestionRepository questionRepository;
    @Autowired
    private AnswerServiceImp answerServiceImp;
    @Autowired
    private TestServiceImp testServiceImp;
    @Autowired
    private TopicServiceImp topicServiceImp;

    public boolean addCategory(QuestionEntity question) throws SQLException {
        questionRepository.saveAndFlush(question);
        return true;
    }

    public List<QuestionEntity> getAll() throws SQLException {
        return questionRepository.findAll();
    }

    @Override
    public boolean update(QuestionEntity question, Long id) throws SQLException {

        Optional<QuestionEntity> historyEntity=questionRepository.findById(id);
        if(!historyEntity.isPresent()){
            throw new SQLException("Argument Not Found ");
        }
        question.setId(id);
        questionRepository.saveAndFlush(question);
        return true;
    }


    public QuestionEntity remove(QuestionEntity question) throws SQLException {
        questionRepository.delete(question);
        return question;
    }

    @Override
    public QuestionEntity getById(Long id) throws SQLException {
        return null;
    }

    @Override
    public boolean removeByid(Long id) throws SQLException {
        return false;
    }
}
