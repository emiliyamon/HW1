public class Node {
    State state; // current state
    Node parent; // previous node
    Action preAction; // the action that was made to get to current state
    HeuristicValue heuristic;


    Node(State state, Node parent, Action preAction, HeuristicValue heuristic) {
        this.state = state;
        this.parent = parent;
        this.preAction = preAction;
        this.heuristic = heuristic;
    }


}
