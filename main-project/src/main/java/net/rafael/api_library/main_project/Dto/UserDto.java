package net.rafael.api_library.main_project.Dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

import java.util.List;

public record UserDto(String login, String password, @Email(message = "email inválido") @NotBlank(message = "campo obrigatório") String email, List<String> roles) {
}
