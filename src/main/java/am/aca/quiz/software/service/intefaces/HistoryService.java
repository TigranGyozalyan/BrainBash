package am.aca.quiz.software.service.intefaces;


import am.aca.quiz.software.entity.HistoryEntity;
import am.aca.quiz.software.service.dto.HistoryDto;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;

@Service
public interface HistoryService {
    //create
    boolean addHistory(HistoryEntity history) throws SQLException;

    //read
    List<HistoryDto> getAll() throws SQLException;

    //update
    boolean update(HistoryEntity history, Long id) throws SQLException;

    //delete
    HistoryDto remove(HistoryEntity history) throws SQLException;

    boolean removeById(Long id) throws SQLException;

    HistoryDto getByid(Long id) throws SQLException;
}