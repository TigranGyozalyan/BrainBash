package am.aca.quiz.software.service.interfaces;


import am.aca.quiz.software.entity.QuestionEntity;
import am.aca.quiz.software.entity.TestEntity;
import am.aca.quiz.software.service.dto.QuestionDto;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.List;

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

    List<BigInteger> findTestIdByTopicId(Long id) throws SQLException;
}
