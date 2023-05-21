public class State {
    static Board board;


    State (Board board) {
        this.board = board;
    }

    public boolean isGoal() {
        HeuristicValue heuristic = new HeuristicValue(board);
        if (heuristic.isZero()) {
            return true;
        } else {
            return false;
        }
    }


    public Action[] actions() {
        int[][] possibleActions = board.checkMoves(board);
        //  (tile,up), (tile,down), (tile,right), (tile,left)
        int arrayLen = 0;
        for (int i = 0; i < possibleActions.length; i++) {
            arrayLen = arrayLen + possibleActions[i][1];
        }
        Action[] actions = new Action[arrayLen];

        int up = possibleActions[0][1];
        int down = possibleActions[1][1];
        int right = possibleActions[2][1];
        int left = possibleActions[3][1];

        int i = 0;
        if (up != 0) {
            actions[i] = new Action(EnumDirections.UP, new Tile((char)possibleActions[0][0]));
            i++;
        }
        if (down != 0) {
            actions[i] = new Action(EnumDirections.DOWN, new Tile((char)possibleActions[1][0]));
            i++;
        }
        if (right != 0) {
            actions[i] = new Action(EnumDirections.RIGHT, new Tile((char)possibleActions[2][0]));
            i++;
        }
        if (left != 0) {
            actions[i] = new Action(EnumDirections.LEFT, new Tile((char)possibleActions[3][0]));
        }
        return actions;
    }


    public State result(Action action) {
        Board newBoard = new Board(board.boardString);
        newBoard.findSpace(newBoard);

        // get index of space
        String[] indexString = newBoard.findSpace(newBoard).split(" ");
        int[] indexInt = new int[indexString.length];
        for (int i = 0; i < indexString.length; i++) {
            indexInt[i] = Integer.parseInt(indexString[i]);
        }
        int rowIndex = indexInt[0];
        int colIndex = indexInt[1];

        switch (action.direction) {
            case UP:
                newBoard.tiles[rowIndex][colIndex] = newBoard.tiles[rowIndex - 1][colIndex];
                newBoard.tiles[rowIndex - 1][colIndex] = new Tile('_');
                break;
            case DOWN:
                newBoard.tiles[rowIndex][colIndex] = newBoard.tiles[rowIndex + 1][colIndex];
                newBoard.tiles[rowIndex + 1][colIndex] = new Tile('_');
                break;
            case RIGHT:
                newBoard.tiles[rowIndex][colIndex] = newBoard.tiles[rowIndex][colIndex - 1];
                newBoard.tiles[rowIndex][colIndex - 1] = new Tile('_');
                break;
            case LEFT:
                newBoard.tiles[rowIndex][colIndex] = newBoard.tiles[rowIndex][colIndex + 1];
                newBoard.tiles[rowIndex][colIndex + 1] = new Tile('_');
                break;
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
