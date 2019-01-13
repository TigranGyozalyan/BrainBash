package am.aca.quiz_software.entities;

import javax.persistence.*;
import java.sql.Date;

@Entity
@Table(name = "history")
public class HistoryEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;
    @Column(name = "start_time",nullable = false)
    private Date startTime;
    @Column(name = "end_time")
    private Date endTime;
    @Column(name = "status",nullable = false,columnDefinition = "upcoming")
    private String status;
    @Column(name = "score")
    private double score;
    @ManyToOne
    private UserEntity userEntity;
    @ManyToOne
    private TestEntity testEntity;

    public HistoryEntity(Date startTime, Date endTime, String status, double score, UserEntity userEntity, TestEntity testEntity) {
        this.startTime = startTime;
        this.endTime = endTime;
        this.status = status;
        this.score = score;
        this.userEntity = userEntity;
        this.testEntity = testEntity;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
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
