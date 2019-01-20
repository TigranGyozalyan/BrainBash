package am.aca.quiz.software.service.implementations;

import am.aca.quiz.software.repository.TopicRepository;
import am.aca.quiz.software.service.dto.TopicDto;
import am.aca.quiz.software.service.intefaces.TopicService;
import am.aca.quiz.software.entity.TopicEntity;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;


@Service
public class TopicServiceImp implements TopicService {


    private final TopicRepository topicRepository;

    public TopicServiceImp(TopicRepository topicRepository) {
        this.topicRepository = topicRepository;
    }


    @Override
    public boolean addTopic(String topicName, Long subCategoryId) throws SQLException {
        return false;
    }

    @Override
    public List<TopicEntity> getAll() throws SQLException {
        return null;
    }

    @Override
    public boolean update(TopicEntity topic, Long id) throws SQLException {
        return false;
    }

    @Override
    public TopicEntity remove(TopicEntity topic) throws SQLException {
        return null;
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
