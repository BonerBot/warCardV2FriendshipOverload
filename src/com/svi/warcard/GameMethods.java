package com.svi.warcard;

import java.util.*;

public class GameMethods {

	public static LinkedList<Card> faroShuffleDeck(int numOfShuffles, LinkedList<Card> deck) {
		LinkedList<Card> shuffledDeck = new LinkedList<>();
		LinkedList<Card> tempDeck = new LinkedList<>();

		for (int i = 0; i < numOfShuffles; i++) {
			if (shuffledDeck.isEmpty()) {
				tempDeck = deck;
			} else {
				tempDeck = shuffledDeck;
			}
			shuffledDeck = new LinkedList<Card>();
			for (int j = 0; j < deck.size() / 2; j++) {
				shuffledDeck.add(tempDeck.get(j));
				shuffledDeck.add(tempDeck.get(j + 26));
			}

		}
		return shuffledDeck;
	}

	public static void dealCards(int numberOfPlayers, List<Player> playerList, LinkedList<Card> shuffledDeck) {
		int n = 0; // number of decks, form from number of players
		do {
			if (n != numberOfPlayers) {
				for (n = 0; n < numberOfPlayers; n++) {					
					playerList.get(n).getPlayerHands().add(shuffledDeck.get(0));
					shuffledDeck.remove(0);
					if (shuffledDeck.isEmpty()) {
						break;
					}
				}
			} else if (n == numberOfPlayers) {
				n = 0;
			}
		} while (!shuffledDeck.isEmpty());
		System.out.println("\nCards are now distributed to each players.");
		// uncomment the next lines if you want to see the starting hand of each
		// player
		System.out.println("\nStarting cards per player : ");
		for (Player player : playerList) {
			Collections.reverse(player.getPlayerHands());
			System.out.println(player.getPlayerName() + " : " + player.getPlayerHands());
		}
	}

	public static List<Card> getTopCards(List<Player> playerList) {
		List<Card> topCardList = new ArrayList<Card>();
		for (Player player : playerList) {
			topCardList.add(player.getPlayerHands().get(0));
			player.getPlayerHands().remove(0);
		}
		return topCardList;
	}

	public static void playGame(List<Player> playerList, int numberOfPlayers) {
		// Create a new List for the top cards use for comparing of Card values
		List<Card> topCardList = new ArrayList<Card>();
		System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
		// shows the hand of each player
		for (Player player : playerList) {
			System.out.println(player.getPlayerName() + " : " + player.getPlayerHands());
		}
		// gets the cards to be compared from the top of deck of each player
		topCardList = (ArrayList<Card>) GameMethods.getTopCards(playerList);
		System.out.println("Table's Cards: " + topCardList);
		// code for comparing the said cards
		Card topCard = Collections.max(topCardList, Comparator.comparing(card -> card.getCardValue()));
		// stores the index of the winning card
		int tempIndex = topCardList.indexOf(topCard);
		// puts the winner card first when returning it at the bottom of the
		// deck
		Collections.rotate(topCardList, topCardList.size() - tempIndex);
		playerList.get(tempIndex).getPlayerHands().addAll(topCardList);
		System.out.println("The winner of the round is " + playerList.get(tempIndex).getPlayerName() + " with the card "
				+ "[" + topCard + "]");
		topCardList.clear();
		playerList.removeIf(player -> player.getPlayerHands().isEmpty());
	}

}