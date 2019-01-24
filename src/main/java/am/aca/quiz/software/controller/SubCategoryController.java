package am.aca.quiz.software.controller;

import am.aca.quiz.software.entity.CategoryEntity;
import am.aca.quiz.software.entity.SubCategoryEntity;
import am.aca.quiz.software.service.implementations.SubCategoryServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


@Controller
@RequestMapping("/subcategory")
public class SubCategoryController {


    private final SubCategoryServiceImp subCategoryServiceImp;

    @Autowired
    public SubCategoryController(SubCategoryServiceImp subCategoryServiceImp) {
        this.subCategoryServiceImp = subCategoryServiceImp;
    }


    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public ModelAndView addSubCategory() throws SQLException {
        ModelAndView modelAndView = new ModelAndView("subCategory");

        //emulate dto mapping

        List<String> categoryStrings = dtoEmulator(subCategoryServiceImp.getCategoryServiceImp().getAll());

        //
        modelAndView.addObject("categories", categoryStrings);
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

        List<String> categoryStrings = dtoEmulator(subCategoryServiceImp.getCategoryServiceImp().getAll());

        modelAndView.addObject("categories",categoryStrings);

        return modelAndView;
    }

    @RequestMapping(method = RequestMethod.GET)
    public List<SubCategoryEntity> getTest() {
        try {
            return subCategoryServiceImp.getAll();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @RequestMapping(value = "delete/{id}",method = RequestMethod.DELETE)
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

    //toDo fix mappers so that they are recognized as beans

    private List<String> dtoEmulator (List<CategoryEntity> categoryEntities) {
        List<String> categoryStrings = new ArrayList<>();
        for (CategoryEntity categoryEntity : categoryEntities) {
            categoryStrings.add(categoryEntity.getType());
        }
        return categoryStrings;
    }

}
