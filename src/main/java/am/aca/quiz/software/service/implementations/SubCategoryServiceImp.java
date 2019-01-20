package am.aca.quiz.software.service.implementations;

import am.aca.quiz.software.entity.CategoryEntity;
import am.aca.quiz.software.repository.SubCategoryRepository;
import am.aca.quiz.software.service.intefaces.SubCategoryService;
import am.aca.quiz.software.entity.SubCategoryEntity;
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


    public boolean addSubCategory(String typename, Long id) throws SQLException {
        CategoryEntity categoryEntity = categoryServiceImp.getById(id);
        SubCategoryEntity subCategory = new SubCategoryEntity(typename, categoryEntity);
        subCategoryRepository.saveAndFlush(subCategory);
        return true;
    }


    public List<SubCategoryEntity> getAll() throws SQLException {
        return subCategoryRepository.findAll();
    }

    public boolean update(SubCategoryEntity subCategory, Long id) throws SQLException {
        SubCategoryEntity updated_subCategory = subCategoryRepository.findById(id).get();
        if (updated_subCategory != null) {
            subCategory.setId(id);
            subCategoryRepository.saveAndFlush(subCategory);
            return true;
        }
        return false;
    }

    @Override
    public SubCategoryEntity getById(Long id) throws SQLException {
        SubCategoryEntity subCategoryEntity = subCategoryRepository.findById(id).get();
        if (subCategoryEntity == null) {
            throw new SQLException("Entity not found");
        }
        return subCategoryEntity;
    }

    @Override
    public boolean removeByid(Long id) throws SQLException {
        SubCategoryEntity deleted_subCategory = subCategoryRepository.findById(id).get();
        subCategoryRepository.delete(deleted_subCategory);
        return true;
    }
}
