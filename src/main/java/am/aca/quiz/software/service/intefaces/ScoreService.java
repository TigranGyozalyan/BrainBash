package am.aca.quiz.software.service.intefaces;


import am.aca.quiz.software.entity.ScoreEntity;
import am.aca.quiz.software.service.dto.ScoreDto;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;

@Service
public interface ScoreService {
    //create
    boolean addScore(ScoreEntity score) throws SQLException;

    //read
    List<ScoreDto> getAll() throws SQLException;

    //update
    boolean update(ScoreEntity score, Long id) throws SQLException;

    //delete
    ScoreDto remove(ScoreEntity score) throws SQLException;

    ScoreDto getByid(Long id) throws SQLException;

    boolean removeByid(Long id) throws SQLException;
}