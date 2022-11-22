package com.mindhub.homebanking.services.implement;
import com.mindhub.homebanking.DTOS.ClientDTO;
import com.mindhub.homebanking.models.Client;
import com.mindhub.homebanking.models.ClientLoan;
import com.mindhub.homebanking.repositories.ClientLoanRepository;
import com.mindhub.homebanking.repositories.ClientRepository;
import com.mindhub.homebanking.services.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ClientServiceImplement implements ClientService {

    @Autowired
    ClientRepository clientRepository;

    @Autowired
    ClientLoanRepository clientLoanRepository;


    @Override
    public List<ClientDTO> getClientsDTO() {
        return clientRepository.findAll().stream().map(client->new ClientDTO(client)).collect(Collectors.toList());
    }

    @Override
    public ClientDTO getClientDTO(Long id) {
        return clientRepository.findById(id).map(client -> new ClientDTO(client)).orElse(null);
    }

    @Override
    public Client findByEmail(String email) {

        return clientRepository.findByEmail(email);
    }

    @Override
    public void saveClient(Client client) {

        clientRepository.save(client);
    }

    @Override
    public void saveClientLoan(ClientLoan clientLoan) {
        clientLoanRepository.save(clientLoan);

    }
}
