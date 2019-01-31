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
    public void addTest(String testName, String description, long duration, List<QuestionDto> questionDtos) throws SQLException {

        List<QuestionEntity> questionEntities= questionDtos
                .stream()
                .map(i-> {
                    try {
                        return questionServiceImp.getById(i.getId());
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                    return null;
                })
                .collect(Collectors.toList());


        TestEntity testEntity=testRepository.findTestEntitiesByTest(testName);

        if(testEntity==null) {
            testEntity = new TestEntity(testName, duration, description);
            testEntity.setQuestionEntities(questionEntities);

        }else {
            testRepository.findTestEntitiesByTest(testName);
            testEntity.setQuestionEntities(questionEntities);
        }
        testRepository.save(testEntity);
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
        testRepository.deleteById(id);
    }

    @Override
    public TestEntity getById(Long id) throws SQLException {
        return testRepository.findById(id).get();
    }

}
