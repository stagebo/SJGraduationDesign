package com.sj.test;

import java.io.File;
import java.util.ArrayList;
import java.util.TreeMap;

import com.sj.utils.ReadSampleUtils;
import com.sj.utils.StringUtils;

public class T {
	public static void main(String[] args) {
		
		 TreeMap<Integer, ArrayList<double[]>> s= ReadSampleUtils.SAMPLE_MAP;
		 for(int i=0;i<10;i++){
			 ArrayList<double[]> l=s.get(i);
			 System.out.println(i+"---"+l.size());
		 }
		/*int[][] a = ReadSampleUtils.readTxt("0_0.txt");
		StringUtils.printArray(a);
		for (int i = 0; i < 10000; i++) {

			a = thinImage(a, 0);
			a = thinImage(a, 1);
		}
		StringUtils.printArray(a);*/
	}

	public static int[][] thinImage(int[][] a, int key) {
		int w = a.length;
		int h = a[0].length;
		int[][] r = new int[w][h];
		for (int i = 1; i < w - 1; i++) {
			for (int j = 1; j < h - 1; j++) {
				/* 读取矩阵 */
				int[] dd = new int[10];
				dd[1] = a[i][j + 1];
				dd[2] = a[i - 1][j + 1];
				dd[3] = a[i - 1][j];
				dd[4] = a[i - 1][j - 1];
				dd[5] = a[i][j - 1];
				dd[6] = a[i + 1][j - 1];
				dd[7] = a[i + 1][j];
				dd[8] = a[i + 1][j + 1];

				// if(dd[1]==1)StringUtils.printArray(dd);
				/* 条件G1 */
				int g1 = 0;
				for (int k = 1; k < 5; k++) {
					if (dd[2 * k - 1] == 0 && (dd[2 * k] == 1 || dd[2 * k + 1] == 1)) {
						g1 += 1;
					}

				}
				boolean G1 = g1 == 1;
				/* 条件G2 */
				int n1 = 0, n2 = 0;
				for (int k = 1; k < 5; k++) {
					n1 += dd[2 * k - 1] + dd[2 * k];
				}
				for (int k = 1; k < 5; k++) {
					n2 += dd[2 * k] + dd[2 * k + 1];
				}
				// boolean G2=n1+n2<=6&&n1+n2>=2;
				n1 = n1 < n2 ? n1 : n2;
				boolean G2 = n1 <= 6 && n1 >= 2;
				/* 条件G3 */
				dd[8] = dd[8] == 1 ? 0 : 1;
				boolean G3 = dd[2] * dd[3] * dd[8] == 0 && dd[1] == 0;
				/* 条件G4 */
				dd[4] = dd[4] == 1 ? 0 : 1;
				boolean G4 = dd[6] * dd[7] * dd[4] == 0 && dd[3] == 0;

				/* chuli */
				switch (key) {
				case 0:
					if (G1 && G2 && G3) {
						r[i][j] = 0;
					} else {
						r[i][j] = a[i][j];
					}
				case 1:
					if (G1 && G2 && G4) {
						r[i][j] = 0;
					} else {
						r[i][j] = a[i][j];
					}
					break;
				}

			}
		}

		return r;
	}
}
