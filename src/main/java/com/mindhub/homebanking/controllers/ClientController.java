package com.mindhub.homebanking.controllers;

import com.mindhub.homebanking.DTOS.ClientDTO;
import com.mindhub.homebanking.models.Account;
import com.mindhub.homebanking.models.AccountType;
import com.mindhub.homebanking.models.Client;
import com.mindhub.homebanking.repositories.AccountRepository;
import com.mindhub.homebanking.repositories.ClientRepository;
import com.mindhub.homebanking.services.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.awt.*;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
public class ClientController {

    @Autowired
    ClientService clientService;
    
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private AccountRepository accountRepository;


    @RequestMapping("/clients")
    public List<ClientDTO> getClientsDTO() {

        return clientService.getClientsDTO();
    }

    @RequestMapping("/clients/{id}")
    public ClientDTO getClientDTO(@PathVariable Long id) {

        return clientService.getClientDTO(id);
    }


    public int getRandomNumber(int min,int max){return (int) ((Math.random() * (max - min)) + min);}

    @PostMapping("/clients")
    public ResponseEntity<Object> register(

            @RequestParam String firstName, @RequestParam String lastName,


            @RequestParam String email, @RequestParam String password) {



        if (firstName.isEmpty()) {

            return new ResponseEntity<>("Falta el nombre", HttpStatus.FORBIDDEN);

        }
        if (lastName.isEmpty()) {

            return new ResponseEntity<>("Falta el apellido", HttpStatus.FORBIDDEN);

        }
        if (email.isEmpty()) {

            return new ResponseEntity<>("Falta el email", HttpStatus.FORBIDDEN);

        }

        if (password.isEmpty()) {

            return new ResponseEntity<>("Falta la contraseña", HttpStatus.FORBIDDEN);

        }



        if (clientService.findByEmail(email) !=  null) {

            return new ResponseEntity<>("Email en uso", HttpStatus.FORBIDDEN);

        }

        Client client = new Client(firstName, lastName, email, passwordEncoder.encode(password));
        clientService.saveClient(client);
        Account account = new Account(client,"VIN"+ getRandomNumber(10000000, 99999999), LocalDateTime.now(),0.0, AccountType.AHORRO);
        accountRepository.save(account);

        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @RequestMapping("/clients/current")
    public ClientDTO getAuthenticationClient(Authentication authentication){
        return  new ClientDTO(clientService.findByEmail(authentication.getName()));

    }

}
