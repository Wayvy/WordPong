package org.spacewave.wordpong;

import org.springframework.stereotype.Service;

import javax.swing.*;
import java.awt.*;

/**
 * A Clock, which tells the time in seconds, from the start of the Program It
 * can be stopped, by pressing return
 * 
 * @author Wavy
 * @version 1.00
 */

@Service
public class Countdown extends Thread {

	private boolean stop;
	private int count;
	private GameFrame gameFrame;

	public Countdown() {
	}

	public void SetGameFrame(GameFrame gameFrame){
        this.gameFrame = gameFrame;
    }

	/**
	 * The executed commands, when the clock is started
	 */
	
	@Override
	public void run() {
		stop = false;
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
			gameFrame.getCountDownLabel().setText("Countdown : " + count);
			System.out.println(count);
			count--;
		}
		gameFrame.getBtn().setEnabled(false);
		gameFrame.getPlayType().setEnabled(false);
		stopCountdown();
	}

	public void setCount(int startFrom) {
		this.count = startFrom;
	}

	public int stopCountdown() {
		stop = true;
		return count;
	}

}
