package am.aca.quiz_software.entities;

import javax.persistence.*;

@Entity
@Table(name = "answer")
public class AnswerEntity {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "description")
    private String description;

    @Column(name = "is_correct",nullable = false)
    private boolean is_correct;

    @Column(name = "answer_text",nullable = false)
    private String answer_text;

    @ManyToOne
    @JoinColumn(name = "question_id",insertable = false,updatable = false)
    private QuestionEntity questionEntity;

    public AnswerEntity() {
    }

    public AnswerEntity(String description, boolean is_correct, String answer_text, QuestionEntity questionEntity) {
        this.description = description;
        this.is_correct = is_correct;
        this.answer_text = answer_text;
        this.questionEntity = questionEntity;
    }

    public Long getId() {
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

    @Override
    public String toString() {
        return "AnswerEntity{" +
                "id=" + id +
                ", description='" + description + '\'' +
                ", is_correct=" + is_correct +
                ", answer_text='" + answer_text + '\'' +
                ", questionEntity=" + questionEntity +
                '}';
    }
}
