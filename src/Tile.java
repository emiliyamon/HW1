public class Tile {
    final char value; // check later

    Tile(char id) {
        this.value = id;
    }

    public static Tile fromChar(char value) {
        return new Tile(value);
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
