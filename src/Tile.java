public class Tile {
    final char value;

    Tile(char value) { // change to int
        this.value = value;
    }

    public static Tile fromChar(char value) {
        return new Tile(value);
    }

    public boolean isSpace() { // check later if function needs parameters
        return (value == '_');
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
