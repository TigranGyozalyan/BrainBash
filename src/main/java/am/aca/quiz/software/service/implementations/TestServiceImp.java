package am.aca.quiz.software.service.implementations;

import am.aca.quiz.software.repository.TestRepository;
import am.aca.quiz.software.entity.TestEntity;
import am.aca.quiz.software.service.dto.TestDto;
import am.aca.quiz.software.service.intefaces.TestService;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;


@Service
public class TestServiceImp implements TestService {


    private final TestRepository testRepository;

    public TestServiceImp(TestRepository testRepository) {
        this.testRepository = testRepository;
    }


    @Override
    public boolean addTest(String testName, String description, LocalDateTime duration) throws SQLException {
        return false;
    }

    @Override
    public List<TestEntity> getAll() throws SQLException {
        return null;
    }

    @Override
    public boolean update(TestEntity test, Long id) throws SQLException {
        return false;
    }

    @Override
    public TestEntity remove(TestEntity test) throws SQLException {
        return null;
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
