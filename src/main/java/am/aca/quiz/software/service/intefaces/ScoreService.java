package am.aca.quiz.software.service.intefaces;


import am.aca.quiz.software.entity.ScoreEntity;
import am.aca.quiz.software.service.dto.ScoreDto;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;

@Service
public interface ScoreService {
    //create
    boolean addScore(double scoreValue,int count,Long topicId,Long userId) throws SQLException;

    //read
    List<ScoreEntity> getAll() throws SQLException;

    //update
    boolean update(ScoreEntity score, Long id) throws SQLException;

    ScoreEntity getByid(Long id) throws SQLException;

    boolean removeByid(Long id) throws SQLException;
}