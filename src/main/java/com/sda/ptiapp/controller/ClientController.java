package com.sda.ptiapp.controller;

import com.sda.ptiapp.model.Client;
import com.sda.ptiapp.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/clients")
@CrossOrigin
public class ClientController {

    private final ClientService service;

    @Autowired
    public ClientController(ClientService service) {
        this.service = service;
    }

    @GetMapping("/all")
    public ResponseEntity<List<Client>> getAllClients() {
        return ResponseEntity.ok(service.getAllClients());
    }

    @GetMapping("/getById/{id}")
    public ResponseEntity<Client> getClientById(@PathVariable("id") Integer id) {
        return ResponseEntity.ok(service.findClientById(id));
    }

    @PostMapping("/add")
    public void addClient(@RequestBody Client client) {
        service.addClient(client);
    }

    @PutMapping("/update/{id}")
    public void updateClient(@PathVariable("id") Integer id, @RequestBody Client client) {
        service.updateClientById(id, client);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteClient(@PathVariable("id") Integer id) {
        service.deleteClientById(id);
    }
}
