public class Tile {
    private final int value;

    public Tile(int value) {
        this.value = value;
    }


    public int getValue() {
        return value;
    }


    /**
     * check if the tile is the "space" tile in a board.
     *
     * @return boolean true or false according to test result.
     */
    public boolean isSpace() {
        if (value == -1) {
            return true;
        } else {
            return false;
        }
    }

    // don't touch!!

    @Override
    public boolean equals(Object other) {
        if (!(other instanceof Tile)) {
            return false;
        }
        Tile tile = (Tile) other;
        return value == tile.value;
    }

    @Override
    public int hashCode() {
        return Integer.hashCode(value);
    }
}
