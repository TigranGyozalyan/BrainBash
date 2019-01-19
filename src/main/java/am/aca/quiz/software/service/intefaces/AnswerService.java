package am.aca.quiz.software.service.intefaces;

import am.aca.quiz.software.entity.AnswerEntity;
import am.aca.quiz.software.service.dto.AnswerDto;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;

@Service
public interface AnswerService {

    //create
    boolean addAnswer(AnswerEntity answer) throws SQLException;

    //read
    List<AnswerDto> getAll() throws SQLException;

    //update
    boolean update(AnswerEntity answer,Long id) throws SQLException;

    //delete

    AnswerDto remove(AnswerEntity answer, Long id) throws SQLException;

    AnswerDto getById(Long id) throws SQLException;

    boolean removeById(Long id) throws  SQLException;
}
