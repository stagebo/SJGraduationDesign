package com.sj.utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

import com.sj.utils.CommonEnum.ImageSample;
/**
 * 样本读取类
 * @author Administrator
 *
 */
public class ReadSampleUtils {
	/**
	 * 保存样本源
	 */
	public static ArrayList<int[][]> SOURCE=new ArrayList<int[][]>();
	/**
	 * 静态读取样本源
	 */
	static{
		for(int i=0;i<10;i++){
			for(int j=0;j<ImageSample.SAMPLE_COUNT;j++){
				SOURCE.add(readTxt(i+"_"+j+".txt"));
			}
		}
	}
	
	/***
	 * 读取样本源方法
	 * @param fileName
	 * @return
	 */
	public static int[][] readTxt(String fileName){
		/*获取样本源路径*/
		String path=ReadSampleUtils.class.getClassLoader().getResource("").getPath()
				.replace("bin", "trainingDigits").replace("%20", " ");
		String filePath = path+fileName;
		String encoding = "GBK";
		File file=null;
		InputStreamReader read=null;
		int result[][]=new int[ImageSample.SAMPLE_WIDTH][ImageSample.SAMPLE_HEIGHT];
		try {
			file = new File(filePath);
			if (!file.isFile() ||! file.exists()) { // 判断文件是否存在
				System.out.println("找不到指定的文件");				
			} 
			read = new InputStreamReader(new FileInputStream(file), encoding);// 考虑到编码格式
			BufferedReader bufferedReader = new BufferedReader(read);
			String lineTxt = null;
			int i=0;
			while ((lineTxt = bufferedReader.readLine()) != null) {
				String[] lineList=lineTxt.split("");
				for(int j=0;j<lineList.length;j++){
					result[i][j]=Integer.parseInt(lineList[j]);
				}
				i+=1;
			}
			read.close();
		} catch (Exception e) {
			System.out.println("读取文件内容出错");
			e.printStackTrace();
		}
		return result;
	}	
}
