package net.rafael.api_library.main_project.Repository;

import net.rafael.api_library.main_project.Models.Client;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ClientRepository extends JpaRepository<Client, UUID> {
    Client findByClientId(String clientId);
}
