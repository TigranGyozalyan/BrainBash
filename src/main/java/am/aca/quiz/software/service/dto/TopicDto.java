package am.aca.quiz.software.service.dto;

import am.aca.quiz.software.entity.TopicEntity;

import java.util.ArrayList;
import java.util.List;

public class TopicDto {
    private Long id;
    private String topicName;

    public static TopicDto mapEntityToDto(TopicEntity topicEntity) {
        TopicDto topicDto = new TopicDto();

        topicDto.setId(topicEntity.getId());
        topicDto.setTopicName(topicEntity.getTopicName());

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
}
