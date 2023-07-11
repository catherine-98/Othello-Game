package Othello;

public class UnplayablePosition extends Position{
	
	//constructor
	public UnplayablePosition(int row, int col) { 
		super(row, col, UNPLAYABLE);
	}

	//constructor
	public UnplayablePosition(int row, int col, char piece) { 
		super(row, col, piece);
	}

	@Override
	public boolean canPlay() {
		return false;
	}
}
