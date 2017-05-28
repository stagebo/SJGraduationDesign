package com.sj.test;

import java.util.List;

import javax.swing.JFrame;

import com.sj.log.Log4jUtils;
import com.sj.utils.ReadSampleUtils;
import com.sj.utils.StringUtils;
import com.sj.widge.DrawFrame;
/**
 * 测试类，主函数入口处
 * @author  Stagebo
 *
 */
public class Test {
	/**
	 * 主函数-程序入口
	 * @param args
	 */
	public static void main(String[] args) {
		Log4jUtils.info(Test.class, "main", "程序开始");
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
		
		
	}
}
