package am.aca.quiz.software.entity;


import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "questions")
public class QuestionEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "question",nullable = false)
    private String question;

    @Column(name = "points",nullable = false)
    private int points;

    @Column (name = "level",nullable = false)
    private int level;

    @Column (name = "correct_amount",nullable = false)
    private int correct_amount;

    @OneToMany(mappedBy = "questionEntity",cascade = CascadeType.ALL)
    private List<AnswerEntity> answerEntities=new ArrayList<>();

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "question_test",
            joinColumns = {@JoinColumn(name = "question_id",insertable = false,updatable = false)},
            inverseJoinColumns = {@JoinColumn(name = "test_id",insertable = false,updatable = false)})
    private List<TestEntity> testEntities=new ArrayList<>();

    @ManyToOne(cascade = CascadeType.ALL)
    private TopicEntity topicEntity;

    public QuestionEntity() {
    }

    public QuestionEntity(String question, int points, int level, int correct_amount) {
        this.question = question;
        this.points = points;
        this.level = level;
        this.correct_amount = correct_amount;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<TestEntity> getTestEntities() {
        return testEntities;
    }

    public void setTestEntities(List<TestEntity> testEntities) {
        this.testEntities = testEntities;
    }

    public TopicEntity getTopicEntity() {
        return topicEntity;
    }

    public void setTopicEntity(TopicEntity topicEntity) {
        this.topicEntity = topicEntity;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public int getCorrect_amount() {
        return correct_amount;
    }

    public void setCorrect_amount(int correct_amount) {
        this.correct_amount = correct_amount;
    }

    public List<AnswerEntity> getAnswerEntities() {
        return answerEntities;
    }

    public void setAnswerEntities(List<AnswerEntity> anwerEntities) {
        this.answerEntities = anwerEntities;
    }

    public Long getId() {
        return id;
    }

    @Override
    public String toString() {
        return "QuestionEntity{" +
                "id=" + id +
                ", question='" + question + '\'' +
                ", points=" + points +
                ", level=" + level +
                ", correct_amount=" + correct_amount +
                ", answerEntities=" + answerEntities +
                ", testEntities=" + testEntities +
                ", topicEntity=" + topicEntity +
                '}';
    }
}
