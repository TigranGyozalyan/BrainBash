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
import org.springframework.security.access.prepost.PreAuthorize;
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

//    @GetMapping
//    public ResponseEntity<List<SubCategoryDto>> getAll() {
//        try {
//            if(subCategoryServiceImp.getAll().isEmpty()){
//                return ResponseEntity.noContent().build();
//            }
//            return ResponseEntity.ok(subCategoryMapper.mapEntitiesToDto(subCategoryServiceImp.getAll()));
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//        return null;
//    }

//    @GetMapping
//    public ResponseEntity<List<SubCategoryDto>> getAll() {
//
//        try {
//            List<SubCategoryEntity> subCategoryEntities = subCategoryServiceImp.getAll();
//            if (subCategoryEntities.isEmpty()) {
//                return ResponseEntity.noContent().build();
//            }
//            return ResponseEntity.ok(subCategoryMapper.mapEntitiesToDto(subCategoryEntities));
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//        return null;
//    }

    @GetMapping("/filter")
    public ResponseEntity<List<SubCategoryDto>> getSubCategoriesById(@RequestParam("categoryId") long categoryId) {
        try {
            System.out.println("Got here");
            List<SubCategoryEntity> subCategoryEntities = subCategoryServiceImp.getAll().stream()
                    .filter(i -> i.getCategory().getId().equals(categoryId))
                    .collect(Collectors.toList());
            return ResponseEntity.ok(subCategoryMapper.mapEntitiesToDto(subCategoryEntities));
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

//    @GetMapping("/{id}")
//    public ResponseEntity<SubCategoryDto> getById(@PathVariable("id") Long id) {
//        try {
//            if(subCategoryServiceImp.getById(id)==null){
//                return ResponseEntity.noContent().build();
//            }
//            return ResponseEntity.ok(subCategoryMapper.mapEntityToDto(subCategoryServiceImp.getById(id)));
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//        return null;
//    }
//    @GetMapping("/{id}")
//    public ResponseEntity<SubCategoryDto> getById(@PathVariable("id") Long id) {
//
//        try {
//            SubCategoryEntity subCategoryEntity = subCategoryServiceImp.getById(id);
//            if (subCategoryEntity == null) {
//                return ResponseEntity.noContent().build();
//            }
//            return ResponseEntity.ok(subCategoryMapper.mapEntityToDto(subCategoryEntity));
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//        return null;
//    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping(value = "/add")
    public ModelAndView addSubCategory() throws SQLException {
        ModelAndView modelAndView = new ModelAndView("subCategory");

        List<CategoryDto> categoryDtos = categoryMapper.mapEntitiesToDto(subCategoryServiceImp.getCategoryServiceImp().getAll()); //Shell we do this by @Query

        modelAndView.addObject("categories", categoryDtos);

        return modelAndView;
    }

    @PostMapping(value = "/add", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
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

    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("/list")
    public ModelAndView subCategoryList() {
        ModelAndView modelAndView = new ModelAndView("subCategoryList");

        try {
            List<SubCategoryDto> subCategoryDtos = subCategoryMapper.mapEntitiesToDto(subCategoryServiceImp.getAll());
            modelAndView.addObject("subCategoryList", subCategoryDtos);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return modelAndView;
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping(value = "/update/{id}")
    public ModelAndView updateSubCategory(@PathVariable Long id) {
        ModelAndView modelAndView = new ModelAndView("subCategoryUpdate");
        List<CategoryDto> categoryDtos = null;
        SubCategoryDto subCategoryDto = null;

        try {
            categoryDtos = categoryMapper.mapEntitiesToDto(subCategoryServiceImp.getCategoryServiceImp().getAll());
            subCategoryDto = subCategoryMapper.mapEntityToDto(subCategoryServiceImp.getById(id));
            modelAndView.addObject("empty", EMPTY);
            modelAndView.addObject("categories", categoryDtos);
            modelAndView.addObject("subCategory", subCategoryDto);

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return modelAndView;
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @PostMapping(value = "/update")
    public ModelAndView updateSubCategory(@RequestParam("id") Long id, @RequestParam Map<String, String> formData) {
        String typeName = formData.get("typename");
        String category = formData.get("categoryList");

        CategoryEntity categoryEntity = null;
        SubCategoryEntity updatedSubCategoryEntity = null;

        try {
            if (!category.equals(EMPTY)) {
                categoryEntity = subCategoryServiceImp.getCategoryServiceImp().getByType(category);

                if (typeName.isEmpty()) {
                    updatedSubCategoryEntity = new SubCategoryEntity(subCategoryServiceImp.getById(id).getTypeName(), categoryEntity);
                } else {
                    updatedSubCategoryEntity = new SubCategoryEntity(typeName, categoryEntity);
                }
            } else {
                categoryEntity = subCategoryServiceImp.getById(id).getCategory();
                if (typeName.isEmpty()) {
                    updatedSubCategoryEntity = new SubCategoryEntity(subCategoryServiceImp.getById(id).getTypeName(), categoryEntity);
                } else {
                    updatedSubCategoryEntity = new SubCategoryEntity(typeName, categoryEntity);

                }
            }
            updatedSubCategoryEntity.setId(id);
            subCategoryServiceImp.update(updatedSubCategoryEntity);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return subCategoryList();

    }


    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping(value = "delete/{id}")
    public ModelAndView deleteSubCategory(@PathVariable("id") Long id) {

        try {
            subCategoryServiceImp.removeById(id);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return subCategoryList();
    }

}
