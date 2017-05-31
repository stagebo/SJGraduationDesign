package com.sj.utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.TreeMap;

import com.sj.log.Log4jUtils;
import com.sj.utils.CommonEnum.ImageSample;

/**
 * 样本读取类
 * 
 * @author Stagebo
 *
 */
public class ReadSampleUtils {
	/**
	 * 保存样本源
	 */
	public static ArrayList<int[][]> SOURCE = new ArrayList<int[][]>();
	/**
	 * map样本库，map映射值<数字，对应数组List>
	 */
	public static TreeMap<Integer, ArrayList<double[]>> SAMPLE_MAP = new TreeMap<Integer, ArrayList<double[]>>();
	/**
	 * 静态读取样本源
	 */
	static {
		Log4jUtils.info("com.sj.utils.ReadSampleUtils", "static block", "开始读取样本文件");
		Log4jUtils.info("com.sj.utils.ReadSampleUtils", "static block", "开始读取样本文件至List中");
		/* 读入List */
		for (int i = 0; i < 10; i++) {
			for (int j = 0; j < ImageSample.SAMPLE_COUNT; j++) {
				int a[][] = readTxt(i + "_" + j + ".txt");
				// a=ImageUtils.thinImage(a);
				SOURCE.add(a);
			}
		}
		Log4jUtils.info("com.sj.utils.ReadSampleUtils", "static block", "读取样本至List中完毕");
		Log4jUtils.info("com.sj.utils.ReadSampleUtils", "static block", "开始读取样本至Map中");
		/* 读入Map */
		SAMPLE_MAP = readTxtMap();
		Log4jUtils.info("com.sj.utils.ReadSampleUtils", "static block", "读取样本至Map中完毕");
	}

	/***
	 * 读取样本源方法
	 * 
	 * @param fileName
	 * @return
	 */
	public static int[][] readTxt(String fileName) {
		/* 获取样本源路径 */
		String path = ReadSampleUtils.class.getClassLoader().getResource("").getPath().replace("bin", "trainingDigits")
				.replace("%20", " ");
		String filePath = path + fileName;
		String encoding = "GBK";
		File file = null;
		InputStreamReader read = null;
		int result[][] = new int[ImageSample.SAMPLE_WIDTH][ImageSample.SAMPLE_HEIGHT];
		try {
			file = new File(filePath);
			if (!file.isFile() || !file.exists()) { // 判断文件是否存在
				Log4jUtils.sever("com.sj.utils.ReadSampleUtils", "readTex", "找不到指定文件");
			}
			read = new InputStreamReader(new FileInputStream(file), encoding);// 考虑到编码格式
			BufferedReader bufferedReader = new BufferedReader(read);
			String lineTxt = null;
			int i = 0;
			while ((lineTxt = bufferedReader.readLine()) != null) {
				String[] lineList = lineTxt.split("");
				for (int j = 0; j < lineList.length; j++) {
					result[i][j] = Integer.parseInt(lineList[j]);
				}
				i += 1;
			}
			read.close();
		} catch (Exception e) {
			Log4jUtils.sever("com.sj.utils.ReadSampleUtils","readTxt", "读取文件内容错误");
			Log4jUtils.sever("com.sj.utils.ReadSampleUtils","readTxt",e.getMessage());
		}
		return result;
	}

	public static double[][] readTxtDouble(String fileName) {
		/* 获取样本源路径 */
		String path = ReadSampleUtils.class.getClassLoader().getResource("").getPath().replace("bin", "trainingDigits")
				.replace("%20", " ");
		String filePath = path + fileName;
		String encoding = "GBK";
		File file = null;
		InputStreamReader read = null;
		double result[][] = new double[ImageSample.SAMPLE_WIDTH][ImageSample.SAMPLE_HEIGHT];
		try {
			file = new File(filePath);
			if (!file.isFile() || !file.exists()) { // 判断文件是否存在
				Log4jUtils.sever("com.sj.utils.ReadSampleUtils", "readTex", "找不到指定文件");
			}
			read = new InputStreamReader(new FileInputStream(file), encoding);// 考虑到编码格式
			BufferedReader bufferedReader = new BufferedReader(read);
			String lineTxt = null;
			int i = 0;
			while ((lineTxt = bufferedReader.readLine()) != null) {
				String[] lineList = lineTxt.split("");
				for (int j = 0; j < lineList.length; j++) {
					result[i][j] = Double.parseDouble(lineList[j]);
				}
				i += 1;
			}
			read.close();
		} catch (Exception e) {
			Log4jUtils.sever("com.sj.utils.ReadSampleUtils","readTxt", "读取文件内容错误");
			Log4jUtils.sever("com.sj.utils.ReadSampleUtils","readTxt",e.getMessage());
		}
		return result;
	}
	
	public static double[] readTxts(String fileName) {
		/* 获取样本源路径 */
		String path = ReadSampleUtils.class.getClassLoader().getResource("").getPath().replace("bin", "trainingDigits")
				.replace("%20", " ");
		String filePath = path + fileName;
		String encoding = "GBK";
		File file = null;
		InputStreamReader read = null;
		double result[] = new double[ImageSample.SAMPLE_WIDTH * ImageSample.SAMPLE_HEIGHT];
		try {
			file = new File(filePath);
			if (!file.isFile() || !file.exists()) { // 判断文件是否存在
				System.out.println("找不到指定的文件");
			}
			read = new InputStreamReader(new FileInputStream(file), encoding);// 考虑到编码格式
			BufferedReader bufferedReader = new BufferedReader(read);
			String lineTxt = null;
			int i = 0;
			while ((lineTxt = bufferedReader.readLine()) != null) {
				String[] lineList = lineTxt.split("");
				for (int j = 0; j < lineList.length; j++) {
					result[i] = Integer.parseInt(lineList[j]);
				}
				i += 1;
			}
			read.close();
		} catch (Exception e) {
			System.out.println("读取文件内容出错");
			e.printStackTrace();
		}
		return result;
	}

	private static TreeMap<Integer, ArrayList<double[]>> readTxtMap() {
		// TODO Auto-generated method stub
		TreeMap<Integer, ArrayList<double[]>> result = new TreeMap<Integer, ArrayList<double[]>>();
		/* 读取所有文件 */
		String path = ReadSampleUtils.class.getClassLoader().getResource("").getPath().replace("bin", "trainingDigits")
				.replace("%20", " ");
		File d = new File(path);
		File[] files = d.listFiles();
		for (File file : files) {
			String fileName = (file.getName());
			String[] fileInfo = fileName.split("[._]");// 0_1.txt
			double[] a = readTxts(fileName);
			int mark = Integer.parseInt(fileInfo[0]);
			ArrayList<double[]> l = result.get(mark);
			if (l == null) {
				l = new ArrayList<double[]>();
			}
			l.add(a);
			result.put(mark, l);
		}
		return result;
	}
}
