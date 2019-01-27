package am.aca.quiz.software.service.implementations;

import am.aca.quiz.software.entity.CategoryEntity;
import am.aca.quiz.software.repository.CategoryRepository;
import am.aca.quiz.software.service.interfaces.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;


@Service
public class CategoryServiceImp implements CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;


    public void addCategory(String type) throws SQLException {
        CategoryEntity categoryEntity = new CategoryEntity(type);
        addCategory(categoryEntity);
    }

    @Override
    public void addCategory(CategoryEntity categoryEntity) throws SQLException {
        if (categoryEntity != null)
            categoryRepository.saveAndFlush(categoryEntity);
    }

    public List<CategoryEntity> getAll() throws SQLException {
        return categoryRepository.findAll();
    }

    @Override
    public void update(CategoryEntity updatedCategory, Long targetId) throws SQLException {
        updatedCategory.setId(targetId);
        addCategory(updatedCategory);
    }

    @Override
    public void removeById(Long id) throws SQLException {
        categoryRepository.removeById(id);
    }

    @Override
    public void remove(CategoryEntity categoryEntity) throws SQLException {
        if (categoryEntity != null)
            categoryRepository.delete(categoryEntity);
    }

    @Override
    public CategoryEntity getById(Long id) throws SQLException {
       return categoryRepository.findCategoryEntityById(id);
    }

    public CategoryEntity getByType(String type) throws  SQLException {
        return categoryRepository.findCategoryEntityByType(type);
    }

}
