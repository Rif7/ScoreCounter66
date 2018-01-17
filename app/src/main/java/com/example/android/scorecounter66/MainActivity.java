package com.example.android.scorecounter66;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

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
        TextView scoreTextView = findViewById(scoreResId);
        scoreTextView.setText(player.getStringScore());
    }

    public void updatePlayersLayouts() {
        updateLayout(player1);
        updateLayout(player2);
        updateRemainingPoints(Pile.getInstance().getStringLeftPoints());
        updateButtonsViews();
        toastWinner();
    }


    public void reset(View view) {
        resetScorePlayer(player1);
        resetScorePlayer(player2);
        Player.resetOnePlayerClicked();
        Pile.getInstance().resetCards();
        updatePlayersLayouts();
        updateRemainingPoints("120");
    }

    public void updateLayout(Player player) {
        for (CardType cardType : CardType.values()) {
            int cardResId = getResources().getIdentifier(player.getPlayerCardViewName(cardType), "id", getPackageName());
            ImageView cardImageView = findViewById(cardResId);
            Context context = cardImageView.getContext();
            String cardPicName = Pile.getInstance().getCardName(cardType) + "_of_clubs_white";
            int whiteCardPicId = context.getResources().getIdentifier(cardPicName, "drawable", context.getPackageName());
            cardImageView.setImageResource(whiteCardPicId);
            if (Pile.getInstance().getCardLeft(cardType) > 0) {
                cardImageView.setBackgroundColor(0x0000FF00);
            } else {
                cardImageView.setBackgroundColor(0xFF00FF00);
            }
        }
    }

    public void updateRemainingPoints(String remainingPoints) {
        TextView quantityTextView = findViewById(R.id.remaining_points);
        quantityTextView.setText(remainingPoints);
    }

    public void updateButtonsViews() {
        Button meldButton1 = (Button) findViewById(R.id.meld_Player1);
        Button meldButton2 = (Button) findViewById(R.id.meld_Player2);
        if (Pile.getInstance().isMeldLeft()) {
            meldButton1.setText(R.string.meld_20);
            meldButton2.setText(R.string.meld_20);
        } else {
            meldButton1.setText(R.string.no_melds);
            meldButton2.setText(R.string.no_melds);
        }
    }

    public Player checkVictoryConditions() {
        if (player1.getScore() >= 66) {
            return player1;
        }
        if (player2.getScore() >= 66) {
            return player2;
        }
        return null;
    }

    public void toastWinner() {
        Player winner = checkVictoryConditions();
        if (winner != null) {
            Toast toast = Toast.makeText(getApplicationContext(),
                    winner.getPlayerName() + " reached 66 points", Toast.LENGTH_SHORT);
            toast.show();
        }
    }

    public void addMeldPoints(Player player) {
        boolean addMeldSuccess = player.addMeld();
        if (addMeldSuccess) {
            int scoreResId = getResources().getIdentifier(player.getPlayerScoreViewName(), "id", getPackageName());
            TextView scoreTextView = findViewById(scoreResId);
            scoreTextView.setText(player.getStringScore());
        }
        updateButtonsViews();
        toastWinner();
    }

    public void changeCardLayout(Player player, CardType cardType) {
        int cardResId = getResources().getIdentifier(player.getPlayerCardViewName(cardType), "id", getPackageName());
        ImageView cardImageView = findViewById(cardResId);
        Context context = cardImageView.getContext();
        if (player.isOneCardChosen()) {
            String cardPicName = Pile.getInstance().getCardName(cardType) + "_of_clubs_black";
            int blackCardPicId = context.getResources().getIdentifier(cardPicName, "drawable", context.getPackageName());
            updatePlayersLayouts();
            cardImageView.setImageResource(blackCardPicId);
        } else {
            updatePlayersLayouts();
            int scoreResId = getResources().getIdentifier(player.getPlayerScoreViewName(), "id", getPackageName());
            TextView scoreTextView = findViewById(scoreResId);
            scoreTextView.setText(player.getStringScore());
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
                Pile.getInstance().returnCardToPile(player.getChosenCard());
                player.resetOneCardChosen();
                updatePlayersLayouts();
            }
        }
    }

}
