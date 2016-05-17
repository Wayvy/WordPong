package game;

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

	/**
	 * The Player will pick a word, that will be send to the other Player, via
	 * the Program.
	 * 
	 * @return - The word, that will be sent to the other player
	 */
	public String pickWord() {
		System.out.println(name + " picks Word");
		String word = scn.next();
		return word;
	}

	/**
	 * The player will have to type away the wordToss, until he gets every
	 * letter right
	 * 
	 * @param toss
	 *            - The WordPass, that has to be caught, by the player
	 */
	public void take(WordPass toss) {
		System.out.println(name + " catches Word" + toss.getWordToPass());
		String word;

		do {
			word = scn.next();
		} while (!word.equals(toss.getWordToPass()));
		return;
		// 3, 2, 1 Go
		// Start Timer
		// Type away
		// Check Correctness
	}

	public String getName() {
		return name;
	}

	public int getScore() {
		return score;
	}
}
