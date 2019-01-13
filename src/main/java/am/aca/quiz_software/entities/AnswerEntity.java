package am.aca.quiz_software.entities;

import javax.persistence.*;

@Entity
@Table(name = "answer")
public class AnswerEntity {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "description",nullable = false)
    private String description;

    @Column(name = "is_correct",nullable = false)
    private boolean is_correct;

    @Column(name = "answer_text",nullable = false)
    private String answer_text;

    @ManyToOne
    private QuestionEntity questionEntity;


    public long getId() {
        return id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isIs_correct() {
        return is_correct;
    }

    public void setIs_correct(boolean is_correct) {
        this.is_correct = is_correct;
    }

    public String getAnswer_text() {
        return answer_text;
    }

    public void setAnswer_text(String answer_text) {
        this.answer_text = answer_text;
    }

    public QuestionEntity getQuestionEntity() {
        return questionEntity;
    }

    public void setQuestionEntity(QuestionEntity questionEntity) {
        this.questionEntity = questionEntity;
    }
}
