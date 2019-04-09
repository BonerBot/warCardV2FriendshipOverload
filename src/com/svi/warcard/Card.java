package com.svi.warcard;

public class Card {

	private String suitName, rankName;
	private int rankValue, suitValue, cardValue;

	public Card(String rankName, int rankValue, String suitName, int suitValue) {
		this.rankName = rankName;
		this.suitName = suitName;
		this.rankValue = rankValue;
		this.suitValue = suitValue;
		this.cardValue = rankValue + suitValue;
	}

	public int getRankValue() {
		return rankValue;
	}

	public int getSuitValue() {
		return suitValue;
	}

	public String getSuitName() {
		return suitName;
	}

	public String getRankName() {
		return rankName;
	}

	public String toString() {
		return (suitName + "-" + rankName);
	}

	public int getCardValue() {
		return cardValue;
	}

	public void setCardValue(int cardValue) {
		this.cardValue = cardValue;
	}

}
