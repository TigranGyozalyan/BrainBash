package am.aca.quiz.software.service.intefaces;

import am.aca.quiz.software.entity.SubCategoryEntity;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;

@Service
public interface SubCategoryService {

    //create
    boolean addSubCategory(String typeName, Long id) throws SQLException;

    //read
    List<SubCategoryEntity> getAll() throws SQLException;

    //update
    boolean update(SubCategoryEntity subCategory, Long id) throws SQLException;

    SubCategoryEntity getById(Long id) throws SQLException;

    boolean removeByid(Long id) throws SQLException;
}