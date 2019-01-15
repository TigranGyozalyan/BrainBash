package am.aca.quiz_software.service.dto;


import am.aca.quiz_software.entity.TopicEntity;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;

@Service
public interface TopicService {
    //create
    void addCategory(TopicEntity topic) throws SQLException;

    //read
    List<TopicEntity> getAll() throws SQLException;

    //update
    void update(TopicEntity topic) throws SQLException;

    //delete
    void remove(TopicEntity topic) throws SQLException;

}
