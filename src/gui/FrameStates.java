package gui;

/**
 * A class listing all possible recurring states the game can have.
 * Each state is represented with a method.
 * 
 * @author fjiz
 * @version 0.1
 *
 */
public class FrameStates {

	GameFrame gframe;

	/**
	 * creates an object with all methods.
	 * 
	 * @param gframe
	 *            The GameFrame this is responding to.
	 * 
	 */
	public FrameStates(GameFrame gframe) {
		this.gframe = gframe;
	}
	
	private void startFrame(){
		gframe.setResponseTxt("");
	}
	
}
