package am.aca.quiz_software.entities;

import javax.persistence.*;

@Entity
@Table(name = "topic")
public class TopicEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "topic_name",nullable = false)
    private String topicName;

   @ManyToOne
    private SubCategoryEntity subCategory;

    public long getId() {
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
}
