/**
 * Represents a state in a puzzle game.
 * This class encapsulates the properties and behavior of a state in a puzzle game, such as the game board configuration.
 */
public class State {
    private Board board;


    public State (Board board) {
        this.board = board;
    }


    public Board getBoard() {
        return board;
    }

    /**
     * checking if all tiles are in goal place.
     *
     * @return true if all tiles are in goal place, false otherwise.
     */
    public boolean isGoal() {
        int correctValue = 1;
        int maxValue = (board.getTiles().length * board.getTiles()[0].length) - 1;

        for (int i = 0; i < board.getTiles().length; i++) {
            for (int j = 0; j < board.getTiles()[0].length; j++) {
                if (i == (board.getTiles().length - 1) && j == (board.getTiles()[0].length-1)) {
                    continue;
                } else if (board.getTiles()[i][j].getValue() == correctValue) {
                    correctValue++;
                }
            }
        }
        return  (correctValue - 1 == maxValue);
    }

    /**
     * Making an Action array of actions, for each tile, using new array: [tile value][can/can't move]. Each row references to different direction this order: up, down, right, left.
     *
     * @return Action array includes the possible action for each tile to move.
     */
    public Action[] actions() {
        // get index of space
        int[] spaceIndex = board.findSpace(board);
        int spaceRowIndex = spaceIndex[0];
        int spaceColIndex = spaceIndex[1];

        int firstRow = 0;
        int maxRow = board.getTiles().length - 1;
        int firstCol = 0;
        int maxCol = board.getTiles()[0].length - 1;

        int[][] movableTiles = new int[4][2];

        boolean upTest = (spaceRowIndex != maxRow); // false if there's no tile below space
        if (upTest) {
            movableTiles[0][0] = board.getTiles()[spaceRowIndex + 1][spaceColIndex].getValue();
            movableTiles[0][1] = 1;
        } else {
            movableTiles[0][0] = 0;
            movableTiles[0][1] = 0;
        }

        boolean downTest = (spaceRowIndex != firstRow); // false if there's no tile above space
        if (downTest) {
            movableTiles[1][0] = board.getTiles()[spaceRowIndex - 1][spaceColIndex].getValue();
            movableTiles[1][1] = 1;
        } else {
            movableTiles[1][0] = 0;
            movableTiles[1][1] = 0;
        }

        boolean rightTest = (spaceColIndex != firstCol); // false if there's no tile to the left of space
        if (rightTest) {
            movableTiles[2][0] = board.getTiles()[spaceRowIndex][spaceColIndex - 1].getValue();
            movableTiles[2][1] = 1;
        } else {
            movableTiles[2][0] = 0;
            movableTiles[2][1] = 0;
        }

        boolean leftTest = (spaceColIndex != maxCol); // false if there's no tile to the right of space
        if (leftTest) {
            movableTiles[3][0] = board.getTiles()[spaceRowIndex][spaceColIndex + 1].getValue();
            movableTiles[3][1] = 1;
        } else {
            movableTiles[3][0] = 0;
            movableTiles[3][1] = 0;
        }
        //  (tile,up), (tile,down), (tile,right), (tile,left)

        int arrayLen = 0;
        for (int i = 0; i < movableTiles.length; i++) {
            arrayLen += movableTiles[i][1];
        }
        Action[] actions = new Action[arrayLen];

        int up = movableTiles[0][1];
        int down = movableTiles[1][1];
        int right = movableTiles[2][1];
        int left = movableTiles[3][1];

        int i = 0;
        if (up != 0) {
            actions[i] = new Action(EnumDirections.UP, new Tile(movableTiles[0][0]));
            i++;
        }
        if (down != 0) {
            actions[i] = new Action(EnumDirections.DOWN, new Tile(movableTiles[1][0]));
            i++;
        }
        if (right != 0) {
            actions[i] = new Action(EnumDirections.RIGHT, new Tile(movableTiles[2][0]));
            i++;
        }
        if (left != 0) {
            actions[i] = new Action(EnumDirections.LEFT, new Tile(movableTiles[3][0]));
        }
        return actions;
    }

    /**
     * Conducting the action to the last board, and making new board and state.
     *
     * @param action the action conducts to the tiles.
     * @return new state made out of action on the previous board.
     */
    public State result(Action action) {
        Tile[][] tiles = board.getTiles();
        Tile tile = action.getTile();
        Tile space = new Tile(-1);
        int value = tile.getValue();
        int[] indexList = board.findIndex(tiles, value);

        int tileRowIndex = indexList[0];
        int tileColIndex = indexList[1];

        int newRow = tileRowIndex;
        int newCol = tileColIndex;

        switch (action.getDirection()) {
            case UP:
                newRow = tileRowIndex - 1;
                break;
            case DOWN:
                newRow = tileRowIndex + 1;
                break;
            case RIGHT:
                newCol = tileColIndex + 1;
                break;
            case LEFT:
                newCol = tileColIndex - 1;
                break;
        }

        Tile[][] newTiles = new Tile[tiles.length][tiles[0].length];
        for (int i = 0; i < tiles.length; i++) {
            for (int j = 0; j < tiles[0].length; j++) {
                newTiles[i][j] = new Tile(tiles[i][j].getValue());
            }
        }

        newTiles[tileRowIndex][tileColIndex] = space;
        newTiles[newRow][newCol] = tile;

        Board newBoard = new Board(newTiles);
        return new State(newBoard);
    }

    // don't touch!!

    @Override
    public boolean equals(Object other) {
        if (!(other instanceof State)) {
            return false;
        }
        State otherState = (State) other;
        return board.equals(otherState.board);
    }

    @Override
    public int hashCode() {
        return board.hashCode();
    }
}
