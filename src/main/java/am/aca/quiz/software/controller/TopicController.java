package am.aca.quiz.software.controller;

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

    public TopicController(TopicServiceImp topicServiceImp, TopicMapper topicMapper, SubCategoryMapper subCategoryMapper) {
        this.topicServiceImp = topicServiceImp;
        this.topicMapper = topicMapper;
        this.subCategoryMapper = subCategoryMapper;
    }


    @GetMapping("/add")
    public ModelAndView addTopic() throws SQLException {
        ModelAndView modelAndView = new ModelAndView("topic");

        List<SubCategoryDto> subCategoryDtos = subCategoryMapper.mapEntitiesToDto(topicServiceImp.getSubCategoryServiceImpl().getAll());

        modelAndView.addObject("subCategories", subCategoryDtos);

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

    @PatchMapping("/update/{id}")
    public void updateTopic(@RequestBody TopicEntity topicEntity,@PathVariable("id") Long id){
        try {
            topicServiceImp.update(topicEntity,id);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
