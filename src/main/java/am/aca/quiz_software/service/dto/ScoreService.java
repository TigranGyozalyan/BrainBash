package am.aca.quiz_software.service.dto;

<<<<<<< HEAD
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
=======
import org.springframework.stereotype.Service;

@Service
public interface ScoreService {
    //TODO methods and constructor
>>>>>>> dfa5e88c635184eb52a4efb6a23550b85acf9daa
}
