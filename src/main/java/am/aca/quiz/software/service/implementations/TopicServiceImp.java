package am.aca.quiz.software.service.implementations;

import am.aca.quiz.software.entity.SubCategoryEntity;
import am.aca.quiz.software.entity.TopicEntity;
import am.aca.quiz.software.repository.TopicRepository;
import am.aca.quiz.software.service.interfaces.TopicService;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;


@Service
public class TopicServiceImp implements TopicService {


    private final TopicRepository topicRepository;
    private final SubCategoryServiceImp subCategoryServiceImpl;

    public TopicServiceImp(TopicRepository topicRepository, SubCategoryServiceImp subCategoryServiceImpl) {
        this.topicRepository = topicRepository;
        this.subCategoryServiceImpl = subCategoryServiceImpl;
    }


    @Override
    public boolean addTopic(String topicName, Long subCategoryId) throws SQLException {
        SubCategoryEntity subCategoryEntity = subCategoryServiceImpl.getById(subCategoryId);
        TopicEntity topicEntity = new TopicEntity(topicName, subCategoryEntity);

        subCategoryEntity.getTopicEntityList().add(topicEntity);

        topicRepository.saveAndFlush(topicEntity);
        return true;
    }


    @Override
    public List<TopicEntity> getAll() throws SQLException {
        return topicRepository.findAll();
    }

    @Override
    public boolean update(TopicEntity topic, Long id) throws SQLException {
        TopicEntity updatedTopic = topicRepository.findById(id).get();

        if (updatedTopic != null) {
            topic.setId(id);
            topicRepository.saveAndFlush(topic);
            return true;
        }

        return false;
    }

    @Override
    public boolean removeByid(Long id) throws SQLException {
        TopicEntity deletedTopic = topicRepository.findById(id).get();
        topicRepository.delete(deletedTopic);
        return true;
    }

    @Override
    public TopicEntity getById(Long id) throws SQLException {
        TopicEntity topicEntity = topicRepository.findById(id).get();

        if (topicEntity == null) {
            throw new SQLException("Entity not found");
        }
        return topicEntity;
    }
}
