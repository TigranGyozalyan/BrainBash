package am.aca.quiz.software.service.intefaces;


import am.aca.quiz.software.entity.TestEntity;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;

@Service
public interface TestService {
    //create
    boolean addTest(TestEntity test) throws SQLException;

    //read
    List<TestEntity> getAll() throws SQLException;

    //update
    boolean update(TestEntity test,Long id) throws SQLException;

    //delete
    TestEntity remove(TestEntity test) throws SQLException;


    boolean removeByid(Long id) throws SQLException;

    TestEntity getByid(Long id) throws SQLException;
}
