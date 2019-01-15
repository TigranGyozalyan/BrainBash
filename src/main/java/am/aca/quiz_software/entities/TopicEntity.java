package am.aca.quiz_software.entities;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "topic")
public class TopicEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "topic_name",nullable = false)
    private String topicName;

   @ManyToOne
   @JoinColumn(name = "subcategory_id",insertable = false,updatable = false)
   private SubCategoryEntity subCategory;

   @OneToMany(mappedBy = "topicEntity")
   private List<QuestionEntity> questionEntities;

   @OneToMany(mappedBy = "topic")
   private  List<ScoreEntity> scoreEntityList;

   public TopicEntity(){

   }

    public TopicEntity(String topicName, SubCategoryEntity subCategory, List<QuestionEntity> questionEntities, List<ScoreEntity> scoreEntityList) {
        this.topicName = topicName;
        this.subCategory = subCategory;
        this.questionEntities = questionEntities;
        this.scoreEntityList = scoreEntityList;
    }

    public Long getId() {
        return id;
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

    @Override
    public String toString() {
        return "TopicEntity{" +
                "id=" + id +
                ", topicName='" + topicName + '\'' +
                ", subCategory=" + subCategory +
                '}';
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
}
