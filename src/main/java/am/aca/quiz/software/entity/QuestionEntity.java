package am.aca.quiz.software.entity;


import am.aca.quiz.software.entity.enums.Level;

import javax.persistence.*;
import javax.validation.constraints.Min;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "questions", indexes = {@Index(name = "IDX_question", columnList = "question"),
        @Index(name = "IDX_topicId", columnList = "topic_entity_id")}
)
public class QuestionEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "question", nullable = false, columnDefinition = "varchar")
    private String question;

    @Min(value = 0, message = "Invalid Point Argument. Points must be >= 0")
    @Column(name = "points", nullable = false)
    private int points;

    @Enumerated(EnumType.STRING)
    @Column(name = "level", nullable = false)
    private Level level;

    @Min(value = 1, message = "The question must have at least one correct answer")
    @Column(name = "correct_answer_count", nullable = false)
    private int correct_amount;

    @OneToMany(mappedBy = "questionEntity", cascade = CascadeType.ALL)
    private List<AnswerEntity> answerEntities = new ArrayList<>();

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "question_test",
            joinColumns = {@JoinColumn(name = "question_id", updatable = false)},
            inverseJoinColumns = {@JoinColumn(name = "test_id", updatable = false)})
    private Set<TestEntity> testEntities = new HashSet<>();

    @ManyToOne
    private TopicEntity topicEntity;


    public QuestionEntity() {
    }

    public QuestionEntity(String question, @Min(value = 0, message = "Invalid Point Argument. Points must be >= 0") int points, String level, @Min(value = 1, message = "The question must have at least one correct answer") int correct_amount, TopicEntity topicEntity) {
        this.question = question;
        this.points = points;
        this.level = Level.getLevel(level);
        this.correct_amount = correct_amount;
        this.topicEntity = topicEntity;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public Level getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = Level.valueOf(level);
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

    public void setAnswerEntities(List<AnswerEntity> answerEntities) {
        this.answerEntities = answerEntities;
    }


    public Set<TestEntity> getTestEntities() {
        return testEntities;
    }

    public void setTestEntities(Set<TestEntity> testEntities) {
        this.testEntities = testEntities;
    }

    public TopicEntity getTopicEntity() {
        return topicEntity;
    }

    public void setTopicEntity(TopicEntity topicEntity) {
        this.topicEntity = topicEntity;
    }

    @Override
    public String toString() {
        return "QuestionEntity{" +
                "id=" + id +
                ", question='" + question + '\'' +
                ", points=" + points +
                ", level=" + level +
                ", correct_amount=" + correct_amount +
                '}';
    }
}


