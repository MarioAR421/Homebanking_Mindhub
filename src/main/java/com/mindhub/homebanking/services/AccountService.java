package com.mindhub.homebanking.services;

import com.mindhub.homebanking.DTOS.AccountDTO;
import com.mindhub.homebanking.models.Account;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

public interface AccountService {

    public List<AccountDTO> getAccountsDTO();

    public AccountDTO getAccountDTO(Long id);

    public void saveAccount(Account account);

    public Account findByNumber(String id);

    public void  deleteAccount(Account account);
}
