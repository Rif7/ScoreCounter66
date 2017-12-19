package com.example.android.scorecounter66;

import java.util.ArrayList;

/**
 * Created by Rif-ACER on 09.12.2017.
 */

public class CardTypes {
    public static final int INVALID_CARD = -1;
    public static final int MELD_POINTS = 20;
    private static final CardTypes ourInstance = new CardTypes();
    private ArrayList<Card> cards;
    private int leftMelds;
    private int leftPoints;

    private CardTypes() {
        cards = new ArrayList<>();
        cards.add(new AceCard());
        cards.add(new TeenCard());
        cards.add(new KingCard());
        cards.add(new QueenCard());
        cards.add(new JackCard());
        cards.add(new NineCard());
        resetCards();
    }

    static CardTypes getInstance() {
        return ourInstance;
    }

    public String getStringLeftPoints() {
        return Integer.toString(leftPoints);
    }

    private Card getCard(CardType cardTypeToFind) {
        for(Card card : cards) {
            if (card.getCardType() == cardTypeToFind) {
                return card;
            }
        }
        return null;
    }

    private boolean isMeldLeft() {
        return leftMelds > 0;
    }

    public int takeMeld() {
        if (isMeldLeft()) {
            leftMelds--;
            return MELD_POINTS;
        }
        return 0;
    }


    String getCardName(CardType cardType) {
        Card card = getCard(cardType);
        return card.getName();
    }

    ArrayList<String> getCardNamesList() {
        ArrayList<String> cardsNames = new ArrayList<>();
        for (Card card : cards) {
            cardsNames.add(card.getName());
        }
        return cardsNames;
    }

    void returnCardToPile(CardType cardType) {
        Card card = getCard(cardType);
        if (card != null) {
            card.returnCardToPile();
            int points = card.getPoints();
            leftPoints += points;
        }
    }


    int getPointsCard(CardType cardType) {
        Card card = getCard(cardType);
        if (card != null) {
            return card.getPoints();
        } else {
            return INVALID_CARD; // No card found
        }
    }

    int takePointsCard(CardType cardType) {
        Card card = getCard(cardType);
        int points = INVALID_CARD;
        if (card.validate()) {
            card.takeCardFromPile();
            points = card.getPoints();
            leftPoints -= points;
        }
        return points;
    }

    void resetCards() {
        for(Card card : cards) {
            card.reset();
        }
        leftPoints = 120;
        leftMelds = 5;
    }
}

abstract class Card{
    private static final int NUBER_CARDS_SAME_TYPE = 4;
    private int leftCards;
    private int points;
    private CardType cardType;
    private String name;

    Card(int points, CardType cardType) {
        this.leftCards = NUBER_CARDS_SAME_TYPE;
        this.points = points;
        this.cardType = cardType;
        this.name = getCardTypeName(cardType);
    }

    private String getCardTypeName(CardType cardType) {
        switch (cardType) {
            case ACE:
                return "ace";
            case TEEN:
                return "teen";
            case KING:
                return "king";
            case QUEEN:
                return "queen";
            case JACK:
                return "jack";
            case NINE:
                return "nine";
        }
        return "None";
    }

    public String getName() {
        return name;
    }

    int getPoints() {
        return this.points;
    }

    CardType getCardType() {
        return cardType;
    }

    void takeCardFromPile() {
        leftCards--;
    }

    void returnCardToPile() {
        leftCards++;
    }

    boolean validate() {
        return leftCards > 0;
    }

    public void reset() {
        this.leftCards = NUBER_CARDS_SAME_TYPE;
    }

}

class AceCard extends Card {
    AceCard() {
        super(11, CardType.ACE);
    }
}

class TeenCard extends Card {
    TeenCard() {
        super(10, CardType.TEEN);
    }
}
class KingCard extends Card {
    KingCard() {
        super(4, CardType.KING);
    }
}
class QueenCard extends Card {
    QueenCard() {
        super(3, CardType.QUEEN);
    }
}
class JackCard extends Card {
    JackCard() {
        super(2, CardType.JACK);
    }
}
class NineCard extends Card {
    NineCard() {
        super(0, CardType.NINE);
    }
}






