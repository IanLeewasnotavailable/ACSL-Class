import java.awt.event.*;
import java.util.Arrays;

public class mouseHandler extends MouseAdapter{
    
    public static Bounds b = new Bounds(minesweeper.WIDTH / 2 - minesweeper.BOARD_WIDTH / 2, 
                                            minesweeper.HEIGHT / 2 - minesweeper.BOARD_HEIGHT / 2,
                                        minesweeper.WIDTH / 2 + minesweeper.BOARD_WIDTH / 2, 
                                            minesweeper.HEIGHT / 2 + minesweeper.BOARD_HEIGHT / 2);
    
    private minesweeperBoard board;
    
    private int[] lastHover;
    
    public mouseHandler(minesweeperBoard b) {
        board = b;
        
        lastHover = null;
    }
    
    public void mouseClicked(MouseEvent e) {
        if(b.withinBounds(e.getX(), e.getY())) {
            int[] RC = minesweeperBoard.graphicsToBoard(e.getX(), e.getY());
            if(!board.uncover(RC[0], RC[1])) {
                minesweeper.gameLost();
            }
        }
    }
    
    public void mouseMoved(MouseEvent e) {
        if(b.withinBounds(e.getX(), e.getY())){
            if(lastHover != null) {
                int[] lHRC = minesweeperBoard.graphicsToBoard(lastHover[0], lastHover[1]);
                if(board.hasHover(lHRC[0], lHRC[1]))
                    board.removeHover(lastHover[0], lastHover[1]);
            }
            int[] cHRC = minesweeperBoard.graphicsToBoard(e.getX(), e.getY());
            if(!board.hasHover(cHRC[0], cHRC[1]))
                board.addHover(e.getX(), e.getY());
            if(lastHover == null) {
                lastHover = new int[2];
            }
            lastHover[0] = e.getX();
            lastHover[1] = e.getY();
            System.out.println("mouse moved: " + Arrays.toString(lastHover));
        }
    }
}

class Bounds{
    int x1, y1, x2, y2;
    
    public Bounds(int x1, int y1, int x2, int y2) {
        this.x1 = x1;
        this.y1 = y1;
        this.x2 = x2;
        this.y2 = y2;
    }
    
    public boolean withinBounds(int x, int y) {
        return x1 <= x && x <= x2 && y1 <= y && y <= y2;
    }
}