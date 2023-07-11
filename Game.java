package Othello;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.io.*;


public class Game {
	public static final int BOARD_SIZE = 8;
	private Player p1;
	private Player p2;
	private Player currentPlayer;
	private Board board;
	private boolean firstHasMove = true;
	private boolean secondHasMove = true;

	public Game() {
		this.p1 = null;
		this.p2 = null;
	}

	public Game(Player p1, Player p2) {
		this.p1 = p1;
		this.p2 = p2;
	}

	public void setCurrentPlayer(Player currentPlayer) {
		this.currentPlayer = currentPlayer;
	}

	public Player getCurrentPlayer() {
		return this.currentPlayer;
	}

	public Player getP1(){
		return p1;
	}

	public Player getP2(){
		return this.p2;
	}
	
	public void taketurn(Player currentPlayer) {
		if (this.currentPlayer.equals(p1)) {
			this.currentPlayer = p2;
		}
		else {
			this.currentPlayer = p1;
		}
	}
	//creating board 
	public Board initBoard(int startingposition) {
		Board newboard = new Board(startingposition);
		this.board = newboard;
		this.currentPlayer = p1;
		newboard.drawBoard();
		return newboard;
	}
	
	public boolean isBothForfeit() {
		if (this.firstHasMove == false && this.secondHasMove == false) {
			return true;}
		return false;
	}
	
	public boolean isGameOver() {
		return this.board.isBoardFull() || isBothForfeit(); }
	
	//end game message
	public void displayWinner() {
		int white_pieces = 0;
		int black_pieces = 0;
		for (int i = 0; i<BOARD_SIZE; i++) {
			   for (int j = 0; j<BOARD_SIZE; j++) {
			    if (this.board.getPositions()[i][j].getPiece() == 'W'){
			    	white_pieces += 1;}
			    else if (this.board.getPositions()[i][j].getPiece() == 'B') {
				    	black_pieces += 1;}
			   }
		}
		System.out.println();
		System.out.println("Game Over!");
		System.out.println("Count of black pieces: " + black_pieces);
		System.out.println("Count of white pieces: " + white_pieces);
			
		if (black_pieces > white_pieces) {
			System.out.println("Winner is " + p1);
		}
		else if (white_pieces > black_pieces) {
			System.out.println("Winner is " + p2);
			}
		else {
			System.out.println("It is a draw.");
		}
	}
	
	//concede, save or place piece, depending on current player's choice
	public void makeMove() {
		Scanner input = new Scanner(System.in);
		int chosenMove = 0;
		boolean validNumber = false;
		System.out.println("\nEnter number: ");

		while (!validNumber) {
			try {
				chosenMove = input.nextInt();
				while (chosenMove != 1 && chosenMove != 2 && chosenMove != 3) {
					System.out.println("Please enter a number between 1 and 3: " ); 
					chosenMove = input.nextInt(); 
					}
				validNumber = true;
			}
			catch (InputMismatchException e){
				input.nextLine();
				System.out.println("Please enter a number between 1 and 3: " ); 
			}
		}
		if (chosenMove == 1) {
			System.out.println("\n" + this.currentPlayer.getName() + " has passed its turn\n");
			if (this.currentPlayer.equals(p1)) {
				this.firstHasMove = false;
			}
			else {this.secondHasMove = false;}
			this.taketurn(this.currentPlayer);
		}
		else if (chosenMove == 2) { 
			this.save();
		}
		else if (chosenMove == 3) { 
			Position userPos = this.board.pieceAtChosenPosition();
			int row = userPos.getRow();
			int col = userPos.getCol();
		
			if (!this.board.checkNeighbour(this.currentPlayer, userPos) || !userPos.canPlay()){
				System.out.println("\nPlease choose a valid position or concede\n");
			}
			else {
				//place piece on board
				this.board.getPositions()[row][col] = new UnplayablePosition(row, col, this.currentPlayer.getColor());
				
				//flip pieces
				if(this.board.checkBottom(userPos, this.currentPlayer)) {
					for (int j = row+1; j < BOARD_SIZE; j++) {
						if (this.board.getPositions()[j][col].getPiece() == this.currentPlayer.getColor()){
							break;
						}
						this.board.flip(this.board.getPositions()[j][col]);
					}
				}
				if(this.board.checkBottomLeft(userPos, this.currentPlayer)) {
					for (int i = col-1, j = row+1; i > 0 && j < BOARD_SIZE; i--, j++) {
						if (this.board.getPositions()[j][i].getPiece() == this.currentPlayer.getColor()){
							break;
						}
						this.board.flip(this.board.getPositions()[j][i]);
					}
				}
				if(this.board.checkBottomRight(userPos, this.currentPlayer)) {
					for (int i = col+1, j = row+1; i < BOARD_SIZE && j < BOARD_SIZE; i++, j++) {
						if (this.board.getPositions()[j][i].getPiece() == this.currentPlayer.getColor()){
							break;
						}
						this.board.flip(this.board.getPositions()[j][i]);
					}
				}
				if(this.board.checkLeft(userPos, this.currentPlayer)) {
					for (int i = col-1; i > 0 ; i--) {
						if (this.board.getPositions()[row][i].getPiece() == this.currentPlayer.getColor()){
							break;
						}
						this.board.flip(this.board.getPositions()[row][i]);
					}
				}
				if(this.board.checkRight(userPos, this.currentPlayer)) {
					for (int i = col+1; i < BOARD_SIZE; i++) {
						if (this.board.getPositions()[row][i].getPiece() == this.currentPlayer.getColor()){
							break;
						}
						this.board.flip(this.board.getPositions()[row][i]);
					}
				}
				if(this.board.checkTopLeft(userPos, this.currentPlayer)) {
					for (int i = col-1, j = row-1; i > 0 && j > 0; i--, j--) {
						if (this.board.getPositions()[j][i].getPiece() == this.currentPlayer.getColor()){
							break;
						}
						this.board.flip(this.board.getPositions()[j][i]);
					}
				}
				if(this.board.checkTop(userPos, this.currentPlayer)) {
					for (int j = row-1; j > 0; j--) {
						if (this.board.getPositions()[j][col].getPiece() == this.currentPlayer.getColor()){
							break;
						}
						this.board.flip(this.board.getPositions()[j][col]);
					}
				}
				if(this.board.checkTopRight(userPos, this.currentPlayer)) {
					for (int i = col+1, j = row-1; i < BOARD_SIZE && j > 0; i++, j--) {
						if (this.board.getPositions()[j][i].getPiece() == this.currentPlayer.getColor()){
							break;
						}
						this.board.flip(this.board.getPositions()[j][i]);
					}
				}
			System.out.println("\n" + this.currentPlayer.getName() + " has placed a piece on the board\n");
			if (this.currentPlayer.equals(p1)) {
				firstHasMove = true;
			}
			else {
				secondHasMove = true;
			}
			this.taketurn(this.currentPlayer);
			}
		}
	}
	
	public Board load() {
		Scanner input = new Scanner(System.in);
		try {
			System.out.println("Enter game name: ");
			String gametoload = input.nextLine();
			gametoload +=  ".txt";
		    File g = new File(gametoload);
			
			//reading file 
			Scanner reader = new Scanner(g);
			try {
			    BufferedReader br = new BufferedReader(new FileReader(g));
			    String nameOnLine1 = br.readLine();
			    p1= new Player(nameOnLine1, 'B');
			    String nameOnLine2 = br.readLine();
			    p2= new Player(nameOnLine2, 'W');
			    String nameOnLine3 = br.readLine();
			    if (nameOnLine3 == nameOnLine1) {
			        this.currentPlayer = p1; 
			    }
			    else {
			        this.currentPlayer = p2;
			    }
			    String boardOnLine4 = br.readLine();    
			    Board loadedboard = new Board(boardOnLine4);
			    this.board = loadedboard;
			        
			    //play loaded game
			    System.out.println("\nLoading game...\n");
			    this.board.drawBoard();
			    this.play();
			    g.delete();
			    input.close();
				br.close();
			} 
			catch (IOException e) {
				System.out.println("Could not read file game");
				e.printStackTrace();
			}
			reader.close();
		} 
		catch (FileNotFoundException e) {
			System.out.println("\nGame name not found. Try again.\n");
			start();
		}
		return board;
	}
	
	private void save(){  
		Scanner input = new Scanner(System.in);
		
		//convert state of board into a string
		String positions = "";
		for (int i = 0; i<BOARD_SIZE; i++) {
		    for (int j = 0; j<BOARD_SIZE; j++) {
		    	positions += this.board.getPositions()[i][j].getPiece();
		    } 
		}
		//creating file
		try {
			System.out.println("Enter file name to save game: ");
			String gamename = input.nextLine();
			String gamefile = gamename + ".txt";
		    File g = new File(gamefile);
		    if (g.createNewFile()) {
		    	System.out.println("\nNew game saved as: " + gamename);
		    	System.out.println("Game will exit.");
		    	} 
		    else 
		    	{
		        System.out.println("Saving to existing game");
		    	System.out.println("Game will exit.");
		    }
		    //writing game to file
			FileWriter writegame = new FileWriter(gamefile);
			writegame.write(p1.getName() + "\n");
			writegame.write(p2.getName() + "\n");	
			writegame.write(this.currentPlayer.getName() + "\n");
			writegame.write(positions + "\n");
		    writegame.close();
		    System.exit(0);
		    } 
		catch (IOException e) {
		      System.out.println("An error occurred.");
		      e.printStackTrace();
		    }
		}
	
	public void play() {
		while(!this.isGameOver()) {
			this.displayInstructions();
			this.makeMove();
			this.board.drawBoard();
		}
		displayWinner();
		System.out.println("Game will exit.");
	}
	
	public void start() {
		Scanner input = new Scanner(System.in);
		System.out.println("Choose an option:");
		System.out.println("1. Start new game");
		System.out.println("2. Load a game");
		System.out.println("3. Quit");
		
		int option = 0;
		boolean validNumber = false;

		while (!validNumber) {
			try {
				option = input.nextInt();
				input.nextLine();
				while (option != 1 && option != 2 && option != 3) {
					System.out.println("Please enter a number between 1 and 3: " ); 
					option = input.nextInt(); 
					input.nextLine();
					}
				validNumber = true;
			}
			catch (InputMismatchException e){
				input.nextLine();
				System.out.println("Please enter a number between 1 and 3: " ); 
			}
		}
		if (option==1) {
			System.out.println("Enter name of players:");
			String firstName = input.nextLine();
			Player p1 = new Player(firstName, 'B');
			String secondName = input.nextLine();
			Player p2 = new Player(secondName, 'W');
			Game newGame = new Game(p1, p2);
	
			System.out.println("Choose a starting position:");
			System.out.println("1. Four by four starting position");
			System.out.println("2. Off-set starting position #1");
			System.out.println("3. Off-set starting position #2");
			System.out.println("4. Off-set starting position #3");
			System.out.println("5. Off-set starting position #4");
			
			int startPos = 0;
			boolean validNumberPos = false;

			while (!validNumberPos) {
				try {
					startPos = input.nextInt();
					input.nextLine();
					while (startPos != 1 && startPos != 2 && startPos != 3 && startPos != 4 && startPos != 5) {
						System.out.println("Please enter a number between 1 and 5: " ); 
						startPos = input.nextInt(); 
						}
					validNumberPos = true;
				}
				catch (InputMismatchException e){
					input.nextLine();
					System.out.println("Please enter a number between 1 and 5: " ); 
				}
			}
			Board board = newGame.initBoard(startPos);
			newGame.play();
			input.close();
			}
		else if (option ==2) { 
			this.board = this.load();
		}
		else if (option ==3) {
			System.out.println("Exiting game...");
			System.exit(0);
		}
	}

	public void displayInstructions() {
		System.out.println("\nIt is " + this.currentPlayer.getName() + "'s turn");
		if (!this.board.isBoardPlayable(this.currentPlayer)) {
			System.out.println("There are no valid moves for current player.");
			System.out.println("1. Concede");
			System.out.println("2. Save");
		}
		else {
			System.out.println("\n1. Concede");
			System.out.println("2. Save");
			System.out.println("3. Place piece");
		}
	}
}

