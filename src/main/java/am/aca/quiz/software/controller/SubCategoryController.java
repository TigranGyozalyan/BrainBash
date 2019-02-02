package am.aca.quiz.software.controller;

import am.aca.quiz.software.entity.CategoryEntity;
import am.aca.quiz.software.entity.SubCategoryEntity;
import am.aca.quiz.software.service.dto.CategoryDto;
import am.aca.quiz.software.service.dto.SubCategoryDto;
import am.aca.quiz.software.service.implementations.SubCategoryServiceImp;
import am.aca.quiz.software.service.mapper.CategoryMapper;
import am.aca.quiz.software.service.mapper.SubCategoryMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


@RestController
@RequestMapping("/subcategory")
public class SubCategoryController {

    private final SubCategoryServiceImp subCategoryServiceImp;
    private final CategoryMapper categoryMapper;
    private final SubCategoryMapper subCategoryMapper;
    private final String EMPTY = "empty";

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

    @RequestMapping(value = "/add", method = RequestMethod.POST, consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    @ResponseBody
    public ModelAndView postNewCategory(@RequestParam Map<String, String> formData) throws SQLException {
        ModelAndView modelAndView = new ModelAndView("subCategory");

        try {
            String category = formData.get("categoryList");
            Long categoryId = subCategoryServiceImp.getCategoryServiceImp().getByType(category).getId();
            String subCategory = formData.get("typename");
            subCategoryServiceImp.addSubCategory(subCategory, categoryId);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        List<CategoryDto> categoryDtos = categoryMapper.mapEntitiesToDto(subCategoryServiceImp.getCategoryServiceImp().getAll());
        modelAndView.addObject("categories", categoryDtos);

        return modelAndView;
    }

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<List<SubCategoryDto>> getSubCategories(@RequestParam("categoryId") int categoryId) {
        try {
            List<SubCategoryEntity> subCategoryEntities = subCategoryServiceImp.getAll()
                    .stream()
                    .filter(it -> it.getCategory().getId() == categoryId)
                    .collect(Collectors.toList());
            List<SubCategoryDto> subCategoryDtos = subCategoryMapper.mapEntitiesToDto(subCategoryEntities);

            return new ResponseEntity<>(subCategoryDtos, HttpStatus.OK);

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

    @RequestMapping(value = "/update", method = RequestMethod.GET)
    public ModelAndView updateSubCategory() {
        ModelAndView modelAndView = new ModelAndView("subCategoryUpdate");
        List<CategoryDto> categoryDtos = null;
        List<SubCategoryDto> subCategoryDtos = null;

        try {
            categoryDtos = categoryMapper.mapEntitiesToDto(subCategoryServiceImp.getCategoryServiceImp().getAll());
            subCategoryDtos = subCategoryMapper.mapEntitiesToDto(subCategoryServiceImp.getAll());
            modelAndView.addObject("empty", EMPTY);
            modelAndView.addObject("categories", categoryDtos);
            modelAndView.addObject("subCategories", subCategoryDtos);

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return modelAndView;
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public ModelAndView updateSubCategory(@RequestParam Map<String, String> formData) {
        ModelAndView modelAndView = new ModelAndView("subCategoryUpdate");
        String typeName = formData.get("typename");
        String category = formData.get("categoryList");
        String subCategory = formData.get("subCategoryList");

        if (typeName != null) {
            CategoryEntity categoryEntity = null;
            try {
                if (!category.equals(EMPTY)) {
                    categoryEntity = subCategoryServiceImp.getCategoryServiceImp().getByType(category);
                } else {
                    categoryEntity = subCategoryServiceImp.getCategoryServiceImp().getById(subCategoryServiceImp.getCategoryIdBySubCategoryTypeName(subCategory));
                }
                SubCategoryEntity updatedSubCategoryEntity = new SubCategoryEntity(typeName, categoryEntity);

                SubCategoryEntity subCategoryEntity = subCategoryServiceImp.getByTypeName(subCategory);
                subCategoryServiceImp.update(updatedSubCategoryEntity, subCategoryEntity);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }


        try {
            List<CategoryDto> categoryDtos = categoryMapper.mapEntitiesToDto(subCategoryServiceImp.getCategoryServiceImp().getAll());
            List<SubCategoryDto> subCategoryDtos = subCategoryMapper.mapEntitiesToDto(subCategoryServiceImp.getAll());
            modelAndView.addObject("empty", EMPTY);
            modelAndView.addObject("categories", categoryDtos);
            modelAndView.addObject("subCategories", subCategoryDtos);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return modelAndView;

    }


}
