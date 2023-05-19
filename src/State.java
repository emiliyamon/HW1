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
        boolean[] possibleActions = board.checkMoves(board);
        //  up, down, right, left

        if (possibleActions[0] && possibleActions[1] && possibleActions[2] && possibleActions[3]) {
            EnumDirections[] actions = {EnumDirections.UP, EnumDirections.DOWN ,EnumDirections.RIGHT ,EnumDirections.LEFT};
        }

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
