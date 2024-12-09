package net.rafael.api_library.main_project.Controllers;

import net.rafael.api_library.main_project.Dto.UserDto;
import net.rafael.api_library.main_project.Mapper.UserMapper;
import net.rafael.api_library.main_project.Services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private final UserService service;

    private final UserMapper mapper;

    public UserController(UserService service, UserMapper mapper) {
        this.service = service;
        this.mapper = mapper;
    }

    @PostMapping("/new")
    @ResponseStatus(HttpStatus.CREATED)
    public void saveUser(@RequestBody UserDto dto) {
        var user = mapper.toEntity(dto);
        service.saveUser(user);
    }
}
