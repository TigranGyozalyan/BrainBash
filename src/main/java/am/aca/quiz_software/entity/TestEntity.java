package am.aca.quiz_software.entity;


import javax.persistence.*;
import java.sql.Date;
import java.util.List;


@Entity
@Table(name = "test")
public class TestEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "test_name",nullable = false)
    private String test_name;

    @Column(name = "duration",nullable = false)
    private Date duration;

    @Column(name = "description",nullable = false)
    private String description;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "question_test",
            joinColumns = {@JoinColumn(name = "test_id")},
            inverseJoinColumns = {@JoinColumn(name = "question_id")})
    private List<QuestionEntity> questionEntities;

    @OneToMany(mappedBy = "testEntity",cascade = CascadeType.ALL)
    private List<HistoryEntity> historyEntities;


    public TestEntity(String test_name, Date duration, String description, List<QuestionEntity> questionEntities, List<HistoryEntity> historyEntities) {
        this.test_name = test_name;
        this.duration = duration;
        this.description = description;
        this.questionEntities = questionEntities;
        this.historyEntities = historyEntities;
    }

    public TestEntity() {
    }

    public Long getId() {
        return id;
    }

    public String getTest_name() {
        return this.test_name;
    }

    public Date getDuration() {
        return this.duration;
    }

    public String getDescription() {
        return this.description;
    }

    public void setTest_name(String test_name) {
        this.test_name = test_name;
    }

    public void setDuration(Date duration) {
        this.duration = duration;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setId(long id) {
        this.id = id;
    }

    public List<QuestionEntity> getQuestionEntities() {
        return questionEntities;
    }

    public void setQuestionEntities(List<QuestionEntity> questionEntities) {
        this.questionEntities = questionEntities;
    }

    public List<HistoryEntity> getHistoryEntities() {
        return historyEntities;
    }

    public void setHistoryEntities(List<HistoryEntity> historyEntities) {
        this.historyEntities = historyEntities;
    }

    @Override
    public String toString() {
        return "TestEntity{" +
                "id=" + id +
                ", test_name='" + test_name + '\'' +
                ", duration=" + duration +
                ", description='" + description + '\'' +
                ", questionEntities=" + questionEntities +
                ", historyEntities=" + historyEntities +
                '}';
    }
}
