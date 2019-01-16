package am.aca.quiz_software.service;

import am.aca.quiz_software.entity.HistoryEntity;
import am.aca.quiz_software.repository.HistoryRepository;
import am.aca.quiz_software.repository.TestRepository;
import am.aca.quiz_software.repository.UserRepository;
import am.aca.quiz_software.service.dto.HistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;


@Service
public class HistoryServiceImp implements HistoryService {

    @Autowired
    private  HistoryRepository historyRepository;


    public boolean addCategory(HistoryEntity history) throws SQLException {
        return false;
    }

    public List<HistoryEntity> getAll() throws SQLException {
        return null;
    }

    public boolean update(HistoryEntity history) throws SQLException {
        return false;
    }

    public HistoryEntity remove(HistoryEntity history) throws SQLException {
        return null;
    }
}
