package am.aca.quiz_software.entities;

import javax.persistence.*;

@Entity
@Table(name = "average_score")
public class ScoreEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "score_value",nullable = false)
    private double value;

    @ManyToOne
    @JoinColumn(name = "topic_id",insertable = false,updatable = false)
    private TopicEntity topic;

    @ManyToOne
    @JoinColumn(name = "user_id",insertable = false,updatable = false)
    private UserEntity userEntity;

    public ScoreEntity() {
    }

    public ScoreEntity(long id, double value, TopicEntity topic, UserEntity userEntity) {
        this.id = id;
        this.value = value;
        this.topic = topic;
        this.userEntity = userEntity;
    }

    public Long getId() {
        return id;
    }

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }

    public TopicEntity getTopic() {
        return topic;
    }

    public void setTopic(TopicEntity topic) {
        this.topic = topic;
    }

    public UserEntity getUserEntity() {
        return userEntity;
    }

    public void setUserEntity(UserEntity userEntity) {
        this.userEntity = userEntity;
    }

    @Override
    public String toString() {
        return "ScoreEntity{" +
                "id=" + id +
                ", value=" + value +
                ", topic=" + topic +
                ", userEntity=" + userEntity +
                '}';
    }
}
