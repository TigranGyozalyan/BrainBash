package am.aca.quiz.software.service.interfaces;

import am.aca.quiz.software.entity.SubCategoryEntity;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;

@Service
public interface SubCategoryService {

    //create
    void addSubCategory(String typeName, Long categoryId) throws SQLException;

    //read
    List<SubCategoryEntity> getAll() throws SQLException;

    SubCategoryEntity getById(Long id) throws SQLException;

    //update
    void update(SubCategoryEntity subCategory, Long id) throws SQLException;

    //delete
    void removeById(Long id) throws SQLException;

    void remove(SubCategoryEntity subCategoryEntity) throws SQLException;
}