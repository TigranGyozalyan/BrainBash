package am.aca.quiz.software.service.interfaces;


import am.aca.quiz.software.entity.HistoryEntity;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.List;

@Service
public interface HistoryService {
    //create
    boolean addHistory(Long userId, Long testId, double score, String status, LocalDateTime startTime, LocalDateTime endTime) throws SQLException;

    //read
    List<HistoryEntity> getAll() throws SQLException;

    //update
    boolean update(HistoryEntity history, Long id) throws SQLException;

    boolean removeById(Long id) throws SQLException;

    HistoryEntity getById(Long id) throws SQLException;
}