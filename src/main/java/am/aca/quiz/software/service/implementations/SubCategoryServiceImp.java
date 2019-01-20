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


    @Override
    public boolean addSubCategory(String typeName, Long id) throws SQLException {
        return false;
    }

    @Override
    public List<SubCategoryDto> getAll() throws SQLException {
        return null;
    }

    @Override
    public boolean update(SubCategoryEntity subCategory, Long id) throws SQLException {
        return false;
    }

    @Override
    public SubCategoryEntity remove(SubCategoryEntity subCategory) throws SQLException {
        return null;
    }

    @Override
    public SubCategoryEntity getById(Long id) throws SQLException {
        return null;
    }

    @Override
    public boolean removeByid(Long id) throws SQLException {
        return false;
    }
}
