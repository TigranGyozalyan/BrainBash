package am.aca.quiz.software.service.interfaces;


import am.aca.quiz.software.entity.QuestionEntity;
import am.aca.quiz.software.entity.TestEntity;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.sql.SQLException;
import java.util.List;
import java.util.Set;

@Service
public interface TestService {
    //create
    void addTest(String testName, String description, long duration, List<QuestionEntity> questionEntities) throws SQLException;

    //read
    List<TestEntity> getAll() throws SQLException;

    //update
    void update(TestEntity test) throws SQLException;


    void removeById(Long id) throws SQLException;

    TestEntity getById(Long id) throws SQLException;

    Set<BigInteger> findTestIdByTopicId(Long id) throws SQLException;
}
