package am.aca.quiz_software.service.implementations;

import am.aca.quiz_software.entity.TopicEntity;
import am.aca.quiz_software.repository.TopicRepository;
import am.aca.quiz_software.service.intefaces.TopicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;


@Service
public class TopicServiceImp implements TopicService {

    @Autowired
    private  TopicRepository topicRepository;
    @Autowired
    private ScoreServiceImp scoreServiceImp;
    @Autowired
    private SubCategoryServiceImp subCategoryServiceImp;
    @Autowired
    private QuestionServiceImp questionServiceImp;

    public boolean addCategory(TopicEntity topic) throws SQLException {
        topicRepository.saveAndFlush(topic);
        return true;
    }

    public List<TopicEntity> getAll() throws SQLException {
        return topicRepository.findAll();
    }

    @Override
    public boolean update(TopicEntity topic, Long id) throws SQLException {
        Optional<TopicEntity> historyEntity=topicRepository.findById(id);
        if(!historyEntity.isPresent()){
            throw new SQLException("Argument Not Found ");
        }
        topic.setId(id);
        topicRepository.saveAndFlush(topic);
        return true;
    }


    public TopicEntity remove(TopicEntity topic) throws SQLException {
        topicRepository.delete(topic);
        return topic;
    }

    @Override
    public boolean removeByid(Long id) throws SQLException {
        return false;
    }

    @Override
    public TopicEntity getById(Long id) throws SQLException {
        return null;
    }
}
