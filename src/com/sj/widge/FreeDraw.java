package com.sj.widge;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.Vector;

import javax.swing.JFrame;
import javax.swing.JPanel;

import com.sj.log.Log4jUtils;

/**
 * 自由画板实现类
 * 
 * @author Stagebo
 *
 */
public class FreeDraw extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 7695678376508345039L;
	/**
	 * 标记是否初始化
	 */
	private boolean isInit = false;
	/**
	 * 标记需要更新的x、y坐标
	 */
	private int nx = 0, ny = 0;
	private boolean isFreeDraw = false;
	/**
	 * 
	 */
	private int lineWidth = 1;
	private Color lineColor = Color.BLACK;
	private Color bkColor = Color.WHITE;

	/**
	 * 构造函数，注册鼠标拖拽事件
	 */
	public FreeDraw() {
		this.addMouseMotionListener(new MouseMotionListener() {

			@Override
			public void mouseDragged(MouseEvent e) {
				// TODO Auto-generated method stub

				int x = e.getX();
				int y = e.getY();

				refresh(x, y);
			}

			@Override
			public void mouseMoved(MouseEvent e) {
				// TODO Auto-generated method stub

			}
		});
	}

	/**
	 * 画板更新函数，
	 * 
	 * @param xx
	 * @param yy
	 */
	public void refresh(int xx, int yy) {
		nx = xx;
		ny = yy;
		isFreeDraw = true;
		repaint();
	}

	/**
	 * 重新绘制函数
	 */
	public void paint(Graphics g) {	
		//Log4jUtils.info(this, "paint", "重新绘制");
		if (!isInit) {
			g.setColor(bkColor);
			g.fillRect(0, 0, getWidth(), getHeight());
			isInit = true;
			return;
		}
		if (isFreeDraw) {
			g.setColor(lineColor);
			g.fillRect(nx, ny, lineWidth, lineWidth);
			isFreeDraw = false;
			return;
		}
		super.paint(g);
	}

	public void paint(Graphics g, int k) {
		super.paint(g);
	}

	public void cleanAll() {
		Log4jUtils.info(this, "cleanAll", "清空面板");
		isInit = false;
		repaint();
	}

	public void setLineWigth(int i) {
		lineWidth = i;
	}

	public void setLineColor(int i) {
		// TODO Auto-generated method stub
		switch (i) {
		case 0:
			lineColor = Color.red;
			break;
		case 1:
			lineColor = Color.yellow;
			break;
		case 2:
			lineColor = Color.green;
			break;
		case 3:
			lineColor = Color.gray;
			break;
		case 4:
			lineColor = Color.black;
			break;
		case 5:
			lineColor = Color.blue;
			break;
		}
	}
}