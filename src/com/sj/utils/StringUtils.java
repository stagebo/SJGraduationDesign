package com.sj.utils;
/**
 * 字符串处理工具
 * @author  Stagebo
 *
 */
public class StringUtils {
	public static void printArray(int a[][]){
		for(int i=0;i<a.length;i++){
			//System.out.print(i);
			for(int j=0;j<a[0].length;j++){
				if(a[i][j]==0)
					System.out.print(0);
				else
					System.out.print(a[i][j]);
			}
			System.out.println();
		}
		
	}
	public static void printArray(double a[][]){
		for(int i=0;i<a.length;i++){
			//System.out.print(i);
			for(int j=0;j<a[0].length;j++){
				if(a[i][j]==0)
					System.out.print(0);
				else
					System.out.print(a[i][j]);
			}
			System.out.println();
		}
		
	}
	public static void printArray(int[] a){
		for(int i=0;i<a.length;i++){
			System.out.print(a[i]+"--");			
		}
		System.out.println();
	}
	public static void printArray(double[] a){
		for(int i=0;i<a.length;i++){
			System.out.print(a[i]+"--");			
		}
		System.out.println();
	}
	
}
