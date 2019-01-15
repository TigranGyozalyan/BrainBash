package am.aca.quiz_software.service.dto;

import am.aca.quiz_software.entity.AnswerEntity;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;

@Service
public interface AnswerService {
    //create
    void addCategory(AnswerEntity answer) throws SQLException;

    //read
    List<AnswerEntity> getAll() throws SQLException;

    //update
    void update(AnswerEntity answer) throws SQLException;

    //delete
    void remove(AnswerEntity answer) throws SQLException;
}
