public class State {
    static Board board;
    static int value; // check later how to turn to HeuristicValue class object


    State (Board board, int value) {
        this.board = board;
        this.value = value;
    }

    public boolean isGoal() {
        if (State.value == 0) {
            return true;
        } else {
            return false;
        }
    }


    public int[] findIndex(Board board) {

    }

    public Action[] actions() {

    }


    public State (Action action) {
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
