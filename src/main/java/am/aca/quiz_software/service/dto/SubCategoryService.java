package am.aca.quiz_software.service.dto;

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
}