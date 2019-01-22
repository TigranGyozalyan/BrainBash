package am.aca.quiz.software.service.interfaces;


import am.aca.quiz.software.entity.ScoreEntity;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;

@Service
public interface ScoreService {
    //create
    boolean addScore(Long topicId, Long userId) throws SQLException;

    //read
    List<ScoreEntity> getAll() throws SQLException;

    //update
    boolean update(ScoreEntity score, Long id) throws SQLException;

    ScoreEntity getById(Long id) throws SQLException;

    boolean removeById(Long id) throws SQLException;

    int getTestCountByTopic(Long user_id);
}