package com.sj.utils;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import com.sj.utils.CommonEnum.ImageSample;
/**
 * 图像工具类
 * @author Administrator
 *
 */
public class ImageUtils {
	private static int THRESHOLD=128;
	/**
	 * 获得图片像素二值化数组
	 * @param img
	 * @return
	 */
	public static int[][] getImageMatrix(BufferedImage img) {
		int w = img.getWidth();
		int h = img.getHeight();
		int result[][] = new int[h][w];
		for (int i = 0; i < w; i++) {
			for (int j = 0; j < h; j++) {
				 int pixel = img.getRGB(i, j); // 下面三行代码将一个数字转换为RGB数字  
	                int r= (pixel & 0xff0000) >> 16;  
	                int g = (pixel & 0xff00) >> 8;  
	                int b = (pixel & 0xff); 
	                int rgbResult=(int)(0.11*r+0.59*g+0.3*b);
	                //System.out.println(rgbResult);
	                result[j][i]=rgbResult>THRESHOLD?1:0;
			}

		}
		
		return result;
	}
	/**
	 * 切掉图片无用部分信息
	 * @param a
	 * @param w
	 * @param h
	 * @return
	 */
	public static int[][] cutWhitePart(int[][] a){
		//TODO
		//return t.getSubarray(a);
		int w=a[0].length,h=a.length;
		int sx=0,sy=0,ex=w-1,ey=h-1;
		boolean isLoop=true;
		for(int i=0;i<w&&isLoop;i++){
			for(int j=0;j<h&&isLoop;j++){
				if(a[j][i]==0)
				{
					isLoop=false;
				}
			}
			if(isLoop)
			sx+=1;
		}
		isLoop=true;
		for(int j=0;j<h&&isLoop;j++){
			for(int i=0;i<w&&isLoop;i++){
				if(a[j][i]==0)
				{
					isLoop=false;
				}
			}
			if(isLoop)
			sy+=1;
		}
		isLoop=true;
		for(int i=w-1;i>=0&&isLoop;i--){
			for(int j=h-1;j>=0&&isLoop;j--){
				if(a[j][i]==0){
					isLoop=false;
				}
			}
			if(isLoop)
			ex-=1;
		}
        isLoop=true;
		for(int j=h-1;j>=0&&isLoop;j--){
			for(int i=w-1;i>=0&&isLoop;i--){
				if(a[j][i]==0){
					isLoop=false;
				}
			}
			if(isLoop)
			ey-=1;
		}

		sy=sy<0?0:sy;
		sx=sx<0?0:sx;
		ex=ex>w?w:ex;
		ey=ey>h?h:ey;
		if(sy>=ey||sx>=ex){
			return null;
		}
		int ww=ex-sx,hh=ey-sy;
		if(ww>hh)
		{
			sy=sy-(ww-hh)/2;
			ey=ey+(ww-hh)/2;
		}else{
			sx=sx-(hh-ww)/2;
			ex=ex+(hh-ww)/2;
		}
		sy=sy<0?0:sy;
		sx=sx<0?0:sx;
		ex=ex>w?w:ex;
		ey=ey>h?h:ey;
		/**/
		ww=ex-sx+1;hh=ey-sy+1;
		int[][] result=new int[hh+2][ww+2];
		for(int i=0;i<result[0].length;i++)
			for(int j=0;j<result.length;j++)
				result[j][i]=2;
		for(int i=0;i<ww;i++){
			for(int j=0;j<hh;j++){
				try{					
				result[j][i]=a[sy+j][sx+i];
				}catch(Exception e){
					System.out.println("异常Index："+i+"--"+j);
					System.out.println("异常："+ww+"--"+hh);
					System.out.println("异常："+sy+"--"+sx);
					//e.printStackTrace();
					return null;
				}
			}
		}
		
		
		return result;
	}
	/**
	 * 根据二维数组矩阵活得二值化图片
	 * @param a
	 * @param w
	 * @param h
	 * @return
	 */
	public static BufferedImage getImageByArray(int[][] a,int w,int h){
		System.out.println(a.length+"--"+a[0].length);
		BufferedImage img=new BufferedImage(a[0].length,a.length, BufferedImage.TYPE_INT_RGB);
		for(int i=0;i<a.length;i++){
			for(int j=0;j<a[0].length;j++){
				try{
					int r=a[i][j];
					img.setRGB(j,i, r==0?0:0xffffff);					
				}catch(Exception e){
					System.out.println(i+"==="+j+a[i][j]);
					e.printStackTrace();
					return null;
				}
			}
		}
		BufferedImage result=new BufferedImage(ImageSample.SAMPLE_WIDTH,ImageSample.SAMPLE_HEIGHT
				,BufferedImage.TYPE_INT_RGB);
		Graphics g=result.getGraphics();
		g.drawImage(img, 0, 0, ImageSample.SAMPLE_WIDTH, ImageSample.SAMPLE_HEIGHT,null);
		
		/*try {
			ImageIO.write(result, "png", new File("C:\\Users\\Administrator\\Desktop\\"+UUID.randomUUID()+".png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
		return result;
		
	}
}
