package am.aca.quiz.software.service.interfaces;

import am.aca.quiz.software.entity.AnswerEntity;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;

@Service
public interface AnswerService {

    //create
    void addAnswer(String answer, String description, boolean isCorrect, Long questionId) throws SQLException;

    //read
    List<AnswerEntity> getAll() throws SQLException;

    //update
    void update(AnswerEntity answer, Long id) throws SQLException;

    AnswerEntity getById(Long id) throws SQLException;

    void removeById(Long id) throws  SQLException;
}
