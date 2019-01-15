package am.aca.quiz_software.service.dto;

import am.aca.quiz_software.entity.ScoreEntity;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;

@Service
public interface ScoreService {
    //create
    void addCategory(ScoreEntity score) throws SQLException;

    //read
    List<ScoreEntity> getAll() throws SQLException;

    //update
    void update(ScoreEntity score) throws SQLException;

    //delete
    void remove(ScoreEntity score) throws SQLException;
}
