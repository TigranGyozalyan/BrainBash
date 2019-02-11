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
import org.springframework.transaction.annotation.Transactional;

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
    public void addHistory(HistoryEntity historyEntity) {
        historyRepository.save(historyEntity);
    }

    public List<HistoryEntity> getAll() throws SQLException {
        return historyRepository.findAll();
    }

    @Transactional
    @Override
    public void update(HistoryEntity history, Long id) throws SQLException {
        historyRepository.save(history);
    }


    @Transactional
    @Override
    public void removeById(Long id) throws SQLException {
        historyRepository.deleteById(id);
    }

    @Override
    public HistoryEntity getById(Long id) throws SQLException {
        Optional<HistoryEntity> historyEntity = historyRepository.findById(id);
        if (!historyEntity.isPresent()) {
            throw new SQLException("Entity Not Found");
        }
        return historyEntity.get();
    }

    public List<HistoryEntity> findAllByStatus(Long userId, Enum status) {
        return historyRepository.findAllByUserEntityIdAndStatus(userId, status);
    }

    public List<HistoryEntity> findAllByUserId(Long userId) {
        return historyRepository.findAllByUserEntityId(userId);
    }

    public List<HistoryEntity> findAllByStatus(Enum status) {
        return historyRepository.findAllByStatus(status);
    }

    public List<HistoryEntity> findByEmail(String email) {
        return historyRepository.findAllByUserEntityEmail(email);
    }

    public HistoryEntity findHistoryByUserIdAndTetId(Long userId, Long testId, String status) {
        return historyRepository.getHistoryEntityByUserIdAndTestId(userId, testId, status);
    }

    public HistoryEntity findHistoryBySUerIdAndStatus(Long userId, String status) {
        return historyRepository.getHistoryByUserIdAndStatus(userId, status);
    }

}
