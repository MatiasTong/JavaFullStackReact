package com.mt.model;

import com.mt.dto.Card;
import com.mt.dto.Deck;
import com.mt.viewmodel.CardDisplay;

import java.util.ArrayList;
import java.util.Scanner;

public class CardGame {
    public void run() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Let's begin");
        boolean keepPlaying;

        do {
            keepPlaying = false;
            Deck deck = new Deck();
            deck.shuffleCards();
            System.out.println("Deck is shuffled");

            do {
                System.out.println("\nHow many cards would you like to draw?");
                int numberOfCards = Integer.parseInt(sc.nextLine());
                if (numberOfCards < deck.getCardsInDeck().size()) {
                    System.out.println("Here are your cards");
                }

                try {
                    ArrayList<Card> drawnCards = deck.draw(numberOfCards);
                    for(Card card: drawnCards){
                        CardDisplay cardDisplay = new CardDisplay(card);
                        System.out.println(cardDisplay);
                    }
//                    System.out.println(drawnCards);

                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }

            } while (deck.getCardsInDeck().size() > 0);

            System.out.println("\nYou're out of cards. Do you want to play again? (Y/N)");
            String playAgain = sc.nextLine().trim().toLowerCase();
            if (playAgain.equals("y") || playAgain.equals("yes")) {
                keepPlaying = true;
            } else {
                System.out.println("Thanks for playing!");
            }

        } while (keepPlaying);


    }

}
