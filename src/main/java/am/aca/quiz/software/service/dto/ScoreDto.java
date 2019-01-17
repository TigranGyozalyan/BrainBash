package am.aca.quiz.software.service.dto;

import am.aca.quiz.software.entity.ScoreEntity;

import java.util.ArrayList;
import java.util.List;

public class ScoreDto {
    private Long id;
    private double value;
    private int count;

    public static ScoreDto mapEntityToDto(ScoreEntity scoreEntity) {
        ScoreDto scoreDto = new ScoreDto();
        scoreDto.setId(scoreEntity.getId());
        scoreDto.setCount(scoreEntity.getCount());
        scoreDto.setValue(scoreEntity.getValue());

        return scoreDto;

    }

    public static List<ScoreDto> mapEntityToDtos(List<ScoreEntity> scoreEntityList) {
        List<ScoreDto> scoreDtoList = new ArrayList<>();

        for (ScoreEntity scoreEntity : scoreEntityList) {
            scoreDtoList.add(mapEntityToDto(scoreEntity));
        }

        return scoreDtoList;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
}
