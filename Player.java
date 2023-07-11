package Othello;

public class Player {
	private String name;
	private char color; 
	
	//constructor
	public Player(String name, char color) { 
		super();
		this.name = name;
		this.color = color;
	}
	
	public String getName() { 
		return name;
	}
	
	public char getColor() {
		return color;
	}
	
	public String toString() {
		return name;
	}
}
