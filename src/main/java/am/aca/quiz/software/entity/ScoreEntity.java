package am.aca.quiz.software.entity;

import javax.persistence.*;

@Entity
@Table(name = "average_score", indexes = {@Index(name = "IDX_usrID_topicID", columnList = "user_id,topic_id")})
public class ScoreEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "score_value", nullable = false, columnDefinition = "numeric default 0.0")
    private double value;

    @Column(name = "count", nullable = false, columnDefinition = "bigint default '0'")
    private int count;

    @ManyToOne
    @JoinColumn(name = "topic_id", updatable = false)
    private TopicEntity topic;

    @ManyToOne
    @JoinColumn(name = "user_id", updatable = false)
    private UserEntity userEntity;

    public ScoreEntity() {
    }

    public ScoreEntity(double value, int count, TopicEntity topic, UserEntity userEntity) {
        this.value = value;
        this.count = count;
        this.topic = topic;
        this.userEntity = userEntity;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
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
            '}';
    }
}
