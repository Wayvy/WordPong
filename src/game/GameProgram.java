package game;

public class GameProgram implements Runnable
{
	public static byte roundNumber;
	public Player player1;
	public Player player2;
	
	@Override
	public void run()
	{
		initiateGame();
		runGameLoop();
	}
	
	private void initiateGame()
	{
		roundNumber = 0;
		player1 = new Player("Player1");
		player2 = new Player("Player2");
	}
	private void runGameLoop()
	{
		while(true)
		{
			//player1.();
			//player1.throwWord(word);
			//player1.endTurn();
			//player2.startTurn();
			//player2.throwWord(word);
			//player2.endTurn();
		}
	}

}
