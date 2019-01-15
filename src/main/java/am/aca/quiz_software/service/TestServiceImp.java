package am.aca.quiz_software.service;

import am.aca.quiz_software.entity.TestEntity;
import am.aca.quiz_software.repository.HistoryRepository;
import am.aca.quiz_software.repository.QuestionRepository;
import am.aca.quiz_software.repository.TestRepository;
import am.aca.quiz_software.service.dto.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;


@Service
public class TestServiceImp implements TestService {

    private final TestRepository testRepository;
    private final QuestionRepository questionRepository;
    private final HistoryRepository historyRepository;

    @Autowired
    public TestServiceImp(TestRepository testRepository, QuestionRepository questionRepository, HistoryRepository historyRepository) {
        this.testRepository = testRepository;
        this.questionRepository = questionRepository;
        this.historyRepository = historyRepository;
    }

    public void addCategory(TestEntity test) throws SQLException {

    }

    public List<TestEntity> getAll() throws SQLException {
        return null;
    }

    public void update(TestEntity test) throws SQLException {

    }

    public void remove(TestEntity test) throws SQLException {

    }
}
