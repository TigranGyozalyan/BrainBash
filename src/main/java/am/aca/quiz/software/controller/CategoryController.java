package am.aca.quiz.software.controller;

import am.aca.quiz.software.entity.CategoryEntity;
import am.aca.quiz.software.service.dto.CategoryDto;
import am.aca.quiz.software.service.implementations.CategoryServiceImp;
import am.aca.quiz.software.service.mapper.CategoryMapper;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.jws.WebParam;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;


@RestController
@RequestMapping("/category")
public class CategoryController {

    private final CategoryServiceImp categoryServiceImp;
    private final CategoryMapper categoryMapper;


    public CategoryController(CategoryServiceImp categoryServiceImp, CategoryMapper categoryMapper) {
        this.categoryServiceImp = categoryServiceImp;
        this.categoryMapper = categoryMapper;
    }


    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public ModelAndView addCategoryPage() {
        return new ModelAndView("category");
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public ModelAndView postNewCategory(@RequestParam("type") String type) {
        ModelAndView modelAndView = new ModelAndView("category");
        try {
            categoryServiceImp.addCategory(type);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return modelAndView;
    }

    @RequestMapping(method = RequestMethod.GET)
    public List<CategoryDto> getCategory() {

        try {
            return categoryMapper.mapEntitiesToDto(categoryServiceImp.getAll());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
    @RequestMapping(value = "/update",method = RequestMethod.GET)
    public ModelAndView update(){
        ModelAndView modelAndVi=new ModelAndView("categoryUpdate");
        List<CategoryDto> categoryDtos=null;
        try {
            categoryDtos=categoryMapper.mapEntitiesToDto(categoryServiceImp.getAll());
            modelAndVi.addObject("categories",categoryDtos);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return modelAndVi;
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public ModelAndView updateCategory(@RequestParam  Map<String,String> formData) {
        ModelAndView modelAndView=new ModelAndView("categoryUpdate");

            String type = formData.get("type");
            String category = formData.get("categoryList");

            if(type!=null){
                try {
                    CategoryEntity updatedCategoryEntit=new CategoryEntity(type);
                    CategoryEntity categoryEntity=categoryServiceImp.getByType(category);
                    categoryServiceImp.update(updatedCategoryEntit,categoryEntity);
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }

        try {
            List<CategoryDto> categoryDtos=categoryMapper.mapEntitiesToDto(categoryServiceImp.getAll());
            modelAndView.addObject("categories",categoryDtos);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return modelAndView;
    }

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE)
    public void deleteCategory(@PathVariable("id") Long id) {
        try {
            categoryServiceImp.removeById(id);
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public CategoryDto getCategoryById(@PathVariable("id") Long id) {
        try {
            return categoryMapper.mapEntityToDto(categoryServiceImp.getById(id));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }


}

