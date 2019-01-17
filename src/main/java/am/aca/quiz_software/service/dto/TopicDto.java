package am.aca.quiz_software.service.dto;

import am.aca.quiz_software.entity.SubCategoryEntity;
import am.aca.quiz_software.entity.TopicEntity;

import java.util.ArrayList;
import java.util.List;

public class TopicDto {
    private Long id;
    private String topicName;
    private SubCategoryEntity subCategory;

    public static TopicDto mapEntityToDto(TopicEntity topic) {
        TopicDto topicDto = new TopicDto();
        topicDto.setId(topic.getId());
        topicDto.setTopicName(topic.getTopicName());
        topicDto.setSubCategory(topic.getSubCategory());

        return topicDto;
    }

    public static List<TopicDto> mapEntityToDtos(List<TopicEntity> topicEntityList) {
        List<TopicDto> topicDtoList = new ArrayList<>();

        for (TopicEntity topicEntity : topicEntityList) {

            topicDtoList.add(mapEntityToDto(topicEntity));
        }
        return topicDtoList;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTopicName() {
        return topicName;
    }

    public void setTopicName(String topicName) {
        this.topicName = topicName;
    }

    public SubCategoryEntity getSubCategory() {
        return subCategory;
    }

    public void setSubCategory(SubCategoryEntity subCategory) {
        this.subCategory = subCategory;
    }
}
