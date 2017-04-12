package com.sj.test;

public class t {
	static int L = 0, W = 0; // 截取的数组的长和宽
	static int[][] array2;

	private static int getFirstIndex(int x, int array[][]) { // 查找指定元素第一次出现的位置
		int temp = array[0].length - 1;
		for (int i = 0; i < array.length; i++)
			for (int j = 0; j < array[i].length; j++) {
				if (array[i][j] == x && j < temp) {
					temp = j;

				}
			}
		return temp;
	}

	private static int getLastIndex(int x, int array[][]) { // 查找指定元素最后一次在数组中出现的位置
		int temp = 0;
		for (int i = 0; i < array.length; i++)
			for (int j = array[i].length - 1; j > 0; j--) {
				if (array[i][j] == x && j > temp) {
					temp = j;

				}
			}
		return temp;
	}

	public static void setW(int[][] array) {
		if (getFirstIndex(0, array) >= 0)
			// System.out.println( getLastIndex(0, array));
			W = getLastIndex(0, array) - getFirstIndex(0, array) + 1;
		// System.out.println(W);
	}

	public static void setL(int[][] array) {
		array2 = new int[array[1].length][array.length];
		for (int i = 0; i < array2.length; i++)
			for (int j = 0; j < array2[i].length; j++) {
				array2[i][j] = array[j][i];
			}

		if (getFirstIndex(0, array2) >= 0)
			L = getLastIndex(0, array2) - getFirstIndex(0, array2) + 1;
		// System.out.println(L);

	}

	public static int[][] getSubarray(int[][] array) {
		int temp1 = 0, temp2 = 0; // 子数组左上角第一个元素在原数组中的坐标。
		setL(array);
		setW(array);

		int[][] subarraytemp = new int[L][W];
		temp1 = getFirstIndex(0, array);
		temp2 = getFirstIndex(0, array2);
		// System.out.println(temp2);
		for (int i = 0; i < subarraytemp.length; i++)
			for (int j = 0; j < subarraytemp[i].length; j++) {
				subarraytemp[i][j] = array[i + temp2][j + temp1];
			}
		int[][] subarray;
		if (L >= W) {
			subarray = new int[L][L];
			for (int i = 0; i < L; i++)
				for (int j = 0; j < L; j++) {

					if ((L - W) / 2 <= j && j < (L - W) / 2 + W) {
						subarray[i][j] = subarraytemp[i][j + (L - W) / 2];
					} else
						subarray[i][j] = 1;
				}
		} else {
			subarray = new int[W][W];
			for (int i = 0; i < W; i++)
				for (int j = 0; j < W; j++) {

					if ((W - L) / 2 <= i && i < (W - L) / 2 + L) {
						subarray[i][j] = subarraytemp[i - (W - L) / 2][j];
					} else
						subarray[i][j] = 1;
				}
		}

		return subarray;
	}

	/*public static void main(String[] args) {
		int[][] arr = { 
				{ 1, 0, 1, 1, 0, 0, 0, 1, 0, 0, 0, 1, 1, 1, 1 },
				{ 1, 1, 1, 0, 0, 1, 1, 0, 0, 0, 1, 1, 1, 0, 1 }, 
				{ 1, 1, 1, 1, 0, 0, 0, 1, 1, 1, 0, 1, 1, 1, 1 } };
		int[][] subarr = getSubarray(arr);
		for (int i = 0; i < subarr.length; i++) {
			for (int j = 0; j < subarr[i].length; j++) {
				System.out.print(subarr[i][j] + " ");
			}
			System.out.println();
		}
	}*/

}
