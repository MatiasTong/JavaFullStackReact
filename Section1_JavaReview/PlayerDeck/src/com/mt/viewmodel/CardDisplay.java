package com.mt.viewmodel;

import com.mt.dto.Card;

public class CardDisplay {
    private int value;

    public CardDisplay(Card card){
        value = card.getValue();
    }

    @Override
    public String toString() {
        return "Card [" +
                + value +
                ']';
    }
}
