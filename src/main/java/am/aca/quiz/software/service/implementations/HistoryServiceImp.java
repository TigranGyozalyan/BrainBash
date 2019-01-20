package am.aca.quiz.software.service.implementations;

import am.aca.quiz.software.repository.HistoryRepository;
import am.aca.quiz.software.entity.HistoryEntity;
import am.aca.quiz.software.service.dto.HistoryDto;
import am.aca.quiz.software.service.intefaces.HistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;


@Service
public class HistoryServiceImp implements HistoryService {

    private final HistoryRepository historyRepository;

    public HistoryServiceImp(HistoryRepository historyRepository) {
        this.historyRepository = historyRepository;
    }


    @Override
    public boolean addHistory(Long userId, Long testId, double score, Enum status, LocalDateTime startTime, LocalDateTime endTime) throws SQLException {
        return false;
    }

    @Override
    public List<HistoryEntity> getAll() throws SQLException {
        return null;
    }

    @Override
    public boolean update(HistoryEntity history, Long id) throws SQLException {
        return false;
    }

    @Override
    public HistoryEntity remove(HistoryEntity history) throws SQLException {
        return null;
    }

    @Override
    public boolean removeById(Long id) throws SQLException {
        return false;
    }

    @Override
    public HistoryEntity getByid(Long id) throws SQLException {
        return null;
    }


}
