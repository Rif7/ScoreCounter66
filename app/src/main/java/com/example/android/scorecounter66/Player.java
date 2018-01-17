package com.example.android.scorecounter66;


class Player {
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

    static void resetOnePlayerClicked() {
        onePlayerClicked = false;
    }

    static boolean isOnePlayerClicked() {
        return onePlayerClicked;
    }

    String getPlayerName() {
        return playerName;
    }

    private void resetTempPoints() {
        this.chosenCardPoints = 0;
    }

    boolean isOneCardChosen() {
        return oneCardChosen;
    }

    CardType getChosenCard() {
        return chosenCard;
    }

    int getScore() {
        return score;
    }

    String getStringScore() {
        return Integer.toString(score);
    }

    void resetOneCardChosen() {
        chosenCard = null;
        oneCardChosen = false;
        resetTempPoints();
        resetOnePlayerClicked();
    }

    void resetScore() {
        resetOneCardChosen();
        score = 0;
    }

    boolean addMeld() {
        int meldPoints = Pile.getInstance().takeMeld();
        score += meldPoints;
        return meldPoints > 0;
    }

    String getPlayerScoreViewName() {
        return "score_" + playerName;
    }

    String getPlayerCardViewName(CardType cardType) {
        return Pile.getInstance().getCardName(cardType) + "_" + playerName;
    }

    boolean addCard(CardType cardType) {
        if (onePlayerClicked && !oneCardChosen) {
            return false; // Fail
        }
        int points = Pile.getInstance().takePointsCard(cardType);
        if (points == Pile.INVALID_CARD) {
            return false;   // No card left
        } else {
            chosenCardPoints += Pile.getInstance().getPointsCard(cardType);
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
