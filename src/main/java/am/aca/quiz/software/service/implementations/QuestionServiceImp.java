package am.aca.quiz.software.service.implementations;

import am.aca.quiz.software.entity.QuestionEntity;
import am.aca.quiz.software.entity.TopicEntity;
import am.aca.quiz.software.entity.enums.Level;
import am.aca.quiz.software.repository.QuestionRepository;
import am.aca.quiz.software.service.interfaces.QuestionService;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;


@Service
public class QuestionServiceImp implements QuestionService {


    private final QuestionRepository questionRepository;
    private final TopicServiceImp topicServiceImp;

    public QuestionServiceImp(QuestionRepository questionRepository, TopicServiceImp topicServiceImp) {
        this.questionRepository = questionRepository;
        this.topicServiceImp = topicServiceImp;
    }


    @Override
    public boolean addQuestion(String question, String level, int correctAnswerCount, int points, Long topicEntityId) throws SQLException {
        TopicEntity topicEntity = topicServiceImp.getById(topicEntityId);

        QuestionEntity questionEntity = new QuestionEntity(question,points,level,correctAnswerCount,topicEntity);

        topicEntity.getQuestionEntities().add(questionEntity);
        questionRepository.saveAndFlush(questionEntity);
        return true;

    }

    @Override
    public List<QuestionEntity> getAll() throws SQLException {
        return questionRepository.findAll();
    }

    @Override
    public boolean update(QuestionEntity question, Long id) throws SQLException {
        QuestionEntity updatedQuestion = questionRepository.findById(id).get();
        if (updatedQuestion != null) {
            question.setId(id);
            questionRepository.saveAndFlush(question);
            return true;
        }
        return false;
    }


    @Override
    public QuestionEntity getById(Long id) throws SQLException {
        QuestionEntity questionEntity = questionRepository.findById(id).get();
        if (questionEntity == null) {
            throw new SQLException("Entity not found");
        }

        return questionEntity;
    }

    @Override
    public boolean removeByid(Long id) throws SQLException {
        QuestionEntity deletedQuestion = questionRepository.findById(id).get();
        questionRepository.delete(deletedQuestion);

        return true;
    }
}
