package com.mindhub.homebanking.controllers;

import com.mindhub.homebanking.models.*;
import com.mindhub.homebanking.services.CardService;
import com.mindhub.homebanking.services.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
public class CardController {

    @Autowired
    CardService cardService;
    @Autowired
    ClientService clientService;


    public int getRandomNumber(int min,int max){
        return (int) ((Math.random() * (max - min)) + min);
    }

    @PostMapping("clients/current/cards")
    public ResponseEntity<Object> createCard(Authentication authentication,
                                             @RequestParam CardType cardType,
                                             @RequestParam CardColor cardColor
    ) {

        Client clientCurrent = clientService.findByEmail(authentication.getName());


        List<Card> listCard = clientCurrent.getCard().stream().filter(card -> card.getType() == cardType).collect(Collectors.toList());

        if (listCard.size() == 3) {
            return new ResponseEntity<>("403 prophibido", HttpStatus.FORBIDDEN);
        }

        String cardNumber = getRandomNumber(1000, 9999)+"-"+getRandomNumber(1000, 9999)+"-"+getRandomNumber(1000, 9999)+"-"+getRandomNumber(1000, 9999);
        int cvv = getRandomNumber(100, 999);

        LocalDateTime thruDate = LocalDateTime.now();
        LocalDateTime fromDate = thruDate.plusYears(5);

        Card card = new Card(clientCurrent, clientCurrent.getFirstName()+" "+clientCurrent.getLastName(),cardType,cardColor,cardNumber,cvv,thruDate,fromDate);
        cardService.saveCard(card);
        return new ResponseEntity<>("has creado una tarjeta con exito", HttpStatus.CREATED);
    }

    @DeleteMapping("cards/delete")
            ResponseEntity<String> deleteCard(@RequestParam String number){
        Card card = cardService.findCard(number);
        cardService.deleteCard(card);
                return new ResponseEntity<>("Se elimino con exito",HttpStatus.CREATED);
    }










}
