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

    public TopicServiceImp getTopicServiceImp() {
        return topicServiceImp;
    }

    @Override
    public void addQuestion(String question, String level, int correctAnswerCount, int points, Long topicEntityId) throws SQLException {
        TopicEntity topicEntity = topicServiceImp.getById(topicEntityId);

        QuestionEntity questionEntity = new QuestionEntity(question, points, level, correctAnswerCount, topicEntity);

        topicEntity.getQuestionEntities().add(questionEntity);
        questionRepository.saveAndFlush(questionEntity);
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
        QuestionEntity questionEntity = questionRepository.findById(id).get();
        if (questionEntity == null) {
            throw new SQLException("Entity not found");
        }

        return questionEntity;
    }

    @Override
    public void removeByid(Long id) throws SQLException {
        QuestionEntity deletedQuestion = questionRepository.findById(id).get();
        questionRepository.delete(deletedQuestion);

    }

    @Override
    public QuestionEntity getQuestionEntityByQuestion(String question) throws SQLException {
        return questionRepository.findQuestionEntitiesByQuestion(question);
    }

    public List<QuestionEntity> getQuestionsByTopicEntity(TopicEntity topicEntity){
        return questionRepository.findQuestionEntitiesByTopicEntity(topicEntity);
    }
}
