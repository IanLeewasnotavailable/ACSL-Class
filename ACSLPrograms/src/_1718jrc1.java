import java.util.*;
public class _1718jrc1 {
	static Scanner in;
	
	static int initPoints;
	static int[] hand;
	static int[] queuedCards;
	
	static int endPoint;
	static String winner;
	
	public static void main(String []args) {
		init();
		solve();
		output();
	}
	public static void init() {
		in = new Scanner(System.in);
		
		initPoints = in.nextInt();
		
		hand = new int[3];
		for(int i = 0; i < 3; i++) {
			hand[i] = in.nextInt();
		}
		
		queuedCards = new int[7];
		for(int i = 0; i < 7; i++) {
			queuedCards[i] = in.nextInt();
		}
		endPoint = 0;
		winner = "";
	}
	public static void solve() {
		
	}
	public static void output() {
		
	}
}
