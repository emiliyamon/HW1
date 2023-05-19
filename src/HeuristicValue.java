public class HeuristicValue {
    int heuristic;

    HeuristicValue(int heuristic) {
        this.heuristic = 0;
    }


    public boolean isZero() {
        return heuristic == 0;
    }


    public void calculateHeuristic(int heuristic) {
        // perform the heuristic calculation based on the node
        this.heuristic = heuristic;
        // later add: return the heuristic value as an integer
    }
}
