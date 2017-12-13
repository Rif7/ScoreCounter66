package com.example.android.scorecounter66;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by Rif-ACER on 05.12.2017.
 */

public class Player {
    private static boolean onePlayerClicked = false;
    private boolean OneCardChosen;
    private int score;
    private int chosenCardPoints;
    private String playerName;

    Player(String playerName) {
        this.OneCardChosen = false;
        this.playerName = playerName;
        resetScore();
    }

    public void resetTempPoints() {
        this.chosenCardPoints = 0;
    }

    public int getScore() {
        return score;
    }

    public String getStringScore() {
        return Integer.toString(score);
    }

    public int chosenCardPoints() {
        return chosenCardPoints;
    }

    public void resetScore() {
        this.chosenCardPoints = 0;
        this.score = 0;
    }

    public static void resetOnePlayerClicked() {
        onePlayerClicked = false;
    }

    public String getPlayerName() {
        return playerName;
    }

    public String getPlayerScoreViewName() {
        return "score_" + playerName;
    }

    public String getPlayerCardViewName(CardType cardType) {
        String PlayerCardViewName = CardTypes.getInstance().getCardName(cardType) + "_" + playerName;
        return PlayerCardViewName;
    }

    public static boolean isOnePlayerClicked() {
        return onePlayerClicked;
    }

    public boolean addCard(CardType cardType) {
        if (onePlayerClicked && !OneCardChosen) {
            return false; // Fail
        }
        if (!OneCardChosen) {
            chosenCardPoints += CardTypes.getInstance().getPointsCard(cardType);
            this.OneCardChosen = true;
        } else {
            chosenCardPoints += CardTypes.getInstance().getPointsCard(cardType);
            score += chosenCardPoints;
            chosenCardPoints = 0;
            this.OneCardChosen = false;
        }
        return CardTypes.getInstance().takePointsCard(cardType) >= 0;
    }
}
