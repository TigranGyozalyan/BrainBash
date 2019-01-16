package am.aca.quiz_software.service;

import am.aca.quiz_software.entity.CategoryEntity;
import am.aca.quiz_software.repository.CategoryRepository;
import am.aca.quiz_software.service.dto.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;


@Service
public class CategoryServiceImp implements CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;


    public boolean addCategory(CategoryEntity category) throws SQLException {
        categoryRepository.saveAndFlush(category);
        return true;
    }

    public List<CategoryEntity> getAll() throws SQLException {
        return categoryRepository.findAll();
    }

    public boolean update(CategoryEntity category) throws SQLException {
        //toDO
        return false;
    }

    public CategoryEntity remove(CategoryEntity category) throws SQLException {
        categoryRepository.delete(category);
        return category;
    }
}
