public class Node {
    State nodeState; //Nodes current state
    Node nodeParent; //Nodes amazing father - not sure you can do that
    Action preAction; //The action that was made to get this state

    public int heuristicValue() {
        return heuristicValueState;
    }


    public Action getAction() {
        return preAction;
    }

    public Node getParent() {
        return nodeParent;
    }

    public State getState() {
        return nodeState;
    }
}
