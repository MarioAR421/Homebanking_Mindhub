package com.mindhub.homebanking;

import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.mindhub.homebanking.models.*;
import com.mindhub.homebanking.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

import static com.mindhub.homebanking.models.AccountType.AHORRO;
import static com.mindhub.homebanking.models.AccountType.CORRIENTE;
import static com.mindhub.homebanking.models.TransactionType.CREDITO;
import static com.mindhub.homebanking.models.TransactionType.DEBITO;

@SpringBootApplication
 public class HomebankingApplication {

	@Autowired

	PasswordEncoder passwordEncoder;


	 public static void main(String[] args) {
		SpringApplication.run(HomebankingApplication.class, args);



	}


 @Bean
	 public CommandLineRunner initData(ClientRepository clientRepository, AccountRepository accountRepository, TransactionRepository transactionRepository, LoanRepository loanRepository,ClientLoanRepository clientLoanRepository, CardRepository cardRepository){
		 return args -> {

			 Client client1 = new Client("Melba", "Morel", "melba@mindhub.com",passwordEncoder.encode("05qwerty"));

			 Client client2 = new Client("Mario", "Rodriguez", "mario@mindhub.com", passwordEncoder.encode("86ytrewq"));


			 Account account1 = new Account(client1,"VIN001", LocalDateTime.now(), 5000.000,CORRIENTE);

			 Account account2 = new Account(client1,"VIN002",LocalDateTime.now().plusDays(1),7500.000,AHORRO);

			 Account account3 = new Account(client2, "VIN003", LocalDateTime.now(), 7000.000,AHORRO);


			 Transaction transaction1 = new Transaction(DEBITO, 500.000, "ALGO", LocalDateTime.now(),account1);

			 Transaction transaction2 = new Transaction(CREDITO, 200.000, "Otra cosa", LocalDateTime.now(),account1);

			 Transaction transaction3 = new Transaction(DEBITO, 700.000, "Comida", LocalDateTime.now(),account3);


			 List<Integer> payments = Arrays.asList (12,24,36,48,60);
			 List<Integer> payments2 = Arrays.asList(6,12,24);
			 List<Integer> payments3 = Arrays.asList(6,12,24,36);


			 Loan loan1 = new Loan("Hipotecario",500000.000,payments);
			 Loan loan3 = new Loan("Automotriz", 300000.000,payments3);
			 Loan loan2 = new Loan("Personal", 100000.000,payments2);


			 ClientLoan clientLoan1 = new ClientLoan(400.000, 60, client1, loan1);
			 ClientLoan clientLoan2 = new ClientLoan(500.000, 12, client1, loan2);
			 ClientLoan clientLoan3 = new ClientLoan(100.000, 24, client2, loan2);
			 ClientLoan clientLoan4 = new ClientLoan(200.000, 36, client2, loan3);


			 Card card1 = new Card(client1,client1.getFirstName()+" "+client1.getLastName(),CardType.DEBIT,CardColor.GOLD,"9999-9999-9999-9999",530,LocalDateTime.now(),LocalDateTime.now().minusYears(5));
			 Card card2 = new Card(client1,client1.getFirstName()+" "+client1.getLastName(),CardType.CREDIT,CardColor.TITANIUM,"8888-8888-8888-8888",830,LocalDateTime.now(),LocalDateTime.now().plusYears(5));
			 Card card3 = new Card(client2,client2.getFirstName()+" "+client2.getLastName(),CardType.CREDIT,CardColor.SILVER,"7777-7777-7777-7777",254,LocalDateTime.now(),LocalDateTime.now().plusYears(5));


			 clientRepository.save(client1);
			 clientRepository.save(client2);

			 accountRepository.save(account1);
			 accountRepository.save(account2);
			 accountRepository.save(account3);

			 transactionRepository.save(transaction1);
			 transactionRepository.save(transaction2);
			 transactionRepository.save(transaction3);

			 loanRepository.save(loan1);
			 loanRepository.save(loan2);
			 loanRepository.save(loan3);

			 clientLoanRepository.save(clientLoan1);
			 clientLoanRepository.save(clientLoan2);
			 clientLoanRepository.save(clientLoan3);
			 clientLoanRepository.save(clientLoan4);

			 cardRepository.save(card1);
			 cardRepository.save(card2);
			 cardRepository.save(card3);

		 };
	 }
 }
