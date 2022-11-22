package com.mindhub.homebanking.controllers;

import com.mindhub.homebanking.DTOS.LoanApplicationDTO;
import com.mindhub.homebanking.DTOS.LoanDTO;
import com.mindhub.homebanking.models.*;
import com.mindhub.homebanking.services.AccountService;
import com.mindhub.homebanking.services.ClientService;
import com.mindhub.homebanking.services.LoanService;
import com.mindhub.homebanking.services.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.lang.reflect.Array;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/api")
public class LoanController {

    @Autowired
    LoanService loanService;

    @Autowired
    ClientService clientService;

    @Autowired
    AccountService accountService;

    @Autowired
    TransactionService transactionService;



    @RequestMapping("/loans")
    public List<LoanDTO> getLoanDTO(){

        return loanService.getLoanDTO();
    }

    @PostMapping("create/loan")
    ResponseEntity<?> createLoan(@RequestParam String name, @RequestParam Double mount ,@RequestParam List<Integer> payments){
        Loan loan= new Loan(name,mount,payments);
        loanService.saveLoan(loan);
        return new ResponseEntity<>("Prestamo creado",HttpStatus.CREATED);

    }

    @Transactional
    @PostMapping("/loans")
    public ResponseEntity<?> newLoan(@RequestBody LoanApplicationDTO loanApplicationDTO, Authentication authentication){

        Client client = clientService.findByEmail(authentication.getName());
        Loan loan = loanService.findByName(loanApplicationDTO.getLoanTypeId());
        Account account = accountService.findByNumber(loanApplicationDTO.getNumberAccount());

        if (loan==null){
            return new ResponseEntity<>("Falta el tipo de prestamo",HttpStatus.FORBIDDEN);
        }

        if (loanApplicationDTO.getNumberAccount().isEmpty()) {
            return new ResponseEntity<>("Error falta la cuenta", HttpStatus.FORBIDDEN);
        }


        if (loanApplicationDTO.getAmount() == 0) {
            return new ResponseEntity<>("Error falta el monto", HttpStatus.FORBIDDEN);
        }


        if (loanApplicationDTO.getPayments() == 0 || loanApplicationDTO.getPayments() == null) {
            return new ResponseEntity<>("Error falta las cuotas ", HttpStatus.FORBIDDEN);
        }

        if(account== null){
            return new ResponseEntity<>("La cuenta no existe",HttpStatus.FORBIDDEN);

        }

        if (!client.getAccounts().contains(account)){
            return new ResponseEntity<>("Cuenta equivocada",HttpStatus.FORBIDDEN);
        }

        if (loanApplicationDTO.getAmount()>loan.getMaxAmount() || loanApplicationDTO.getAmount() <=0){
            return new ResponseEntity<>("Error en el monto del prestamo",HttpStatus.FORBIDDEN);
        }

        if (!loan.getPayments().contains(loanApplicationDTO.getPayments())){
            return new ResponseEntity<>("Error en cantidad de cuotas ",HttpStatus.FORBIDDEN);
        }

        Double amountLoan= 0.0;

        if(loan.getName().equals("Hipotecario")){
            amountLoan = loanApplicationDTO.getAmount() + (loanApplicationDTO.getAmount()*20/100);

        }

        if(loan.getName().equals("Automotriz")){
            amountLoan = loanApplicationDTO.getAmount() + (loanApplicationDTO.getAmount()*10/100);

        }

        if(loan.getName().equals("Personal")){
            amountLoan = loanApplicationDTO.getAmount() + (loanApplicationDTO.getAmount()*5/100);

        }

        ClientLoan clientLoan = new ClientLoan(amountLoan,loanApplicationDTO.getPayments(),client,loan);
        Transaction transaction = new Transaction(TransactionType.CREDITO,loanApplicationDTO.getAmount(),"Prestamo "+loan.getName(), LocalDateTime.now(),account);
        clientService.saveClientLoan(clientLoan);
        transactionService.saveTransaction(transaction);
        Double amountNew = account.getBalance()+loanApplicationDTO.getAmount();
        account.setBalance(amountNew);

        accountService.saveAccount(account);

        return new ResponseEntity<>("Prestamo aprobado",HttpStatus.CREATED);

    }




}
