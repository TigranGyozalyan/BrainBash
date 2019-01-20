package am.aca.quiz.software.service.intefaces;


import am.aca.quiz.software.entity.TestEntity;
import am.aca.quiz.software.service.dto.TestDto;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.List;

@Service
public interface TestService {
    //create
    boolean addTest(String testName, String description, LocalDateTime duration) throws SQLException;

    //read
    List<TestEntity> getAll() throws SQLException;

    //update
    boolean update(TestEntity test,Long id) throws SQLException;


    boolean removeByid(Long id) throws SQLException;

    TestEntity getByid(Long id) throws SQLException;
}
