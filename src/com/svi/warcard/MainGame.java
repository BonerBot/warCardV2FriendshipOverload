package com.svi.warcard;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.*;

public class MainGame {
	public static void main(String[] args) throws IOException, IllegalArgumentException {

		System.out.println("~~~~~~~~~~~~War Card Game - SVI Edition~~~~~~~~~~~");
		System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
		System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
		LinkedList<Card> deck = new LinkedList<>();

		// creating list for Deck
		File userTxt = new File("input");
		try {
			Scanner scanner = new Scanner(userTxt);

			while (scanner.hasNextLine()) {
				String data[] = scanner.nextLine().split(",");
				for (String data1 : data) {

					String pair[] = data1.split("-");
					Rank rank = getRankEnum(pair[1]);
					Suit suit = getSuitEnum(pair[0]);

					if (rank != null && suit != null) {
						Card card = new Card(rank.getRankName(), rank.getRankValue(), suit.getSuitName(),
								suit.getSuitValue());
						deck.add(card);
					}
				}

			}
			scanner.close();
		} catch (FileNotFoundException e) {
			System.out.println("Unable to load deck file. Closing JVM.");
			System.exit(0);
//			deck = new LinkedList<>();
//			for (Suit suit : Suit.values()) {
//				for (Rank rank : Rank.values()) {
//					Card card = new Card(rank.getRankName(), rank.getRankValue(), suit.getSuitName(),
//							suit.getSuitValue());
//					deck.add(card);
//				}
//			}
		}

		// adding cards to deck
		// printing the deck
		System.out.println("Initial Deck: ");
		System.out.println(deck);

		int shuffleCount = 0, playerCount = 0;
		boolean wrongInput = true;
		// boolean wrongInput = true;
		// Initializes scanInputner
		Scanner scanInput = new Scanner(System.in);
		do {
			try {
				System.out.print("Number of players: ");
				playerCount = scanInput.nextInt();
				if (playerCount >= 2) {
					System.out.println("Number of Players: " + playerCount);
					wrongInput = false;
				} else {	
					System.out.print("Players must range between 2 to 52, please try again.\n");
				}
			} catch (InputMismatchException e) {
				System.out.println("Invalid input. Please try again.");
				scanInput.nextLine();
			}
		} while (wrongInput);
		wrongInput = true;
		do {
			try {
				System.out.print("How many times the deck will be shuffled: ");
				shuffleCount = scanInput.nextInt();
				if (shuffleCount == 0) {
					System.out.print("Shuffle the deck atleast once.\n");
				} else if (shuffleCount < 0) {
					System.out.print("Shuffle Count must be a positive number.\n");
				} else {
					wrongInput = false;
				}
			}

			catch (InputMismatchException ex) {
				System.out.println("Invalid input. Please try again.");
				scanInput.nextLine();
			}
		} while (wrongInput);
		scanInput.close();

		// Shuffle the deck
		LinkedList<Card> shuffledDeck = GameMethods.faroShuffleDeck(shuffleCount, deck);
		System.out.println("Shuffle Deck: ");
		System.out.println(shuffledDeck);

		// Create players then deal cards to players
		List<Player> playerList = new ArrayList<>();
		for (int i = 0; i < playerCount; i++) {
			playerList.add(new Player("Player " + (i + 1)));
		}

		// Deal cards
		GameMethods.dealCards(playerCount, playerList, shuffledDeck);
		playerList.removeIf(player -> player.getPlayerHands().isEmpty());
		// Start Game
		int round = 1;
		while (playerList.size() > 1) {
			System.out.println("\nROUND: " + round);
			GameMethods.playGame(playerList, playerCount);
			System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
			round++;
		}
		// If player list size = 1 , then game will end
		System.out.println("GAME OVER");
		System.out.println("\nThe winner is " + playerList.get(0).getPlayerName() + ". Total Round : " + (round - 1));
		for (Player player : playerList) {
			System.out.println("Winning Deck : " + player.getPlayerHands());
		}
	}

	public static Rank getRankEnum(String rankName) {
		for (Rank rank : Rank.values()) {
			if (rank.getRankName().trim().equals(rankName)) {
				return rank;
			}
		}
		return null;
	}

	public static Suit getSuitEnum(String suitName) {
		for (Suit suit : Suit.values()) {
			if (suit.getSuitName().trim().equals(suitName)) {
				return suit;
			}
		}
		return null;
	}
}