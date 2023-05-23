public class HeuristicValue {
    private Board board;
    private int heuristic;

    public HeuristicValue(Board board) {
        this.board = board;
        this.heuristic = 0;
    }


    public Board getBoard() {
        return board;
    }

    public int getHeuristic() {
        return heuristic;
    }


}
