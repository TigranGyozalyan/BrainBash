package am.aca.quiz.software.service.implementations;

import am.aca.quiz.software.repository.TopicRepository;
import am.aca.quiz.software.service.intefaces.TopicService;
import am.aca.quiz.software.entity.TopicEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;


@Service
public class TopicServiceImp implements TopicService {

    @Autowired
    private TopicRepository topicRepository;
    @Autowired
    private ScoreServiceImp scoreServiceImp;
    @Autowired
    private SubCategoryServiceImp subCategoryServiceImp;
    @Autowired
    private QuestionServiceImp questionServiceImp;

    public boolean addTopic(TopicEntity topic) throws SQLException {
        topicRepository.saveAndFlush(topic);
        return true;
    }

    public List<TopicEntity> getAll() throws SQLException {
        return topicRepository.findAll();
    }

    @Override
    public boolean update(TopicEntity topic, Long id) throws SQLException {
        TopicEntity updated_topic = getById(id);
        if (updated_topic != null) {
            topic.setId(id);
            return addTopic(topic);
        }
        return false;
    }


    public TopicEntity remove(TopicEntity topic) throws SQLException {
        topicRepository.delete(topic);
        return topic;
    }

    @Override
    public boolean removeByid(Long id) throws SQLException {
        TopicEntity deleted_topic = getById(id);
        topicRepository.delete(deleted_topic);
        return true;
    }

    @Override
    public TopicEntity getById(Long id) throws SQLException {
        Optional<TopicEntity> topicEntity = topicRepository.findById(id);
        if (!topicEntity.isPresent()) {
            throw new SQLException("Entity not found");
        }
        return topicEntity.get();
    }
}
