package com.example.android.scorecounter66;

/**
 * Created by Rif-ACER on 05.12.2017.
 */

public class Player {
    private static boolean onePlayerClicked = false;
    private final String playerName;
    private boolean oneCardChosen;
    private int score;
    private int chosenCardPoints;
    private CardType chosenCard;

    Player(String playerName) {
        resetOneCardChosen();
        this.playerName = playerName;
        resetScore();
    }

    public static void resetOnePlayerClicked() {
        onePlayerClicked = false;
    }

    public static boolean isOnePlayerClicked() {
        return onePlayerClicked;
    }

    private void resetTempPoints() {
        this.chosenCardPoints = 0;
    }

    boolean isOneCardChosen() {
        return oneCardChosen;
    }

    public CardType getChosenCard() {
        return chosenCard;
    }

    public String getStringScore() {
        return Integer.toString(score);
    }

    public void resetOneCardChosen() {
        chosenCard = null;
        oneCardChosen = false;
        resetTempPoints();
        resetOnePlayerClicked();
    }

    public void resetScore() {
        resetOneCardChosen();
        score = 0;
    }

    public boolean addMeld() {
        int meldPoints = CardTypes.getInstance().takeMeld();
        score += meldPoints;
        return meldPoints > 0;
    }

    public String getPlayerScoreViewName() {
        return "score_" + playerName;
    }

    public String getPlayerCardViewName(CardType cardType) {
        return CardTypes.getInstance().getCardName(cardType) + "_" + playerName;
    }

    public boolean addCard(CardType cardType) {
        if (onePlayerClicked && !oneCardChosen) {
            return false; // Fail
        }
        int points = CardTypes.getInstance().takePointsCard(cardType);
        if (points == CardTypes.INVALID_CARD) {
            return false;   // No card left
        } else {
            chosenCardPoints += CardTypes.getInstance().getPointsCard(cardType);
            if (!oneCardChosen) {
                chosenCard = cardType;
                oneCardChosen = true;
                onePlayerClicked = true;
            } else {
                score += chosenCardPoints;
                resetOneCardChosen();
            }
            return true;
        }
    }
}
