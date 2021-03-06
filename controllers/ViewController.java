package controllers;

import views.*;
import models.*;

public class ViewController {

	public static final int MODE_BET = 1;
	public static final int MODE_DISCARD = 2;
	public static final int MODE_SCORE = 3;

	PokerGame gameController;
	MainGameFrame mainGameFrame;
	Player player;

	int mode;

	int highlightedCardCount = 0;

	public ViewController (PokerGame gc, Player player) {

		// Assign vars
		this.gameController = gc;
		this.player = player;
		mode = MODE_BET;

		// InputView needs a reference to the ViewController
		mainGameFrame = new MainGameFrame(this);

	}

	public void setMode (int mode) {
		this.mode = mode;
	}

	public void updateBalanceViewAmount (int amount) {
		mainGameFrame.balanceView.setText("Player Balance: " + amount);
	}

	public void clearPlayArea () {
		mainGameFrame.playAreaView.removeAll();
	}

	public void loadHandInPlayArea (Hand hand) {

		for (Card c: hand.getCards()) {
			mainGameFrame.playAreaView.add(new CardView(this,c));
		}

		mainGameFrame.playAreaView.revalidate();
		mainGameFrame.playAreaView.repaint();

	}

	public void loadEmptyHandInPlayArea () {
		for (int i = 0; i < 5; i++) {
			mainGameFrame.playAreaView.add(new CardView(this, new Card(0, 0)));
		}

		mainGameFrame.playAreaView.revalidate();
		mainGameFrame.playAreaView.repaint();
	}

	public void updateOutputConsole (int event) {
		mainGameFrame.inputView.outputConsole.updateConsole(event);
	}

	public void updateOutputConsole (int event, int amount) {
		mainGameFrame.inputView.outputConsole.updateConsole(event, amount);
	}

	public void updateOutputConsole (String combination) {
		mainGameFrame.inputView.outputConsole.updateConsole(combination);
	}

	public void relayBetToGameController (int amount) {
		gameController.placeBet(amount);
	}

	public void relayDiscardCommandToGameController () {
		gameController.discardSelected();
		highlightedCardCount = 0;	// reset highlight count
	}

	public void relayNextRoundStart() {
		gameController.nextRoundStart();
	}

	public boolean canHighlight () {

		if (mode == MODE_DISCARD && highlightedCardCount < 3) {
			highlightedCardCount+=1;
			return true;
		} else {
			return false;
		}
	}

	public void cardUnhilighted () {
		highlightedCardCount-=1;
	}

	public int getPlayerBalance () {
		return player.getBalance();
	}

	public void gameOver () {
		mainGameFrame.inputView.gameOverMode();
	}

}
