package am.aca.quiz_software.entities;


import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "questions")
public class QuestionEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "question",nullable = false)
    private String question;

    @Column(name = "points",nullable = false)
    private int points;

    @Column (name = "level",nullable = false)
    private int level;

    @Column (name = "correct_amount",nullable = false)
    private int correct_amount;

    @OneToMany(mappedBy = "question")
    private List<AnswerEntity> answerEntities;

    @ManyToMany
    private List<TestEntity> testEntities;

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

    public long getId() {
        return id;
    }
}
