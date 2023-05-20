import java.util.Arrays;

public class Board {
    String boardString;
    Tile[][] tiles;


    public Board(String boardString) {
        this.boardString = boardString;
        String[] boardRowList;
        String[] rowTileList;
        char charTile;
        boardRowList = boardString.split("|"); // splits the board into string rows in a list

        for (int i = 0; i < boardRowList.length; i++) {
            rowTileList = boardRowList[i].split(" "); // splits the rows into string tiles in a list
            for (int j = 0; j < rowTileList.length; j++) {
                charTile = rowTileList[j].charAt(0);
                tiles[i][j] = Tile.fromChar(charTile);
            }
        }
    }


    public Board(Board originalBoard) {
        this.tiles = new Tile[originalBoard.tiles.length][originalBoard.tiles[0].length];
        for (int i = 0; i < originalBoard.tiles.length; i++) {
            for (int j = 0; j < originalBoard.tiles[i].length; j++) {
                this.tiles[i][j] = originalBoard.tiles[i][j];
            }
        }
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


    public int[][] checkMoves(Board board) {
        // get index of space
        String[] indexString = board.findSpace(board).split(" ");
        int[] indexInt = new int[indexString.length];
        for (int i = 0; i < indexString.length; i++) {
            indexInt[i] = Integer.parseInt(indexString[i]);
        }

        int rowIndex = indexInt[0];
        int colIndex = indexInt[1];
        int firstRow = 0;
        int maxRow = board.tiles.length - 1;
        int firstCol = 0;
        int maxCol = board.tiles[0].length - 1;


        int[][] movableTiles = new int[4][2];

        boolean up = !(rowIndex == maxRow); // false if there's no tile below space
        if (up) {
            movableTiles[0][0] = board.tiles[rowIndex + 1][colIndex].value;
            movableTiles[0][1] = 1;
        } else {
            movableTiles[0][0] = 0;
            movableTiles[0][1] = 0;
        }

        boolean down = !(rowIndex == firstRow); // false if there's no tile above space
        if (down) {
            movableTiles[1][0] = board.tiles[rowIndex - 1][colIndex].value;
            movableTiles[1][1] = 1;
        } else {
            movableTiles[1][0] = 0;
            movableTiles[1][1] = 0;
        }

        boolean right = !(colIndex == firstCol); // false if there's no tile to the left of space
        if (right) {
            movableTiles[2][0] = board.tiles[rowIndex][colIndex + 1].value;
            movableTiles[2][1] = 1;
        } else {
            movableTiles[2][0] = 0;
            movableTiles[2][1] = 0;
        }

        boolean left = !(colIndex == maxCol); // false if there's no tile to the right of space
        if (left) {
            movableTiles[3][0] = board.tiles[rowIndex][colIndex - 1].value;
            movableTiles[3][1] = 1;
        } else {
            movableTiles[3][0] = 0;
            movableTiles[3][1] = 0;
        }
        
        return movableTiles;
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