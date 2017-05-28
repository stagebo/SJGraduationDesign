package com.sj.algorithm;

import java.awt.image.BufferedImage;

import com.sj.utils.ImageUtils;
import com.sj.utils.NeuralNetwork;

public class ANNRecognize {
	public int DistinguishImages(BufferedImage img, NeuralNetwork bp) {
		/* 获得图片二值化矩阵 */
		int imgR[][] = ImageUtils.getImageMatrix(img);

		/* 提取图片有用部分 */
		imgR = ImageUtils.cutWhitePart(imgR);
		/* 切图部分代码存在边界问题待处理，边缘化问题返回空，暂不做处理 */
		if (imgR == null) {
			return -1;
		}
		/* 将有用部分绘制成样本大小图片 */
		BufferedImage imgs = ImageUtils.getImageByArray(imgR, img.getWidth(), img.getHeight());
		/* 将图片扩充到样本大小 */
		imgR = ImageUtils.getImageMatrix(imgs);
		// StringUtils.printArray(imgR);
		/* 反转 */
		for (int i = 0; i < imgR.length; i++) {
			for (int j = 0; j < imgR.length; j++) {
				if (imgR[i][j] == 0) {
					imgR[i][j] = 1;
				} else {
					imgR[i][j] = 0;
				}
			}
		}
		/* 返回识别结果 */
		return recognize(imgR, bp);
	}

	public int recognize(int[][] a, NeuralNetwork bp) {
		double[] arr = new double[a.length * a[0].length];
		int index = 0;
		for (int i = 0; i < a.length; i++) {
			for (int j = 0; j < a[0].length; j++) {
				arr[index] = a[i][j];
			}
		}
		return (int) bp.compute(arr);
	}
}