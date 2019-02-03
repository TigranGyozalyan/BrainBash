package am.aca.quiz.software.entity;

import am.aca.quiz.software.entity.enums.Role;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.Size;
import java.util.*;

@Entity
@Table(name = "users")
public class UserEntity implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "first_name", nullable = false)
    private String name;
    @Column(name = "last_name", nullable = false)
    private String surname;

    @Column(name = "emails", nullable = false, unique = true)
    @Email(message = "Please provide a valid email address")
    private String email;

    @Column(name = "active",nullable = false)
    private boolean active;

    @Size(min = 3)
    @Column(name = "nickname", nullable = false)
    private String nickname;

    @Size(min = 6)
    @Column(name = "passwords", nullable = false)
    private String password;

    @Column(name="activationCode")
    private String activationCode;


    @ElementCollection(targetClass = Role.class, fetch = FetchType.EAGER)
    @CollectionTable(name = "user_role", joinColumns = @JoinColumn(name = "user_id"))
    @Enumerated(EnumType.STRING)
    private Set<Role> roles=new HashSet<>();

    @OneToMany(mappedBy = "userEntity", cascade = CascadeType.ALL)
    private List<HistoryEntity> historyList = new ArrayList<>();

    @OneToMany(mappedBy = "userEntity", cascade = CascadeType.ALL)
    private List<ScoreEntity> scoreList = new ArrayList<>();



    public UserEntity() {
    }


    public UserEntity(String name, String surname, String email,String nickname, String password) {
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.nickname = nickname;
        this.password = password;

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

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
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

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return isActive();
    }

    public void setUsername(String username) {
        this.email = username;
    }
    @Override
    public String getUsername() {
        return this.email;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return getRoles();
    }

    public String getActivationCode() {
        return activationCode;
    }

    public void setActivationCode(String activationCode) {
        this.activationCode = activationCode;
    }
}