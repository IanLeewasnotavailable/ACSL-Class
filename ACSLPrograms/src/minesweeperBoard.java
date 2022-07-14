public class minesweeperBoard {
    private int[][] board;
    
    public minesweeperBoard() {
        board = new int[10][10];
        
        //place bombs randomly
        for(int b = 0;b < 10;b++) {
            int row = (int)(Math.random() * 10);
            int col = (int)(Math.random() * 10);
            while(board[row][col] == 1) {
                row = (int)(Math.random() * 10);
                col = (int)(Math.random() * 10);
            }
            board[row][col] = 1;
        }
        
        for(int i = 0;i<10;i++) {
            for(int j = 0;j<10;j++) {
                board[i][j] += countAround(i, j) * 10;
            }
        }
    }
    
    private int countAround(int row, int col) {
        int count = 0;
        for(int i = -1;i<=1;i++) {
            for(int j = -1;j<=1;j++) {
                if(i != 0 || j != 0) {
                    if(0 <= row + i && row + i <= 9 &&
                       0 <= col + j && col + j <= 9) {
                        if(board[row + i][col + j] % 10 == 1) {
                            count ++;
                        }
                    }
                }
            }
        }
        return count;
    }
    
    public void printBoard() {
        for(int i = 0;i<10;i++) {
            for(int j = 0;j<10;j++) {
                if(board[i][j] % 10 == 1) {
                    System.out.print("* ");
                }
                else if(board[i][j] / 10 % 10 > 0) {
                    System.out.print(board[i][j] / 10 % 10 + " ");
                }
                else {
                    System.out.print("_ ");
                }
            }
            System.out.println();
        }
    }
    
    public boolean hasBomb (int row, int col) {
        return board[row][col] % 10 == 1;
    }
    
    public int getProximity (int row, int col) {
        return board[row][col] / 10 % 10;
    }
    
    public boolean hasHover(int row, int col) {
        return board[row][col] / 1000 % 10 == 1;
    }
    
    public void addHover(int x, int y) {
        int[] boardCoord = graphicsToBoard(x, y);
        int col = boardCoord[1];
        int row = boardCoord[0];
        if(0 <= row && row <= 9 && 0 <= col && col <= 9 &&
                board[row][col] < 2000) {
            board[row][col] += 1000;
        }
    }
    
    public void removeHover(int x, int y) {
        int[] boardCoord = graphicsToBoard(x, y);
        int col = boardCoord[1];
        int row = boardCoord[0];
        if(0 <= row && row <= 9 && 0 <= col && col <= 9 &&
                board[row][col] >= 1000) {
            board[row][col] -= 1000;
        }
    }
    
    public boolean uncovered(int row, int col) {
        return board[row][col] / 100 % 10 == 1;
    }
    
    /*
     * false - bomb found gameover
     * true - number found, start "chain reaction"
     */
    public boolean uncover(int row, int col) {
        if(hasBomb(row, col)) {
            if(board[row][col] / 100 % 10 == 0) board[row][col] += 100;
            return false;
        }
        int p = getProximity(row, col);
        if(board[row][col] / 100 % 10 == 0) {
            board[row][col] += 100;
            if(p == 0) {
                for(int i = -1;i<=1;i++) {
                    for(int j = -1;j <= 1;j++) {
                        if(i != 0 || j != 0) {
                            if(0 <= row + i && row + i <= 9 &&
                                0 <= col + j && col + j <= 9) {
                                    uncover(row + i, col + j);
                            }
                        }
                    }
                }
            }
        }
        
        return true;
    }
    
    public static int[] graphicsToBoard(int... loc) {
        int x = loc[0] - (minesweeper.WIDTH / 2 - minesweeper.BOARD_WIDTH / 2);
        int y = loc[1] - (minesweeper.HEIGHT / 2 - minesweeper.BOARD_HEIGHT / 2);
        return new int[] {y / 50, x / 50};
    }
}