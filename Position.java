package Othello;

public abstract class Position {

	private char piece;
	public static final char UNPLAYABLE = '*';
	public static final char EMPTY = ' ';
	public static final char BLACK = 'B';
	public static final char WHITE = 'W';
	private int row;
	private int col;
	
	//constructor
	public Position(int row, int col) {
		this.row = row;
		this.col = col;
	}
	//constructor
	public Position(int row, int col, char piece) {
		this.row = row;
		this.col = col;
		this.piece = piece;
	}
	
	public void setRow(int row) {
		this.row = row;
	}
	
	public int getRow() {
		return row;
	}
	
	public void setCol(int col) {
		this.col = col;
	}
	
	public int getCol() {
		return col;
	}
	
	public void setBlack() {
		this.piece = BLACK;
	}
	
	public void setWhite() {
		this.piece = WHITE;
	}
	
	public void setUnplayable() {
		this.piece = UNPLAYABLE;
	}
	
	public void setEmpty() {
		this.piece = EMPTY;
	}
	
	public char getPiece() {
		return piece;
	}
	
	@Override
	public String toString() {
		String s = String.valueOf(piece);
		return s;
	}
	
	public abstract boolean canPlay();
}
