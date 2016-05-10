package game;

public class Player 
{
	private String name;
	private int score;
	
	public Player(String name)
	{
		this.name = name;
		score = 0;
	}
	
	public void increaseScore(int pointsToAdd)
	{
		score += pointsToAdd;
	}
	
	public String getName() {
		return name;
	}
	public int getScore() {
		return score;
	}
	
	public void throwWord(WordPass word)
	{
		//if(Action ==true)
		//until (entry exists)
		//check String Word in Database
		//create new WordPass
		//endTurn
	}
	
	public void endTurn()
	{
		//close TurnDialog for current Player
		//create wait Dialog for current Player
		//open TurnDialog for other player
	}
}
