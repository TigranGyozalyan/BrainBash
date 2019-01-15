package am.aca.quiz_software.service.dto;

<<<<<<< HEAD
import am.aca.quiz_software.entity.TestEntity;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;

@Service
public interface TestService {
    //create
    void addCategory(TestEntity test) throws SQLException;

    //read
    List<TestEntity> getAll() throws SQLException;

    //update
    void update(TestEntity test) throws SQLException;

    //delete
    void remove(TestEntity test) throws SQLException;
=======
import org.springframework.stereotype.Service;

@Service
public interface TestService {
    //Todo methods and constructor
>>>>>>> dfa5e88c635184eb52a4efb6a23550b85acf9daa
}
