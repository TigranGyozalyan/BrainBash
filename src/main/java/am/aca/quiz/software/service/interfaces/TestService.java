package am.aca.quiz.software.service.interfaces;


import am.aca.quiz.software.entity.TestEntity;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.List;

@Service
public interface TestService {
    //create
    boolean addTest(String testName, String description, long duration) throws SQLException;

    //read
    List<TestEntity> getAll() throws SQLException;

    //update
    boolean update(TestEntity test, Long id) throws SQLException;


    boolean removeById(Long id) throws SQLException;

    TestEntity getById(Long id) throws SQLException;
}
