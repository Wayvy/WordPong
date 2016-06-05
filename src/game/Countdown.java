package game;

/**
 * A Clock, which tells the time in seconds, from the start of the Program It
 * can be stopped, by pressing return
 * 
 * @author Wavy
 * @version 1.00
 */
public class Countdown extends Thread {

	private boolean stop;
	private int count;

	public Countdown() {
	}

	/**
	 * The executed commands, when the clock is started
	 */
	
	@Override
	public void run() {
		while (count >= 0) {
			try {
				Thread.sleep(1000);
			}

			catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			if (stop)
				break;
			System.out.println(count);
			count--;
		}
	}

	public void setCount(int startFrom) {
		this.count = startFrom;
	}

	public int stopCountdown() {
		stop = true;
		return count;
	}

}
