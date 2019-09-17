package org.spacewave.wordpong;

import org.spacewave.wordpong.game.GameController;
import org.spacewave.wordpong.infrastructure.Connection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.swing.*;
import java.awt.*;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * A Clock, which tells the time in seconds, from the start of the Program It
 * can be stopped, by pressing return
 * 
 * @author Wavy
 * @version 1.00
 */

@Service
public class Countdown implements Runnable {

    @Autowired
    private GameController gameController;

	private Thread worker;
	private final AtomicBoolean running = new AtomicBoolean(false);
	private final AtomicBoolean stopped = new AtomicBoolean(true);
	private final AtomicBoolean isYourTurn = new AtomicBoolean(false);
	private int interval;

	private Connection connection;
	private int count;
	private GameFrame gameFrame;

	public void start() {
		worker = new Thread(this);
		running.set(true);
		stopped.set(false);
		worker.start();
	}

	public void stop() {
		running.set(false);
	}

	public void run() {
		running.set(true);
		gameFrame.getCountDownLabel().setText("Countdown : " + count);
		while (running.get()) {
			try {
				Thread.sleep(interval);
			} catch (InterruptedException e){
				stop();
				worker.interrupt();
			}
			if(count <= 0){
			    if(isYourTurn.get()){
                	gameController.GetGameOver(connection);
				}
			    stop();
                worker.interrupt();
			}
			gameFrame.getCountDownLabel().setText("Countdown : " + count);
			count--;

		}
	}
	public void interrupt() {
		running.set(false);
		stopped.set(true);
		worker.interrupt();
	}

	public Countdown() {
		interval = 1000;
	}

	public void SetGameFrame(GameFrame gameFrame){
        this.gameFrame = gameFrame;
    }

	public void setCount(int startFrom) {
		this.count = startFrom;
	}

	public boolean isRunning() {
		return running.get();
	}

	public boolean isStopped() {
		return stopped.get();
	}

    public void SetConnection(Connection connection) {
        this.connection = connection;
    }

	public boolean isYourTurn() {
		return isYourTurn.get();
	}

	public void setYourTurn(boolean yourTurn) {
		isYourTurn.set(yourTurn);
	}
}
