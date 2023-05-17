public class Node {
    State State; //Nodes current state
    Node Parent; //Nodes amazing father
    Action Action; //The action that was made to get this state

    //public int heuristicValue() {
        //return
    //}

    public Action getAction() {
        return Action;
    }

    public Node getParent() {
        return Parent;
    }

    public State getState() {
        return State;
    }
}
