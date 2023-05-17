import java.util.Arrays;

public class Board {
    int rows;
    int cols;
    Tile[][] tiles;

    public Board(int rows, int cols, Tile[][] tiles) {
        this.rows = rows;
        this.cols = cols;
        tiles = new Tile[rows][cols];
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