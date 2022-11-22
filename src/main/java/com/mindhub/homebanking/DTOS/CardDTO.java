package com.mindhub.homebanking.DTOS;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.mindhub.homebanking.models.Card;
import com.mindhub.homebanking.models.CardColor;
import com.mindhub.homebanking.models.CardType;
import com.mindhub.homebanking.models.Client;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class CardDTO {

    private long id;

    private Client client;

    private String cardholder;

    private CardType type;

    private CardColor color;

    private String number;

    private int cvv;

    private LocalDateTime thruDate;

    private LocalDateTime fromDate;


    public CardDTO() {
    }

    public CardDTO(Card card) {
        this.id = card.getId();
        this.client = card.getClient();
        this.cardholder = card.getCardholder();
        this.type = card.getType();
        this.color = card.getColor();
        this.number = card.getNumber();
        this.cvv = card.getCvv();
        this.thruDate = card.getThruDate();
        this.fromDate = card.getFromDate();
     }

    public long getId() {
        return id;
    }

    @JsonIgnore

    public Client getClient() {
        return client;
    }

    public String getCardholder() {
        return cardholder;
    }

    public CardType getType() {
        return type;
    }

    public CardColor getColor() {
        return color;
    }

    public String getNumber() {
        return number;
    }

    public int getCvv() {
        return cvv;
    }

    public LocalDateTime getThruDate() {
        return thruDate;
    }

    public LocalDateTime getFromDate() {
        return fromDate;
    }


    public void setClient(Client client) {
        this.client = client;
    }

    public void setCardholder(String cardholder) {
        this.cardholder = cardholder;
    }

    public void setType(CardType type) {
        this.type = type;
    }

    public void setColor(CardColor color) {
        this.color = color;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public void setCvv(int cvv) {
        this.cvv = cvv;
    }

    public void setThruDate(LocalDateTime thruDate) {
        this.thruDate = thruDate;
    }

    public void setFromDate(LocalDateTime fromDate) {
        this.fromDate = fromDate;
    }
}
