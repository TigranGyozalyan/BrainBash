package am.aca.quiz.software.service.intefaces;

import am.aca.quiz.software.entity.QuestionEntity;
import am.aca.quiz.software.service.dto.QuestionDto;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;

@Service
public interface QuestionService {
    //create
    boolean addQuestion(String question,Enum level,int correctAnswerCount,int points,Long topicCentityId) throws SQLException;

    //read
    List<QuestionEntity> getAll() throws SQLException;

    //update
    boolean update(QuestionEntity question, Long id) throws SQLException;

    QuestionEntity getById(Long id) throws SQLException;

    boolean removeByid(Long id) throws SQLException;

}

