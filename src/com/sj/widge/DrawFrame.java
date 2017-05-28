package com.sj.widge;

import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.imageio.ImageIO;
import javax.swing.*;

import com.sj.algorithm.ANNRecognize;
import com.sj.algorithm.DistinguishImage;
import com.sj.utils.NeuralNetwork;
import com.sj.utils.ReadSampleUtils;
import com.sj.utils.ScreenUtils;

/**
 * 主窗体
 * 
 * @author Stagebo
 *
 */
public class DrawFrame extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 换行符
	 */
	private static String LINE_SEPARATOR = System.getProperty("line.separator");

	/**
	 * 神经网络
	 */
	final private static NeuralNetwork bp = new NeuralNetwork();
	static {
		bp.setData(ReadSampleUtils.SAMPLE_MAP);
	}

	/**
	 * 构造函数
	 */
	public DrawFrame() {
		super("绘图Test");

		// �˵���
		JMenuBar myBar = new JMenuBar();
		setJMenuBar(myBar);
		/* 一级菜单 */
		JMenu[] m = { new JMenu("操作"), new JMenu("形状"), new JMenu("切换画笔大小"), new JMenu("颜色") };
		/* 二级菜单 */
		JMenuItem[][] mI = { { new JMenuItem("清空"), new JMenuItem("�½�"), new JMenuItem("����") },
				{ new JMenuItem("自由"), new JMenuItem("ֱ直线"), new JMenuItem("椭圆"), new JMenuItem("矩形") },
				{ new JMenuItem("5"), new JMenuItem("10"), new JMenuItem("15"), new JMenuItem("30"), },
				{ new JMenuItem("红色"), new JMenuItem("黄色"), new JMenuItem("绿色"), new JMenuItem("灰色"),
						new JMenuItem("黑色"), new JMenuItem("蓝色") } };

		int i;
		int j;
		for (i = 0; i < m.length; i++) {
			myBar.add(m[i]);
			for (j = 0; j < mI[i].length; j++) {
				m[i].add(mI[i][j]);
			}
		}
		/*
		 * this.setBounds((int)Toolkit.getDefaultToolkit().getScreenSize().
		 * getWidth()/2-390 ,
		 * (int)Toolkit.getDefaultToolkit().getScreenSize().getHeight()/2-250 ,
		 * 780 , 500);
		 */
		/* 容器 */
		final Container contentPane = getContentPane();
		System.out.println(getWidth() + "-----" + getHeight());
		contentPane.setBounds(0, myBar.getHeight(), getWidth(), getHeight() - myBar.getHeight());

		final FreeDraw panel = new FreeDraw();
		final JButton btn = new JButton("识别");
		final JButton btn_ann = new JButton("神经网络识别");
		final JButton btn_ann_train = new JButton("神经网络训练");

		final JButton btn_clear = new JButton("重画");
		final JTextArea text = new JTextArea();
		// contentPane.setLayout(new BorderLayout());
		contentPane.setLayout(null);
		/**/
		contentPane.add(panel);
		panel.setBackground(new Color(255, 255, 255));
		panel.setBounds(10, 10, 400, 400);
		/**/
		contentPane.add(btn);
		btn.setBounds(600, 280, 120, 30);
		/**/
		btn_ann_train.setBounds(420, 320, 120, 30);
		contentPane.add(btn_ann_train);
		/**/
		btn_ann.setBounds(600, 320, 120, 30);
		contentPane.add(btn_ann);
		/**/
		btn_clear.setBounds(420, 280, 120, 30);
		contentPane.add(btn_clear);
		/**/
		text.setBounds(420, 110, 300, 150);
		text.setLineWrap(true);
		text.setWrapStyleWord(true);
		contentPane.add(text);
		text.append("结果展示：" + LINE_SEPARATOR);

		/* 欧式距离识别按钮截图 */
		btn.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				/* 截取屏幕图像 */
				int x = panel.getX() + getX() + 30;
				int y = panel.getY() + getY() + 80;
				int width = panel.getWidth() - 50;
				int height = panel.getHeight() - 80;
				System.out.println(x + "--" + y + "--" + width + "--" + height);
				BufferedImage img = ScreenUtils.getScreenShot(x, y, width, height);
				/* 识别图像 */
				int aimResult = DistinguishImage.DistinguishImages(img);
				/* 输出识别结果 */
				String time = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()).toString();
				text.setText(time + "--识别结果：" + aimResult + LINE_SEPARATOR);

			}
		});

		/* 人工神经网络识别 */
		btn_ann.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				//int x = panel.getX() + getX() + 8;
				//int y = panel.getY() + getY() + 22+myBar.getHeight();
				//int width = panel.getWidth();
				//int height = panel.getHeight();
				// System.out.println(x + "--" + y + "--" + width + "--" +
				// height);
				//BufferedImage img = ScreenUtils.getScreenShot(x, y, width, height);
				/*获取面板绘图结果*/
				
				BufferedImage img = new BufferedImage(panel.getWidth(),panel.getHeight(), BufferedImage.TYPE_INT_RGB);   
				Graphics2D g2 =(Graphics2D) img.getGraphics();
				panel.paint(g2);
				 try {
					ImageIO.write(img,"jpg",new File("C:\\Users\\Administrator\\Desktop\\1.jpg"));
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				/* 识别图像 */
				//int aimResult = new ANNRecognize().DistinguishImages(img,bp);
				/* 输出识别结果 */
				//String time = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()).toString();
				//text.setText(time + "--ann识别结果：" + aimResult + LINE_SEPARATOR);
			}
		});
		/**/
		btn_ann_train.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				if (!bp.isTrained)
					bp.train();
				text.setText("训练完成！" + LINE_SEPARATOR);
			}
		});
		/* 清空画布按钮注册事件 */
		btn_clear.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				panel.cleanAll();
				text.setText("");
			}
		});

		mI[0][0].addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				//panel.readFromFile();
			}

		});

		mI[0][1].addActionListener(new ActionListener() {
			// �½�

			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				panel.cleanAll();
			}

		});

		mI[0][2].addActionListener(new ActionListener() {
			// ����

			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				//panel.saveToFile();
			}

		});

		for (int type = 0; type < 4; type++) {
			// ͼ��
			final int t = type;
			mI[1][type].addActionListener(new ActionListener() {

				public void actionPerformed(ActionEvent e) {
					//panel.setDrawType(t);
				}
			});
			mI[2][type].addActionListener(new ActionListener() {

				public void actionPerformed(ActionEvent e) {
					JMenuItem j = (JMenuItem) e.getSource();
					int width = Integer.parseInt(j.getText().toString());
					panel.setLineWigth(width);
				}

			});
		}
		for (int type = 0; type < 6; type++) {
			// ��ɫ
			final int t = type;
			mI[3][type].addActionListener(new ActionListener() {

				public void actionPerformed(ActionEvent e) {
					panel.setLineColor(t);
				}
			});
		}
	}

}