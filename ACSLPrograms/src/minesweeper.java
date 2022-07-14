import javax.swing.*;
import java.awt.*;

public class minesweeper extends JPanel implements Runnable{
    public static int WIDTH = 1080, HEIGHT = 720;
    public static int BOARD_WIDTH = 500, BOARD_HEIGHT = 500;
    
    private JFrame window;
    private mouseHandler mh;
    private minesweeperBoard board;
    
    private boolean gameLost;
    
    private int[][][] points;
    
    public minesweeper() {
        board = new minesweeperBoard();
        
        window = new JFrame("Minesweeper");
        
        this.setPreferredSize(new Dimension(WIDTH, HEIGHT));
        this.setMinimumSize(new Dimension(WIDTH, HEIGHT));
        this.setMaximumSize(new Dimension(WIDTH, HEIGHT));
        mh = new mouseHandler(board);
        this.addMouseListener(mh);
        this.addMouseMotionListener(mh);
        window.add(this);
        
        window.pack();
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setVisible(true);
        
        points = new int[10][10][];
        for(int i = 0;i<10;i++) {
            for(int j = 0;j< 10;j++) {
                int x = WIDTH / 2 - BOARD_WIDTH / 2 + j * 50;
                int y = HEIGHT / 2 - BOARD_HEIGHT / 2 + i * 50;
                points[i][j] = new int[]{x, y};
            }
        }
    }
    
    public void paint(Graphics g) {
        super.paint(g);
        
        g.setColor(Color.BLACK);
        g.drawRect(WIDTH / 2 - BOARD_WIDTH / 2, HEIGHT / 2 - BOARD_HEIGHT / 2,
                    BOARD_WIDTH, BOARD_HEIGHT);
        for(int i = 0; i < 9; i++) {
            //vertical lines
            int x = WIDTH / 2 - BOARD_WIDTH / 2 + (i + 1) * 50;
            g.drawLine(x, HEIGHT / 2 - BOARD_HEIGHT / 2, x, HEIGHT / 2 + BOARD_HEIGHT / 2);
            
            //horizontal lines
            int y = HEIGHT / 2 - BOARD_HEIGHT / 2 + (i + 1) * 50;
            g.drawLine(WIDTH / 2 - BOARD_WIDTH / 2, y, WIDTH / 2 + BOARD_WIDTH / 2, y);
        }
        
        for(int i = 0;i<10;i++) {
            for(int j =0;j<10;j++) {
                if(board.uncovered(i, j)) {
                    if(board.hasBomb(i, j)) {
                        g.setColor(Color.RED);
                        g.fillRect(points[i][j][0], points[i][j][1], 50, 50);
                    }
                    else {
                        g.setColor(Color.BLACK);
                        int p = board.getProximity(i, j);
                        if(p != 0)
                            g.drawString(p + "", points[i][j][0] + 22, points[i][j][1] + 12 + 20);
                    }
                }
                else {
                    g.setColor(new Color(0, 0, 0, 50));
                    g.fillRect(points[i][j][0], points[i][j][1], 50, 50);
                }
                if(board.hasHover(i, j)) {
                    g.setColor(new Color(0, 0, 0, 100));
                    g.fillRect(points[i][j][0], points[i][j][1], 50, 50);
                }
            }
        }
    }
    
    public void run() {
        while(!gameLost) {
            this.repaint();
            try {
                Thread.sleep(12);
            }catch(InterruptedException e) {
                e.printStackTrace();
            }
        }
        
        if(gameLost) {
            this.repaint();
            System.out.println("You lost!");
        }
    }
    
    public static minesweeper m;
    
    public static void main(String[] args) {
        m = new minesweeper();
        Thread paintJob = new Thread(m);
        paintJob.start();
    }
    
    public static void gameLost() {
        m.gameLost = true;
    }
    
    
}
