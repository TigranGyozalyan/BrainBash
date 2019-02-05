package am.aca.quiz.software.service.interfaces;


import am.aca.quiz.software.entity.HistoryEntity;
import am.aca.quiz.software.entity.enums.Status;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.List;

@Service
public interface HistoryService {
    //create
    void addHistory(Long userId, Long testId, double score, Status status, LocalDateTime startTime, LocalDateTime endTime) throws SQLException;

    //read
    List<HistoryEntity> getAll() throws SQLException;

    //update
    void update(HistoryEntity history, Long id) throws SQLException;

    void removeById(Long id) throws SQLException;

    HistoryEntity getById(Long id) throws SQLException;
}