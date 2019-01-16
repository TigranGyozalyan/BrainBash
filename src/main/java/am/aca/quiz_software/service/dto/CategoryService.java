package am.aca.quiz_software.service.dto;

import am.aca.quiz_software.entity.CategoryEntity;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;

@Service
public interface CategoryService {
    //create
    boolean addCategory(CategoryEntity category) throws SQLException;

    //read
    List<CategoryEntity> getAll() throws SQLException;

    //update
    boolean update(CategoryEntity category) throws SQLException;

    //delete
    CategoryEntity remove(CategoryEntity category) throws SQLException;
}

