package am.aca.quiz_software.service.dto;

import am.aca.quiz_software.entity.AnswerEntity;
import am.aca.quiz_software.repository.AnswerRepository;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;

@Service
public interface AnswerService {

    //create
    boolean addCategory(AnswerEntity answer) throws SQLException;

    //read
    List<AnswerEntity> getAll() throws SQLException;

    //update
    boolean update(AnswerEntity answer,Long id) throws SQLException;

    //delete
    AnswerEntity remove(AnswerEntity answer) throws SQLException;

    //
}
