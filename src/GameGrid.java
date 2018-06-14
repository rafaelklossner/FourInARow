	/**
	 * Diese Modul realiert die Funktionalitäten rund um das Grid (Spielfeld)
	 */

public class GameGrid {

	int grid[][];
	int posHighlightBar;
	int posMenuHighlightBar;
	int player;
	int lastStoneCol;
	int lastStoneRow;
	GameState gameState;
	Direction direction;

	GameGrid(){
		grid = new int[FourInARow.COL][FourInARow.ROW];
		for(int col=0; col < FourInARow.COL; col++) { // init grid
			for(int row=0; row < FourInARow.ROW; row++) {
				grid[col][row] = 0;
			}
		}
		player = 1;
		posHighlightBar = 0;
		gameState = GameState.Menu;
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
			lastStoneCol = col;
			lastStoneRow = row;
			int playerOld = player;
			changePlayer();
			direction = checkWin(col, row);
			if (direction != Direction.NoWin) {
				return playerOld;
			}else {
				return 0;
			}
		}else {
			return -1; // Error
		}
	}
	
	
	
	private void changePlayer() {
		if(player == 1) {
			player = 2;
		}else {
			player = 1;
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
	private Direction checkWin(int col, int row) {
		int player = grid[col][row];
		if (player == 0) {
			return Direction.NoWin; // there is no stone
		}

		if(row - 3 >= 0) { // up in all 3 directions
			if (player == grid[col][row-1] && // look up
					player == grid[col][row-2] &&
					player == grid[col][row-3])
				return Direction.Up;
			if (col - 3 >= 0 &&
					player == grid[col-1][row-1] && // look up & left
					player == grid[col-2][row-2] &&
					player == grid[col-3][row-3])
				return Direction.UpLeft;
			if (col + 3 < FourInARow.COL &&
					player == grid[col+1][row-1] && // look up & right
					player == grid[col+2][row-2] &&
					player == grid[col+3][row-3])
				return Direction.UpRight;
		}

		if (row + 3 < FourInARow.ROW) { // down in all 3 directions
			if (player == grid[col][row+1] && // look down
					player == grid[col][row+2] &&
					player == grid[col][row+3])
				return Direction.Down;
			if (col - 3 >= 0 &&
					player == grid[col-1][row+1] && // look down & left
					player == grid[col-2][row+2] &&
					player == grid[col-3][row+3])
				return Direction.DownLeft;
			if (col + 3 < FourInARow.COL &&
					player == grid[col+1][row+1] && // look down & right
					player == grid[col+2][row+2] &&
					player == grid[col+3][row+3])
				return Direction.DownRight;
		}
		
		// left and right
		if (col + 3 < FourInARow.COL &&
				player == grid[col+1][row] && // look right
				player == grid[col+2][row] &&
				player == grid[col+3][row])
			return Direction.Right;
		if (col - 3 >= 0 &&
				player == grid[col-1][row] && // look left
				player == grid[col-2][row] &&
				player == grid[col-3][row])
			return Direction.Left;
		
		return Direction.NoWin; // no winner found
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
	
	public void placeMenuHighlightBar(int choice) {
		posMenuHighlightBar = choice;
	}
}
