package com.example.android.scorecounter66;

import org.junit.Test;

import static org.junit.Assert.assertEquals;


public class ExampleUnitTest {
    private void resetCardTypes() {
        Pile.getInstance().resetCards();
    }

    private Player[] createPlayers() {
        Player[] players = new Player[2];
        players[0] = new Player("Test1");
        players[1] = new Player("Test2");
        resetCardTypes();
        return players;
    }

    private void giveCardsForPlayers(Player player1, int numCards1, Player player2, int numCards2) {
        for (CardType cardType : CardType.values()) {
            for (int i = 0; i < numCards1; i++) {
                player1.addCard(cardType);
            }
        }
        for (CardType cardType : CardType.values()) {
            for (int i = 0; i < numCards2; i++) {
                player2.addCard(cardType);
            }
        }

    }

    @Test
    public void correctPlayerClicked() throws Exception {
        Player[] players = createPlayers();
        giveCardsForPlayers(players[0], 1, players[1], 0);
        assertEquals("30", players[0].getStringScore());
        assertEquals("0", players[1].getStringScore());
        assertEquals("90", Pile.getInstance().getStringLeftPoints());
        giveCardsForPlayers(players[0], 0, players[1], 1);
        assertEquals("30", players[0].getStringScore());
        assertEquals("30", players[1].getStringScore());
        assertEquals("60", Pile.getInstance().getStringLeftPoints());
    }

    // Cannot both players have more than 120 points from cards
    @Test
    public void onePlayerMax120() throws Exception {
        Player[] players = createPlayers();
        giveCardsForPlayers(players[0], 5, players[1], 1);
        assertEquals("120", players[0].getStringScore());
        assertEquals("0", players[1].getStringScore());
        assertEquals("0", Pile.getInstance().getStringLeftPoints());
    }

    // When one player clicked on card clicking another card has no result
    @Test
    public void onlyOnePlayerClicked() throws Exception {
        Player[] players = createPlayers();
        players[0].addCard(CardType.ACE);
        giveCardsForPlayers(players[0], 0, players[1], 1);
        assertEquals("0", players[1].getStringScore());
        assertEquals("109", Pile.getInstance().getStringLeftPoints());
        players[0].addCard(CardType.ACE);
        giveCardsForPlayers(players[0], 0, players[1], 1);
        assertEquals("22", players[0].getStringScore());
        assertEquals("30", players[1].getStringScore());
        assertEquals("68", Pile.getInstance().getStringLeftPoints());

    }

    // Cannot use more than 4 cards of one type
    @Test
    public void only4Cards() throws Exception {
        Player[] players = createPlayers();
        for (int i = 0; i < 5; i++) {
            players[0].addCard(CardType.JACK);
        }
        players[1].addCard(CardType.JACK);
        assertEquals("8", players[0].getStringScore());
        assertEquals("0", players[1].getStringScore());
        assertEquals("112", Pile.getInstance().getStringLeftPoints());
    }

    // After reset all cards are available
    @Test
    public void reset() throws Exception {
        Player[] players = createPlayers();
        giveCardsForPlayers(players[0], 1, players[1], 1);
        assertEquals("30", players[0].getStringScore());
        assertEquals("30", players[1].getStringScore());
        assertEquals("60", Pile.getInstance().getStringLeftPoints());
        players[0].resetScore();
        assertEquals("0", players[0].getStringScore());
        players[1].resetScore();
        assertEquals("0", players[1].getStringScore());
        Pile.getInstance().resetCards();
        assertEquals("120", Pile.getInstance().getStringLeftPoints());
        giveCardsForPlayers(players[0], 0, players[1], 5);
        assertEquals("120", players[1].getStringScore());
        assertEquals("0", Pile.getInstance().getStringLeftPoints());
    }

    // Reset is possible when one card is clicked
    @Test
    public void resetWhenOneCardIsCLicked() throws Exception {
        Player[] players = createPlayers();
        players[0].addCard(CardType.QUEEN);
        assertEquals("0", players[1].getStringScore());
        assertEquals("117", Pile.getInstance().getStringLeftPoints());
        players[0].resetScore();
        assertEquals("0", players[0].getStringScore());
        Pile.getInstance().resetCards();
        assertEquals("120", Pile.getInstance().getStringLeftPoints());
        for (int i = 0; i < 5; i++) {
            players[0].addCard(CardType.QUEEN);
        }
        assertEquals("12", players[0].getStringScore());
        assertEquals("108", Pile.getInstance().getStringLeftPoints());
    }

    // return to pile
    @Test
    public void returnCardToPile() throws Exception {
        Player[] players = createPlayers();
        players[0].addCard(CardType.KING);
        Pile.getInstance().returnCardToPile(CardType.KING);
        assertEquals("120", Pile.getInstance().getStringLeftPoints());
        players[0].resetScore();
        for (int i = 0; i < 5; i++) {
            players[0].addCard(CardType.KING);
        }
        assertEquals("16", players[0].getStringScore());
        assertEquals("104", Pile.getInstance().getStringLeftPoints());
    }
}