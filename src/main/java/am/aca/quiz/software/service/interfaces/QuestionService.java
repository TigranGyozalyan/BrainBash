package am.aca.quiz.software.service.interfaces;

import am.aca.quiz.software.entity.QuestionEntity;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;

@Service
public interface QuestionService {
    //create
    void addQuestion(String question, String level, int correctAnswerCount, int points, Long topicCentityId) throws SQLException;

    //read
    List<QuestionEntity> getAll() throws SQLException;

    //update
    void update(QuestionEntity updatedQuestion) throws SQLException;

    QuestionEntity getById(Long id) throws SQLException;

    void removeById(Long id) throws SQLException;

    QuestionEntity getQuestionEntityByQuestion(String question) throws SQLException;

}

