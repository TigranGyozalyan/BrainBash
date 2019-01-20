package am.aca.quiz.software.service.mapper;

import am.aca.quiz.software.entity.ScoreEntity;
import am.aca.quiz.software.service.dto.ScoreDto;
import am.aca.quiz.software.service.mapper.structure.MapEntityToDto;

import java.util.ArrayList;
import java.util.List;

public class ScoreMapper implements MapEntityToDto<ScoreEntity, ScoreDto> {
    @Override
    public ScoreDto mapEntityToDto(ScoreEntity scoreEntity) {
        ScoreDto scoreDto = new ScoreDto();
        scoreDto.setId(scoreEntity.getId());
        scoreDto.setCount(scoreEntity.getCount());
        scoreDto.setValue(scoreEntity.getValue());

        return scoreDto;
    }

    @Override
    public List<ScoreDto> mapEntitiesToDto(List<ScoreEntity> scoreEntityList) {
        List<ScoreDto> scoreDtoList = new ArrayList<>();

        for (ScoreEntity scoreEntity : scoreEntityList) {
            scoreDtoList.add(mapEntityToDto(scoreEntity));
        }

        return scoreDtoList;
    }
}