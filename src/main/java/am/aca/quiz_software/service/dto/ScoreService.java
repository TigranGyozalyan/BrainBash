package am.aca.quiz_software.service.dto;


import am.aca.quiz_software.entity.ScoreEntity;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;

@Service
public interface ScoreService {
    //create
    boolean addCategory(ScoreEntity score) throws SQLException;

    //read
    List<ScoreEntity> getAll() throws SQLException;

    //update
    boolean update(ScoreEntity score, Long id) throws SQLException;

    //delete
    ScoreEntity remove(ScoreEntity score) throws SQLException;

    ScoreEntity removeById(Long id) throws SQLException;

    ScoreEntity getById(Long id) throws SQLException;
}