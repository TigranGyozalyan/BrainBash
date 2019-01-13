package am.aca.quiz_software.entities;

import javax.persistence.*;

@Entity
@Table(name = "average_score")
public class ScoreEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private long id;
    @Column(name = "score_value",nullable = false,columnDefinition = "0")
    private double value;

    @ManyToOne(cascade = CascadeType.ALL)
    private TopicEntity topic;
    @ManyToOne(cascade = CascadeType.ALL)
    private UserEntity userEntity;

    public ScoreEntity() {
    }

    public ScoreEntity(long id, double value, TopicEntity topic, UserEntity userEntity) {
        this.id = id;
        this.value = value;
        this.topic = topic;
        this.userEntity = userEntity;
    }

    public long getId() {
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
