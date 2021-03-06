package am.aca.quiz.software.entity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "topic", indexes = {@Index(name = "IDX_topic_name", columnList = "topic_name")})
public class TopicEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "topic_name", nullable = false)
    private String topicName;

    @ManyToOne
    @JoinColumn(name = "subcategory_id")
    private SubCategoryEntity subCategory;

    @OneToMany(mappedBy = "topicEntity", cascade = CascadeType.ALL)
    private List<QuestionEntity> questionEntities = new ArrayList<>();

    @OneToMany(mappedBy = "topic", cascade = CascadeType.ALL)
    private List<ScoreEntity> scoreEntityList = new ArrayList<>();

    public TopicEntity() {

    }

    public TopicEntity(String topicName, SubCategoryEntity subCategory) {
        this.topicName = topicName;
        this.subCategory = subCategory;
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

    public List<QuestionEntity> getQuestionEntities() {
        return questionEntities;
    }

    public void setQuestionEntities(List<QuestionEntity> questionEntities) {
        this.questionEntities = questionEntities;
    }

    public List<ScoreEntity> getScoreEntityList() {
        return scoreEntityList;
    }

    public void setScoreEntityList(List<ScoreEntity> scoreEntityList) {
        this.scoreEntityList = scoreEntityList;
    }

    @Override
    public String toString() {
        return "TopicEntity{" +
            "id=" + id +
            ", topicName='" + topicName + '\'' +
            ", subCategory=" + subCategory +
            '}';
    }
}
