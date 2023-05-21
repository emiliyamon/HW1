public class Tile {
    final int value;

    public Tile(int value) { // change to int
        this.value = value;
    }


    public boolean isSpace() { // check later if function needs parameters
        return (value == -1);
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
