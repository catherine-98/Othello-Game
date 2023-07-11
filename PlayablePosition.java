package Othello;

public class PlayablePosition extends Position{
	
	//constructor
	public PlayablePosition(int row, int col) { 
		super(row, col, EMPTY);
	}

	@Override
	public boolean canPlay() {
		return true;
	}
	
}
