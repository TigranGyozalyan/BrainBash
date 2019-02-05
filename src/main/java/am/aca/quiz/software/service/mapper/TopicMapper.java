package am.aca.quiz.software.service.mapper;

import am.aca.quiz.software.entity.TopicEntity;
import am.aca.quiz.software.service.dto.TopicDto;
import am.aca.quiz.software.service.mapper.structure.MapEntityToDto;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class TopicMapper implements MapEntityToDto<TopicEntity, TopicDto> {
    @Override
    public TopicDto mapEntityToDto(TopicEntity topicEntity) {
        TopicDto topicDto = new TopicDto();

        topicDto.setId(topicEntity.getId());
        topicDto.setTopicName(topicEntity.getTopicName());

        Long subCategoryId = topicEntity.getSubCategory().getId();
        topicDto.setSubCategoryId(subCategoryId);

        return topicDto;
    }

    @Override
    public List<TopicDto> mapEntitiesToDto(List<TopicEntity> topicEntityList) {
//        List<TopicDto> topicDtoList = new ArrayList<>();
//        for (TopicEntity topicEntity : topicEntityList) {
//            topicDtoList.add(mapEntityToDto(topicEntity));
//        }
//        return topicDtoList;

        return topicEntityList
                .stream()
                .map(this::mapEntityToDto)
                .collect(Collectors.toList());

    }
}
