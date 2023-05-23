/**
 * Represents a node in a search algorithm
 * This class encapsulates the properties and behavior of a node in a search algorithm, such as a state, parent node, and action
 */
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


    /**
     * expand function gets all possible actions on a current state board represented in the node
     * and create an array of "children" based on the possible actions
     *
     * @return Node array of "child" Nodes which can be created for the current node (who will be set as the parent)
     */
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


    /**
     * function for getting the heuristic value of the current node
     * @return int value of current node's board heuristic value
     */
    public int heuristicValue() {
        HeuristicValue heuristic = new HeuristicValue(this.state.getBoard());
        return heuristic.getHeuristic();
    }
}
