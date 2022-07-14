import java.util.*;
public class _1819jrc1 {
	static Scanner in;
	public static void main(String []args) {
		Line1();
		Line2();
		Line5();
	}
	public static void Line1() {
		String nums1 = in.nextLine();
		int length = nums1.length();
		System.out.println(length);
	}
	public static void Line2() {
		String nums2 = in.nextLine();
		int first = nums2.length() - 1;
		int last = nums2.length();
		int sum = first + last;
		System.out.println(sum);
	}
	public static void Line5() {
		String nums5 = in.nextLine();
		int mid = 0;
		if(nums5.length() % 2 == 0) {
			System.out.println(nums5.length() /2);
		}
		else {
			System.out.println(mid);
		}
	}
}
