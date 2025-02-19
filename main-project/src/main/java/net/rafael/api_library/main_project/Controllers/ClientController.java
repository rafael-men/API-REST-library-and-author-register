package net.rafael.api_library.main_project.Controllers;

import net.rafael.api_library.main_project.Models.Client;
import net.rafael.api_library.main_project.Services.ClientService;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/clients")
public class ClientController {

    private final ClientService service;

    public ClientController(ClientService service) {
        this.service = service;
    }

    @PostMapping("/new")
    @ResponseStatus(HttpStatus.CREATED)
    @PreAuthorize("hasRole('ADMIN')")
    public void saveClient(@RequestBody Client client) {
        service.saveClient(client);
    }
}
