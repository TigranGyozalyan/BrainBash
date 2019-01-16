package am.aca.quiz_software.service;

import am.aca.quiz_software.entity.TopicEntity;
import am.aca.quiz_software.repository.QuestionRepository;
import am.aca.quiz_software.repository.ScoreRepository;
import am.aca.quiz_software.repository.SubCategoryRepository;
import am.aca.quiz_software.repository.TopicRepository;
import am.aca.quiz_software.service.dto.TopicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;


@Service
public class TopicServiceImp implements TopicService {

    private final TopicRepository topicRepository;
    private final QuestionRepository questionRepository;
    private final ScoreRepository scoreRepository;
    private final SubCategoryRepository subCategoryRepository;

    @Autowired
    public TopicServiceImp(TopicRepository topicRepository, QuestionRepository questionRepository, ScoreRepository scoreRepository, SubCategoryRepository subCategoryRepository) {
        this.topicRepository = topicRepository;
        this.questionRepository = questionRepository;
        this.scoreRepository = scoreRepository;
        this.subCategoryRepository = subCategoryRepository;
    }

    public void addCategory(TopicEntity topic) throws SQLException {
        topicRepository.saveAndFlush(topic);
        return true;
    }

    public List<TopicEntity> getAll() throws SQLException {
        return topicRepository.findAll();
    }

    public void update(TopicEntity topic) throws SQLException {
        //toDO
    }

    public TopicEntity remove(TopicEntity topic) throws SQLException {
        topicRepository.delete(topic);
        return topic;
    }
}
