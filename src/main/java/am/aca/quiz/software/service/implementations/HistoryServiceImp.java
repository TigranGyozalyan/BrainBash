package am.aca.quiz.software.service.implementations;

import am.aca.quiz.software.repository.HistoryRepository;
import am.aca.quiz.software.entity.HistoryEntity;
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

    public boolean addCategory(HistoryEntity history) throws SQLException {
        historyRepository.saveAndFlush(history);
        return true;
    }

    public List<HistoryEntity> getAll() throws SQLException {
        return historyRepository.findAll();
    }

    @Override
    public boolean update(HistoryEntity history, Long id) throws SQLException {
        Optional<HistoryEntity> historyEntity=historyRepository.findById(id);
        if(!historyEntity.isPresent()){
            throw new SQLException("Argument Not Found ");
        }
        history.setId(id);
        historyRepository.saveAndFlush(history);
        return true;

    }


    public HistoryEntity remove(HistoryEntity history) throws SQLException {
        historyRepository.delete(history);
        return history;
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
