package am.aca.quiz.software.service.dto;

import am.aca.quiz.software.entity.UserEntity;

import java.util.ArrayList;
import java.util.List;

public class UserDto {

    private Long id;
    private String name;
    private String surname;
    private String email;
    private String nickname;
    private String password;
    private boolean is_admin;
    private String image;

    public static UserDto mapEntityToDto(UserEntity userEntity){
        UserDto userDto=new UserDto();
        userDto.setEmail(userEntity.getEmail());
        userDto.setId(userEntity.getId());
        userDto.setImage(userEntity.getImage());
        userDto.setIs_admin(userEntity.getIs_admin());
        userDto.setName(userEntity.getName());
        userDto.setNickname(userEntity.getNickname());
        userDto.setPassword(userEntity.getPassword());
        userDto.setSurname(userEntity.getSurname());

        return userDto;
    }
    public static List<UserDto> mapEntitiesToDto(List<UserEntity> userEntityList){

        List<UserDto> userDtoList=new ArrayList<>();
        for(UserEntity userEntity: userEntityList){
            userDtoList.add(mapEntityToDto(userEntity));
        }
        return userDtoList;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public boolean isIs_admin() {
        return is_admin;
    }

    public void setIs_admin(boolean is_admin) {
        this.is_admin = is_admin;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
