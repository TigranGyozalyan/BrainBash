package am.aca.quiz.software.entity;


import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;


@Entity
@Table(name = "test")
public class TestEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "test_name",nullable = false)
    private String test;

    @Column(name = "duration",nullable = false)
    private long duration;

    @Column(name = "description",nullable = false,columnDefinition = "text")
    private String description;

    @ManyToMany
    @JoinTable(name = "question_test",
            joinColumns = {@JoinColumn(name = "test_id")},
            inverseJoinColumns = {@JoinColumn(name = "question_id")})
    private List<QuestionEntity> questionEntities=new ArrayList<>();

    @OneToMany(mappedBy = "testEntity",cascade = CascadeType.ALL)
    private List<HistoryEntity> historyEntities=new ArrayList<>();


    public TestEntity(String test_name,  String description, long duration , List<QuestionEntity> questionEntities) {
        this.test = test_name;
        this.duration = duration;
        this.description = description;
        this.questionEntities = questionEntities;
    }

    public TestEntity() {
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public String getTest_name() {
        return this.test;
    }

    public long getDuration() {
        return this.duration;
    }

    public String getDescription() {
        return this.description;
    }

    public void setTest_name(String test_name) {
        this.test = test_name;
    }

    public void setDuration(long duration) {
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
                ", test_name='" + test + '\'' +
                ", duration=" + duration +
                ", description='" + description + '\'' +
                '}';
    }
}
