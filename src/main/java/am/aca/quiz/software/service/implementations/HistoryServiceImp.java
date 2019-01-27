package am.aca.quiz.software.service.implementations;

import am.aca.quiz.software.entity.HistoryEntity;
import am.aca.quiz.software.entity.TestEntity;
import am.aca.quiz.software.entity.UserEntity;
import am.aca.quiz.software.entity.enums.Status;
import am.aca.quiz.software.repository.HistoryRepository;
import am.aca.quiz.software.service.interfaces.HistoryService;
import am.aca.quiz.software.service.interfaces.TestService;
import am.aca.quiz.software.service.interfaces.UserService;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;


@Service
public class HistoryServiceImp implements HistoryService {

    private final HistoryRepository historyRepository;
    private final UserService userService;
    private final TestService testService;

    public HistoryServiceImp(HistoryRepository historyRepository, UserService userService, TestService testService) {
        this.historyRepository = historyRepository;
        this.userService = userService;
        this.testService = testService;
    }


    @Override
    public boolean addHistory(Long userId, Long testId, double score, String status, LocalDateTime startTime, LocalDateTime endTime) throws SQLException {
        UserEntity userEntity = userService.getById(userId);
        TestEntity testEntity = testService.getById(testId);


        HistoryEntity historyEntity = new HistoryEntity(startTime, status, score, userEntity, testEntity);

        userEntity.getHistoryList().add(historyEntity);
        testEntity.getHistoryEntities().add(historyEntity);

        historyRepository.saveAndFlush(historyEntity);
        return true;


    }

    public List<HistoryEntity> getAll() throws SQLException {
        return historyRepository.findAll();
    }

    @Override
    public boolean update(HistoryEntity history, Long id) throws SQLException {
        HistoryEntity updated_history = historyRepository.findById(id).get();
        if (updated_history != null) {
            history.setId(id);
            historyRepository.saveAndFlush(history);
            return true;
        }
        return false;
    }


    @Override
    public boolean removeById(Long id) throws SQLException {
        HistoryEntity deleted_history = historyRepository.findById(id).get();
        historyRepository.delete(deleted_history);
        return true;
    }

    @Override
    public HistoryEntity getById(Long id) throws SQLException {
        Optional<HistoryEntity> historyEntity = historyRepository.findById(id);
        if (!historyEntity.isPresent()) {
            throw new SQLException("Entity Not Found");
        }
        return historyEntity.get();
    }
}
