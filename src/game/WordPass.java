package game;

/**
 * A WordPass is the Base of the game. Like a ball these will be send from player to player.
 * One player has to throw the Word and the other one has to catch it. A WordPass consists
 * of the word, the points the catching player can maximally get, the time, the catching player will
 * have and the difficulty of the word.
 * @author Wavy
 *
 */
public class WordPass 
{
	private String wordToPass;
	private int maxPoints;
	private float time;
	private byte difficulty;
	
	/**
	 * Constructs the word, which player typed to send to the player to type it away
	 * @param wordToPass - the word, that has to be typed away
	 */
	public WordPass(String wordToPass)
	{
		if (isViableWord(wordToPass))
		{
			this.wordToPass = wordToPass;
			calculateMaxPoints(wordToPass);
			calculateTimeToType(wordToPass);	
		}
		
		else 
		{
			
		}
		
	}
	
	
	private boolean isViableWord (String word)
	{
		return true;
	}
	
	/**
	 * Calculates the points, the player, who types away the word can maximally get 
	 * @param word - The word the sending player typed
	 * @return the points the typing player can get if he types in minimal time
	 */
	private int calculateMaxPoints(String word)
	{
		int points = word.length() * GameProgram.roundNumber;
		return points;
	}
	
	/**
	 * Calculates the time, the typing player will get to type away the word he gets
	 * @param word - The word, which the sending player typed
	 * @return the amount of time the typing player will get
	 */
	private float calculateTimeToType(String word)
	{
		float durancy = word.length() / GameProgram.roundNumber;
		return durancy;
	}
	
	
}
