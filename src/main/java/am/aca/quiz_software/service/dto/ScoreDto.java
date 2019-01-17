package am.aca.quiz_software.service.dto;

import am.aca.quiz_software.entity.ScoreEntity;
import am.aca.quiz_software.entity.TopicEntity;
import am.aca.quiz_software.entity.UserEntity;

import java.util.ArrayList;
import java.util.List;

public class ScoreDto {
    private Long id;
    private double value;
    private int count;
    private TopicEntity topicEntity;
    private UserEntity userEntity;

    public static ScoreDto mapEntityToDto(ScoreEntity scoreEntity) {
        ScoreDto scoreDto = new ScoreDto();
        scoreDto.setId(scoreEntity.getId());
        scoreDto.setValue(scoreEntity.getValue());
        scoreDto.setCount(scoreEntity.getCount());
        scoreDto.setTopicEntity(scoreEntity.getTopic());
        scoreDto.setUserEntity(scoreEntity.getUserEntity());

        return scoreDto;
    }

    public List<ScoreDto> mapEntityToDtos(List<ScoreEntity> scoreEntityList) {

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

    public TopicEntity getTopicEntity() {
        return topicEntity;
    }

    public void setTopicEntity(TopicEntity topicEntity) {
        this.topicEntity = topicEntity;
    }

    public UserEntity getUserEntity() {
        return userEntity;
    }

    public void setUserEntity(UserEntity userEntity) {
        this.userEntity = userEntity;
    }
}
