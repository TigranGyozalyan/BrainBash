package am.aca.quiz.software.service.implementations;

import am.aca.quiz.software.entity.CategoryEntity;
import am.aca.quiz.software.entity.SubCategoryEntity;
import am.aca.quiz.software.repository.SubCategoryRepository;
import am.aca.quiz.software.service.interfaces.SubCategoryService;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;


@Service
public class SubCategoryServiceImp implements SubCategoryService {

    private final SubCategoryRepository subCategoryRepository;
    private final CategoryServiceImp categoryServiceImp;

    public SubCategoryServiceImp(SubCategoryRepository subCategoryRepository, CategoryServiceImp categoryServiceImp) {
        this.subCategoryRepository = subCategoryRepository;
        this.categoryServiceImp = categoryServiceImp;
    }


    public CategoryServiceImp getCategoryServiceImp() {
        return categoryServiceImp;
    }

    public void addSubCategory(String typename, Long categoryId) throws SQLException {
        CategoryEntity categoryEntity = categoryServiceImp.getById(categoryId);
        if (categoryEntity != null) {
            SubCategoryEntity subCategoryEntity = new SubCategoryEntity();
            subCategoryEntity.setTypeName(typename);
            subCategoryEntity.setCategory(categoryEntity);
            categoryEntity.getSubCategoryEntityLists().add(subCategoryEntity);
            subCategoryRepository.saveAndFlush(subCategoryEntity);
        }
    }

    public List<SubCategoryEntity> getAll() throws SQLException {
        return subCategoryRepository.findAll();
    }

    public void update(SubCategoryEntity subCategory, Long targetId) throws SQLException {
        if (subCategory != null) {
            subCategory.setId(targetId);
            subCategoryRepository.saveAndFlush(subCategory);
        }
    }

    @Override
    public SubCategoryEntity getById(Long id) throws SQLException {
        Optional<SubCategoryEntity> subCategoryEntity = subCategoryRepository.findById(id);
        if (!subCategoryEntity.isPresent()) {
            throw new SQLException("Entity not found");
        }
        return subCategoryEntity.get();
    }

    public void removeById(Long id) throws SQLException {
        SubCategoryEntity deleted_subCategory = getById(id);
        remove(deleted_subCategory);
    }

    @Override
    public void remove(SubCategoryEntity subCategoryEntity) throws SQLException {
        if (subCategoryEntity != null) {
            subCategoryRepository.delete(subCategoryEntity);
        }
    }

}
