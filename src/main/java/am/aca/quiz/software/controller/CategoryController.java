package am.aca.quiz.software.controller;

import am.aca.quiz.software.entity.CategoryEntity;
import am.aca.quiz.software.service.dto.CategoryDto;
import am.aca.quiz.software.service.implementations.CategoryServiceImp;
import am.aca.quiz.software.service.mapper.CategoryMapper;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.sql.SQLException;
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

    @PreAuthorize("hasAuthority('ADMIN')")
    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public ModelAndView addCategoryPage() {
        return new ModelAndView("category");
    }

    @PreAuthorize("hasAuthority('ADMIN')")
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

    @GetMapping
    public ModelAndView categoryList(){
        ModelAndView modelAndView=new ModelAndView("categoryList");

        try {
            modelAndView.addObject("categoryList",categoryMapper.mapEntitiesToDto(categoryServiceImp.getAll()));
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return modelAndView;
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @RequestMapping(value = "/update/{id}",method = RequestMethod.GET)
    public ModelAndView update(@PathVariable Long id){
        ModelAndView modelAndVi=new ModelAndView("categoryUpdate");
        try {
            CategoryDto categoryDto=categoryMapper.mapEntityToDto(categoryServiceImp.getById(id));
            modelAndVi.addObject("category",categoryDto);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return modelAndVi;
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public ModelAndView updateCategory(@RequestParam("categoryId") Long categoryId,@RequestParam  Map<String,String> formData) {


        String type = formData.get("type");
        try {
            CategoryEntity categoryEntity=categoryServiceImp.getById(categoryId);
            categoryEntity.setType(type);
            categoryServiceImp.update(categoryEntity);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return categoryList();
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
    public ModelAndView deleteCategory(@PathVariable("id") Long id) {
        try {
            categoryServiceImp.removeById(id);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return categoryList();
    }






    /*
                  meaningless
     */
//
//    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
//    public CategoryDto getCategoryById(@PathVariable("id") Long id) {
//        try {
//            return categoryMapper.mapEntityToDto(categoryServiceImp.getById(id));
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//        return null;
//    }

}

