package am.aca.quiz.software.service.interfaces;


import am.aca.quiz.software.entity.TopicEntity;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;

@Service
public interface TopicService {
    //create
    void addTopic(String topicName, Long subCategoryId) throws SQLException;

    //read
    List<TopicEntity> getAll() throws SQLException;

    //update
    void update(TopicEntity topic) throws SQLException;


    void removeById(Long id) throws SQLException;

    TopicEntity getById(Long id) throws SQLException;
}
