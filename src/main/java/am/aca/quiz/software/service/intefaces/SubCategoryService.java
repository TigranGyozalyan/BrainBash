package am.aca.quiz.software.service.intefaces;

import am.aca.quiz.software.entity.SubCategoryEntity;
import am.aca.quiz.software.service.dto.SubCategoryDto;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;

@Service
public interface SubCategoryService {

    //create
    boolean addSubCategory(SubCategoryEntity subCategory) throws SQLException;

    //read
    List<SubCategoryDto> getAll() throws SQLException;

    //update
    boolean update(SubCategoryEntity subCategory,Long id) throws SQLException;

    //delete
    SubCategoryDto remove(SubCategoryEntity subCategory) throws SQLException;

    SubCategoryDto getById(Long id) throws SQLException;

    boolean removeByid(Long id) throws SQLException;
}