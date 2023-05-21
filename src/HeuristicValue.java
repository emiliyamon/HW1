public class HeuristicValue {
    Board board;
    int heuristic;

    HeuristicValue(Board board) {
        this.board = board;
        int numRows = board.tiles.length;
        int numCols = board.tiles[0].length;
        this.heuristic = 0;

        for (int i = 0; i < numRows; i++) {
            for (int j = 0; j < numCols; j++) {
                if (board.tiles[i][j].value != 0) {
                    int intValue = board.tiles[i][j].value;
                    int goalRow = (intValue - 1) / numRows;
                    int goalCol = (intValue - 1) % numCols;
                    int absRow = i - goalRow;
                    if (absRow < 0) {
                        absRow = absRow * -1;
                    }
                    int absCol = i - goalCol;
                    if (absCol < 0) {
                        absCol = absCol * -1;
                    }
                    heuristic += absRow + absCol;
                }
            }
        }
    }



    public boolean isZero() {
        if (heuristic == 0) {
            return true;
        }
        return false;
    }

}
