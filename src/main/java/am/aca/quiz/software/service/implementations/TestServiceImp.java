package am.aca.quiz.software.service.implementations;

import am.aca.quiz.software.repository.TestRepository;
import am.aca.quiz.software.entity.TestEntity;
import am.aca.quiz.software.service.intefaces.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;


@Service
public class TestServiceImp implements TestService {

    @Autowired
    private TestRepository testRepository;
    @Autowired
    private HistoryServiceImp historyServiceImp;
    @Autowired
    private QuestionServiceImp questionServiceImp;

    public boolean addCategory(TestEntity test) throws SQLException {
        testRepository.saveAndFlush(test);
        return true;
    }

    public List<TestEntity> getAll() throws SQLException {
        return testRepository.findAll();
    }

    @Override
    public boolean update(TestEntity test, Long id) throws SQLException {
        Optional<TestEntity> historyEntity=testRepository.findById(id);
        if(!historyEntity.isPresent()){
            throw new SQLException("Argument Not Found ");
        }
        test.setId(id);
        testRepository.saveAndFlush(test);
        return true;
    }


    public TestEntity remove(TestEntity test) throws SQLException {
        testRepository.delete(test);
        return test;
    }

    @Override
    public boolean removeByid(Long id) throws SQLException {
        return false;
    }

    @Override
    public TestEntity getByid(Long id) throws SQLException {
        return null;
    }
}
