package am.aca.quiz.software.service.implementations;

import am.aca.quiz.software.repository.HistoryRepository;
import am.aca.quiz.software.entity.HistoryEntity;
import am.aca.quiz.software.service.dto.HistoryDto;
import am.aca.quiz.software.service.intefaces.HistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;


@Service
public class HistoryServiceImp implements HistoryService {

    @Autowired
    private HistoryRepository historyRepository;
    @Autowired
    private TestServiceImp testServiceImp;
    @Autowired
    private UserServiceImp userServiceImp;

    public boolean addHistory(HistoryEntity history) throws SQLException {
        historyRepository.saveAndFlush(history);
        return true;
    }

    public List<HistoryDto> getAll() throws SQLException {
        return HistoryDto.mapEntitesToDto(historyRepository.findAll());
    }

    @Override
    public boolean update(HistoryEntity history, Long id) throws SQLException {
        HistoryEntity updated_history = historyRepository.findById(id).get();
        if (updated_history != null) {
            history.setId(id);
            return addHistory(history);
        }
        return false;
    }


    public HistoryDto remove(HistoryEntity history) throws SQLException {
        historyRepository.delete(history);
        return HistoryDto.mapEntityToDto(history);
    }

    @Override
    public boolean removeById(Long id) throws SQLException {
        HistoryEntity deleted_history = historyRepository.findById(id).get();
        historyRepository.delete(deleted_history);
        return true;
    }

    @Override
    public HistoryDto getByid(Long id) throws SQLException {
        Optional<HistoryEntity> historyEntity = historyRepository.findById(id);
        if (!historyEntity.isPresent()) {
            throw new SQLException("Entity Not Found");
        }
        return HistoryDto.mapEntityToDto(historyEntity.get());
    }
}
