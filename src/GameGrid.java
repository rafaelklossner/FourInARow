
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
	
	void placeStone(){
		
	}
	
	int[][] getGrid() {
		return grid;
	}
}
