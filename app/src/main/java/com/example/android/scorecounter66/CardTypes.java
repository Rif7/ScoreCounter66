package com.example.android.scorecounter66;

import java.util.ArrayList;

/**
 * Created by Rif-ACER on 09.12.2017.
 */

public class CardTypes {
    private ArrayList<Card> cards;
    private static final CardTypes ourInstance = new CardTypes();

    static CardTypes getInstance() {
        return ourInstance;
    }

    private CardTypes() {
        cards = new ArrayList<Card>();
        cards.add(new AceCard());
        cards.add(new TeenCard());
        cards.add(new KingCard());
        cards.add(new QueenCard());
        cards.add(new JackCard());
        cards.add(new NineCard());
    }

    Card getCard(CardType cardTypeToFind) {
        for(Card card : cards) {
            if (card.getCardType() == cardTypeToFind) {
                return card;
            }
        }
        return null;
    }

    String getCardName(CardType cardType) {
        Card card = getCard(cardType);
        String cardName = card.getName();
        return cardName;
    }

    int getPointsCard(CardType cardType) {
        Card card = getCard(cardType);
        if (card != null) {
            return card.getPoints();
        } else {
            return -1; // No card found
        }
    }

    int takePointsCard(CardType cardType) {
        Card card = getCard(cardType);
        int points = -1;
        if (card.validate()) {
            card.takeCardFromPile();
            points = card.getPoints();
        }
        return points;
    }

    void resetCards() {
        for(Card card : cards) {
            card.reset();
        }
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






