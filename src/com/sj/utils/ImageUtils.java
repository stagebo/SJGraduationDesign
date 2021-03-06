package com.sj.utils;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

import javax.imageio.ImageIO;

import com.sj.log.Log4jUtils;
import com.sj.utils.CommonEnum.ImageSample;

/**
 * 图像工具类
 * 
 * @author Stagebo
 *
 */
public class ImageUtils {
	private static int THRESHOLD = 128;

	/**
	 * 获得图片像素二值化数组
	 * 
	 * @param img
	 * @return int[][]
	 */
	public static int[][] getImageMatrix(BufferedImage img) {
		int w = img.getWidth();
		int h = img.getHeight();
		int result[][] = new int[h][w];
		for (int i = 0; i < w; i++) {
			for (int j = 0; j < h; j++) {
				int pixel = img.getRGB(i, j); // 下面三行代码将一个数字转换为RGB数字
				int r = (pixel & 0xff0000) >> 16;
				int g = (pixel & 0xff00) >> 8;
				int b = (pixel & 0xff);
				int rgbResult = (int) (0.11 * r + 0.59 * g + 0.3 * b);
				// System.out.println(rgbResult);
				result[j][i] = rgbResult > THRESHOLD ? 1 : 0;
			}

		}

		return result;
	}

	/**
	 * 切掉图片无用部分信息
	 * 
	 * @param a
	 * @param w
	 * @param h
	 * @return
	 */
	public static int[][] cutWhitePart(int[][] a) {
		// TODO
		// return t.getSubarray(a);
		int w = a[0].length, h = a.length;
		int sx = 0, sy = 0, ex = w - 1, ey = h - 1;
		boolean isLoop = true;
		for (int i = 0; i < w && isLoop; i++) {
			for (int j = 0; j < h && isLoop; j++) {
				if (a[j][i] == 0) {
					isLoop = false;
				}
			}
			if (isLoop)
				sx += 1;
		}
		isLoop = true;
		for (int j = 0; j < h && isLoop; j++) {
			for (int i = 0; i < w && isLoop; i++) {
				if (a[j][i] == 0) {
					isLoop = false;
				}
			}
			if (isLoop)
				sy += 1;
		}
		isLoop = true;
		for (int i = w - 1; i >= 0 && isLoop; i--) {
			for (int j = h - 1; j >= 0 && isLoop; j--) {
				if (a[j][i] == 0) {
					isLoop = false;
				}
			}
			if (isLoop)
				ex -= 1;
		}
		isLoop = true;
		for (int j = h - 1; j >= 0 && isLoop; j--) {
			for (int i = w - 1; i >= 0 && isLoop; i--) {
				if (a[j][i] == 0) {
					isLoop = false;
				}
			}
			if (isLoop)
				ey -= 1;
		}

		sy = sy < 0 ? 0 : sy;
		sx = sx < 0 ? 0 : sx;
		ex = ex > w ? w : ex;
		ey = ey > h ? h : ey;
		if (sy >= ey || sx >= ex) {
			return null;
		}
		int ww = ex - sx, hh = ey - sy;
		if (ww > hh) {
			sy = sy - (ww - hh) / 2;
			ey = ey + (ww - hh) / 2;
		} else {
			sx = sx - (hh - ww) / 2;
			ex = ex + (hh - ww) / 2;
		}
		sy = sy < 0 ? 0 : sy;
		sx = sx < 0 ? 0 : sx;
		ex = ex > w ? w : ex;
		ey = ey > h ? h : ey;
		/**/
		ww = ex - sx + 1;
		hh = ey - sy + 1;
		int[][] result = new int[hh + 2][ww + 2];
		for (int i = 0; i < result[0].length; i++)
			for (int j = 0; j < result.length; j++)
				result[j][i] = 2;
		for (int i = 0; i < ww; i++) {
			for (int j = 0; j < hh; j++) {
				try {
					result[j][i] = a[sy + j][sx + i];
				} catch (Exception e) {
					// System.out.println("异常Index：" + i + "--" + j);
					// System.out.println("异常：" + ww + "--" + hh);
					// System.out.println("异常：" + sy + "--" + sx);
					Log4jUtils.sever("com.sj.utils.ImageUtils", "cutWhitePart", "图片剪切遇到异常！");
					// e.printStackTrace();
					return null;
				}
			}
		}

		return result;
	}

	/**
	 * 根据二维数组矩阵活得二值化图片
	 * 
	 * @param a
	 *            int[][] 图像矩阵
	 * @param w
	 *            int 图像宽度
	 * @param h
	 *            int 图像高度
	 * @return
	 */
	public static BufferedImage getImageByArray(int[][] a, int w, int h) {
		// System.out.println(a.length + "--" + a[0].length);
		BufferedImage img = new BufferedImage(a[0].length, a.length, BufferedImage.TYPE_INT_RGB);
		for (int i = 0; i < a.length; i++) {
			for (int j = 0; j < a[0].length; j++) {
				try {
					int r = a[i][j];
					img.setRGB(j, i, r == 0 ? 0 : 0xffffff);
				} catch (Exception e) {
					// System.out.println(i + "===" + j + a[i][j]);
					e.printStackTrace();
					return null;
				}
			}
		}
		BufferedImage result = new BufferedImage(ImageSample.SAMPLE_WIDTH, ImageSample.SAMPLE_HEIGHT,
				BufferedImage.TYPE_INT_RGB);
		Graphics g = result.getGraphics();
		g.drawImage(img, 0, 0, ImageSample.SAMPLE_WIDTH, ImageSample.SAMPLE_HEIGHT, null);

		/*
		 * try { ImageIO.write(result, "png", new
		 * File("C:\\Users\\Administrator\\Desktop\\"+UUID.randomUUID()+".png"))
		 * ; } catch (IOException e) { // TODO Auto-generated catch block
		 * e.printStackTrace(); }
		 */
		return result;

	}

	/**
	 * 细化图像
	 * 
	 * @param a
	 *            int[][]
	 * @param key
	 *            int
	 * @return 细化后的图像矩阵
	 */
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

	/**
	 * 细化图像
	 * 
	 * @param a
	 * @return 细化后的图像矩阵
	 */
	public static int[][] thinImage(int[][] a) {
		for (int i = 0; i < 100; i++) {
			a = thinImage(a, 0);
			a = thinImage(a, 1);
		}
		return a;
	}

	/**
	 * 编码图片，将图片编码成base64
	 * 
	 * @param img
	 * @return String 编码后的字符串
	 * @throws IOException
	 */
	public static String encodeImage(BufferedImage img) throws IOException {
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		ImageIO.write(img, "jpg", baos);
		byte[] bytes = baos.toByteArray();
		return new sun.misc.BASE64Encoder().encodeBuffer(bytes).trim();
	}

	/**
	 * 解码图片。将base64码图片信息解码
	 * 
	 * @param code
	 * @return BufferedImage 图片对象
	 * @throws IOException
	 */
	public static BufferedImage decodeImage(String code) throws IOException {
		byte[] b = new sun.misc.BASE64Decoder().decodeBuffer(code);
		ByteArrayInputStream bais = new ByteArrayInputStream(b);
		BufferedImage img = ImageIO.read(bais);
		return img;
	}

	public static boolean filter(double[][] arr,double[][] f){
		for(int i=1;i<arr.length-1;i++){
			for(int j=1;j<arr[0].length-1;j++){
				arr[i][j]=arr[i-1][j-1]*f[0][0]+	arr[i-1][j]*  f[0][1]+ 	 arr[i-1][j+1]* f[0][2]+
						  arr[i][j-1]*  f[1][0]+	arr[i][j]*    f[1][1]+	 arr[i][j+1]*   f[1][2]+
						  arr[i+1][j-1]*f[2][0]+	arr[i+1][j]*  f[2][1]+	 arr[i+1][j+1]* f[2][2]	;
				arr[i][j]=Math.abs(arr[i][j]);
				arr[i][j]/=4;
				arr[i][j]=arr[i][j]>0.5?1:0;
			}
		}
		return true;
	}
	
}
