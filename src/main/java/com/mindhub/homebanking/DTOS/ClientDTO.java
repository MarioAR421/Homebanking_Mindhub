package com.mindhub.homebanking.DTOS;

import com.mindhub.homebanking.models.Client;

import java.util.Set;
import java.util.stream.Collectors;

public class ClientDTO {
        private long id;
        private String firstName;
        private String lastName;
        private String email;
        private Set<AccountDTO> account;
        private Set<ClientLoanDTO> loan;
        private Set<CardDTO> card;



        public ClientDTO() {
        }
        public ClientDTO(Client client) {
            this.id = client.getId();
            this.firstName = client.getFirstName();
            this.lastName = client.getLastName();
            this.email = client.getEmail();
            this.loan = client.getClientLoan().stream().map(ClientLoanDTO::new).collect(Collectors.toSet());
            this.account = client.getAccounts().stream().map(AccountDTO::new).collect(Collectors.toSet());
            this.card = client.getCard().stream().map(CardDTO::new).collect(Collectors.toSet());
        }


    public long getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getEmail() {
        return email;
    }

    public Set<AccountDTO> getAccount() {
        return account;
    }

    public Set<ClientLoanDTO> getLoan() {
        return loan;
    }

    public Set<CardDTO> getCard() {
        return card;
    }


    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setAccount(Set<AccountDTO> account) {
        this.account = account;
    }

    public void setLoan(Set<ClientLoanDTO> loan) {
        this.loan = loan;
    }

    public void setCard(Set<CardDTO> card) {
        this.card = card;
    }
}


