import java.util.Arrays;

public class Board {

    String boardString;
    Tile[][] tiles;


    public void setBoardString() {
        this.boardString = boardString;
    }

    public String getBoardString(String boardString) {
        return this.boardString;
    }


    public Board(String boardString) {
        this.boardString = boardString;
        String[] boardRowList;
        String[] rowTileList;
        char charTile;
        int i = 0;
        boardRowList = boardString.split("|"); // splits the board into string rows in a list

        for (String tilesRow : boardRowList) {
            rowTileList = tilesRow.split(" "); // splits the rows into string tiles in a list
            for (int j = 0; j < rowTileList.length; j++) {
                charTile = rowTileList[j].charAt(0);
                tiles[i][j] = Tile.fromChar(charTile);
            }
        }
    }


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