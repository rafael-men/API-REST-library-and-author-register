package net.rafael.api_library.main_project.Dto;


import java.util.List;

public record UserDto(String login, String password, List<String> roles) {
}
