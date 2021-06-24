package com.sda.ptiapp.service;

import com.sda.ptiapp.exception.ClientNotFoundException;
import com.sda.ptiapp.model.Client;
import com.sda.ptiapp.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClientService {

    ClientRepository clientRepository;

    @Autowired
    public ClientService(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    public void addClient(Client client) {
        clientRepository.save(client);
    }

    public List<Client> getAllClients() {
        return (List<Client>) clientRepository.findAll();
    }

    public Client findClientById(Integer id) {
        return clientRepository.findById(id)
                .orElseThrow(() -> new ClientNotFoundException("Client with id " + id + " not found"));
    }

    public void deleteClientById(Integer id) {
        Optional<Client> client = clientRepository.findById(id);
        if (client.isPresent()) {
            clientRepository.deleteById(client.get().getId());
        } else {
            throw new ClientNotFoundException("Client with id " + id + " not found");
        }
    }

    public void updateClientById(Integer id, Client newClient) {
        Client oldClient = findClientById(id);
        updateClient(newClient, oldClient);
        clientRepository.save(oldClient);
    }

    private void updateClient(Client newClient, Client oldClient) {
        oldClient.setEmail(newClient.getEmail());
        oldClient.setPhone(newClient.getPhone());
        oldClient.setName((newClient.getName()));
        oldClient.setCardNo(newClient.getCardNo());
    }
}
