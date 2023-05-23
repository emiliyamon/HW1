public class Action {
    private EnumDirections direction;
    private Tile tile;

    public Action(EnumDirections direction, Tile tile) {
        this.direction = direction;
        this.tile = tile;
    }


    public EnumDirections getDirection() {
        return direction;
    }


    public Tile getTile() {
        return tile;
    }

    public String toString() {
        return "move" + " " + tile + " " + direction;
    }
}
