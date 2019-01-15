package am.aca.quiz_software.service.dto;

import am.aca.quiz_software.entity.AnswerEntity;
import am.aca.quiz_software.entity.QuestionEntity;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;

@Service
public interface QuestionService {
    //create
    void addCategory(QuestionEntity question) throws SQLException;

    //read
    List<QuestionEntity> getAll() throws SQLException;

    //update
    void update(QuestionEntity question) throws SQLException;

    //delete
    void remove(QuestionEntity question) throws SQLException;
}
