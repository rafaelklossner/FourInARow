	/**
	 * Diese Modul realiert die Funktionalitäten rund um das Grid (Spielfeld)
	 */

public class GameGrid {

	int grid[][];
	int posHighlightBar;
	int player;

	GameGrid(){
		grid = new int[FourInARow.COL][FourInARow.ROW];
		for(int col=0; col < FourInARow.COL; col++) { // init grid
			for(int row=0; row < FourInARow.ROW; row++) {
				grid[col][row] = 0;
			}
		}
		player = 1;
		posHighlightBar = 0;
	}

	/**
	 * Platziert den Stein in der ersten freien Linie in der Spalte
	 * @param col gibt die Spalte an an der der Stein gesetzt werden soll (beginnt bei 0)
	 * @param player Player der den Stein spielt
	 * @return error Message (0=continue, 1 Player1 wins, 2 Player2 wins, -1 error)
	 */
	int placeStone(int col){
		int row;
		row = getRowIndex(col);
		if(row >= 0) { // prüfe ob Platz frei
			grid[col][row] = player;
			System.out.println("col: " + col + " row: " + row);
			if(player == 1) {
				player = 2;
			}else {
				player = 1;
			}
			System.out.println("checkWin result: " + checkWin(col, row));
			return checkWin(col, row) ;
		}else {
			System.out.println("Col is full");
			return -1; // Error
		}
	}

	/**
	 * Gibt den Index der ersten leeren Reihe zurück
	 * @param col gibt die Spalte an in der der Index gesucht wird
	 * @return row Index der Reihe (beginnt bei 0)
	 */
	private int getRowIndex(int col) {
		for(int row=0; row < FourInARow.ROW; row++) {
			if(grid[col][row] != 0) {
				return row-1;
			}
		}
		return FourInARow.ROW - 1;
	}

	/**
	 * Prüft ob durch den aktuellen Stein jemand gewonnen hat
	 * @param row aktuelle Reihe (beginnt bei 0)
	 * @param col aktuelle Spalte (beginnt bei 0)
	 * @param playerIndex or 0
	 */
	private int checkWin(int col, int row) {
		int player = grid[col][row];
		if (player == 0) {
			return player; // there is no stone
		}

		if(row - 3 >= 0) { // up in all 3 directions
			if (player == grid[col][row-1] && // look up
					player == grid[col][row-2] &&
					player == grid[col][row-3])
				return player;
			if (col - 3 >= 0 &&
					player == grid[col-1][row-1] && // look up & left
					player == grid[col-2][row-2] &&
					player == grid[col-3][row-3])
				return player;
			if (col + 3 < FourInARow.COL &&
					player == grid[col+1][row-1] && // look up & right
					player == grid[col+2][row-2] &&
					player == grid[col+3][row-3])
				return player;
		}

		if (row + 3 < FourInARow.ROW) { // down in all 3 directions
			if (player == grid[col][row+1] && // look down
					player == grid[col][row+2] &&
					player == grid[col][row+3])
				return player;
			if (col - 3 >= 0 &&
					player == grid[col-1][row+1] && // look down & left
					player == grid[col-2][row+2] &&
					player == grid[col-3][row+3])
				return player;
			if (col + 3 < FourInARow.COL &&
					player == grid[col+1][row+1] && // look down & right
					player == grid[col+2][row+2] &&
					player == grid[col+3][row+3])
				return player;
		}
		
		// left an right
		if (col + 3 < FourInARow.COL &&
				player == grid[col+1][row] && // look right
				player == grid[col+2][row] &&
				player == grid[col+3][row])
			return player;
		if (col - 3 >= 0 &&
				player == grid[col-1][row] && // look left
				player == grid[col-2][row] &&
				player == grid[col-3][row])
			return player;
		
		return 0; // no winner found
	}

	/**
	 * Gibt das Array mit den Spielsteinen zurück
	 * @return grid Array mit den Spielsteinen (0=leer, 1=Spieler1; 2=Spieler2)
	 */
	public int[][] getGrid() {
		return grid;
	}
	
	public void placeHighlightBar(int col) {
		posHighlightBar = col;
	}
}
