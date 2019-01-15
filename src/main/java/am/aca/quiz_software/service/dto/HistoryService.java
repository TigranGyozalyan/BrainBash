package am.aca.quiz_software.service.dto;


import am.aca.quiz_software.entity.HistoryEntity;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;

@Service
public interface HistoryService {
    //create
    void addCategory(HistoryEntity history) throws SQLException;

    //read
    List<HistoryEntity> getAll() throws SQLException;

    //update
    void update(HistoryEntity history) throws SQLException;

    //delete
    void remove(HistoryEntity history) throws SQLException;
}
