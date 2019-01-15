package am.aca.quiz_software.entity;


import javax.persistence.*;
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

    @OneToMany(mappedBy = "questionEntity")
    private List<AnswerEntity> answerEntities;

    @ManyToMany
    @JoinTable(name = "question_test",
            joinColumns = {@JoinColumn(name = "question_id",insertable = false,updatable = false)},
            inverseJoinColumns = {@JoinColumn(name = "test_id",insertable = false,updatable = false)})
    private List<TestEntity> testEntities;

    @ManyToOne
    private TopicEntity topicEntity;

    public QuestionEntity() {
    }

    public QuestionEntity(String question, int points, int level, int correct_amount, List<AnswerEntity> answerEntities, List<TestEntity> testEntities) {
        this.question = question;
        this.points = points;
        this.level = level;
        this.correct_amount = correct_amount;
        this.answerEntities = answerEntities;
        this.testEntities = testEntities;
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
