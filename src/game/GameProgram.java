package game;

/**
 * The GameProgram Class contains the procedure for the game, it sets up the
 * rules and mechanics for the Game.
 * 
 * @author Wavy
 * @version 0.50
 */
public class GameProgram implements Runnable {
	public static byte roundNumber;
	private Player player1;
	private Player player2;

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
		player1 = new Player("Player1");
		player2 = new Player("Player2");
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
				word = player1.pickWord();
			} while (!IsInDataBase(word));

			WordPass pass = toss(word);
			startTimer();
			player2.take(pass);

			do {
				word = player2.pickWord();
			} while (!IsInDataBase(word));

			pass = toss(word);
			startTimer();
			player1.take(pass);
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

	private void startTimer() {
		// Do something in the GUI Thread
	}

	/**
	 * Initiate next round
	 */
	private void nextRound() {
		roundNumber++;
		System.out.println(player1.getName() + " " + player1.getScore());
		System.out.println(player2.getName() + " " + player2.getScore());

	}

}
