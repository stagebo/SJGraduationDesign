package com.sj.utils;

import java.awt.AWTException;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.image.BufferedImage;

public class ScreenUtils {
	/**
	 * 指定屏幕区域截图，返回截图的BufferedImage对象
	 * 
	 * @param x
	 * @param y
	 * @param width
	 * @param height
	 * @return
	 */
	public static BufferedImage getScreenShot(int x, int y, int width, int height) {
		BufferedImage bfImage = null;
		try {
			Robot robot = new Robot();
			bfImage = robot.createScreenCapture(new Rectangle(x, y, width, height));
			
		} catch (AWTException e) {
			e.printStackTrace();
		}
		
		return bfImage;
	}
}
