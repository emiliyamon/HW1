public class Action {
    EnumDirections direction;
    Tile tile;

    Action(EnumDirections direction, Tile tile) {
        this.direction = direction;
        this.tile = tile;
    }

    public String toString () {
        return "move" + " " + tile + " " + direction;
    }
}
