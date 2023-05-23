public class State {
    private Board board;


    public State (Board board) {
        this.board = board;
    }


    public Board getBoard() {
        return board;
    }

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
        boolean flag = (correctValue - 1 == maxValue);
        return flag;
    }


    public Action[] actions() {
        int[][] possibleActions = board.checkMoves(this.board);
        //  (tile,up), (tile,down), (tile,right), (tile,left)
        int arrayLen = 0;
        for (int i = 0; i < possibleActions.length; i++) {
            arrayLen += possibleActions[i][1];
        }
        Action[] actions = new Action[arrayLen];

        int up = possibleActions[0][1];
        int down = possibleActions[1][1];
        int right = possibleActions[2][1];
        int left = possibleActions[3][1];

        int i = 0;
        if (up != 0) {
            actions[i] = new Action(EnumDirections.UP, new Tile(possibleActions[0][0]));
            i++;
        }
        if (down != 0) {
            actions[i] = new Action(EnumDirections.DOWN, new Tile(possibleActions[1][0]));
            i++;
        }
        if (right != 0) {
            actions[i] = new Action(EnumDirections.RIGHT, new Tile(possibleActions[2][0]));
            i++;
        }
        if (left != 0) {
            actions[i] = new Action(EnumDirections.LEFT, new Tile(possibleActions[3][0]));
        }
        return actions;
    }


    public State result(Action action) {
        Tile[][] tiles = board.getTiles();
        Tile tile = action.getTile();
        Tile space = new Tile(-1);
        int value = tile.getValue();
        int[] indexList = board.findIndex(tiles, value);

        int rowIndex = indexList[0];
        int colIndex = indexList[1];

        int newRow = rowIndex;
        int newCol = colIndex;

        switch (action.getDirection()) {
            case UP:
                newRow = rowIndex - 1;
                break;
            case DOWN:
                newRow = rowIndex + 1;
                break;
            case RIGHT:
                newCol = colIndex + 1;
                break;
            case LEFT:
                newCol = colIndex - 1;
                break;
        }

        Tile[][] newTiles = new Tile[tiles.length][tiles[0].length];
        for (int i = 0; i < tiles.length; i++) {
            for (int j = 0; j < tiles[0].length; j++) {
                newTiles[i][j] = new Tile(tiles[i][j].getValue());
            }
        }

        newTiles[rowIndex][colIndex] = tile;
        newTiles[newRow][newCol] = space;

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
