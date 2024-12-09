package net.rafael.api_library.main_project.Mapper;

import net.rafael.api_library.main_project.Dto.UserDto;
import net.rafael.api_library.main_project.Models.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {

    User toEntity(UserDto dto);
}
