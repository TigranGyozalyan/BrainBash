package am.aca.quiz.software.service.interfaces;


import am.aca.quiz.software.entity.ScoreEntity;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;

@Service
public interface ScoreService {

    //read
    List<ScoreEntity> getAll() throws SQLException;

    ScoreEntity getById(Long id) throws SQLException;

    //update
    void update(ScoreEntity score) throws SQLException;

}