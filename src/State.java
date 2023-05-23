public class State {
    private Board board;


    public State (Board board) {
        this.board = board;
    }


    public Board getBoard() {
        return board;
    }

    public boolean isGoal() {
        int correctValue = 1;
        int maxValue = (board.getTiles().length * board.getTiles()[0].length) - 1;//1*2=2-1=  1

        for (int i = 0; i < board.getTiles().length; i++) { //i=0
            for (int j = 0; j < board.getTiles()[0].length; j++) {//j=0,1
                if (i == (board.getTiles().length - 1) && j == (board.getTiles()[0].length-1)) {//i=0 && j=1
                    continue;
                } else if (board.getTiles()[i][j].getValue() == correctValue) {
                    correctValue++;//2
                }
            }
        }
        boolean flag = (correctValue - 1 == maxValue);
        return flag;
    }


    public Action[] actions() {
        // get index of space
        int[] spaceIndex = board.findSpace(board);
        int spaceRowIndex = spaceIndex[0]; //0
        int spaceColIndex = spaceIndex[1];  //0

        int firstRow = 0;
        int maxRow = board.getTiles().length - 1;
        int firstCol = 0;
        int maxCol = board.getTiles()[0].length - 1;

        int[][] movableTiles = new int[4][2];

        boolean upTest = (spaceRowIndex != maxRow); // false if there's no tile below space
        if (upTest) {
            movableTiles[0][0] = board.getTiles()[spaceRowIndex + 1][spaceColIndex].getValue();
            movableTiles[0][1] = 1;
        } else {
            movableTiles[0][0] = 0;
            movableTiles[0][1] = 0;
        }

        boolean downTest = (spaceRowIndex != firstRow); // false if there's no tile above space
        if (downTest) {
            movableTiles[1][0] = board.getTiles()[spaceRowIndex - 1][spaceColIndex].getValue();
            movableTiles[1][1] = 1;
        } else {
            movableTiles[1][0] = 0;
            movableTiles[1][1] = 0;
        }

        boolean rightTest = (spaceColIndex != firstCol); // false if there's no tile to the left of space
        if (rightTest) {
            movableTiles[2][0] = board.getTiles()[spaceRowIndex][spaceColIndex - 1].getValue();
            movableTiles[2][1] = 1;
        } else {
            movableTiles[2][0] = 0;
            movableTiles[2][1] = 0;
        }

        boolean leftTest = (spaceColIndex != maxCol); // false if there's no tile to the right of space
        if (leftTest) {
            movableTiles[3][0] = board.getTiles()[spaceRowIndex][spaceColIndex + 1].getValue();
            movableTiles[3][1] = 1;
        } else {
            movableTiles[3][0] = 0;
            movableTiles[3][1] = 0;
        }
        //  (tile,up), (tile,down), (tile,right), (tile,left)

        int arrayLen = 0;
        for (int i = 0; i < movableTiles.length; i++) {
            arrayLen += movableTiles[i][1];
        }
        Action[] actions = new Action[arrayLen];

        int up = movableTiles[0][1];
        int down = movableTiles[1][1];
        int right = movableTiles[2][1];
        int left = movableTiles[3][1];

        int i = 0;
        if (up != 0) {
            actions[i] = new Action(EnumDirections.UP, new Tile(movableTiles[0][0]));
            i++;
        }
        if (down != 0) {
            actions[i] = new Action(EnumDirections.DOWN, new Tile(movableTiles[1][0]));
            i++;
        }
        if (right != 0) {
            actions[i] = new Action(EnumDirections.RIGHT, new Tile(movableTiles[2][0]));
            i++;
        }
        if (left != 0) {
            actions[i] = new Action(EnumDirections.LEFT, new Tile(movableTiles[3][0]));
        }
        return actions;
    }


    public State result(Action action) {
        Tile[][] tiles = board.getTiles();
        Tile tile = action.getTile();
        Tile space = new Tile(-1);
        int value = tile.getValue();
        int[] indexList = board.findIndex(tiles, value);

        int rowIndex = indexList[0];
        int colIndex = indexList[1];

        int newRow = rowIndex;
        int newCol = colIndex;

        switch (action.getDirection()) {
            case UP:
                newRow = rowIndex - 1;
                break;
            case DOWN:
                newRow = rowIndex + 1;
                break;
            case RIGHT:
                newCol = colIndex + 1;
                break;
            case LEFT:
                newCol = colIndex - 1;
                break;
        }

        Tile[][] newTiles = new Tile[tiles.length][tiles[0].length];
        for (int i = 0; i < tiles.length; i++) {
            for (int j = 0; j < tiles[0].length; j++) {
                newTiles[i][j] = new Tile(tiles[i][j].getValue());
            }
        }

        newTiles[rowIndex][colIndex] = tile;
        newTiles[newRow][newCol] = space;

        Board newBoard = new Board(newTiles);
        return new State(newBoard);
    }

    // don't touch!!

    @Override
    public boolean equals(Object other) {
        if (!(other instanceof State)) {
            return false;
        }
        State otherState = (State) other;
        return board.equals(otherState.board);
    }

    @Override
    public int hashCode() {
        return board.hashCode();
    }
}
