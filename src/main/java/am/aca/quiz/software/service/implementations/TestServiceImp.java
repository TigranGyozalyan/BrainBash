package am.aca.quiz.software.service.implementations;

import am.aca.quiz.software.entity.QuestionEntity;
import am.aca.quiz.software.entity.TestEntity;
import am.aca.quiz.software.repository.TestRepository;
import am.aca.quiz.software.service.dto.QuestionDto;
import am.aca.quiz.software.service.interfaces.TestService;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PatchMapping;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


@Service
public class TestServiceImp implements TestService {

    private final TestRepository testRepository;
    private final QuestionServiceImp questionServiceImp;

    public TestServiceImp(TestRepository testRepository, QuestionServiceImp questionServiceImp) {
        this.testRepository = testRepository;
        this.questionServiceImp = questionServiceImp;
    }


    @Override
    public void addTest(String testName, String description, long duration, List<QuestionEntity> questionEntities) throws SQLException{
        TestEntity testEntity = new TestEntity(testName,description,duration,questionEntities);
        testRepository.saveAndFlush(testEntity);
    }

    @Override
    public List<TestEntity> getAll() throws SQLException {
        return testRepository.findAll();
    }

    //TODO
    @Override
    public void update(TestEntity test, Long id) throws SQLException {
        TestEntity testEntity = testRepository.findById(id).get();
        if (testEntity != null) {
            test.setId(id);
            testRepository.saveAndFlush(test);
        }

    }


    @Override
    public void removeById(Long id) throws SQLException {
        if(testRepository.findById(id)!=null) {
            testRepository.deleteById(id);
        }
    }

    @Override
    public TestEntity getById(Long id) throws SQLException {
        return testRepository.findById(id).get();
    }

}
