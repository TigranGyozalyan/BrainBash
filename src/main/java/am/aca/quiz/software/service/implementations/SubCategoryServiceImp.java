package am.aca.quiz.software.service.implementations;

import am.aca.quiz.software.repository.SubCategoryRepository;
import am.aca.quiz.software.service.dto.SubCategoryDto;
import am.aca.quiz.software.service.intefaces.SubCategoryService;
import am.aca.quiz.software.entity.SubCategoryEntity;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;


@Service
public class SubCategoryServiceImp implements SubCategoryService {


    private final SubCategoryRepository subCategoryRepository;

    public SubCategoryServiceImp(SubCategoryRepository subCategoryRepository) {
        this.subCategoryRepository = subCategoryRepository;
    }


    public boolean addSubCategory(SubCategoryEntity subCategory) throws SQLException {
        subCategoryRepository.saveAndFlush(subCategory);
        return true;
    }

    @Override
    public boolean addSubCategory(String typeName, Long id) throws SQLException {
        return false;
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
