package am.aca.quiz.software.service.mapper;

import am.aca.quiz.software.entity.TopicEntity;
import am.aca.quiz.software.service.dto.TopicDto;
import am.aca.quiz.software.service.mapper.structure.MapEntityToDto;

import java.util.ArrayList;
import java.util.List;

public class TopicMapper implements MapEntityToDto<TopicEntity, TopicDto> {

    @Override
    public TopicDto mapEntityToDto(TopicEntity topicEntity) {
        TopicDto topicDto = new TopicDto();

        topicDto.setId(topicEntity.getId());
        topicDto.setTopicName(topicEntity.getTopicName());

        return topicDto;
    }

    @Override
    public List<TopicDto> mapEntitiesToDto(List<TopicEntity> topicEntityList) {
        List<TopicDto> topicDtoList = new ArrayList<>();
        for (TopicEntity topicEntity : topicEntityList) {
            topicDtoList.add(mapEntityToDto(topicEntity));
        }

        return topicDtoList;
    }
}
