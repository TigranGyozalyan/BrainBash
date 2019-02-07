package am.aca.quiz.software.controller;

import am.aca.quiz.software.entity.SubCategoryEntity;
import am.aca.quiz.software.entity.TopicEntity;
import am.aca.quiz.software.service.dto.SubCategoryDto;
import am.aca.quiz.software.service.dto.TopicDto;
import am.aca.quiz.software.service.implementations.SubCategoryServiceImp;
import am.aca.quiz.software.service.implementations.TopicServiceImp;
import am.aca.quiz.software.service.mapper.SubCategoryMapper;
import am.aca.quiz.software.service.mapper.TopicMapper;
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
@RequestMapping("/topic")
public class TopicController {
    private final TopicServiceImp topicServiceImp;
    private final TopicMapper topicMapper;
    private final SubCategoryMapper subCategoryMapper;
    private final SubCategoryServiceImp subCategoryServiceImp;
    private final String EMPTY = "empty";

    public TopicController(TopicServiceImp topicServiceImp, TopicMapper topicMapper, SubCategoryMapper subCategoryMapper, SubCategoryServiceImp subCategoryServiceImp) {
        this.topicServiceImp = topicServiceImp;
        this.topicMapper = topicMapper;
        this.subCategoryMapper = subCategoryMapper;
        this.subCategoryServiceImp = subCategoryServiceImp;
    }


    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("/add")
    public ModelAndView addTopic() throws SQLException {
        ModelAndView modelAndView = new ModelAndView("topic");

        List<SubCategoryDto> subCategoryDtos = subCategoryMapper.mapEntitiesToDto(topicServiceImp.getSubCategoryServiceImpl().getAll());

        modelAndView.addObject("subcategories", subCategoryDtos);

        return modelAndView;
    }

    @PostMapping(value = "/add", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    @ResponseBody
    public ModelAndView postNewTopic(@RequestParam Map<String, String> formData) throws SQLException {
        ModelAndView modelAndView = new ModelAndView("topic");

        String subCategory = formData.get("subcategoryList");
        Long subCategoryId = topicServiceImp.getSubCategoryServiceImpl().getByTypeName(subCategory).getId();
        String topic = formData.get("topicName");
        topicServiceImp.addTopic(topic, subCategoryId);

        List<SubCategoryDto> subCategoryDtos = subCategoryMapper.mapEntitiesToDto(topicServiceImp.getSubCategoryServiceImpl().getAll());

        modelAndView.addObject("subcategories", subCategoryDtos);

        return modelAndView;
    }


    @GetMapping
    public ResponseEntity<List<TopicDto>> getAllTopics(@RequestParam("subCategoryId") Long subCategoryId) {

        try {
            List<TopicEntity> topicEntities =
                    topicServiceImp.getAll()
                            .stream()
                            .filter(i -> i.getSubCategory().getId().equals(subCategoryId))
                            .collect(Collectors.toList());
            return new ResponseEntity<>(topicMapper.mapEntitiesToDto(topicEntities), HttpStatus.OK);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("/list")
    public ModelAndView topicList() {
        ModelAndView modelAndView = new ModelAndView("topicList");
        try {
            modelAndView.addObject("topicList", topicMapper.mapEntitiesToDto(topicServiceImp.getAll()));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return modelAndView;
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("/delete/{id}")
    public ModelAndView deleteTopic(@PathVariable("id") Long id) {
        try {
            topicServiceImp.removeById(id);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return topicList();
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping(value = "/update/{id}")
    public ModelAndView updateTopic(@PathVariable("id") Long id) {
        ModelAndView modelAndView = new ModelAndView("topicUpdate");

        try {
            List<SubCategoryDto> subCategoryDtos = subCategoryMapper.mapEntitiesToDto(topicServiceImp.getSubCategoryServiceImpl().getAll());

            modelAndView.addObject("empty", EMPTY);
            modelAndView.addObject("subCategories", subCategoryDtos);
            modelAndView.addObject("topic", topicMapper.mapEntityToDto(topicServiceImp.getById(id)));
        } catch (SQLException e) {
            e.printStackTrace();
        }


        return modelAndView;
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @PostMapping(value = "/update")
    public ModelAndView updateTopic(@RequestParam("topicId") Long topicId, @RequestParam Map<String, String> formDate) {

        String topicName = formDate.get("topicName");
        String subCategory = formDate.get("subCategoryList");


        SubCategoryEntity subCategoryEntity = null;
        TopicEntity updateTopicEntity = null;
        try {
            if (!subCategory.equals(EMPTY)) {
                subCategoryEntity = subCategoryServiceImp.getByTypeName(subCategory);
                if (topicName.isEmpty()) {
                    updateTopicEntity = new TopicEntity(topicServiceImp.getById(topicId).getTopicName(), subCategoryEntity);
                } else {
                    updateTopicEntity = new TopicEntity(topicName, subCategoryEntity);
                }
            } else {
                subCategoryEntity = topicServiceImp.getById(topicId).getSubCategory();
                if (topicName.isEmpty()) {
                    updateTopicEntity = new TopicEntity(topicServiceImp.getById(topicId).getTopicName(), subCategoryEntity);
                } else {
                    updateTopicEntity = new TopicEntity(topicName, subCategoryEntity);
                }
            }
            updateTopicEntity.setId(topicId);
            topicServiceImp.update(updateTopicEntity);

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return topicList();
    }

//    @GetMapping(value = "/{id}")
//    public ResponseEntity<TopicDto> getById(@PathVariable("id") Long id) {
//        try {
//            if (topicServiceImp.getById(id) == null) {
//                return ResponseEntity.noContent().build();
//            }
//            return ResponseEntity.ok(topicMapper.mapEntityToDto(topicServiceImp.getById(id)));
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//        return null;
//    }

//    @GetMapping("/all")
//    public ResponseEntity<List<TopicDto>> getAllTopics() {
//        try {
//            if (topicServiceImp.getAll().isEmpty()) {
//                return ResponseEntity.noContent().build();
//            }
//            return ResponseEntity.ok(topicMapper.mapEntitiesToDto(topicServiceImp.getAll()));
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//        return null;
//    }
}
