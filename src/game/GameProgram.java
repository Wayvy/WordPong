package game;

import gui.FrameStates;

/**
 * The GameProgram Class contains the procedure for the game, it sets up the
 * rules and mechanics for the Game.
 * 
 * @author Wavy, (fjiz)
 * @version 0.51
 */
public class GameProgram extends Thread{
	
	public static byte roundNumber;
	public static Countdown countdown;
	private Player[] players;
	private FrameStates states;

	public GameProgram(int numberOfPlayers, FrameStates states)
	{
		this.players = new Player[numberOfPlayers];
		this.states = states;
	}
	
	@Override
	public void run() {
		initiateGame();
		System.out.println("Initiate Game");
		runGameLoop();
	}

	/**
	 * The Frame
	 */
	private void initiateGame() {
		roundNumber = 1;
		players[0] = new Player("Player1");
		players[1] = new Player("Player2");
	}

	/**
	 * The procedure
	 */
	private void runGameLoop() {
		String word;
		/*
		 * An infinite loop, that still needs a break conditions
		 */
		while (true) {
			do {
				word = players[0].pickWord();
			} while (!IsInDataBase(word));

			WordPass pass = toss(word);
			startTimer(pass.getTime());
			players[1].take(pass);
			players[1].increaseScore(countdown.stopCountdown());

			do {
				word = players[1].pickWord();
			} while (!IsInDataBase(word));

			pass = toss(word);
			startTimer(pass.getTime());
			players[0].take(pass);
			players[0].increaseScore(countdown.stopCountdown());
			nextRound();

		}
	}

	/**
	 * Will check, whether a word is in the defined database and return a
	 * boolean
	 * 
	 * @param word
	 *            - The word that has to be checked
	 * @return - Returns true if you can find the word, else the method return
	 *         false
	 */
	private boolean IsInDataBase(String word) {
		return true;
	}

	/**
	 * Creates a new WordPass Object, that will be tossed to the other player
	 * 
	 * @param word
	 *            - The Word, which creates the basis for the toss
	 * @return - The WordPass object, that will be created
	 */
	private WordPass toss(String word) {
		return new WordPass(word);
	}

	private void startTimer(int time) {
		countdown = new Countdown();
		countdown.setCount(time);
		countdown.start();
	}

	/**
	 * Initiate next round
	 */
	private void nextRound() {
		roundNumber++;
		System.out.println(players[0].getName() + " " + players[0].getScore());
		System.out.println(players[1].getName() + " " + players[1].getScore());

	}

}
