package am.aca.quiz.software.controller;

import am.aca.quiz.software.entity.SubCategoryEntity;
import am.aca.quiz.software.entity.TopicEntity;
import am.aca.quiz.software.service.dto.SubCategoryDto;
import am.aca.quiz.software.service.dto.TopicDto;
import am.aca.quiz.software.service.implementations.TopicServiceImp;
import am.aca.quiz.software.service.mapper.SubCategoryMapper;
import am.aca.quiz.software.service.mapper.TopicMapper;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/topic")
public class TopicController {
    private final TopicServiceImp topicServiceImp;
    private final TopicMapper topicMapper;
    private final SubCategoryMapper subCategoryMapper;
    private final String EMPTY = "empty";

    public TopicController(TopicServiceImp topicServiceImp, TopicMapper topicMapper, SubCategoryMapper subCategoryMapper) {
        this.topicServiceImp = topicServiceImp;
        this.topicMapper = topicMapper;
        this.subCategoryMapper = subCategoryMapper;
    }


    @GetMapping("/add")
    public ModelAndView addTopic() throws SQLException {
        ModelAndView modelAndView = new ModelAndView("topic");

        List<SubCategoryDto> subCategoryDtos = subCategoryMapper.mapEntitiesToDto(topicServiceImp.getSubCategoryServiceImpl().getAll());

        modelAndView.addObject("subcategories", subCategoryDtos);

        return modelAndView;
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST, consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
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
    public List<TopicDto> getAllTopics() {

        try {
            return topicMapper.mapEntitiesToDto(topicServiceImp.getAll());
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    @DeleteMapping("delete/{id}")
    public void deleteTopic(@PathVariable("id") Long id) {
        try {
            topicServiceImp.removeByid(id);
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    @GetMapping(value = "/update")
    public ModelAndView updateTopic() {
        ModelAndView modelAndView = new ModelAndView("topicUpdate");

        List<SubCategoryDto> subCategoryDtos = null;
        List<TopicDto> topicDtos = null;

        try {
            subCategoryDtos = subCategoryMapper.mapEntitiesToDto(topicServiceImp.getSubCategoryServiceImpl().getAll());
            topicDtos = topicMapper.mapEntitiesToDto(topicServiceImp.getAll());

            modelAndView.addObject("empty", EMPTY);
            modelAndView.addObject("subCategories", subCategoryDtos);
            modelAndView.addObject("topics", topicDtos);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return modelAndView;
    }

    @PostMapping(value = "/update")
    public ModelAndView updateTopic(@RequestParam Map<String, String> formDate) {
        ModelAndView modelAndView = new ModelAndView("topicUpdate");

        String topicName = formDate.get("topicName");
        String subCategory = formDate.get("subCategoryList");
        String topic = formDate.get("topicList");

        if (!topicName.isEmpty()) {
            SubCategoryEntity subCategoryEntity = null;
            try {
                if (!subCategory.equals(EMPTY)) {
                    subCategoryEntity = topicServiceImp.getSubCategoryServiceImpl().getByTypeName(subCategory);
                } else {
                    subCategoryEntity = topicServiceImp.getSubCategoryServiceImpl().getById(topicServiceImp.getSubCategoryIdByTopicName(topic));
                }
                TopicEntity updatedTopicEntity = new TopicEntity(topicName, subCategoryEntity);
                TopicEntity topicEntity = topicServiceImp.getByTopicName(topic);

                topicServiceImp.update(updatedTopicEntity, topicEntity);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }


        try {

            List<SubCategoryDto> subCategoryDtos = subCategoryDtos = subCategoryMapper.mapEntitiesToDto(topicServiceImp.getSubCategoryServiceImpl().getAll());
            List<TopicDto> topicDtos = topicDtos = topicMapper.mapEntitiesToDto(topicServiceImp.getAll());
            modelAndView.addObject("empty", EMPTY);
            modelAndView.addObject("subCategories", subCategoryDtos);
            modelAndView.addObject("topics", topicDtos);
            ;
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return modelAndView;
    }
}
