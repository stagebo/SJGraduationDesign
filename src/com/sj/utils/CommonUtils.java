package com.sj.utils;

import java.util.Arrays;

public class CommonUtils {
	public static int getMin(int[] a){
		int min=a[0];
		for(int i=0;i<a.length;i++){
			if(a[i]<min){
				min=a[i];
			}
		}
		return min;
	}
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
