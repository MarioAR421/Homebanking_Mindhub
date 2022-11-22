package com.mindhub.homebanking.controllers;

import com.mindhub.homebanking.DTOS.AccountDTO;
import com.mindhub.homebanking.models.Account;
import com.mindhub.homebanking.models.AccountType;
import com.mindhub.homebanking.models.Card;
import com.mindhub.homebanking.models.Client;
import com.mindhub.homebanking.services.AccountService;
import com.mindhub.homebanking.services.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;


@RestController
@RequestMapping("/api")
public class AccountController {

    @Autowired
    private ClientService clientService;

    @Autowired
    private AccountService accountService;

    @RequestMapping("/accounts")
    public List<AccountDTO> getAccountDTO(){

        return accountService.getAccountsDTO();
    }

    @RequestMapping("/accounts/{id}")
    public AccountDTO getAccountDTO(@PathVariable Long id){
        return accountService.getAccountDTO(id);

    }
    public int getRandomNumber(int min,int max){
        return (int) ((Math.random() * (max - min)) + min);
    }

    @PostMapping("clients/current/accounts")

    public ResponseEntity<?> createAccount(@RequestParam AccountType type, Authentication authentication){

        Client clientCurrent = clientService.findByEmail(authentication.getName());

        if(clientCurrent.getAccounts().size() >= 3){
            return new ResponseEntity<>("limit accounts", HttpStatus.FORBIDDEN);
        }
        else{

            Account account = new Account(clientCurrent,"VIN"+getRandomNumber(10000000,99999999), LocalDateTime.now(),0.0,type);
            accountService.saveAccount(account);
            return new ResponseEntity<>(HttpStatus.CREATED);
        }

    }


    @DeleteMapping("account/delete")
    ResponseEntity<String> deleteAccount(@RequestParam String number){
        Account account = accountService.findByNumber(number);
        accountService.deleteAccount(account);
        return new ResponseEntity<>("Se elimino con exito",HttpStatus.CREATED);
    }


}
