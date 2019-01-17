package am.aca.quiz_software.service.intefaces;


import am.aca.quiz_software.entity.TopicEntity;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;

@Service
public interface TopicService {
    //create
    boolean addCategory(TopicEntity topic) throws SQLException;

    //read
    List<TopicEntity> getAll() throws SQLException;

    //update
    boolean update(TopicEntity topic, Long id) throws SQLException;

    //delete
    TopicEntity remove(TopicEntity topic) throws SQLException;


    boolean removeByid(Long id) throws SQLException;

    TopicEntity getById(Long id) throws SQLException;
}
