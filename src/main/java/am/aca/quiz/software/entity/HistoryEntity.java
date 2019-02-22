package am.aca.quiz.software.entity;

import am.aca.quiz.software.entity.enums.Status;

import javax.persistence.*;
import javax.validation.constraints.Min;
import java.time.LocalDateTime;

@Entity
@Table(name = "history", indexes = {@Index(name = "IDX_STATUS", columnList = "status"),
    @Index(name = "IDX_userId_testId_status", columnList = "status,user_id,test_id"),
    @Index(name = "IDX_session_is", columnList = "session_id")}
)
public class HistoryEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "start_time", nullable = false, columnDefinition = "timestamp default CURRENT_DATE")
    private LocalDateTime startTime;

    @Column(name = "end_time")
    private LocalDateTime endTime;


    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    private Status status;

    @Min(value = 0, message = "Invalid Score Value")
    @Column(name = "score")
    private double score;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserEntity userEntity;

    @ManyToOne
    @JoinColumn(name = "test_id")
    private TestEntity testEntity;

    @Column(name = "session_id")
    private String sessionId;

    public HistoryEntity() {
    }

    public HistoryEntity(LocalDateTime startTime, Status status, @Min(value = 0, message = "Invalid Score Value") double score, UserEntity userEntity, TestEntity testEntity) {
        this.startTime = startTime;
        this.status = status;
        this.score = score;
        this.userEntity = userEntity;
        this.testEntity = testEntity;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDateTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalDateTime startTime) {
        this.startTime = startTime;
    }

//    public Status getStatus() {
//        return status;
//    }
//
//    public void setStatus(String status) {
//        this.status = Status.valueOf(status.toLowerCase());
//    }


    public String getSessionId() {
        return sessionId;
    }

    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }

    public LocalDateTime getEndTime() {
        return endTime;
    }

    public void setEndTime(LocalDateTime endTime) {
        this.endTime = endTime;
    }

    public double getScore() {
        return score;
    }

    public void setScore(double score) {
        this.score = score;
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
        return "HistoryDto{" +
            "id=" + id +
            ", startTime=" + startTime +
            ", endTime=" + endTime +
            ", status='" + status + '\'' +
            ", score=" + score +
            '}';
    }


}
