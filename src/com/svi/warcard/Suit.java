package com.svi.warcard;

public enum Suit {
	DIAMOND(4, "D"),
	HEART(3, "H"),
	SPADE(2, "S"),
	CLUB(1, "C");

	private String suitCode;
	private int suitValue;

	private Suit(int suitValue, String suitName) {
		this.suitCode = suitName;
		this.suitValue = suitValue;
	}

	public String getSuitName() {
		return suitCode;
	}

	public int getSuitValue() {
		return suitValue;
	}

}