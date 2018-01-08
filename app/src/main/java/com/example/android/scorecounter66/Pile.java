package com.example.android.scorecounter66;

import java.util.HashMap;

class Pile {
    static final int INVALID_CARD = -1;
    private static final int MELD_POINTS = 20;
    private static final Pile ourInstance = new Pile();
    private HashMap<CardType, Card> cards;
    private int leftMelds;
    private int leftPoints;

    private Pile() {
        cards = new HashMap<>(6);
        cards.put(CardType.ACE, new AceCard());
        cards.put(CardType.TEEN, new TeenCard());
        cards.put(CardType.KING, new KingCard());
        cards.put(CardType.QUEEN, new QueenCard());
        cards.put(CardType.JACK, new JackCard());
        cards.put(CardType.NINE, new NineCard());
        resetCards();
    }

    static Pile getInstance() {
        return ourInstance;
    }

    String getStringLeftPoints() {
        return Integer.toString(leftPoints);
    }

    private Card getCard(CardType cardTypeToFind) {
        return cards.get(cardTypeToFind);
    }

    private boolean isMeldLeft() {
        return leftMelds > 0;
    }

    int takeMeld() {
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
        for (Card card : cards.values()) {
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
    private String name;

    Card(int points, CardType cardType) {
        this.leftCards = NUBER_CARDS_SAME_TYPE;
        this.points = points;
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

    void takeCardFromPile() {
        leftCards--;
    }

    void returnCardToPile() {
        leftCards++;
    }

    boolean validate() {
        return leftCards > 0;
    }

    void reset() {
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






