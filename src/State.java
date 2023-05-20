public class State {
    static Board board;
    static HeuristicValue heuristic;


    State (Board board, HeuristicValue heuristic) {
        this.board = board;
        this.heuristic = heuristic;
    }

    public boolean isGoal() {
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


//    public State (Action action) {
//        State newState = Action; //applying the action to the state (board)
//        return newState;
//    }

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
