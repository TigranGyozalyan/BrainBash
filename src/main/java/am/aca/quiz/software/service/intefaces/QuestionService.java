package am.aca.quiz.software.service.intefaces;

import am.aca.quiz.software.entity.QuestionEntity;
import am.aca.quiz.software.service.dto.QuestionDto;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;

@Service
public interface QuestionService {
    //create
    boolean addQuestion(QuestionEntity question) throws SQLException;

    //read
    List<QuestionDto> getAll() throws SQLException;

    //update
    boolean update(QuestionEntity question, Long id) throws SQLException;

    //delete
    QuestionDto remove(QuestionEntity question) throws SQLException;

    QuestionDto getById(Long id) throws SQLException;

    boolean removeByid(Long id) throws SQLException;

}

