package com.mindhub.homebanking.DTOS;

import com.mindhub.homebanking.models.ClientLoan;

public class ClientLoanDTO {

    private long id;
    private long LoanId;
    private String name;
    private Double amount;
    private int payments;

    public ClientLoanDTO() {
    }

    public ClientLoanDTO(ClientLoan clientLoan) {
        this.id = clientLoan.getId();
        this.LoanId = clientLoan.getLoan().getId();
        this.name = clientLoan.getLoan().getName();
        this.amount = clientLoan.getAmount();
        this.payments = clientLoan.getPayments();

    }

    public long getId() {
        return id;
    }

    public long getLoanId() {
        return LoanId;
    }

    public String getName() {
        return name;
    }

    public Double getAmount() {
        return amount;
    }

    public int getPayments() {
        return payments;
    }


    public void setId(long id) {
        this.id = id;
    }

    public void setLoanId(long loanId) {
        LoanId = loanId;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public void setPayments(int payments) {
        this.payments = payments;
    }

}
