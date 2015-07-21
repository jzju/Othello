import java.awt.List;
import java.util.ArrayList;

import javax.swing.JOptionPane;

public class Game {
	static int[][] matrix = new int[10][10];
	int player;
	
	public static void main(String[] args) {
		 new Game();
		
	}

	/**
	 * Controll whos turn it is 
	 */
	
	public Game() {
		player = 1;
		matrix[4][4] = 1;
		matrix[4][5] = -1;
		matrix[5][4] = -1;
		matrix[5][5] = 1;
		
		GUI gui = new GUI();
		gui.paint(matrix);
		jjucbr jjucbr = new jjucbr();
		int time = gui.getTime();
		gui.updateLabel(player*-1);
		boolean finish = false;
		int noMove = 1;
		boolean valid = false;
		
		while (!finish) {
			int[][] countMatrix = new int[10][10];
			countMatrix = jjucbr.getAlphaBetaMatrix(matrix, player,3, time);
			
			// change turn if cant move
			if (noMove == 0) {
					noMove = 1;
				}
			for (int i = 1; i < 9; i++) {
				for (int j = 1; j < 9; j++) {
					if (countMatrix[i][j] != 100) {
						noMove = 0;
					}
				}
			}
			if (noMove == 1) {
				gui.msg("Player "+player+" no moves");
				gui.updateLabel(player);
				noMove = 2;
				player = player * -1;
				continue;
			} else if (noMove == 2) {
				gui.msg("Game over");
				finish = true;
				continue;
			}
			// Wait until player pushes a button which is a valid 
			while(!valid)		
			{
				int b = gui.paintrec(countMatrix);
				gui.updateLabel(player);
				int x = b / 10 , y = b % 10;
				valid = turn(x, y, player);
			}
			valid = false;
			gui.paint(matrix);
		}
	}

	/**
	 * Change the board when player put a piece at position (x,y)
	 * 
	 * @param x
	 * 			x position of the piece that is going to be placed 
	 * @param y
	 * 			y position of the piece that is going to be placed
	 * @param player
	 * 			represent the player, -1 for black player and 1 for white player
	 */
	public boolean turn(int x, int y, int player) {
		boolean valid = false;
		x++;
		y++;
		int a = 0;
		int b = 0;

		if (matrix[x][y] == 0) {

			ArrayList<Integer> change = new ArrayList<Integer>(16);
			if (matrix[x - 1][y - 1] == player * -1) {
				int tx = x - 1, ty = y - 1;
				while (matrix[tx][ty] == player * -1) {
					change.add(tx);
					change.add(ty);
					tx--;
					ty--;
				}
				if (matrix[tx][ty] == player) {
					for(int i = 0; i < change.size(); i++) {
						a = change.get(i);
						i++;
						b = change.get(i);
						matrix[a][b] = player;
					}
					valid = true;
				}
				change.clear();
			}

			if (matrix[x][y - 1] == player * -1) {
				int tx = x, ty = y - 1;
				while (matrix[tx][ty] == player * -1) {
					change.add(tx);
					change.add(ty);
					ty--;
				}
				if (matrix[tx][ty] == player) {
					for(int i = 0; i < change.size(); i++) {
						a = change.get(i);
						i++;
						b = change.get(i);
						matrix[a][b] = player;
					}
					valid = true;
				}
				change.clear();
			}

			if (matrix[x + 1][y - 1] == player * -1) {
				int tx = x + 1, ty = y - 1;
				while (matrix[tx][ty] == player * -1) {
					change.add(tx);
					change.add(ty);
					ty--;
					tx++;
				}
				if (matrix[tx][ty] == player) {
					for(int i = 0; i < change.size(); i++) {
						a = change.get(i);
						i++;
						b = change.get(i);
						matrix[a][b] = player;
					}
					valid = true;
				}
				change.clear();

			}

			if (matrix[x + 1][y] == player * -1) {
				int tx = x + 1, ty = y;
				while (matrix[tx][ty] == player * -1) {
					change.add(tx);
					change.add(ty);
					tx++;
				}
				if (matrix[tx][ty] == player) {
					for(int i = 0; i < change.size(); i++) {
						a = change.get(i);
						i++;
						b = change.get(i);
						matrix[a][b] = player;
					}
					valid = true;
				}
				change.clear();

			}

			if (matrix[x + 1][y + 1] == player * -1) {
				int tx = x + 1, ty = y + 1;
				while (matrix[tx][ty] == player * -1) {
					change.add(tx);
					change.add(ty);
					ty++;
					tx++;
				}
				if (matrix[tx][ty] == player) {
					for(int i = 0; i < change.size(); i++) {
						a = change.get(i);
						i++;
						b = change.get(i);
						matrix[a][b] = player;
					}
					valid = true;
				}
				change.clear();
			}

			if (matrix[x][y + 1] == player * -1) {
				int tx = x, ty = y + 1;
				while (matrix[tx][ty] == player * -1) {
					change.add(tx);
					change.add(ty);
					ty++;
				}
				if (matrix[tx][ty] == player) {
					for(int i = 0; i < change.size(); i++) {
						a = change.get(i);
						i++;
						b = change.get(i);
						matrix[a][b] = player;
					}
					valid = true;
				}
				change.clear();
			}

			if (matrix[x - 1][y + 1] == player * -1) {
				int tx = x - 1, ty = y + 1;
				while (matrix[tx][ty] == player * -1) {
					change.add(tx);
					change.add(ty);
					ty++;
					tx--;
				}
				if (matrix[tx][ty] == player) {
					for(int i = 0; i < change.size(); i++) {
						a = change.get(i);
						i++;
						b = change.get(i);
						matrix[a][b] = player;
					}
					valid = true;
				}
				change.clear();
			}

			if (matrix[x - 1][y] == player * -1) {
				int tx = x - 1, ty = y;
				while (matrix[tx][ty] == player * -1) {
					change.add(tx);
					change.add(ty);
					tx--;
				}
				if (matrix[tx][ty] == player) {
					for(int i = 0; i < change.size(); i++) {
						a = change.get(i);
						i++;
						b = change.get(i);
						matrix[a][b] = player;
					}
					valid = true;
				}
				change.clear();

			}

			if (valid) {
				matrix[x][y] = player;
				this.player = player * -1;
			}
		}
		return valid;
	}
}