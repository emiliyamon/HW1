public class State {
    private Board board;


    public State (Board board) {
        this.board = board;
    }


    public Board getBoard() {
        return board;
    }

    public boolean isGoal() {
        Board goalBoard = board.goalBoard(board.getBoardString());
        boolean flag = true;

        for (int i = 0; i < goalBoard.getTiles().length; i++) {
            for (int j = 0; j < goalBoard.getTiles()[0].length; j++) {
                if (goalBoard.getTiles()[i][j].getValue() != this.board.getTiles()[i][j].getValue()) {
                    flag = false;
                    break;
                }
            }
        }
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
        Board newBoard = new Board(this.board);
        Tile[][] tiles = board.getTiles();
        Tile tile = action.getTile();
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

        newBoard.getTiles()[rowIndex][colIndex] = newBoard.getTiles()[newRow][newCol];
        newBoard.getTiles()[newRow][newCol] = new Tile(-1);


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
