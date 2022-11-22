package com.mindhub.homebanking.DTOS;

import com.mindhub.homebanking.models.Account;
import com.mindhub.homebanking.models.Transaction;
import com.mindhub.homebanking.models.TransactionType;

import java.time.LocalDateTime;

public class TransactionDTO {

    private long id;

    private TransactionType type;

    private Double mount;

    private String description;

    private LocalDateTime date;

    private Account account;

    public TransactionDTO() {
    }

    public TransactionDTO(Transaction transaction) {
        this.id = transaction.getId();
        this.type = transaction.getType();
        this.mount = transaction.getAmount();
        this.description = transaction.getDescription();
        this.date = transaction.getDate();
        this.account = transaction.getAccount();
    }

    public long getId() {
        return id;
    }

    public TransactionType getType() {
        return type;
    }

    public Double getMount() {
        return mount;
    }

    public String getDescription() {
        return description;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public Account getAccount() {
        return account;
    }

    public void setType(TransactionType type) {
        this.type = type;
    }

    public void setMount(Double mount) {
        this.mount = mount;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public void setAccount(Account account) {
        this.account = account;
    }
}
