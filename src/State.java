public class State {
    Board board;

    public boolean isGoal() {
        int h = heuristicValue();
        if (h == 0) {
            return true;
        }

    }
    public Action[] actions() {
        //move the tile UP/DOWN/RIGHT/LEFT (that order) according to the actual options from this state
        return EnumDirections;

    }
    public State (Action action){
        State newState = Action; //applying the action to the state (board)
        return newState;
    }
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
