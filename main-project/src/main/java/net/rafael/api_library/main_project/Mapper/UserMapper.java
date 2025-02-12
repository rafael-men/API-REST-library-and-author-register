package net.rafael.api_library.main_project.Mapper;


import net.rafael.api_library.main_project.Dto.UserDto;
import net.rafael.api_library.main_project.Models.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface UserMapper {

    @Mapping(target = "email",source = "email")
    User toEntity(UserDto dto);
}
