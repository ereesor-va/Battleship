package main;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.DatagramSocket;

public class GameControl {
	int[][] board;
	DatagramSocket sock;

	public GameControl() {
		board = new int[10][10];
		for(int i=0;i<10;i+=1) {
			for(int j = 0;j<10;j+=1) {
				board[i][j]=0;
			}
		}			
	}
	
	public void reset() {
		board = new int[10][10];
		for(int i=0;i<10;i+=1) {
			for(int j = 0;j<10;j+=1) {
				board[i][j]=0;
			}
		}
	}
	
	public void printBoard() {
		for(int i=0;i<10;i+=1) {
			for(int j = 0;j<10;j+=1) {
				System.out.print(board[i][j]+" ");
			}
			System.out.println("");
		}
	}
	
	/* Types
	 * 0- 1 spot test
	 * 1- 2 spot ship
	 * 2- 3 spot ship
	 * 3- 3 spot ship
	 * 4- 4 spot ship
	 * 5- 5 spot ship
	 * 
	 * Location
	 * Given as an x,y pair in an int array
	 * 0,0 is top left corner
	 * 
	 * Direction
	 * 1-north
	 * 2-east
	 * 3-south
	 * 4-west
	*/
	public void placeShip(int type,int x,int y,int direction) throws Exception {
		if(x<0||x>9||y<0||y>9) {
			throw new Exception("Invalid Location") ;
		}
		
		if(type==0) {
			board[y][x]=99;
		}else if(type<=5) {
			if(direction==1) {
				if(y-type>=0) {
					for(int i =0;i<=type;i+=1) {
						board[y-i][x]=type;
					}
				}else {
					System.out.println("Nice Try, No Hiding Ships");
				}
			}else if(direction==2) {
				if(x+type<=9) {
					for(int i =0;i<=type;i+=1) {
						board[y][x+i]=type;
					}
				}else {
					System.out.println("Nice Try, No Hiding Ships");
				}
			}else if(direction==3) {
				if(y+type<=9) {
					for(int i =0;i<=type;i+=1) {
						board[y+i][x]=type;
					}
				}else {
					System.out.println("Nice Try, No Hiding Ships");
				}
			}else if(direction==4) {
				if(x-type>=0) {
					for(int i =0;i<=type;i+=1) {
						board[y][x-i]=type;
					}
				}else {
					System.out.println("Nice Try, No Hiding Ships");
				}
			}else {
				throw new Exception("Invalid Direction") ;
			}
		}else {
			throw new Exception("Invalid Type") ;
		}
	}
	
	public int fire(int x,int y) {
		
		return board[y][x];
	}
	
	public boolean isBoardEmpty() {
		boolean empty=true;
		for(int i=0;i<10;i+=1) {
			for(int j = 0;j<10;j+=1) {
				if(board[i][j]!=0) {
					empty=false;
				}
			}
			
		}
		return empty;
	}
	
	public void printRules() {
		System.out.println("The rules for placing ships:");
		System.out.println("1st number-x location -- 0 is left side, 9 is right side");
		System.out.println("2nd number-y location -- 0 is top, 9 is bottom");
		System.out.println("3rd number-direction -- 1=north 2=east 3=south 4=west");
		System.out.println("Enter 'h' to reprint these rules");
	}
	
	public void startGame(DatagramSocket s) {
		sock=s;
		BufferedReader console = new BufferedReader(new InputStreamReader(System.in));
		String input="";
		String[] vars;
		int x,y,dir;
		printBoard();
		printRules();
		System.out.println("Place your dingy (2 space ship)");
		try {
			input=console.readLine();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		vars=input.split(" ");
		x=Integer.parseInt(vars[0]);
		y=Integer.parseInt(vars[1]);
		dir=Integer.parseInt(vars[2]);
		try {
			placeShip(1,x,y,dir);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		printBoard();
		System.out.println("Place your cruiser (3 space ship)");
		try {
			input=console.readLine();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		vars=input.split(" ");
		x=Integer.parseInt(vars[0]);
		y=Integer.parseInt(vars[1]);
		dir=Integer.parseInt(vars[2]);
		try {
			placeShip(2,x,y,dir);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		printBoard();
		System.out.println("Place your destroyer (4 space ship)");
		try {
			input=console.readLine();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		vars=input.split(" ");
		x=Integer.parseInt(vars[0]);
		y=Integer.parseInt(vars[1]);
		dir=Integer.parseInt(vars[2]);
		try {
			placeShip(3,x,y,dir);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		printBoard();
		System.out.println("Place your dreadnought (5 space ship)");
		try {
			input=console.readLine();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		vars=input.split(" ");
		x=Integer.parseInt(vars[0]);
		y=Integer.parseInt(vars[1]);
		dir=Integer.parseInt(vars[2]);
		try {
			placeShip(4,x,y,dir);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		printBoard();
		System.out.println("Place your carrier (6 space ship)");
		try {
			input=console.readLine();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		vars=input.split(" ");
		x=Integer.parseInt(vars[0]);
		y=Integer.parseInt(vars[1]);
		dir=Integer.parseInt(vars[2]);
		try {
			placeShip(5,x,y,dir);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		printBoard();
		
	}
}






