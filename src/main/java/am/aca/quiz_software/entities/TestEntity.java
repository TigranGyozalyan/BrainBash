package am.aca.quiz_software.entities;


import javax.persistence.*;
import java.sql.Date;
import java.util.List;


@Entity
@Table(name = "test")
public class TestEntity {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "test_name",nullable = false)
    private String test_name;

    @Column(name = "duration",nullable = false)
    private Date duration;

    @Column(name = "description",nullable = false)
    private String description;

    @ManyToMany
    private List<QuestionEntity> questionEntities;

    public TestEntity(String test_name, Date duration, String description, List<QuestionEntity> questionEntities) {
        this.test_name = test_name;
        this.duration = duration;
        this.description = description;
        this.questionEntities = questionEntities;
    }

    public TestEntity() {
    }

    public long getId() {
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
}
