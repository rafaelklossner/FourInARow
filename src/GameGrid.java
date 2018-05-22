	/**
	 * Diese Modul realiert die Funktionalitäten rund um das Grid (Spielfeld)
	 */

public class GameGrid {
	final int COL = 7;
	final int ROW = 6;
	private int grid[][];

	GameGrid(){
		grid = new int[COL][ROW];
		for(int col=0; col < COL; col++) { // init grid
			for(int row=0; row < ROW; row++) {
				grid[col][row] = 0;
			}
		}
	}

	/**
	 * Platziert den Stein in der ersten freien Linie in der Spalte
	 * @param col gibt die Spalte an an der der Stein gesetzt werden soll (beginnt bei 0)
	 * @param player Player der den Stein spielt
	 * @return error Message (0=continue, 1 Player1 wins, 2 Player2 wins, -1 error)
	 */
	int placeStone(int col, Player player){
		int row;
		row = getRowIndex(col);
		if(row < ROW) { // prüfe ob Platz frei
			grid[col][row] = player.index;
			return checkWin(col, row);
		}else {
			return -1; // Error
		}
	}

	/**
	 * Gibt den Index der ersten leeren Reihe zurück
	 * @param col gibt die Spalte an in der der Index gesucht wird
	 * @return row Index der Reihe (beginnt bei 0)
	 */
	private int getRowIndex(int col) {
		int row = 0; 
		while(grid[col][row] == 0) {
			row++;
		}
		row--;
		return row;
	}

	/**
	 * Prüft ob durch den aktuellen Stein jemand gewonnen hat
	 * @param row aktuelle Reihe (beginnt bei 0)
	 * @param col aktuelle Spalte (beginnt bei 0)
	 * @param playerIndex or 0
	 */
	private int checkWin(int row, int col) {
		int player = grid[row][col];
		if (player == 0) {
			return player; // there is no stone
		}

		if(row - 3 >= 0) { // down in all 3 directions
			if (player == grid[row-1][col] && // look down
					player == grid[row-2][col] &&
					player == grid[row-3][col])
				return player;
			if (col - 3 >= 0 &&
					player == grid[row-1][col-1] && // look down & left
					player == grid[row-2][col-2] &&
					player == grid[row-3][col-3])
				return player;
			if (col + 3 < COL &&
					player == grid[row-1][col+1] && // look down & right
					player == grid[row-2][col+2] &&
					player == grid[row-3][col+3])
				return player;
		}

		if (row + 3 < ROW) { // up in 2 directions
			if (col + 3 < COL &&
					player == grid[row+1][col+1] && // look up & right
					player == grid[row+2][col+2] &&
					player == grid[row+3][col+3])
				return player;
			if (col - 3 >= 0 &&
					player == grid[row+1][col-1] && // look up & left
					player == grid[row+2][col-2] &&
					player == grid[row+3][col-3])
				return player;
		}
		
		// left an right
		if (col + 3 < COL &&
				player == grid[row][col+1] && // look right
				player == grid[row][col+2] &&
				player == grid[row][col+3])
			return player;
		if (col - 3 >= 0 &&
				player == grid[row][col-1] && // look left
				player == grid[row][col-2] &&
				player == grid[row][col-3])
			return player;
		
		return 0; // no winner found
	}

	/**
	 * Gibt das Array mit den Spielsteinen zurück
	 * @return grid Array mit den Spielsteinen (0=leer, 1=Spieler1; 2=Spieler2)
	 */
	int[][] getGrid() {
		return grid;
	}
}
