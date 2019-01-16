package am.aca.quiz_software.service;

import am.aca.quiz_software.entity.SubCategoryEntity;
import am.aca.quiz_software.repository.CategoryRepository;
import am.aca.quiz_software.repository.SubCategoryRepository;
import am.aca.quiz_software.repository.TopicRepository;
import am.aca.quiz_software.service.dto.SubCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;


@Service
public class SubCategoryServiceImp implements SubCategoryService {

    @Autowired
    private  SubCategoryRepository subCategoryRepository;

    public boolean addCategory(SubCategoryEntity subCategory) throws SQLException {
        subCategoryRepository.saveAndFlush(subCategory);
        return true;
    }

    public List<SubCategoryEntity> getAll() throws SQLException {
        return subCategoryRepository.findAll();
    }

    public boolean update(SubCategoryEntity subCategory) throws SQLException {
        //toDO
        return false;
    }

    public SubCategoryEntity remove(SubCategoryEntity subCategory) throws SQLException {
        subCategoryRepository.delete(subCategory);
        return subCategory;
    }
}
