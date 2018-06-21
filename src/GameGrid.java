	/**
	 * Diese Modul realiert die Funktionalitäten rund um das Grid (Spielfeld)
	 * Zudem enthält es Informationen zu den Steinen und Positionen der Auswahlbalken in Spiel und Menu.
	 * Zusätzlich ist auch der aktuelle Spielzustand gespeichert.
	 */

public class GameGrid {

	public int grid[][];
	public int posHighlightBar;
	public int posMenuHighlightBar;
	public int player;
	public int lastStoneCol;
	public int lastStoneRow;
	public GameState gameState;
	public Direction direction;

	/**
	 * Konstruktor initialisiert das Spielfeldarray grid, setzt den Spieler der am Zug ist auf Spieler 1 und
	 * lässt das Spiel mit dem Menu Fenster beginnen.
	 */
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
	 * @return InPlayState Rückmeldung wie das Spiel weiter gehen soll (Unentschieden, witer spielen, Sieg, volle Spalte)
	 */
	public InPlayState placeStone(int col){
		int row;
		row = getRowIndex(col);
		if(row >= 0) { // prüfe ob Platz frei
			grid[col][row] = player;
			lastStoneCol = col;
			lastStoneRow = row;
			int playerOld = player;
			changePlayer();
			direction = checkWin(col, row);
			if (direction == Direction.StandOff){
				return InPlayState.StandOff;
			}else if(direction == Direction.NoWin){
				return InPlayState.NoWin;
			}else {
				if (playerOld == 1) {
					return InPlayState.Player1;
				}else {
					return InPlayState.Player2;	
				}
			}
		}else {
			return InPlayState.ColumnFull;
		}
	}
	
	/**
	 * Wechselt den Spieler der am Zug ist.
	 */
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
	 * Prüft ob durch den aktuellen Stein jemand gewonnen hat und
	 * gibt die Siegrichtung, zur darstellung zurück.
	 * @param row aktuelle Reihe (beginnt bei 0)
	 * @param col aktuelle Spalte (beginnt bei 0)
	 * @return Direction Siegrichtung, Unentscheden wenn Spielfeld voll oder kein Sieger 
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
		
		if (grid[0][0] != 0 && grid[1][0] != 0 && grid[2][0] != 0 && grid[3][0] != 0 && grid[4][0] != 0
				&& grid[5][0] != 0 && grid[6][0] != 0) {
			return Direction.StandOff;
		}
		
		return Direction.NoWin; // no winner found, continue
	}

	/**
	 * Gibt das Array mit den Spielsteinen zurück
	 * @return grid Array mit den Spielsteinen (0=leer, 1=Spieler1; 2=Spieler2)
	 */
	public int[][] getGrid() {
		return grid;
	}
	
	/**
	 * Verändert die Position vom Auswahlbalken im Spiel.
	 * @param col
	 */
	public void placeHighlightBar(int col) {
		posHighlightBar = col;
	}
	
	/**
	 * Verändert die Position vom Auswahlbalken im Menu
	 * @param choice
	 */
	public void placeMenuHighlightBar(int choice) {
		posMenuHighlightBar = choice;
	}
}
