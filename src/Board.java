import java.util.Arrays;

public class Board {

    String boardString;
    Tile[][] tiles;


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


    public void setBoardString() {
        this.boardString = boardString;
    }

    public String getBoardString(String boardString) {
        return this.boardString;
    }


    public String findSpace(Board board) {
        for (int i = 0; i < board.tiles.length; i++) {
            for (int j = 0; j < board.tiles[0]. length; j++) {
                if (board.tiles[i][j].isSpace()) {
                    return i + " " + j;
                }
            }
        }
        return "Mistake in loop";
    }


    public boolean[] checkMoves(Board board) {
        // get index of space
        String[] indexString = board.findSpace(board).split(" ");
        int[] indexInt = new int[indexString.length];

        for (int i = 0; i < indexString.length; i++) {
            indexInt[i] = Integer.parseInt(indexString[i]);
        }

        int firstRow = 0;
        int maxRow = board.tiles.length - 1;
        int firstCol = 0;
        int maxCol = board.tiles[0].length - 1;

        boolean noAbove = (indexInt[0] == firstRow);
        boolean noBelow = (indexInt[0] == maxRow);
        boolean noLeft = (indexInt[1] == firstCol);
        boolean noRight = (indexInt[1] == maxCol);

        boolean up = noBelow;
        boolean down = noAbove;
        boolean right = noLeft;
        boolean left = noRight;

        boolean[] possibleActions = {up, down, right, left};
        return possibleActions;
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