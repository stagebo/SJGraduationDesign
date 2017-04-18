package com.sj.test;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.List;

import javax.swing.JFrame;

import com.sj.utils.ImageUtils;
import com.sj.utils.ReadSampleUtils;
import com.sj.utils.StringUtils;
import com.sj.widge.DrawFrame;

public class Test {
	public static void main(String[] args) {
		DrawFrame app = new DrawFrame();
		app.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		app.setBounds(450, 100, 750, 460);
		app.setVisible(true);

		/*
		 * int[][] i=new int[2][3]; System.out.println(i.length);
		 * System.out.println(i[0].length);
		 */

		/*
		 * int s[][]=new int[][]{ {1,1,1,1,1,1,1,1} ,{1,1,1,1,1,1,1,1}
		 * ,{1,1,1,1,1,1,1,1} ,{1,1,0,1,0,1,1,1} ,{1,1,0,0,0,1,1,1}
		 * ,{1,1,1,0,1,1,1,1} ,{1,1,1,0,1,1,1,1} }; int
		 * i[][]=ImageUtils.cutWhitePart(s); StringUtils.printArray(i);
		 */
		// String
		// path=ReadSampleUtils.class.getClassLoader().getResource("").getPath().replace("bin",
		// "trainingDigits").replace("%20", " ");
		// System.out.println(path);
		List<int[][]> l=ReadSampleUtils.SOURCE;
		int s=l.size();
		StringUtils.printArray(l.get(170));
		System.out.println();
		StringUtils.printArray(l.get(169));
		System.out.println(s);
		
	}
}
class rt1{
	public static void main(String[] args) {
		
		System.out.println("tju.edu.cn");
		
	}
}

