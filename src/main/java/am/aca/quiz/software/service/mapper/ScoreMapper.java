package am.aca.quiz.software.service.mapper;

import am.aca.quiz.software.entity.ScoreEntity;
import am.aca.quiz.software.service.dto.ScoreDto;
import am.aca.quiz.software.service.implementations.TopicServiceImp;
import am.aca.quiz.software.service.mapper.structure.MapEntityToDto;
import org.springframework.stereotype.Component;

import java.sql.SQLException;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class ScoreMapper implements MapEntityToDto<ScoreEntity, ScoreDto> {

    private TopicMapper topicMapper;
    private TopicServiceImp topicServiceImp;

    public ScoreMapper(TopicMapper topicMapper, TopicServiceImp topicServiceImp) {
        this.topicMapper = topicMapper;
        this.topicServiceImp = topicServiceImp;
    }

    @Override
    public ScoreDto mapEntityToDto(ScoreEntity scoreEntity) {
        ScoreDto scoreDto = new ScoreDto();

        scoreDto.setId(scoreEntity.getId());
        scoreDto.setCount(scoreEntity.getCount());
        scoreDto.setValue(scoreEntity.getValue());

        Long userId = scoreEntity.getUserEntity().getId();
        Long topicId = scoreEntity.getTopic().getId();

        scoreDto.setUserId(userId);
        scoreDto.setTopicId(topicId);

        try {
            scoreDto.setTopic(topicMapper.mapEntityToDto(topicServiceImp.getById(topicId)));
        } catch (SQLException e) {
            e.printStackTrace();
        }


        return scoreDto;
    }

    @Override
    public List<ScoreDto> mapEntitiesToDto(List<ScoreEntity> scoreEntityList) {

        return scoreEntityList
                .stream()
                .map(this::mapEntityToDto)
                .collect(Collectors.toList());

    }
}
