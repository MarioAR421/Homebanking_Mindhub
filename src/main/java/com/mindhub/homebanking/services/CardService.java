package com.mindhub.homebanking.services;

import com.mindhub.homebanking.models.Card;

public interface CardService {

    public void saveCard(Card card);

    public void deleteCard(Card card);

    public Card findCard(String number);
}
