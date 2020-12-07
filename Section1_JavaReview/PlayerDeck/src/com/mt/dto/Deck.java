package com.mt.dto;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class Deck {
    private int deckID;
    public ArrayList<Card> cardsInDeck;
    public ArrayList<Card> cardsDrawn;
    private ArrayList<Card> cardsDiscarded;

    public Deck() {
        int[] values = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
        ArrayList<Card> cardList = new ArrayList<>();
        for (int value : values) {
            cardList.add(new Card(Integer.toString(value), Integer.toString(value), value));
        }
        this.cardsInDeck = cardList;
        this.cardsDrawn = new ArrayList<>();
        this.cardsDiscarded = new ArrayList<>();
    }

    public void shuffleCards() {
        Collections.shuffle(this.cardsInDeck);
    }

    public Card draw() {
        Card drawnCard = cardsInDeck.remove(cardsInDeck.size());
        cardsDrawn.add(drawnCard);
        return drawnCard;
    }

    public ArrayList<Card> draw(int number) throws Exception {
        ArrayList <Card> cardList = new ArrayList<>();
        if(number<=cardsInDeck.size()) {
            for (int i = 0; i < number; i++) {
                cardList.add(cardsInDeck.get(cardsInDeck.size() - 1));
                Card drawnCard = cardsInDeck.remove(cardsInDeck.size() - 1);

                cardsDrawn.add(drawnCard);
            }
            return cardList;
        } else {
            int size = cardsInDeck.size();
            throw new Exception("not enough cards left to draw" + size);
        }
    }

    public void play(Card card) {
        if (cardsDrawn.remove(card)) {
            cardsDiscarded.add(card);
        }
    }

   //Getters and setters
    public int getDeckID() {
        return deckID;
    }

    public void setDeckID(int deckID) {
        this.deckID = deckID;
    }

    public ArrayList<Card> getCardsInDeck() {
        return cardsInDeck;
    }

    public void setCardsInDeck(ArrayList<Card> cardsInDeck) {
        this.cardsInDeck = cardsInDeck;
    }

    public ArrayList<Card> getCardsDrawn() {
        return cardsDrawn;
    }

    public void setCardsDrawn(ArrayList<Card> cardsDrawn) {
        this.cardsDrawn = cardsDrawn;
    }

    public ArrayList<Card> getCardsDiscarded() {
        return cardsDiscarded;
    }

    public void setCardsDiscarded(ArrayList<Card> cardsDiscarded) {
        this.cardsDiscarded = cardsDiscarded;
    }

    @Override
    public String toString() {
        return "Deck{" +
                "deckID=" + deckID +
                ", cardsInDeck=" + cardsInDeck +
                ", cardsDrawn=" + cardsDrawn +
                ", cardsDiscarded=" + cardsDiscarded +
                '}';
    }
}

