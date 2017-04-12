package com.sj.widge;

import java.awt.*; 
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

import javax.imageio.ImageIO;
import javax.swing.*;

import com.sj.algorithm.DistinguishImage;
import com.sj.utils.ImageUtils;
import com.sj.utils.ScreenUtils;
import com.sj.utils.StringUtils; 
 
 
public class DrawFrame extends JFrame{ 
     
    /**
     * 
     */ 
    private static final long serialVersionUID = 1L; 
 
    private static String LINE_SEPARATOR=System.getProperty("line.separator");
    public DrawFrame() 
    { 
        super("绘图Test"); 
         
        //�˵��� 
        JMenuBar myBar = new JMenuBar(); 
        setJMenuBar(myBar); 
         
        JMenu []m = {new JMenu("�ļ�"), new JMenu("ͼ��"), new JMenu("切换画笔大小"), new JMenu("��ɫ")}; 
         
        JMenuItem [][]mI = { 
                    {new JMenuItem("��"),new JMenuItem("�½�"),new JMenuItem("����")}, 
                    {new JMenuItem("����"),new JMenuItem("ֱ��"), 
                     new JMenuItem("��Բ"),new JMenuItem("����")}, 
                    {new JMenuItem("5"), new JMenuItem("10"),new JMenuItem("15"),new JMenuItem("30"),}, 
                    {new JMenuItem("��ɫ"),new JMenuItem("��ɫ"),new JMenuItem("��ɫ"), 
                     new JMenuItem("��ɫ"),new JMenuItem("��ɫ"), new JMenuItem("��ɫ")} 
                }; 
         
        int i; 
        int j; 
        for(i=0;i<m.length;i++) 
        { 
            myBar.add(m[i]); 
            for(j=0;j<mI[i].length;j++) 
            { 
                m[i].add(mI[i][j]); 
            } 
        } 
         
        //���� 
        final Container contentPane = getContentPane(); 
        contentPane.setBounds(0, myBar.getHeight(),getWidth(),getHeight() - myBar.getHeight()); 
        final MyPanel panel = new MyPanel(); 
        final JButton btn=new JButton("识别");
        final JButton btn_clear=new JButton("重画");
        final JTextArea text=new JTextArea();
      //contentPane.setLayout(new BorderLayout());
        contentPane.setLayout(null);
        /**/
        contentPane.add(panel);
        panel.setBackground(new Color(255,255,255));
        panel.setBounds(10,10,400,400);
        /**/
        contentPane.add(btn);
        btn.setBounds(530, 280, 100, 30);
        /**/
        btn_clear.setBounds(420,280,100,30);
        contentPane.add(btn_clear);
        /**/
        text.setBounds(420,110,300,150);
        text.setLineWrap(true);
        text.setWrapStyleWord(true);
        contentPane.add(text);
        text.append("结果展示："+LINE_SEPARATOR);
        
        //���ּ����� 
        /*识别按钮截图*/
        btn.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				/*截取屏幕图像*/
				int x=panel.getX()+getX()+30;
				int y=panel.getY()+getY()+80;
				int width=panel.getWidth()-50;
				int height=panel.getHeight()-80;
				System.out.println(x+"--"+y+"--"+width+"--"+height);
				BufferedImage img = ScreenUtils.getScreenShot(x, y, width, height);
				/*try {
					ImageIO.write(img, "png", new File("C:\\Users\\1\\Desktop\\"+UUID.randomUUID()+".png"));
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}*/
				/*识别图像*/
				int aimResult=DistinguishImage.DistinguishImages(img);
				/*输出识别结果*/
				String time=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()).toString();
				text.setText(time+ "--识别结果："+aimResult+LINE_SEPARATOR);
				
			}
		});
        
        /*清空画布按钮注册事件*/
        btn_clear.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				 panel.cleanAll(); 
			}
		});
        mI[0][0].addActionListener(new ActionListener() 
        { 
            public void actionPerformed(ActionEvent arg0) { 
                // TODO Auto-generated method stub 
                panel.readFromFile(); 
            } 
             
        }); 
         
        mI[0][1].addActionListener(new ActionListener() 
        { 
            //�½� 
            
            public void actionPerformed(ActionEvent arg0) { 
                // TODO Auto-generated method stub 
                panel.cleanAll(); 
            } 
             
        }); 
         
        mI[0][2].addActionListener(new ActionListener() 
        { 
            //���� 
            
            public void actionPerformed(ActionEvent arg0) { 
                // TODO Auto-generated method stub 
                panel.saveToFile(); 
            } 
             
        }); 
         
        for(int type=0;type<4;type++) 
        {    
            //ͼ�� 
            final int t = type; 
            mI[1][type].addActionListener(new ActionListener() 
            { 
                
                public void actionPerformed(ActionEvent e) { 
                    panel.setDrawType(t); 
                }    
            }); 
        } 
         
        for(int type=0;type<4;type++) 
        { 
            //�߿� 
            mI[2][type].addActionListener(new ActionListener() 
            { 
                
                public void actionPerformed(ActionEvent e) { 
                    JMenuItem j = (JMenuItem)e.getSource(); 
                    int width = Integer.parseInt(j.getText().toString()); 
                    panel.setLineWigth(width); 
                } 
                 
            }); 
        } 
         
        for(int type=0;type<6;type++) 
        { 
            //��ɫ 
            final int t = type; 
            mI[3][type].addActionListener(new ActionListener() 
            { 
                
                public void actionPerformed(ActionEvent e) { 
                    panel.setLineColor(t); 
                } 
            }); 
        } 
    } 
     
} 