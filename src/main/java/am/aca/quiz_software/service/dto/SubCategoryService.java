package am.aca.quiz_software.service.dto;

import am.aca.quiz_software.entity.SubCategoryEntity;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;

@Service
public interface SubCategoryService {

    //create
    boolean addCategory(SubCategoryEntity subCategory) throws SQLException;

    //read
    List<SubCategoryEntity> getAll() throws SQLException;

    //update
    boolean update(SubCategoryEntity subCategory) throws SQLException;

    //delete
    SubCategoryEntity remove(SubCategoryEntity subCategory) throws SQLException;
}