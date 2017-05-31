package com.sj.test;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JTextArea;

import com.sj.utils.ImageUtils;
import com.sj.utils.ReadSampleUtils;

public class FilterTest {
	public static void main(String[] args) {
		JFrame f=new JFrame();
		f.setBounds(100, 100, 800, 600);
		f.setLayout(null);
		
		final JTextArea  t1=new JTextArea();
		t1.setBounds(0, 10, 390, 500);
		f.add(t1);
		
		final JTextArea  t2=new JTextArea();
		t2.setBounds(400, 10, 390, 500);
		f.add(t2);
	
		JButton btn=new JButton("转换");
		btn.setBounds(360, 510, 100, 25);
		f.add(btn);			
		
		f.setVisible(true);
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		btn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				double[][] arr=ReadSampleUtils.readTxtDouble("2_1.txt");
				for(int i=0;i<arr.length;i++){
					for(int j=0;j<arr[0].length;j++){
						t1.append((int)(arr[i][j])+"");
					}
					t1.append("\n");
				}
				double[][] filter=new double[][]{{-1,0,1},{-2,0,2},{-1,0,1}};
				boolean f=ImageUtils.filter(arr, filter);
				for(int i=0;i<arr.length;i++){
					for(int j=0;j<arr[0].length;j++){
						t2.append((int)(arr[i][j])+"");
					}
					t2.append("\n");
				}
			}
		});
		
	}
	
}
