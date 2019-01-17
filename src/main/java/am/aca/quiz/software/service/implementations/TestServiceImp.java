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

    public boolean addTest(TestEntity test) throws SQLException {
        testRepository.saveAndFlush(test);
        return true;
    }

    public List<TestEntity> getAll() throws SQLException {
        return testRepository.findAll();
    }

    @Override
    public boolean update(TestEntity test, Long id) throws SQLException {
        TestEntity updated_test = getByid(id);
        if (updated_test != null) {
            test.setId(id);
            return addTest(test);
        }
        return false;
    }


    public TestEntity remove(TestEntity test) throws SQLException {
        testRepository.delete(test);
        return test;
    }

    @Override
    public boolean removeByid(Long id) throws SQLException {
        TestEntity deleted_test = getByid(id);
        testRepository.delete(deleted_test);
        return true;
    }

    @Override
    public TestEntity getByid(Long id) throws SQLException {
        Optional<TestEntity> testEntity = testRepository.findById(id);
        if (!testEntity.isPresent()) {
            throw new SQLException("Entity not found");
        }
        return testEntity.get();
    }
}
