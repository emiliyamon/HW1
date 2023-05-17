public class Action {
    EnumDirections direction;
    Tile tile;

    Action(EnumDirections direction, Tile tile) {
        this.direction = direction;
        this.tile = tile;
    }

    public String toString () {
        String move = "Move ";
        String tileString = String.valueOf(tile);
        String directionString = String.valueOf(direction);
        String returnString = move + " " + tileString + " " + directionString;

        return returnString;
    }
}
