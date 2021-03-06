package am.aca.quiz.software.service.implementations;

import am.aca.quiz.software.entity.CategoryEntity;
import am.aca.quiz.software.repository.CategoryRepository;
import am.aca.quiz.software.service.interfaces.CategoryService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.SQLException;
import java.util.List;


@Service
public class CategoryServiceImp implements CategoryService {

    private final CategoryRepository categoryRepository;

    public CategoryServiceImp(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public void addCategory(String type) throws SQLException {
        if (categoryRepository.findCategoryEntityByType(type) == null) {
            CategoryEntity categoryEntity = new CategoryEntity(type);
            addCategory(categoryEntity);
        } else {
            throw new SQLException("Category is existing");
        }

    }

    @Override
    public void addCategory(CategoryEntity categoryEntity) throws SQLException {
        categoryRepository.save(categoryEntity);
    }

    public List<CategoryEntity> getAll() throws SQLException {
        List<CategoryEntity> categoryList = categoryRepository.findAll();
        if (categoryList != null) {
            return categoryList;
        } else {
            throw new SQLException("Category table is empty");
        }
    }

    @Transactional
    @Override
    public void removeById(Long id) throws SQLException {
        if (categoryRepository.findById(id).isPresent()) {
            categoryRepository.deleteById(id);
        } else {
            throw new SQLException("category not found");
        }
    }

    @Override
    public CategoryEntity getById(Long id) throws SQLException {
        if (categoryRepository.findById(id).isPresent()) {
            return categoryRepository.findById(id).get();
        } else {
            throw new SQLException("category not found");
        }
    }

    public CategoryEntity getByType(String type) throws SQLException {
        CategoryEntity targetEntity = categoryRepository.findCategoryEntityByType(type);
        if (targetEntity != null) {
            return targetEntity;
        } else {
            throw new SQLException("category not found");
        }
    }

    @Transactional
    @Override
    public void update(CategoryEntity categoryEntity) {
        categoryRepository.save(categoryEntity);
    }
}
