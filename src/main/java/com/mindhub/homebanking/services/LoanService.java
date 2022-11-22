package com.mindhub.homebanking.services;

import com.mindhub.homebanking.DTOS.LoanDTO;
import com.mindhub.homebanking.models.Loan;

import java.util.List;

public interface LoanService {

    public List<LoanDTO> getLoanDTO();

    public Loan findByName(String name);

    public void saveLoan(Loan loan);
}
