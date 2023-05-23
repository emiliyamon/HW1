public class HeuristicValue {
    private int heuristic;

    public HeuristicValue(Board board) {
        heuristic = 0;

        int rows = board.getTiles().length;
        int cols = board.getTiles()[0].length;
        int size = rows * cols;

        for (int i = 0; i < board.getTiles().length; i++) {
            for (int j = 0; j < board.getTiles()[0].length; j++) {
                int tileValue = board.getTiles()[i][j].getValue();

                if (tileValue != -1) {
                    int targetRow = (tileValue - 1) / size;
                    int targetCol = (tileValue - 1) % size;
                    int distance = Math.abs(i - targetRow) + Math.abs(j - targetCol);
                    heuristic += distance;
                }
            }
        }
    }


    public int getHeuristic() {
        return heuristic;
    }


}
