/**
 * Represents an action in a puzzle game
 * This class encapsulates the properties and behavior of an action that can be performed in a puzzle game
 */
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


    /**
     * function for printing the move made in a string format
     *
     * @return string description of move made
     */
    public String toString() {
        String[] directionsList = {"up", "down", "right", "left"};
        int i = -1;
        switch (direction) {
            case UP:
                i = 0;
                break;
            case DOWN:
                i = 1;
                break;
            case RIGHT:
                i = 2;
                break;
            case LEFT:
                i = 3;
                break;
        }
        return "Move " + tile.getValue() + " " + directionsList[i];
    }

}
