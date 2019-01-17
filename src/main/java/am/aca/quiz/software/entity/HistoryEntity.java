package am.aca.quiz.software.entity;

import javax.persistence.*;
import java.sql.Date;

@Entity
@Table(name = "history")
public class HistoryEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Long id;
    @Column(name = "start_time",nullable = false)
    private Date startTime;
    @Column(name = "end_time")
    private Date endTime;
    @Column(name = "status",nullable = false)
    private String status;
    @Column(name = "score")
    private double score;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id",insertable = false,updatable = false)
    private UserEntity userEntity;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "test_id",insertable = false,updatable = false)
    private TestEntity testEntity;

    public HistoryEntity() {
    }

    public HistoryEntity(Date startTime, Date endTime, String status, double score, UserEntity userEntity, TestEntity testEntity) {
        this.startTime = startTime;
        this.endTime = endTime;
        this.status = status;
        this.score = score;
        this.userEntity = userEntity;
        this.testEntity = testEntity;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public double getScore() {
        return score;
    }

    public void setScore(double score) {
        this.score = score;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public UserEntity getUserEntity() {
        return userEntity;
    }

    public void setUserEntity(UserEntity userEntity) {
        this.userEntity = userEntity;
    }

    public TestEntity getTestEntity() {
        return testEntity;
    }

    public void setTestEntity(TestEntity testEntity) {
        this.testEntity = testEntity;
    }

    @Override
    public String toString() {
        return "HistoryEntity{" +
                "id=" + id +
                ", startTime=" + startTime +
                ", endTime=" + endTime +
                ", status='" + status + '\'' +
                ", score=" + score +
                ", userEntity=" + userEntity +
                ", testEntity=" + testEntity +
                '}';
    }
}