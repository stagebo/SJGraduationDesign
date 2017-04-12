package com.sj.algorithm;

import java.awt.image.BufferedImage;
import java.util.ArrayList;

import com.sj.utils.CommonEnum.ImageSample;
import com.sj.utils.CommonUtils;
import com.sj.utils.ImageUtils;
import com.sj.utils.ReadSampleUtils;
import com.sj.utils.StringUtils;

public class DistinguishImage {
	/**
	 * 识别图片矩阵
	 * @param a int[][] 图片矩阵
	 * @return int 识别结果
	 */
	public static int DistinguishNumber(int[][] a) {
		StringUtils.printArray(a);
		for(int i=0;i<a.length;i++){
			for(int j=0;j<a.length;j++){
				if(a[i][j]==0){
					a[i][j]=1;
				}else{
					a[i][j]=0;
				}
			}
		}		
		double[] distence = new double[10];
		ArrayList<int[][]> source = ReadSampleUtils.SOURCE;
		for (int i = 0; i < 10; i++) {
			double d = 0;
			for (int j = 0; j < ImageSample.SAMPLE_COUNT ; j++) {
				double di = 0;
				int r[][] = source.get(i * ImageSample.SAMPLE_COUNT + j);
				di=CommonUtils.getEuDistence(r, a);				
				d += di;
			}
			distence[i]=d;

		}
		ArrayList<int[][]> l2=new ArrayList<int[][]>();
		for (int i = 0; i < 10; i++) {
			double d = 0;
			int temp[][]=new int[ImageSample.SAMPLE_COUNT][ImageSample.SAMPLE_COUNT];
			for (int j = 0; j < ImageSample.SAMPLE_COUNT ; j++) {
				int r[][] = source.get(i * ImageSample.SAMPLE_COUNT + j);
				temp=CommonUtils.MaxtrixAdd(temp, r);
			}
			l2.add(temp);
		}
		double[] l3=new double[10];
		for(int i=0;i<l2.size();i++){
			l3[i]=CommonUtils.getEuDistence(a, l2.get(i));
		}
		for(int i=0;i<distence.length;i++)
			distence[i]+=l3[i];
		StringUtils.printArray(distence);
		int result=CommonUtils.getMinIndex(distence);
		return result;
	}

	/**
	 * 识别图片
	 * @param BufferedImage img 图片
	 * @return int 识别结果
	 */
	public static int DistinguishImages(BufferedImage img) {
		/* 获得图片二值化矩阵 */
		int imgR[][] = ImageUtils.getImageMatrix(img);

		/* 提取图片有用部分 */
		imgR = ImageUtils.cutWhitePart(imgR);

		if(imgR==null){
			return -1;
		}
		/* 将有用部分绘制成样本大小图片 */
		BufferedImage imgs = ImageUtils.getImageByArray(imgR, img.getWidth(),
				img.getHeight());
		/* 将图片扩充到样本大小 */
		imgR = ImageUtils.getImageMatrix(imgs);
		//StringUtils.printArray(imgR);

		/* 返回识别结果 */
		return DistinguishNumber(imgR);
	}
}
