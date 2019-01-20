package am.aca.quiz.software.service.dto;

import am.aca.quiz.software.entity.TopicEntity;

import java.util.ArrayList;
import java.util.List;

public class TopicDto {
    private Long id;
    private String topicName;

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
