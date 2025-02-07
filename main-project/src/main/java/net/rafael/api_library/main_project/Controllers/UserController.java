package net.rafael.api_library.main_project.Controllers;


import net.rafael.api_library.main_project.Dto.UserDto;
import net.rafael.api_library.main_project.Mapper.UserMapper;
import net.rafael.api_library.main_project.Services.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService service;
    private final UserMapper mapper;

    public UserController(UserService service, UserMapper mapper) {
        this.service = service;
        this.mapper = mapper;
    }

    @PostMapping("/new")
    @ResponseStatus(HttpStatus.CREATED)
    public void save(@RequestBody UserDto dto) {
        var user = mapper.toEntity(dto);
        service.save(user);
    }
}
