package Othello;
import java.util.Scanner;

public class Board {
	public static final int BOARD_SIZE = 8;
	private Position[][] positions = new Position[BOARD_SIZE][BOARD_SIZE];
	
	public Position[][] getPositions() {
		return this.positions;
	}	
	
	//constructor that initializes board from loading a game
	public Board(String positions) {
		//creating an empty board
		for (int i = 0; i<BOARD_SIZE; i++) {
		    for (int j = 0; j<BOARD_SIZE; j++) {
		    	this.positions[i][j] = new PlayablePosition(i, j);
		    } 
		}
		//converting string in file to a board
		int index = 0;
		for (int i = 0; i<BOARD_SIZE; i++) {
		    for (int j = 0; j<BOARD_SIZE; j++) {
		    	char piece =  positions.charAt(index);
		    	if (piece == 'B') {
					this.positions[i][j] = new UnplayablePosition(i, j, Position.BLACK);
		    	}
		    	
		    	else if (piece == 'W') {
					this.positions[i][j] = new UnplayablePosition(i, j, Position.WHITE);
		    	}
		    	
		    	else if (piece == '*') {
			    	this.positions[i][j] = new UnplayablePosition(i, j);

		    	}
		    	else if (piece == ' ') {
			    	this.positions[i][j] = new PlayablePosition(i, j);
		    	}
		    	index++;
		    } 
		}
	}
	
	//constructor that initializes board when creating new game
	public Board(int startPos) {		
		//creating an empty board 
		for (int i = 0; i<BOARD_SIZE; i++) {
		    for (int j = 0; j<BOARD_SIZE; j++) {
		    	this.positions[i][j] = new PlayablePosition(i, j);
		    }
		}
		//adding unplayable pieces, which are the same for every game
		this.positions[3][7] = new UnplayablePosition(3, 7);
		this.positions[4][7] = new UnplayablePosition(4, 7);
		
		//adding starting position depending on user choice
		if (startPos == 1) {
			this.positions[2][2] = new UnplayablePosition(2, 2, Position.WHITE);
			this.positions[2][3] = new UnplayablePosition(2, 3, Position.WHITE);
			this.positions[3][2] = new UnplayablePosition(3, 2, Position.WHITE);
			this.positions[3][3] = new UnplayablePosition(3, 3, Position.WHITE);
			this.positions[4][2] = new UnplayablePosition(4, 2, Position.BLACK);
			this.positions[4][3] = new UnplayablePosition(4, 3, Position.BLACK);
			this.positions[5][2] = new UnplayablePosition(5, 2, Position.BLACK);
			this.positions[5][3] = new UnplayablePosition(5, 3, Position.BLACK);
			this.positions[2][4] = new UnplayablePosition(2, 4, Position.BLACK);
			this.positions[2][5] = new UnplayablePosition(2, 5, Position.BLACK);
			this.positions[3][4] = new UnplayablePosition(3, 4, Position.BLACK);
			this.positions[3][5] = new UnplayablePosition(3, 5, Position.BLACK);
			this.positions[4][4] = new UnplayablePosition(4, 4, Position.WHITE);
			this.positions[4][5] = new UnplayablePosition(4, 5, Position.WHITE);
			this.positions[5][4] = new UnplayablePosition(5, 4, Position.WHITE);
			this.positions[5][5] = new UnplayablePosition(5, 5, Position.WHITE);
		}
		else if (startPos == 2) {
			this.positions[2][2] = new UnplayablePosition(2, 2, Position.WHITE);
			this.positions[2][3] = new UnplayablePosition(2, 2, Position.BLACK);
			this.positions[3][2] = new UnplayablePosition(3, 2, Position.BLACK);
			this.positions[3][3] = new UnplayablePosition(3, 3, Position.WHITE);
		}
		else if (startPos == 3) {
			this.positions[2][4] = new UnplayablePosition(2, 4, Position.WHITE);
			this.positions[2][5] = new UnplayablePosition(2, 5, Position.BLACK);
			this.positions[3][4] = new UnplayablePosition(3, 4, Position.BLACK);
			this.positions[3][5] = new UnplayablePosition(3, 5, Position.WHITE);
		}
		else if (startPos == 4) {
			this.positions[4][2] = new UnplayablePosition(4, 2, Position.WHITE);
			this.positions[4][3] = new UnplayablePosition(4, 3, Position.BLACK);
			this.positions[5][2] = new UnplayablePosition(5, 2, Position.BLACK);
			this.positions[5][3] = new UnplayablePosition(5, 3, Position.WHITE);
		}
		else if (startPos == 5) {
			this.positions[4][4] = new UnplayablePosition(4, 4, Position.WHITE);
			this.positions[4][5] = new UnplayablePosition(4, 5, Position.BLACK);
			this.positions[5][4] = new UnplayablePosition(5, 4, Position.BLACK);
			this.positions[5][5] = new UnplayablePosition(5, 5, Position.WHITE);
		}
	}
	
	public void drawBoard() {
		//displaying the board with row and column characters
		System.out.print("  "); //row
		for(int i = 0; i < BOARD_SIZE; ++i)
		   System.out.print("  " + (char)(i + '1') + " ");
		System.out.println();
		System.out.println("  " + "_________________________________");
	   
		for (int i = 0; i<BOARD_SIZE; i++) { //column
			System.out.println();
		    for (int j = 0; j<BOARD_SIZE; j++) {
		    	if(j == 0){
		    		System.out.print((char)(i + 'a'));
		    		System.out.print(" | ");}
		        System.out.print(this.positions[i][j] + " | ");
		    }
		    System.out.println();
		    System.out.println("  " + "_________________________________");
		}
	}
	
	//returns the position in the board based on the user's coordinates 
	public Position pieceAtChosenPosition() {
		Scanner input = new Scanner(System.in);
		System.out.println("\nEnter coordinates: ");
		String coordinates = input.nextLine();
		
		while (coordinates.length() != 2) {
			System.out.println("Please enter valid coordinates, letter first than number " ); 
			coordinates = input.nextLine(); 
			}
		
		char input_row = coordinates.charAt(0);
		char input_col = coordinates.charAt(1);
	
		int index_row = 0;
		int index_col = 0;
		
		switch (input_row) {
			case 'a':
				index_row = 0;
				break;
			case 'b':
				index_row = 1;
				break;
			case 'c':
				index_row = 2;
				break;
			case 'd':
				index_row = 3;
				break;
			case 'e':
				index_row = 4;
				break;
			case 'f':
				index_row = 5;
				break;
			case 'g':
				index_row = 6;
				break;
			case 'h':
				index_row = 7;
				break;
			default: {
				System.out.println("Please enter valid coordinates, letter first than number " ); 
				coordinates = input.nextLine();}
				
		}
		switch (input_col) {
			case '1':
				index_col = 0;
				break;
			case '2':
				index_col = 1;
				break;
			case '3':
				index_col = 2;
				break;
			case '4':
				index_col = 3;
				break;
			case '5':
				index_col = 4;
				break;
			case '6':
				index_col = 5;
				break;
			case '7':
				index_col = 6;
				break;
			case '8':
				index_col = 7;
				break;
			default:{
				System.out.println("Please enter valid coordinates, letter first than number " ); 
				coordinates = input.nextLine();}
		}
		Position pos = this.positions[index_row][index_col];	
		return pos;
	}
	

	public void flip(Position position) {
		if (position.getPiece()  == 'W') {
			position.setBlack();
		}
		else if (position.getPiece() == 'B') {
			position.setWhite();
		}
	}

	//returns true if for current player the chosen position is playable
	public boolean checkNeighbour(Player currentPlayer, Position position) {
		if (this.checkBottom(position, currentPlayer) ||
		this.checkBottomLeft(position, currentPlayer) ||
		this.checkBottomRight(position, currentPlayer) ||
		this.checkTop(position, currentPlayer) ||
		this.checkTopLeft(position, currentPlayer) ||
		this.checkTopRight(position, currentPlayer) ||
		this.checkLeft(position, currentPlayer) ||
		this.checkRight(position, currentPlayer)) {
			return true;
		}
		return false;
	}
	
	//returns true if for current player at least one empty position can flip pieces in at least one direction
	public boolean isBoardPlayable(Player currentPlayer) {
		for (int i = 0; i < BOARD_SIZE; i++) {
		    for (int j = 0; j < BOARD_SIZE; j++) {
		    	if ( this.positions[i][j].canPlay() == false) { 
    				continue;
				}
				if (this.checkNeighbour(currentPlayer, this.positions[i][j])) {
						return true;
					}
				}
			}
			return false;
		}
	
	//the following booleans return true if a position has a neighbour of the opposite color
	//and if at the end of the line, there is a piece of the current player's color 
	//with only pieces of the opposite color in between
	public boolean checkTopLeft(Position pos, Player currentPlayer) {
		int row = pos.getRow();
		int col = pos.getCol();
		
		if(col == 0 || row == 0) {
			return false;
		}
		if (this.positions[row-1][col-1].getPiece() == this.getOppColor(currentPlayer)) {
			for (row = row-1-1,col = col-1-1; row >= 0 && col >= 0; row--, col--) {
				if (this.positions[row][col].getPiece() == '*' || (this.positions[row][col].getPiece() == ' ')) {
					break;
				}
				if (this.positions[row][col].getPiece() == currentPlayer.getColor()) {
					return true;
				}
			}
		}
		return false;
	}
	
	public boolean checkTop(Position pos, Player currentPlayer) {
		int row = pos.getRow();
		int col = pos.getCol();

		if(row==0) {
			return false;
		}
		if (this.positions[row-1][col].getPiece() == this.getOppColor(currentPlayer)) {
			for (row = row-1-1; row >=0; row--) {
				if (this.positions[row][col].getPiece() == '*' || (this.positions[row][col].getPiece() == ' ')) {
					break;
				}
				if (this.positions[row][col].getPiece() == currentPlayer.getColor()) {
					return true;
				}
			}
		}
		return false;
	}
	
	public boolean checkTopRight(Position pos, Player currentPlayer) {
		int row = pos.getRow();
		int col = pos.getCol();
		
		if(col == 7  || row == 0) {
			return false;
		}
		if (this.positions[row-1][col+1].getPiece() == this.getOppColor(currentPlayer)) {
			for (row = row-1-1,col = col+1+1; row >= 0 && col <= 7; row--, col++) {
				if (this.positions[row][col].getPiece() == '*' || (this.positions[row][col].getPiece() == ' ')) {
					break;
				}
				if (this.positions[row][col].getPiece() == currentPlayer.getColor()) {
					return true;
				}
			}
		}
		return false;
	}
	
	public boolean checkLeft(Position pos, Player currentPlayer) {
		int row = pos.getRow();
		int col = pos.getCol();

		if(col == 0) {
			return false;
		}
		if (this.positions[row][col-1].getPiece() == this.getOppColor(currentPlayer)) {
			for (col = col-1-1; col >= 0; col--) {
				if (this.positions[row][col].getPiece() == '*' || (this.positions[row][col].getPiece() == ' ')) {
					break;
				}
				if (this.positions[row][col].getPiece() == currentPlayer.getColor()) {
					return true;
				}
			}
		}
		return false;
	}
	
	public boolean checkRight(Position pos, Player currentPlayer) {
		int row = pos.getRow();
		int col = pos.getCol();
		
		if(col == 7) {
			return false;
		}
		if (this.positions[row][col+1].getPiece() == this.getOppColor(currentPlayer)) {
			for (col = col+1+1; col <= 7; col++) {
				if (this.positions[row][col].getPiece() == '*' || (this.positions[row][col].getPiece() == ' ')) {
					break;
				}
				if (this.positions[row][col].getPiece() == currentPlayer.getColor()) {
					return true;
				}
			}
		}
		return false;
	}
	
	public boolean checkBottomLeft(Position pos, Player currentPlayer) {
		int row = pos.getRow();
		int col = pos.getCol();
		
		if(col == 0 || row == 7) {
			return false;
		}
		if (this.positions[row+1][col-1].getPiece() == this.getOppColor(currentPlayer)) { 
			for (row = row+1+1,col = col-1-1; row <= 7 && col >= 0; row++, col--) {
				if (this.positions[row][col].getPiece() == '*' || (this.positions[row][col].getPiece() == ' ')) {
					break;
				}
				if (this.positions[row][col].getPiece() == currentPlayer.getColor()) {
					return true;
				}
			}
		}
		return false;
	}
	
	public boolean checkBottom(Position pos, Player currentPlayer) {
		int row = pos.getRow();
		int col = pos.getCol();
	
		if(row == 7) {
			return false;
		}
		if (this.positions[row+1][col].getPiece() == this.getOppColor(currentPlayer)) {
			for (row = row+1+1; row <= 7; row++) {
				if (this.positions[row][col].getPiece() == '*' || (this.positions[row][col].getPiece() == ' ')) {
					break;
				}
				if (this.positions[row][col].getPiece() == currentPlayer.getColor()) {
					return true;
					
				}
			}
		}
		return false;
	}
	
	public boolean checkBottomRight(Position pos, Player currentPlayer) {
		int row = pos.getRow();
		int col = pos.getCol();

		if(col == 7 || row == 7){
			return false;
		}
		if (this.positions[row+1][col+1].getPiece() == this.getOppColor(currentPlayer)) {
			for (row = row+1+1,col = col+1+1; row <= 7 && col <= 7; row++, col++) {
				if (this.positions[row][col].getPiece() == '*' || (this.positions[row][col].getPiece() == ' ')) {
					break;
				}
				if (this.positions[row][col].getPiece() == currentPlayer.getColor()) {
					return true;
				}
			}
		}
		return false;
	}
	
	private char getOppColor(Player currentPlayer) {
		char currentColor = currentPlayer.getColor();
		if (currentColor == 'B') 
			return 'W';
		else 
			return 'B';
	}

	public boolean isBoardFull() {
		int countPieces = 0;
		for (int i = 0; i<BOARD_SIZE; i++) {
		    for (int j = 0; j<BOARD_SIZE; j++) {
		    	if (this.positions[i][j].getPiece() != ' '){
		    		countPieces += 1;
		    	}
		    }	
		}
		if (countPieces == 64) {
			return true;}
		else {
			return false; }
	}
}
