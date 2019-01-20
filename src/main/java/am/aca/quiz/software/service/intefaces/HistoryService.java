package am.aca.quiz.software.service.intefaces;


import am.aca.quiz.software.entity.HistoryEntity;
import am.aca.quiz.software.service.dto.HistoryDto;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.List;

@Service
public interface HistoryService {
    //create
    boolean addHistory(Long userId, Long testId, double score, Enum status,LocalDateTime startTime,LocalDateTime endTime) throws SQLException;

    //read
    List<HistoryEntity> getAll() throws SQLException;

    //update
    boolean update(HistoryEntity history, Long id) throws SQLException;

    boolean removeById(Long id) throws SQLException;

    HistoryEntity getByid(Long id) throws SQLException;
}