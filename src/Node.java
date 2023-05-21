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
        Action[] possibleActions = state.actions();
        Node[] children = new Node[possibleActions.length];
        int i = 0;
        for (Action action : possibleActions) {
            children[i].preAction = new Action(preAction.direction, preAction.tile);
            children[i].state = state.result(action);
            children[i].parent = this; // check later
            i++;
        }
        return children;
    }

    public int heuristicValue() {
        HeuristicValue heuristic = new HeuristicValue(this.state.board);
        return heuristic.heuristic;
    }
}
