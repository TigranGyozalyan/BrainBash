package am.aca.quiz.software.service.implementations;

import am.aca.quiz.software.entity.SubCategoryEntity;
import am.aca.quiz.software.entity.TopicEntity;
import am.aca.quiz.software.repository.TopicRepository;
import am.aca.quiz.software.service.interfaces.TopicService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

    public SubCategoryServiceImp getSubCategoryServiceImpl() {
        return subCategoryServiceImpl;
    }



    @Override
    public void addTopic(String topicName, Long subCategoryId) throws SQLException {
        SubCategoryEntity subCategoryEntity = subCategoryServiceImpl.getById(subCategoryId);
        if (subCategoryEntity != null) {
            if (topicRepository.findByTopicName(topicName) == null) {
                TopicEntity topicEntity = new TopicEntity(topicName, subCategoryEntity);
                subCategoryEntity.getTopicEntityList().add(topicEntity);
                topicRepository.saveAndFlush(topicEntity);
            } else {
                throw new SQLException("topic is exist");
            }
        }
    }


    @Override
    public List<TopicEntity> getAll() throws SQLException {
        List<TopicEntity> topicList = topicRepository.findAll();

        if (topicList != null) {
            return topicList;
        } else {
            throw new SQLException("SubCategory table is empty");
        }
    }

    @Override
    public void update(TopicEntity updatedTopic) throws SQLException {

        topicRepository.save(updatedTopic);
    }

    @Transactional
    @Override
    public void removeById(Long id) throws SQLException {
        TopicEntity deletedTopic = topicRepository.findById(id).get();
        if (deletedTopic != null) {
            topicRepository.deleteById(id);
        } else {
            throw new SQLException("entity not found");
        }

    }

    @Override
    public TopicEntity getById(Long id) throws SQLException {
        TopicEntity topicEntity = topicRepository.findById(id).get();
        if (topicEntity == null) {
            throw new SQLException("Entity not found");
        }
        return topicEntity;
    }

    public TopicEntity getByTopicName(String topicName) throws SQLException {
        TopicEntity targetEntity = topicRepository.findByTopicName(topicName);
        if (targetEntity != null) {
            return targetEntity;
        } else {
            throw new SQLException("entity not found");
        }

    }

    public Long getSubCategoryIdByTopicName(String name){
        return topicRepository.findSubCategoryIdByTopicName(name);
    }
}
