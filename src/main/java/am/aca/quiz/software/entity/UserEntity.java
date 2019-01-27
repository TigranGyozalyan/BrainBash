package am.aca.quiz.software.entity;

import am.aca.quiz.software.entity.enums.Role;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "users")
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "first_name", nullable = false)
    private String name;
    @Column(name = "last_name", nullable = false)
    private String surname;

    @Column(name = "emails", nullable = false, unique = true)
    // @Pattern(regexp="^[\\w-\\+]+(\\.[\\w]+)@[\\w-]+(\\.[\\w]+)(\\.[a-z]{2,})$", message="Invalid email address!")
    @Email(message = "Please provide a valid email address")
    private String email;

    @Size(min = 3)
    @Pattern(regexp = "^[\\\\p{L} .'-]+$", message = "Invalid Nickname")
    @Column(name = "nickname", nullable = false)
    private String nickname;

    @Size(min = 6)
    @Column(name = "passwords", nullable = false)
    private String password;

    @Enumerated(EnumType.STRING)
    @Column(name = "role", nullable = false)
    private Role role;

    @Column(name = "avatar_image", columnDefinition = "text default 'C:\\Users\\User\\Desktop\\picture\\pic.jpg'")
    private String image;

    @OneToMany(mappedBy = "userEntity", cascade = CascadeType.ALL)
    private List<HistoryEntity> historyList = new ArrayList<>();

    @OneToMany(mappedBy = "userEntity", cascade = CascadeType.ALL)
    private List<ScoreEntity> scoreList = new ArrayList<>();

    public UserEntity() {
    }


    public UserEntity(String name, String surname, String email, String nickname, String password, Role role) {
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.nickname = nickname;
        this.password = password;
        this.role = role;

    }

    public void setId(Long id) {
        this.id = id;
    }


    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public List<HistoryEntity> getHistoryList() {
        return historyList;
    }

    public void setHistoryList(List<HistoryEntity> historyList) {
        this.historyList = historyList;
    }

    public List<ScoreEntity> getScoreList() {
        return scoreList;
    }

    public void setScoreList(List<ScoreEntity> scoreList) {
        this.scoreList = scoreList;
    }

    @Override
    public String toString() {
        return "UserEntity{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", email='" + email + '\'' +
                ", nickname='" + nickname + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
