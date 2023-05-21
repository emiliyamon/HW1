public class Node {
    State state; // current state
    Node parent; // previous node
    Action preAction; // the action that was made to get to current state


    public Node(State state, Node parent, Action preAction) {
        this.state = state;
        this.parent = parent;
        this.preAction = preAction;
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
        Action[] possibleActions = this.state.actions();
        Node[] children = new Node[possibleActions.length];
        int i = 0;
        for (Action action : possibleActions) {
            State newState = this.state.result(action);
            Action newAction = new Action(action.direction, action.tile);
            Node child = new Node(newState, this, newAction);
            children[i] = child;
            i++;
        }
        return children;
    }

    public int heuristicValue() {
        HeuristicValue heuristic = new HeuristicValue(this.state.board);
        return heuristic.heuristic;
    }
}
