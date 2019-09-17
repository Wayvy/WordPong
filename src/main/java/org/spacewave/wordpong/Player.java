package org.spacewave.wordpong;

import java.util.Scanner;

/**
 * A Player can be described with the attributes name and score. The name will
 * be asked at the beginning of the game. The points will change, every round,
 * depending on the points one can get every round
 * 
 * @author Wavy
 * @version 0.50
 */
public class Player {
	private String name;
	private int score;
	Scanner scn = new Scanner(System.in);

	/**
	 * Construction of a Player, the name will be delivered from the sender.
	 * Points will
	 * 
	 * @param name
	 */
	public Player(String name) {
		this.name = name;
		score = 0;
	}

	/**
	 * Increases the owners score, with the points of the word.
	 * 
	 * @param pointsToAdd
	 *            - The points, that will be added to the Score
	 */
	public void increaseScore(int pointsToAdd) {
		score += pointsToAdd;
	}

	public String getName() {
		return name;
	}

	public int getScore() {
		return score;
	}
}
