package com.henu.feifei.algorithm;
import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		System.out.println(Integer.MAX_VALUE);
		Scanner scanner = new Scanner(System.in);
		String str;
		while (!(str = scanner.nextLine()).equals("0 0 0")) {
			String[] num = str.split(" ");
			System.out.println(
					getNumOfTemp(Integer.parseInt(num[0]), Integer.parseInt(num[1]), Integer.parseInt(num[2])));
		}
	}

	public static int getNumOfTemp(int A, int B, int N) {
		int result = 0, a1 = 1, a2 = 1;
		if (N == 1) {
			a1 = 1;
			result = 1;
		} else if (N == 2) {
			a2 = 1;
			result = 1;
		} else {
			for (int i = 3; i <= N; i++) {
				result = (a2 * A + a1 * B)%7;
				a1 = a2;
				a2 = result;
			}
		}
		return result;
	}
}