package com.sj.utils;

/**
 * 工具类
 * @author Stagebo
 *
 */
public class CommonUtils {
	/**
	 * 获得数组最大值
	 * @param a
	 * @return
	 */
	public static int getMax(int[] a){
		int max=a[0];
		for(int i=0;i<a.length;i++){
			if(a[i]>max){
				max=a[i];
			}
		}
		return max;
	}
	/**
	 * 活得数组最小值
	 * @param a
	 * @return
	 */
	public static int getMin(int[] a){
		int min=a[0];
		for(int i=0;i<a.length;i++){
			if(a[i]<min){
				min=a[i];
			}
		}
		return min;
	}
	/**
	 * 获得数组最小值下标
	 * @param a
	 * @return
	 */
	public static int getMinIndex(int[] a){
		int min=a[0];
		int minIndex=0;
		for(int i=0;i<a.length;i++){
			if(a[i]<min){
				min=a[i];
				minIndex=i;
			}
		}
		return minIndex;
	}
	/**
	 * 获得数组最大值所在的下标
	 * @param a
	 * @return
	 */
	public static int getMaxIndex(int[] a){
		int max=a[0];
		int maxIndex=0;
		for(int i=0;i<a.length;i++){
			if(a[i]>max){
				max=a[i];
				maxIndex=i;
			}
		}
		return maxIndex;
	}
	/***
	 * 获取数组最小值所在的下标
	 * @param a
	 * @return
	 */
	public static int getMinIndex(double[] a){
		double min=a[0];
		int minIndex=0;
		for(int i=0;i<a.length;i++){
			if(a[i]<min){
				min=a[i];
				minIndex=i;
			}
		}
		return minIndex;
	}
	/**
	 * 求矩阵的欧式距离
	 * @param a
	 * @param b
	 * @return
	 */
	public static double getEuDistence(int[][]a,int[][]b){
		if(a==null||b==null)return -1;
		if(a.length!=b.length)return -1;
		if(a.length<1||b.length<1)return -1;
		if(a[0].length!=b[0].length)return -1;
		double di=0;
		for (int n = 0; n < a.length; n++) {
			for (int m = 0; m < a[0].length; m++) {
				di += (a[m][n] - b[m][n]) * (a[m][n] - b[m][n])*(m+n);
			}
		}
		
		return di;
	}
	/**
	 * 二维矩阵相加
	 * @param a
	 * @param b
	 * @return
	 */
	public static int[][] MaxtrixAdd(int[][]a,int[][]b){
		if(a==null||b==null)return null;
		if(a.length!=b.length)return null;
		if(a.length<1||b.length<1)return null;
		if(a[0].length!=b[0].length)return null;
		for(int i=0;i<a[0].length;i++)
			for(int j=0;j<a.length;j++)
				a[i][j]+=b[i][j];
		
		return a;
	}
}
