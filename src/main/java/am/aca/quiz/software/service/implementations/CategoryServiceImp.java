package am.aca.quiz.software.service.implementations;

import am.aca.quiz.software.entity.CategoryEntity;
import am.aca.quiz.software.repository.CategoryRepository;
import am.aca.quiz.software.service.intefaces.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;


@Service
public class CategoryServiceImp implements CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;
    @Autowired
    private SubCategoryServiceImp subCategoryServiceImp;


    public boolean addCategory(CategoryEntity category) throws SQLException {
        categoryRepository.saveAndFlush(category);
        return true;
    }

    public List<CategoryEntity> getAll() throws SQLException {
        return categoryRepository.findAll();
    }

    @Override
    public boolean update(CategoryEntity category, Long id) throws SQLException {

        CategoryEntity updated_category = getById(id);
        if (updated_category != null) {
            category.setId(id);
            return addCategory(category);
        }
        return false;
    }

    public CategoryEntity remove(CategoryEntity category) throws SQLException {
        categoryRepository.delete(category);
        return category;
    }

    @Override
    public boolean removeById(Long id) throws SQLException {
        CategoryEntity deleted_category = getById(id);
        categoryRepository.delete(deleted_category);
        return true;
    }

    @Override
    public CategoryEntity getById(Long id) throws SQLException {
        Optional<CategoryEntity> categoryEntity = categoryRepository.findById(id);

        if (!categoryEntity.isPresent()) {
            throw new SQLException("Entity Not Found");
        }
        return categoryEntity.get();
    }
}
