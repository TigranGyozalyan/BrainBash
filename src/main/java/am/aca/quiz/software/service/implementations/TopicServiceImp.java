package am.aca.quiz.software.service.implementations;

import am.aca.quiz.software.entity.SubCategoryEntity;
import am.aca.quiz.software.entity.TopicEntity;
import am.aca.quiz.software.repository.TopicRepository;
import am.aca.quiz.software.service.interfaces.TopicService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigInteger;
import java.sql.SQLException;
import java.util.List;
import java.util.Set;


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
                topicRepository.save(topicEntity);
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

    @Transactional
    @Override
    public void update(TopicEntity updatedTopic) throws SQLException {
        topicRepository.save(updatedTopic);
    }

    @Transactional
    @Override
    public void removeById(Long id) throws SQLException {
        if (topicRepository.findById(id).isPresent()) {
            topicRepository.deleteById(id);
        } else {
            throw new SQLException();
        }

    }

    @Override
    public TopicEntity getById(Long id) throws SQLException {
        if (topicRepository.findById(id).isPresent()) {
            return topicRepository.findById(id).get();
        }
        throw new SQLException("Entity not found");
    }

    public Set<BigInteger> findTopicIdByTestId(Long id) {
        return topicRepository.findTopicByTestId(id);
    }

    public Long findTopicIdByTest(Long id) {
        return topicRepository.findTopicIdByTestId(id);
    }
}
