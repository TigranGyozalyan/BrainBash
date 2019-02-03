package am.aca.quiz.software.service.interfaces;

import am.aca.quiz.software.entity.CategoryEntity;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;

@Service
public interface CategoryService{
    //create
    void addCategory(String type) throws SQLException;

    void addCategory(CategoryEntity categoryEntity) throws SQLException;

    //read
    List<CategoryEntity> getAll() throws SQLException;

    CategoryEntity getById(Long id) throws SQLException;

    //update
    void update(CategoryEntity category) throws SQLException;

    //delete

    void removeById(Long id) throws SQLException;

    void remove(CategoryEntity categoryEntity) throws SQLException;
}

