package com.svi.warcard;

public enum Rank {
	ACE(14, "A"),
	KING(13, "K"),
	QUEEN(12, "Q"),
	JACK(11, "J"),
	TEN(10, "10"),
	NINE(9, "9"),
	EIGHT(8, "8"),
	SEVEN(7, "7"),
	SIX(6, "6"),
	FIVE(5, "5"),
	FOUR(4, "4"),
	THREE(3, "3"),
	TWO(2, "2");

	private String rankCode;
	private int rankValue;

	private Rank(int rankValue, String rankName) {
		this.rankValue = rankValue;
		this.rankCode = rankName;
	}

	public String getRankName() {
		return rankCode;
	}

	public int getRankValue() {
		return rankValue;
	}

}