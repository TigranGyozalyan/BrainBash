package am.aca.quiz.software.service.mapper;

import am.aca.quiz.software.entity.UserEntity;
import am.aca.quiz.software.service.dto.UserDto;
import am.aca.quiz.software.service.mapper.structure.MapEntityToDto;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class UserMapper implements MapEntityToDto<UserEntity, UserDto> {
    @Override
    public UserDto mapEntityToDto(UserEntity userEntity) {
        UserDto userDto=new UserDto();
        userDto.setEmail(userEntity.getEmail());
        userDto.setId(userEntity.getId());
        userDto.setRole(userEntity.getRoles());
        userDto.setName(userEntity.getName());
        userDto.setNickname(userEntity.getNickname());
        userDto.setPassword(userEntity.getPassword());
        userDto.setSurname(userEntity.getSurname());

        return userDto;
    }

    @Override
    public List<UserDto> mapEntitiesToDto(List<UserEntity> userEntityList) {


        return userEntityList
                .stream()
                .map(this::mapEntityToDto)
                .collect(Collectors.toList());

    }
}
