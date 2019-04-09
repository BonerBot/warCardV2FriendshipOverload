package com.svi.warcard;

import java.util.ArrayList;
import java.util.List;

public class Player {
	private String playerName;
	private List<Card> hand;

	public Player(String name) {
		this.playerName = name;
		hand = new ArrayList<Card>();
	}

	public String getPlayerName() {
		return playerName;
	}

	public List<Card> getPlayerHands() {
		return hand;
	}
}