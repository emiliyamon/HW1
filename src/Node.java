public class Node {
    State state; // current state
    Node parent; // previous node
    Action preAction; // the action that was made to get to current state


    public Node(State state, Node parent, Action action) {
        this.state = state;
        this.parent = parent;
        this.preAction = action;
    }

    public State getState() {
        return state;
    }

    public Node getParent() {
        return parent;
    }

    public Action getAction() {
        return preAction;
    }


    public Node[] expand() {
        Action[] possibleActions = state.actions();
        Node[] children = new Node[possibleActions.length];
        int i = 0;
        for (Action action : possibleActions) {
            children[i].preAction = action;
            children[i].state = state.result(action);
            children[i].parent = this; // check later
            i++;
        }
        return children;
    }

    public int heuristicValue() {
        Board board = this.state.board;
        int numRows = board.tiles.length;
        int numCols = board.tiles[0].length;
        int distance = 0;

        for (int i = 0; i < numRows; i++) {
            for (int j = 0; j < numCols; j++) {
                char charValue = board.tiles[i][j].value;
                if (charValue != '_') {
                    int intValue = charValue;
                    int goalRow = (intValue - 1) / numRows;
                    int goalCol = (intValue - 1) % numCols;
                    int absRow = i - goalRow;
                    if (absRow < 0) {
                        absRow = absRow * -1;
                    }
                    int absCol = i - goalCol;
                    if (absCol < 0) {
                        absCol = absCol * -1;
                    }
                    distance += absRow + absCol;
                }
            }
        }
        return distance;
    }
}
