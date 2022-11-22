package com.mindhub.homebanking.controllers;

import com.mindhub.homebanking.models.Account;
import com.mindhub.homebanking.models.Client;
import com.mindhub.homebanking.models.Transaction;
import com.mindhub.homebanking.models.TransactionType;
import com.mindhub.homebanking.services.AccountService;
import com.mindhub.homebanking.services.ClientService;
import com.mindhub.homebanking.services.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.Set;

@RestController
@RequestMapping("/api")
public class TransactionController {

    @Autowired
    TransactionService transactionService;

    @Autowired
    ClientService clientService;

    @Autowired
    AccountService accountService;

    @Transactional
    @PostMapping("/transactions")
    public ResponseEntity<Object> cratedTransaction(Authentication authentication,
                     @RequestParam Double amount, @RequestParam String description,
                     @RequestParam String numberDestiny, @RequestParam String numberOrigin
    ){

        Client client = clientService.findByEmail(authentication.getName());
        Account accountDestiny = accountService.findByNumber(numberDestiny);
        Account accountOrigin = accountService.findByNumber(numberOrigin);

        if (amount == null){
            return new ResponseEntity<>("Falta el monto", HttpStatus.FORBIDDEN);
        }
        if (description.isEmpty()){
            return new ResponseEntity<>("Falta la descripcion", HttpStatus.FORBIDDEN);
        }
        if (numberDestiny.isEmpty()){
            return new ResponseEntity<>("Falta la cuenta de destino", HttpStatus.FORBIDDEN);
        }
        if (numberOrigin.isEmpty()){
            return new ResponseEntity<>("Falta la cuenta de origen", HttpStatus.FORBIDDEN);
        }

        if (accountOrigin == null){
            return new ResponseEntity<>("La cuenta de origen no existe",HttpStatus.FORBIDDEN);
        }

        if (accountDestiny == null){
            return new ResponseEntity<>("La cuenta de destino no existe",HttpStatus.FORBIDDEN);
        }

        if (accountOrigin.equals(accountDestiny)){
            return new ResponseEntity<>("Cuentas iguales",HttpStatus.FORBIDDEN);
        }

        Set<Account> setAccounts= client.getAccounts();
        if (!setAccounts.contains(accountOrigin)){
            return new ResponseEntity<>("Cuenta de origen no pertenece",HttpStatus.FORBIDDEN);

        }

        if (accountOrigin.getBalance() < amount){
            return new ResponseEntity<>("Fondos insuficientes",HttpStatus.FORBIDDEN);
        }

        else {

            Transaction transactionOrigin = new Transaction(TransactionType.DEBITO, amount, description, LocalDateTime.now(), accountOrigin);
            Transaction transactionDestiny = new Transaction(TransactionType.CREDITO, amount, description, LocalDateTime.now(), accountDestiny);

            transactionService.saveTransaction(transactionOrigin);
            transactionService.saveTransaction(transactionDestiny);

            Double amountOrigin = accountOrigin.getBalance() - amount;
            Double amountDestiny = accountDestiny.getBalance() + amount;

            accountOrigin.setBalance(amountOrigin);
            accountDestiny.setBalance(amountDestiny);

            return new ResponseEntity<>("Transaccion exitosa",HttpStatus.CREATED);
        }






    }
}
