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

    private final HistoryRepository historyRepository;
    private final UserRepository userRepository;
    private final TestRepository testRepository;

    @Autowired
    public HistoryServiceImp(HistoryRepository historyRepository, UserRepository userRepository, TestRepository testRepository) {
        this.historyRepository = historyRepository;
        this.userRepository = userRepository;
        this.testRepository = testRepository;
    }

    public void addCategory(HistoryEntity history) throws SQLException {

    }

    public List<HistoryEntity> getAll() throws SQLException {
        return null;
    }

    public void update(HistoryEntity history) throws SQLException {

    }

    public void remove(HistoryEntity history) throws SQLException {

    }
}
