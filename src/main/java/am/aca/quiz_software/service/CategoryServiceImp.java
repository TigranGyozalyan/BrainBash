package am.aca.quiz_software.service;

import am.aca.quiz_software.entity.CategoryEntity;
import am.aca.quiz_software.repository.CategoryRepository;
import am.aca.quiz_software.repository.SubCategoryRepository;
import am.aca.quiz_software.service.dto.CategoryService;
import am.aca.quiz_software.service.dto.SubCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;


@Service
public class CategoryServiceImp implements CategoryService {

    private final CategoryRepository categoryRepository;
    private final SubCategoryRepository subCategoryRepository;

    @Autowired
    public CategoryServiceImp(CategoryRepository categoryRepository, SubCategoryRepository subCategoryRepository) {
        this.categoryRepository = categoryRepository;
        this.subCategoryRepository = subCategoryRepository;
    }

    public void addCategory(CategoryEntity category) throws SQLException {
        categoryRepository.saveAndFlush(category);
        return true;
    }

    public List<CategoryEntity> getAll() throws SQLException {
        return categoryRepository.findAll();
    }

    public void update(CategoryEntity category) throws SQLException {
        //toDO
    }

    public CategoryEntity remove(CategoryEntity category) throws SQLException {
        categoryRepository.delete(category);
        return category;
    }
}
