package am.aca.quiz_software.service.dto;

<<<<<<< HEAD
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
=======
import org.springframework.stereotype.Service;

@Service
public interface HistoryService {
    //TODO methods and constructor
>>>>>>> dfa5e88c635184eb52a4efb6a23550b85acf9daa
}
