
public class GameGrid {
	private int xSize = 7;
	private int ySize = 6;
	private int grid[][];
	
	GameGrid(){
		grid = new int[xSize][ySize];
		for(int x=0; x < xSize; x++) { // init grid
			for(int y=0; y < ySize; y++) {
				grid[x][y] = 0;
			}
		}
	}
	
	/**
	* Platziert den Stein in der ersten freien Linie in der Spalte
	* @param column gibt die Spalte an an der der Stein gesetzt werden soll
	* @param player Player der den Stein spielt
	* @return error Message (0=ok, -1 error)
	*/
	int placeStone(int column, Player player){
		int line;
		line = getLineIndex(column);
		if(line < ySize-1) { // prüfe ob Platz frei
			grid[column][line] = player.index;
			return 0;
		}else {
			return -1; // Error
		}
	}
	
	/**
	* Gibt den Index der ersten leeren Reihe zurück
	* @param column gibt die Spalte an in der der Index gesucht wird
	* @return line Index der Reihe
	*/
	private int getLineIndex(int column) {
		int line = 0; 
		while(grid[column][line] == 0) {
			line++;
		}
		line--;
		return line;
	}
	
	/**
	* Gibt das Array mit den Spielsteinen zurück
	* @return grid Array mit den Spielsteinen (0=leer, 1=Spieler1; 2=Spieler2)
	*/
	int[][] getGrid() {
		return grid;
	}
}
