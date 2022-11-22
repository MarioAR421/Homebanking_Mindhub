package com.mindhub.homebanking;

import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.mindhub.homebanking.models.*;
import com.mindhub.homebanking.repositories.*;
import net.bytebuddy.dynamic.scaffold.TypeInitializer;
import org.hamcrest.Matcher;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import javax.swing.*;

import static com.mindhub.homebanking.models.TransactionType.DEBITO;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

import java.time.LocalDateTime;
import java.util.List;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)

public class RepositoriesTest {
/*
    @Autowired
    LoanRepository loanRepository;

    @Autowired
    ClientRepository clientRepository;

    @Autowired
    AccountRepository accountRepository;

    @Autowired
    CardRepository cardRepository;

    @Autowired
    TransactionRepository transactionRepository;

    @Test
    public void existLoans(){
        List<Loan> loans = loanRepository.findAll();
        assertThat(loans,is(not(empty())));
    }

    @Test
    public void existPersonalLoan(){
        List<Loan> loans = loanRepository.findAll();
        assertThat(loans, hasItem(hasProperty("name", is("Personal"))));
    }

    @Test
    public void ExistClient(){
        Client client = clientRepository.findByEmail("melba@mindhub.com");
        assertThat(client,hasProperty("firstName", is("Melba")));
    }

    @Test
    public void CreateClient(){
        Client client = new Client("<Juan>", "Perez", "juan@mindhub.com","123");
        Client client1 = clientRepository.findByEmail("juan@mindhub.com");
        assertThat(client, equalTo(client1));
    }

    @Test
    public void findAccount(){
        Account account = accountRepository.findByNumber("VIN001");
        assertThat(account,is(not(null)));

    }

    @Test
    public void findCard(){
        Card card = cardRepository.findByNumber("9999-9999-9999-9999");
        assertThat(card,is(not(null)));
    }

    @Test
    public void existTransactions(){
        List<Transaction> transactions = transactionRepository.findAll();
        assertThat(transactions,is(not(empty())));
    }
*/



}
