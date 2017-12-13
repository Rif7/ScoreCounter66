package com.example.android.scorecounter66;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
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

    public void resetScorePlayer1() {
        int scoreResId = getResources().getIdentifier(player1.getPlayerScoreViewName(), "id", getPackageName());
        player1.resetScore();
        TextView scoreTextView = (TextView) findViewById(scoreResId);
        scoreTextView.setText(player1.getStringScore());
    }

    public void resetScorePlayer2() {
        int scoreResId = getResources().getIdentifier(player1.getPlayerScoreViewName(), "id", getPackageName());
        player2.resetScore();
        TextView scoreTextView = (TextView) findViewById(scoreResId);
        scoreTextView.setText(player2.getStringScore());
    }

    public void reset(View view) {
        resetScorePlayer1();
        resetScorePlayer2();
        Player.resetOnePlayerClicked();
        CardTypes.getInstance().resetCards();
        resetLayout();
    }

    public void resetLayout() {
        Player player = player1;
        CardType cardType = CardType.ACE;
    }

    public void changeCardLayout(Player player, CardType cardType) {
        int cardResId = getResources().getIdentifier(player.getPlayerCardViewName(cardType), "id", getPackageName());
        ImageView cardImageView = (ImageView) findViewById(cardResId);
        Context context = cardImageView.getContext();
        if (player.chosenCardPoints() != 0) {
            int blackCardPicId = context.getResources().getIdentifier("ace_of_clubs_black", "drawable", context.getPackageName());
            cardImageView.setImageResource(blackCardPicId);
        } else {
            int whiteCardPicId = context.getResources().getIdentifier("ace_of_clubs_white", "drawable", context.getPackageName());
            cardImageView.setImageResource(whiteCardPicId);
            int scoreResId = getResources().getIdentifier(player.getPlayerScoreViewName(), "id", getPackageName());
            TextView scoreTextView = (TextView) findViewById(scoreResId);
            scoreTextView.setText(player.getStringScore());
        }
    }

    public void clickAcePlayer1(View view) {
        Player player = player1;
        CardType cardType = CardType.ACE;

        boolean addCardSucces = player.addCard(cardType);
        if (addCardSucces) {
            changeCardLayout(player, cardType);
        }
    }

    public void clickTeenPlayer1(View view) {
        Player player = player1;
        CardType cardType = CardType.TEEN;

        boolean addCardSucces = player.addCard(cardType);
        if (addCardSucces) {
            int cardResId = getResources().getIdentifier(player.getPlayerCardViewName(cardType), "id", getPackageName());
            ImageView cardImageView = (ImageView) findViewById(cardResId);
            if (player.chosenCardPoints() != 0) {
                cardImageView.setImageResource(R.drawable.teen_of_clubs_black);
            } else {
                cardImageView.setImageResource(R.drawable.teen_of_clubs_white);
                int scoreResId = getResources().getIdentifier(player.getPlayerScoreViewName(), "id", getPackageName());
                TextView scoreTextView = (TextView) findViewById(scoreResId);
                scoreTextView.setText(player.getStringScore());
            }
        }
    }

    public void clickKingPlayer1(View view) {
    }

    public void clickQueenPlayer1(View view) {
    }

    public void clickJackPlayer1(View view) {
    }

    public void clickNinePlayer1(View view) {
    }

    public void clickMeldPlayer1(View view) {
    }

    public void clickAcePlayer2(View view) {}

    public void clickTeenPlayer2(View view) {}

    public void clickKingPlayer2(View view) {
    }

    public void clickQueenPlayer2(View view) {
    }

    public void clickJackPlayer2(View view) {
    }

    public void clickNinePlayer2(View view) {
    }

    public void clickMeldPlayer2(View view) {
    }

}
