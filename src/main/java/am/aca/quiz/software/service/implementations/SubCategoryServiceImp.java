package am.aca.quiz.software.service.implementations;

import am.aca.quiz.software.entity.CategoryEntity;
import am.aca.quiz.software.repository.SubCategoryRepository;
import am.aca.quiz.software.service.dto.SubCategoryDto;
import am.aca.quiz.software.service.intefaces.SubCategoryService;
import am.aca.quiz.software.entity.SubCategoryEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;


@Service
public class SubCategoryServiceImp implements SubCategoryService {

    @Autowired
    private SubCategoryRepository subCategoryRepository;
    @Autowired
    private TopicServiceImp topicServiceImp;
    @Autowired
    private CategoryServiceImp categoryServiceImp;

    public boolean addSubCategory(String typename,Long id) throws SQLException {
        CategoryEntity categoryEntity= categoryServiceImp.getById(id);
        SubCategoryEntity subCategory=new SubCategoryEntity(typename,);
        subCategoryRepository.saveAndFlush(subCategory);
        return true;
    }

    public List<SubCategoryDto> getAll() throws SQLException {
        return SubCategoryDto.mapEntityToDtos(subCategoryRepository.findAll());
    }

    public boolean update(SubCategoryEntity subCategory, Long id) throws SQLException {
        SubCategoryEntity updated_subCategory = subCategoryRepository.findById(id).get();
        if (updated_subCategory != null) {
            subCategory.setId(id);
            return addSubCategory(subCategory);
        }
        return false;
    }

    public SubCategoryDto remove(SubCategoryEntity subCategory) throws SQLException {
        subCategoryRepository.delete(subCategory);
        return SubCategoryDto.mapEntityToDto(subCategory);
    }

    @Override
    public SubCategoryDto getById(Long id) throws SQLException {
        Optional<SubCategoryEntity> subCategoryEntity = subCategoryRepository.findById(id);
        if (!subCategoryEntity.isPresent()) {
            throw new SQLException("Entity not found");
        }
        return SubCategoryDto.mapEntityToDto(subCategoryEntity.get());
    }

    @Override
    public boolean removeByid(Long id) throws SQLException {
        SubCategoryEntity deleted_subCategory = subCategoryRepository.findById(id).get();
        subCategoryRepository.delete(deleted_subCategory);
        return true;
    }
}
