package com.gepardec.blubb;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Card {

    private Suit suit;
    private Rank rank;

    public String getDescription(){
        var descriptionSuit = switch (suit) {
            case CLUBS  -> "Kreuz";
            case DIAMONDS -> "Karo";
            case HEARTS -> "Herz";
            case SPADES -> "Pik";
        };

        var descriptionRank = switch(rank) {
            case ACE -> "Ass";
            case QUEEN -> "Dame";
            case KING -> "König";
            case JACK -> "Bube";
            default -> rank.getValue();
        };

        return descriptionSuit + " " + descriptionRank;
    }



}
