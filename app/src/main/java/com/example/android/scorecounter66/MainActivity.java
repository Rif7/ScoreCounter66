package com.example.android.scorecounter66;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    Player player1;
    Player player2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        createPlayers();
    }

    public void createPlayers() {
        player1 = new Player(getResources().getString(R.string.Player1));
        player2 = new Player(getResources().getString(R.string.Player2));
    }

    public void resetScorePlayer(Player player) {
        int scoreResId = getResources().getIdentifier(player.getPlayerScoreViewName(), "id", getPackageName());
        player.resetScore();
        TextView scoreTextView = (TextView) findViewById(scoreResId);
        scoreTextView.setText(player.getStringScore());
    }


    public void reset(View view) {
        resetScorePlayer(player1);
        resetScorePlayer(player2);
        Player.resetOnePlayerClicked();
        CardTypes.getInstance().resetCards();
        resetLayout(player1);
        resetLayout(player2);
        updateRemainingPoints("0");
    }

    public void resetLayout(Player player) {
        for (CardType cardType : CardType.values()) {
            int cardResId = getResources().getIdentifier(player.getPlayerCardViewName(cardType), "id", getPackageName());
            ImageView cardImageView = (ImageView) findViewById(cardResId);
            Context context = cardImageView.getContext();
            String cardPicName = CardTypes.getInstance().getCardName(cardType) + "_of_clubs_white";
            int whiteCardPicId = context.getResources().getIdentifier(cardPicName, "drawable", context.getPackageName());
            cardImageView.setImageResource(whiteCardPicId);
            updateRemainingPoints(CardTypes.getInstance().getStringLeftPoints());
        }
    }

    public void updateRemainingPoints(String remainingPoints) {
        TextView quantityTextView = (TextView) findViewById(R.id.remaining_points);
        quantityTextView.setText(remainingPoints);
    }

    public void addMeldPoints(Player player) {
        boolean addMeldSuccess = player.addMeld();
        if (addMeldSuccess) {
            int scoreResId = getResources().getIdentifier(player.getPlayerScoreViewName(), "id", getPackageName());
            TextView scoreTextView = (TextView) findViewById(scoreResId);
            scoreTextView.setText(player.getStringScore());
        } // TO do disable buttons
    }

    public void changeCardLayout(Player player, CardType cardType) {
        int cardResId = getResources().getIdentifier(player.getPlayerCardViewName(cardType), "id", getPackageName());
        ImageView cardImageView = (ImageView) findViewById(cardResId);
        Context context = cardImageView.getContext();
        if (player.isOneCardChosen()) {
            String cardPicName = CardTypes.getInstance().getCardName(cardType) + "_of_clubs_black";
            int blackCardPicId = context.getResources().getIdentifier(cardPicName, "drawable", context.getPackageName());
            cardImageView.setImageResource(blackCardPicId);
        } else {
            resetLayout(player);
            int scoreResId = getResources().getIdentifier(player.getPlayerScoreViewName(), "id", getPackageName());
            TextView scoreTextView = (TextView) findViewById(scoreResId);
            scoreTextView.setText(player.getStringScore());
            ;
        }
    }

    public void clickAcePlayer1(View view) {
        Player player = player1;
        CardType cardType = CardType.ACE;

        boolean addCardSuccess = player.addCard(cardType);
        if (addCardSuccess) {
            changeCardLayout(player, cardType);
        }
    }

    public void clickTeenPlayer1(View view) {
        Player player = player1;
        CardType cardType = CardType.TEEN;

        boolean addCardSuccess = player.addCard(cardType);
        if (addCardSuccess) {
            changeCardLayout(player, cardType);
        }
    }

    public void clickKingPlayer1(View view) {
        Player player = player1;
        CardType cardType = CardType.KING;

        boolean addCardSuccess = player.addCard(cardType);
        if (addCardSuccess) {
            changeCardLayout(player, cardType);
        }
    }

    public void clickQueenPlayer1(View view) {
        Player player = player1;
        CardType cardType = CardType.QUEEN;

        boolean addCardSuccess = player.addCard(cardType);
        if (addCardSuccess) {
            changeCardLayout(player, cardType);
        }
    }

    public void clickJackPlayer1(View view) {
        Player player = player1;
        CardType cardType = CardType.JACK;

        boolean addCardSuccess = player.addCard(cardType);
        if (addCardSuccess) {
            changeCardLayout(player, cardType);
        }
    }

    public void clickNinePlayer1(View view) {
        Player player = player1;
        CardType cardType = CardType.NINE;

        boolean addCardSuccess = player.addCard(cardType);
        if (addCardSuccess) {
            changeCardLayout(player, cardType);
        }
    }

    public void clickMeldPlayer1(View view) {
        Player player = player1;

        addMeldPoints(player);
    }


    public void clickAcePlayer2(View view) {
        Player player = player2;
        CardType cardType = CardType.ACE;

        boolean addCardSuccess = player.addCard(cardType);
        if (addCardSuccess) {
            changeCardLayout(player, cardType);
        }
    }

    public void clickTeenPlayer2(View view) {
        Player player = player2;
        CardType cardType = CardType.TEEN;

        boolean addCardSuccess = player.addCard(cardType);
        if (addCardSuccess) {
            changeCardLayout(player, cardType);
        }
    }

    public void clickKingPlayer2(View view) {
        Player player = player2;
        CardType cardType = CardType.KING;

        boolean addCardSuccess = player.addCard(cardType);
        if (addCardSuccess) {
            changeCardLayout(player, cardType);
        }
    }

    public void clickQueenPlayer2(View view) {
        Player player = player2;
        CardType cardType = CardType.QUEEN;

        boolean addCardSuccess = player.addCard(cardType);
        if (addCardSuccess) {
            changeCardLayout(player, cardType);
        }
    }

    public void clickJackPlayer2(View view) {
        Player player = player2;
        CardType cardType = CardType.JACK;

        boolean addCardSuccess = player.addCard(cardType);
        if (addCardSuccess) {
            changeCardLayout(player, cardType);
        }
    }

    public void clickNinePlayer2(View view) {
        Player player = player2;
        CardType cardType = CardType.NINE;

        boolean addCardSuccess = player.addCard(cardType);
        if (addCardSuccess) {
            changeCardLayout(player, cardType);
        }
    }

    public void clickMeldPlayer2(View view) {
        Player player = player2;

        addMeldPoints(player);
    }

    public void undo(View view) {
        if (Player.isOnePlayerClicked()) {
            Player player;
            if (player1.isOneCardChosen()) {
                player = player1;
            } else {
                player = player2;
            }
            if (player.isOneCardChosen()) {
                CardTypes.getInstance().returnCardToPile(player.getChosenCard());
                player.resetOneCardChosen();
                resetLayout(player);
            }
        }
    }

}
