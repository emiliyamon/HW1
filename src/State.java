public class State {
    Board board;

    //public boolean isGoal () {}
    public State (Action action){
        State newState = //appliying the action to the state (board)
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
