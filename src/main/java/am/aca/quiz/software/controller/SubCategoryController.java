package am.aca.quiz.software.controller;

import am.aca.quiz.software.entity.SubCategoryEntity;
import am.aca.quiz.software.service.dto.CategoryDto;
import am.aca.quiz.software.service.dto.SubCategoryDto;
import am.aca.quiz.software.service.implementations.SubCategoryServiceImp;
import am.aca.quiz.software.service.mapper.CategoryMapper;
import am.aca.quiz.software.service.mapper.SubCategoryMapper;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.sql.SQLException;
import java.util.List;


@RestController
@RequestMapping("/subcategory")
public class SubCategoryController {

    private final SubCategoryServiceImp subCategoryServiceImp;
    private final CategoryMapper categoryMapper;
    private final SubCategoryMapper subCategoryMapper;

    public SubCategoryController(SubCategoryServiceImp subCategoryServiceImp, CategoryMapper categoryMapper, SubCategoryMapper subCategoryMapper) {
        this.subCategoryServiceImp = subCategoryServiceImp;
        this.categoryMapper = categoryMapper;
        this.subCategoryMapper = subCategoryMapper;
    }


    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public ModelAndView addSubCategory() throws SQLException {
        ModelAndView modelAndView = new ModelAndView("subCategory");

        List<CategoryDto> categoryDtos = categoryMapper.mapEntitiesToDto(subCategoryServiceImp.getCategoryServiceImp().getAll()); //Shell we do this by @Query

        modelAndView.addObject("categories", categoryDtos);

        return modelAndView;
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public ModelAndView postNewCategory(@RequestBody MultiValueMap<String, String> formData) throws SQLException {
        ModelAndView modelAndView = new ModelAndView("subCategory");

        try {
            String category = formData.get("categoryList").get(0);
            Long categoryId = subCategoryServiceImp.getCategoryServiceImp().getByType(category).getId();
            String subCategory = formData.get("typename").get(0);
            subCategoryServiceImp.addSubCategory(subCategory, categoryId);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        List<CategoryDto> categoryDtos = categoryMapper.mapEntitiesToDto(subCategoryServiceImp.getCategoryServiceImp().getAll());
        modelAndView.addObject("categories", categoryDtos);

        return modelAndView;
    }

    @RequestMapping(method = RequestMethod.GET)
    public List<SubCategoryDto> getTest() {
        try {
            return subCategoryMapper.mapEntitiesToDto(subCategoryServiceImp.getAll());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @RequestMapping(value = "delete/{id}", method = RequestMethod.DELETE)
    public void deleteSubCategory(@PathVariable("id") Long id) {
        try {
            subCategoryServiceImp.removeById(id);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @RequestMapping(value = "/update/{id}", method = RequestMethod.PATCH)
    public void updateCategory(@RequestBody SubCategoryEntity subCategoryEntity, @PathVariable("id") Long id) {
        try {
            subCategoryServiceImp.update(subCategoryEntity, id);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
