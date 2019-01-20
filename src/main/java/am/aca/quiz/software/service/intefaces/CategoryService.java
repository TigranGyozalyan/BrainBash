package am.aca.quiz.software.service.intefaces;

import am.aca.quiz.software.entity.CategoryEntity;
import am.aca.quiz.software.service.dto.CategoryDto;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;

@Service
public interface CategoryService {
    //create
    boolean addCategory(String type) throws SQLException;

    //read
    List<CategoryDto> getAll() throws SQLException;

    //update
    boolean update(CategoryEntity category,Long id) throws SQLException;

    //delete
    CategoryDto remove(CategoryEntity category) throws SQLException;

    boolean removeById(Long id) throws SQLException;

    CategoryDto getById(Long id) throws SQLException;
}

