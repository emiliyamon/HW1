public class Node {
    private State state; // current state
    private Node parent; // previous node
    private Action preAction; // the action that was made to get to current state


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
        Action[] actions = this.state.actions();
        Node[] children = new Node[actions.length];
        int i = 0;
        for (Action action : actions) {
            State newState = this.state.result(action);
            Action newAction = new Action(action.getDirection(), action.getTile());
            Node child = new Node(newState, this, newAction);
            children[i] = child;
            i++;
        }
        return children;
    }

    public int heuristicValue() {
        HeuristicValue heuristic = new HeuristicValue(this.state.getBoard());
        return heuristic.getHeuristic();
    }
}
