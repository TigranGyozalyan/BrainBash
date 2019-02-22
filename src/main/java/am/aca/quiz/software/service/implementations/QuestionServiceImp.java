package am.aca.quiz.software.service.implementations;

import am.aca.quiz.software.entity.QuestionEntity;
import am.aca.quiz.software.entity.TopicEntity;
import am.aca.quiz.software.repository.QuestionRepository;
import am.aca.quiz.software.service.interfaces.QuestionService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
    public void addQuestion(String question, String level, int correctAnswerCount, int points, Long topicEntityId) throws SQLException {
        TopicEntity topicEntity = topicServiceImp.getById(topicEntityId);

        QuestionEntity questionEntity = new QuestionEntity(question, points, level, correctAnswerCount, topicEntity);

        topicEntity.getQuestionEntities().add(questionEntity);
        questionRepository.save(questionEntity);
    }

    @Override
    public List<QuestionEntity> getAll() throws SQLException {
        return questionRepository.findAll();
    }

    @Override
    public void update(QuestionEntity questionEntity) throws SQLException {
        questionRepository.save(questionEntity);
    }

    @Override
    public QuestionEntity getById(Long id) throws SQLException {
        if (questionRepository.findById(id).isPresent()) {
            return questionRepository.findById(id).get();
        }
        throw new SQLException("Entity not found");
    }

    @Transactional
    @Override
    public void removeById(Long id) throws SQLException {
        if (questionRepository.findById(id).isPresent()) {
            QuestionEntity deletedQuestion = questionRepository.findById(id).get();
            questionRepository.delete(deletedQuestion);
        } else {
            throw new SQLException();
        }
    }

    @Override
    public QuestionEntity getQuestionEntityByQuestion(String question) throws SQLException {
        return questionRepository.findQuestionEntitiesByQuestion(question);
    }

    public List<QuestionEntity> getQuestionsByTopicEntity(TopicEntity topicEntity) {
        return questionRepository.findQuestionEntitiesByTopicEntity(topicEntity);
    }
}
