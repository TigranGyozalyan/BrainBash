package am.aca.quiz.software.service.intefaces;


import am.aca.quiz.software.entity.TopicEntity;
import am.aca.quiz.software.service.dto.TopicDto;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;

@Service
public interface TopicService {
    //create
    boolean addTopic(TopicEntity topic) throws SQLException;

    //read
    List<TopicDto> getAll() throws SQLException;

    //update
    boolean update(TopicEntity topic, Long id) throws SQLException;

    //delete
    TopicDto remove(TopicEntity topic) throws SQLException;


    boolean removeByid(Long id) throws SQLException;

    TopicDto getById(Long id) throws SQLException;
}
