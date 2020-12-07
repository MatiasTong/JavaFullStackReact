package com.mt;

import com.mt.dto.Card;
import com.mt.dto.Deck;

import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Let's begin");
            Deck deck = new Deck();
            deck.shuffleCards();
            System.out.println("Deck is shuffled");

            do {
                System.out.println("How many cards would you like to draw?");
                int numberOfCards = Integer.parseInt(sc.nextLine());
                System.out.println("Here are your cards");
                try{
                ArrayList<Card> drawnCards = deck.draw(numberOfCards);
                System.out.println(drawnCards);

                }catch(Exception e){
                    System.out.println(e.getMessage());
                }

            } while(deck.getCardsInDeck().size()>0);



    }
}
