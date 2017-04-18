package com.sj.algorithm;

import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

import com.sj.utils.CommonEnum;
import com.sj.utils.CommonEnum.ImageSample;
import com.sj.utils.CommonUtils;
import com.sj.utils.ImageUtils;
import com.sj.utils.ReadSampleUtils;
import com.sj.utils.StringUtils;

public class DistinguishImage {

	/**
	 * 识别图片矩阵
	 * 
	 * @param a
	 *            int[][] 图片矩阵
	 * @return int 识别结果
	 */
	public static int DistinguishNumber(int[][] a) {
		StringUtils.printArray(a);
		for (int i = 0; i < a.length; i++) {
			for (int j = 0; j < a.length; j++) {
				if (a[i][j] == 0) {
					a[i][j] = 1;
				} else {
					a[i][j] = 0;
				}
			}
		}
		double[] distence = new double[10];
		ArrayList<int[][]> source = ReadSampleUtils.SOURCE;
		for (int i = 0; i < 10; i++) {
			double d = 0;
			for (int j = 0; j < ImageSample.SAMPLE_COUNT; j++) {
				double di = 0;
				int r[][] = source.get(i * ImageSample.SAMPLE_COUNT + j);
				di = CommonUtils.getEuDistence(r, a);
				d += di;
			}
			distence[i] = d;

		}
		ArrayList<int[][]> l2 = new ArrayList<int[][]>();
		for (int i = 0; i < 10; i++) {
			double d = 0;
			int temp[][] = new int[ImageSample.SAMPLE_COUNT][ImageSample.SAMPLE_COUNT];
			for (int j = 0; j < ImageSample.SAMPLE_COUNT; j++) {
				int r[][] = source.get(i * ImageSample.SAMPLE_COUNT + j);
				temp = CommonUtils.MaxtrixAdd(temp, r);
			}
			l2.add(temp);
		}
		double[] l3 = new double[10];
		for (int i = 0; i < l2.size(); i++) {
			l3[i] = CommonUtils.getEuDistence(a, l2.get(i));
		}
		for (int i = 0; i < distence.length; i++)
			distence[i] += l3[i];
		StringUtils.printArray(distence);
		int result = CommonUtils.getMinIndex(distence);
		return result;
	}

	public static int DistinguishNumber1(int[][] a) {
		for (int i = 0; i < a.length; i++) {
			for (int j = 0; j < a.length; j++) {
				if (a[i][j] == 0) {
					a[i][j] = 1;
				} else {
					a[i][j] = 0;
				}
			}
		}
		StringUtils.printArray(a);
		ArrayList<int[][]> source = ReadSampleUtils.SOURCE;
		TreeMap<Double, List<Integer>> distenceMap = new TreeMap<Double, List<Integer>>();
		for (int i = 0; i < source.size(); i++) {
			int index=i/CommonEnum.ImageSample.SAMPLE_COUNT;
			double v = CommonUtils.getEuDistence(a, source.get(i));
			List<Integer> list=null;
			if (distenceMap.containsKey(v)) {
				list=distenceMap.get(v);				
			} else {
				list=new LinkedList<Integer>();				
			}
			list.add(index);
			distenceMap.put(v, list);
		}
		TreeMap<Double, List<Integer>> dMap=new TreeMap<Double,List<Integer>>();
		for(int i=0;i<20;i++){
			Double key=distenceMap.firstKey();//System.out.println(key+"---"+ distenceMap.get(key));
			dMap.put(key, distenceMap.get(key));
			distenceMap.remove(key);
		}
		int[] re=new int[distenceMap.size()];
		Double[] d=dMap.keySet().toArray(new Double[0]);
		for(int i=0;i<d.length;i++){	
			double vIndex=d[i];
			List<Integer> value=dMap.get(vIndex);
			if(value==null){
				continue;
			}
			for(int j=0;j<value.size();j++){
				int v=value.get(j);System.out.println(v);
				re[v]+=1;
			}
		}
		StringUtils.printArray(re);
		int result=CommonUtils.getMaxIndex(re);
		return result;
	}

	/**
	 * 识别图片
	 * 
	 * @param BufferedImage
	 *            img 图片
	 * @return int 识别结果
	 */
	public static int DistinguishImages(BufferedImage img) {
		/* 获得图片二值化矩阵 */
		int imgR[][] = ImageUtils.getImageMatrix(img);

		/* 提取图片有用部分 */
		imgR = ImageUtils.cutWhitePart(imgR);

		if (imgR == null) {
			return -1;
		}
		/* 将有用部分绘制成样本大小图片 */
		BufferedImage imgs = ImageUtils.getImageByArray(imgR, img.getWidth(), img.getHeight());
		/* 将图片扩充到样本大小 */
		imgR = ImageUtils.getImageMatrix(imgs);
		// StringUtils.printArray(imgR);

		/* 返回识别结果 */
		return DistinguishNumber1(imgR);
	}
}
