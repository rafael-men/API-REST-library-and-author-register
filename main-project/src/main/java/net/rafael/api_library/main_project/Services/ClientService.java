package net.rafael.api_library.main_project.Services;

import net.rafael.api_library.main_project.Models.Client;
import net.rafael.api_library.main_project.Repository.ClientRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class ClientService {

    private final ClientRepository clientRepository;
    private final PasswordEncoder encoder;

    public ClientService(ClientRepository clientRepository, PasswordEncoder encoder) {
        this.clientRepository = clientRepository;
        this.encoder = encoder;
    }

    public Client saveClient(Client client) {
        var cryptoPassword = encoder.encode(client.getClientSecret());
        client.setClientSecret(cryptoPassword);
        return clientRepository.save(client);
    }

    public Client findByClientId(String clientId) {
        return clientRepository.findByClientId(clientId);
    }
}
