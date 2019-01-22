package am.aca.quiz.software.controller;

import am.aca.quiz.software.entity.CategoryEntity;
import am.aca.quiz.software.service.implementations.CategoryServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.List;

@RestController
@RequestMapping("/category")
public class CategoryController {

    @Autowired
    private CategoryServiceImp categoryServiceImp;


    @RequestMapping(value = "/add")
    public String addCategoryPage(){
        return "CategoryAdd.jsp";
    }

    @RequestMapping(method = RequestMethod.GET)
    public List<CategoryEntity> getTest(){
        try {
            return categoryServiceImp.getAll();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
    @RequestMapping(value = "/{c}",method = RequestMethod.POST)
    public void postNewCategory(@PathVariable("c") String category){
        try {

             categoryServiceImp.addCategory(category);
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
    @RequestMapping(value = "/{id}",method = RequestMethod.PATCH)
    public void updateCategory(@RequestBody CategoryEntity categoryEntity,@PathVariable("id") Long id){
        try {
             categoryServiceImp.update(categoryEntity,id);
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
    @RequestMapping(value = "/{id}",method = RequestMethod.DELETE)
    public void deleteCategory(@PathVariable("id")Long id){
        try {
             categoryServiceImp.removeById(id);
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
    @RequestMapping(value = "/{id}",method = RequestMethod.GET)
    public CategoryEntity getCategoryById(@PathVariable("id") Long id){
        try {
            return categoryServiceImp.getById(id);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }


}
