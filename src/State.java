public class State {
    Board board;


    public State (Board board) {
        this.board = board;
    }

    public boolean isGoal() {
        Board goalBoard = new Board(board.boardString);
        int value = 0;
        for (int i = 0; i < goalBoard.tiles.length; i++) {
            for (int j = 0; j < goalBoard.tiles[0].length; j++) {
                goalBoard.tiles[i][j] = new Tile(value);
                value++;
            }
        }
        for (int i = 0; i < goalBoard.tiles.length; i++) {
            for (int j = 0; j < goalBoard.tiles[0].length; j++) {
                if (goalBoard.tiles[i][j].value != board.tiles[i][j].value) {
                    return false;
                }
            }
        }
        return true;
    }


    public Action[] actions() {
        int[][] possibleActions = board.checkMoves(board);
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
        Board newBoard = new Board(board.boardString);
        int rowIndex = -1;
        int colIndex = -1;

        for (int i = 0; i < newBoard.tiles.length; i++) {
            for (int j = 0; j < newBoard.tiles[0].length; j++) {
                if (newBoard.tiles[i][j].value == action.tile.value) {
                    rowIndex = i;
                    colIndex = j;
                    break;
                }
            }
        }

        int newRow = rowIndex;
        int newCol = colIndex;

        switch (action.direction) {
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

        if (newRow >= 0 && newRow < newBoard.tiles.length && newCol >= 0 && newCol < newBoard.tiles[0].length) {
            newBoard.tiles[rowIndex][colIndex] = newBoard.tiles[newRow][newCol];
            newBoard.tiles[newRow][newCol] = new Tile(-1);
        }

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
