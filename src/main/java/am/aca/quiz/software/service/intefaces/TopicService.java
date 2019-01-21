package am.aca.quiz.software.service.intefaces;


import am.aca.quiz.software.entity.TopicEntity;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;

@Service
public interface TopicService {
    //create
    boolean addTopic(String topicName, Long subCategoryId) throws SQLException;

    //read
    List<TopicEntity> getAll() throws SQLException;

    //update
    boolean update(TopicEntity topic, Long id) throws SQLException;


    boolean removeByid(Long id) throws SQLException;

    TopicEntity getById(Long id) throws SQLException;
}
