import java.util.*;
public class _1617jrc1 {
	static Scanner in;
	
	static Card[] hand;
	
	static Card output;
	
	public static void main(String []args) {
		in = new Scanner(System.in);
		for(int i = 0; i < 10; i++) {
			init();
			solve();
			output();
		}
	}

	public static void init() {
	
		StringTokenizer st = new StringTokenizer(in.nextLine(), ", ");
		int oppNum = Integer.parseInt(st.nextToken());
		char oppChar = st.nextToken().charAt(0);
		oppPlay = new Card(oppNum, oppChar);
		
		hand = new Card[5];
		for(int i = 0; i < 5; i++) {
			int num = Integer.parseInt(st.nextToken());
			char suit = st.nextToken().charAt(0);
			hand[i] = new Card(num, suit);
		}
		
		output = null;
		
		System.out.println();
	}
	
	public static void solve() {
		for(int i = 0; i < 5; i++) {
			if(hand[i].suit == oppPlay.suit && hand[i].num > oppPlay.num) {
				if(output == null) {
					output = hand[i];
				}
				else if(output.num > hand[i].num) {
					output = hand[i];
				}
			}
		}
	}
	
	public static void output() {
		if(output == null) {
			System.out.println("NONE");
		}
		else {
			System.out.println(output.num + " " + output.suit);
		}
	}

	class Card {
		int num;
		char suit;
		
		public Card(int n, char s) {
			this.num = n;
			this.suit = s;
		}
	}
}