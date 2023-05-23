import java.util.Arrays;

/**
 * Represents a puzzle board
 * provides methods of checking for desired tiles
 */
public class Board {
    private Tile[][] tiles;


    public Board(String boardString) {

        String[] boardRowList = boardString.split("\\|");
        String[] rowTileList = boardRowList[0].split(" ");
        String[][] tilesString = new String[boardRowList.length][rowTileList.length];

        for (int i = 0; i < boardRowList.length; i++) {
            tilesString[i] = boardRowList[i].split(" ");
        }
        this.tiles = new Tile[tilesString.length][tilesString[0].length];

        for (int i = 0; i < tilesString.length; i++) {
            for (int j = 0; j < tilesString[0].length; j++) {
                if (tilesString[i][j].equals("_")) {
                    tiles[i][j] = new Tile(-1);
                } else {
                    tiles[i][j] = new Tile(Integer.parseInt(tilesString[i][j]));
                }
            }
        }
    }


    public Board(Tile[][] tiles) {
        this.tiles = tiles;

        for (int i = 0; i < tiles.length; i++) {
            for (int j = 0; j < tiles[0].length; j++) {
                this.tiles[i][j] = new Tile(tiles[i][j].getValue());
            }
        }
    }


    public Tile[][] getTiles() {
        return tiles;
    }


    /**
     * finds the index of the "space" tile in the current board.
     *
     * @param board current board
     * @return int array which consist of  {row index, col index} of "space" tile
     */
    public int[] findSpace(Board board) {
        int[] spaceIndex = new int[2];
        for (int i = 0; i < board.tiles.length; i++) {
            for (int j = 0; j < board.tiles[0].length; j++) {
                if (tiles[i][j].isSpace()) {
                    spaceIndex[0] = i;
                    spaceIndex[1] = j;
                    break;
                }
            }
        }
        return spaceIndex;
    }


    /**
     * finds the index of the requested value in the current board
     *
     * @param tiles current board Tile array
     * @param value the desired Tile value
     * @return int array which consist of  {row index, col index} of desired tile
     */
    public int[] findIndex(Tile[][] tiles, int value) {
        int[] indexList = new int[2];
        for (int i = 0; i < tiles.length; i++) {
            for (int j = 0; j < tiles[0].length; j++) {
                if (tiles[i][j].getValue() == value) {
                    indexList[0] = i;
                    indexList[1] = j;
                    break;
                }
            }
        }
        return indexList;
    }


    // don't touch!!

    @Override
    public boolean equals(Object other) {
        if (!(other instanceof Board)) {
            return false;
        }
        Board board = (Board) other;
        return Arrays.deepEquals(tiles, board.tiles);
    }

    @Override
    public int hashCode() {
        return Arrays.deepHashCode(tiles);
    }
}