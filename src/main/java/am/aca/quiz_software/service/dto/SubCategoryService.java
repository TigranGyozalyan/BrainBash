package am.aca.quiz_software.service.dto;

<<<<<<< HEAD
import am.aca.quiz_software.entity.SubCategoryEntity;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;

@Service
public interface SubCategoryService {

    //create
    void addCategory(SubCategoryEntity subcCtegory) throws SQLException;

    //read
    List<SubCategoryEntity> getAll() throws SQLException;

    //update
    void update(SubCategoryEntity subcCtegory) throws SQLException;

    //delete
    void remove(SubCategoryEntity subcCtegory) throws SQLException;
=======
import org.springframework.stereotype.Service;

@Service
public interface SubCategoryService {
    //TODO methods and constructor
>>>>>>> dfa5e88c635184eb52a4efb6a23550b85acf9daa
}
