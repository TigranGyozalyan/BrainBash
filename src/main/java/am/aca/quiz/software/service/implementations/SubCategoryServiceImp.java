package am.aca.quiz.software.service.implementations;

import am.aca.quiz.software.repository.SubCategoryRepository;
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

    public boolean addCategory(SubCategoryEntity subCategory) throws SQLException {
        subCategoryRepository.saveAndFlush(subCategory);
        return true;
    }

    public List<SubCategoryEntity> getAll() throws SQLException {
        return subCategoryRepository.findAll();
    }

    public boolean update(SubCategoryEntity subCategory,Long id) throws SQLException {
        Optional<SubCategoryEntity> historyEntity=subCategoryRepository.findById(id);
        if(!historyEntity.isPresent()){
            throw new SQLException("Argument Not Found ");
        }
        subCategory.setId(id);

        subCategoryRepository.saveAndFlush(subCategory);
        return true;
    }

    public SubCategoryEntity remove(SubCategoryEntity subCategory) throws SQLException {
        subCategoryRepository.delete(subCategory);
        return subCategory;
    }

    @Override
    public SubCategoryEntity removeById(Long id) throws SQLException {
        return null;
    }

    @Override
    public boolean removeByid(Long id) throws SQLException {
        return false;
    }
}
