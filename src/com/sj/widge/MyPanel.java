package com.sj.widge;

import java.awt.BasicStroke; 
import java.awt.Color; 
import java.awt.Graphics; 
import java.awt.Graphics2D; 
import java.awt.Panel; 
import java.awt.Point; 
import java.awt.event.MouseAdapter; 
import java.awt.event.MouseEvent; 
import java.awt.event.MouseMotionAdapter; 
import java.io.BufferedReader; 
import java.io.BufferedWriter; 
import java.io.FileReader; 
import java.io.FileWriter; 
import java.util.Vector; 

/**
 * 
 * @author Stagebo
 *
 */
public class MyPanel extends Panel{ 
     
    /**
     * 
     */ 
    private static final long serialVersionUID = 1L; 
 
    enum DrawType 
    { 
        FreeDom, 
        Line, 
        Ellipse, 
        Rectangle 
    } 
     
    private Vector<Vector<Point>> FreedomDatas = new Vector<Vector<Point>>(); //freeDomʱʹ�� 
    private Vector<Vector<Point>> lineDatas = new Vector<Vector<Point>>(); //Lineʱʹ�� 
    private Vector<Vector<Point>> ellipseDatas = new Vector<Vector<Point>>(); 
    private Vector<Vector<Point>> rectangleDatas = new Vector<Vector<Point>>(); 
     
    private Color lineColor = Color.blue; 
    private int lineWidth = 5; 
    private DrawType drawType = DrawType.FreeDom; 
     
    private Vector<Vector<Vector<Point>>> allWriteDatas = new Vector<Vector<Vector<Point>>>(); 
    private Vector<Vector<Vector<Point>>> allReadDatas = new Vector<Vector<Vector<Point>>>(); 
     
    public MyPanel() 
    { 
        addMouseListener(new MouseAdapter() 
        { 
            public void mousePressed(MouseEvent e) 
            { 
                switch(drawType) 
                { 
                    case FreeDom: 
                        Point p = new Point(e.getX(),e.getY()); 
                        Vector<Point> newLine = new Vector<Point>(); 
                        newLine.add(p); 
                        FreedomDatas.add(newLine);  
                        break; 
                    case Line: 
                        //��ֱ�� 
                        Point prePoint = new Point(e.getX(),e.getY()); 
                        Vector<Point> newl = new Vector<Point>(); 
                        newl.add(prePoint); 
                        lineDatas.add(newl); 
                        break; 
                    case Ellipse: 
                        //����Բ 
                        Point newPoint = new Point(e.getX(),e.getY()); 
                        Vector<Point> newp = new Vector<Point>(); 
                        newp.add(newPoint); 
                        ellipseDatas.add(newp); 
                        break; 
                    case Rectangle: 
                        //������ 
                        Point nPoint = new Point(e.getX(),e.getY()); 
                        Vector<Point> newn = new Vector<Point>(); 
                        newn.add(nPoint); 
                        rectangleDatas.add(newn); 
                        break; 
                } 
 
            } 
             
            public void mouseReleased(MouseEvent e) 
            { 
                switch(drawType) 
                { 
                case FreeDom: 
                    break; 
                case Line: 
                    Point endPoint = new Point(e.getX(),e.getY()); 
                    int n = lineDatas.size() - 1; 
                    Vector<Point> newl = lineDatas.get(n); 
                    newl.add(endPoint); 
                    break; 
                case Ellipse: 
                    Point endPoint2 = new Point(e.getX(),e.getY()); 
                    int n2 = ellipseDatas.size() - 1; 
                    Vector<Point> newl2 = ellipseDatas.get(n2); 
                    newl2.add(endPoint2); 
                    break; 
                case Rectangle: 
                    Point endPoint3 = new Point(e.getX(),e.getY()); 
                    int n3 = rectangleDatas.size() - 1; 
                    Vector<Point> newl3 = rectangleDatas.get(n3); 
                    newl3.add(endPoint3); 
                    break; 
                } 
                repaint(); //ˢ�»��� 
            } 
        
			
        }); 
         
        addMouseMotionListener(new MouseMotionAdapter() 
        { 
            public void mouseDragged(MouseEvent e) 
            { 
                switch(drawType) 
                { 
                case FreeDom: 
                    Point p = new Point(e.getX(),e.getY()); 
                    int n = FreedomDatas.size()-1; //�õ����һ���ߵ�λ�� 
                    Vector<Point> lastLine = FreedomDatas.get(n); 
                    lastLine.add(p); 
                    break; 
                case Line: 
                    //ֱ�� 
                    break; 
                case Ellipse: 
                    //��Բ 
                    break; 
                case Rectangle: 
                    //���� 
                    break; 
                } 
 
                repaint(); //ˢ�»��� 
            } 
        }); 
    } 
     
    @Override 
    public void paint(Graphics g) 
    { 
        g.clearRect(0, 0, getWidth(), getHeight()); 
        g.setColor(lineColor); 
         
        Graphics2D g_2D = (Graphics2D)g; 
        BasicStroke stroke = new BasicStroke(lineWidth,BasicStroke.CAP_ROUND,BasicStroke.JOIN_ROUND); 
        g_2D.setStroke(stroke); 
         
        switch(drawType) 
        { 
        case FreeDom: 
            Vector<Point> v; 
            Point s,e; 
            int i,j,m; 
            int n = FreedomDatas.size(); 
            for(i=0;i<n;i++) 
            { 
                v = FreedomDatas.get(i); 
                m = v.size()-1; 
                for(j=0;j<m;j++) 
                { 
                    s = (Point)v.get(j); 
                    e = (Point)v.get(j+1); 
                    g.drawLine(s.x, s.y, e.x, e.y); 
                } 
            } 
            break; 
        case Line: 
            //ֱ�� 
            int ls = lineDatas.size(); 
            for(int li=0;li<ls;li++) 
            { 
                Vector<Point> twoPoints = lineDatas.get(li); 
                Point startPoint = twoPoints.get(0); 
                Point endPoint = twoPoints.get(1); 
                g.drawLine(startPoint.x,startPoint.y,endPoint.x,endPoint.y); 
            } 
            break; 
        case Ellipse: 
            //��Բ 
            int es = ellipseDatas.size(); 
            for(int ei=0;ei<es;ei++) 
            { 
                Vector<Point> twoPoints = ellipseDatas.get(ei); 
                Point startPoint = twoPoints.get(0); 
                Point endPoint = twoPoints.get(1); 
                int width = Math.abs(endPoint.x - startPoint.x); 
                int height = Math.abs(endPoint.y - startPoint.y); 
                g.drawOval(startPoint.x,startPoint.y,width,height); 
            } 
            break; 
        case Rectangle: 
            //���� 
            int rs = rectangleDatas.size(); 
            for(int ri=0;ri<rs;ri++) 
            { 
                Vector<Point> twoPoints = rectangleDatas.get(ri); 
                Point startPoint = twoPoints.get(0); 
                Point endPoint = twoPoints.get(1); 
                int width = Math.abs(endPoint.x - startPoint.x); 
                int height = Math.abs(endPoint.y - startPoint.y); 
                g.drawRect(startPoint.x,startPoint.y,width,height); 
            } 
            break; 
        } 
 
    } 
     
    public void setDrawType(int i) 
    { 
        switch(i) 
        { 
        case 0: 
            drawType = DrawType.FreeDom; 
            break; 
        case 1: 
            drawType = DrawType.Line; 
            break; 
        case 2: 
            drawType = DrawType.Ellipse; 
            break; 
        case 3: 
            drawType = DrawType.Rectangle; 
            break; 
        } 
        repaint(); 
    } 
     
    public void setLineWigth(int i) 
    { 
        lineWidth = i; 
        repaint(); 
    } 
     
    public void setLineColor(int i) 
    { 
        switch(i) 
        { 
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
        repaint(); 
    } 
     
    public void cleanAll() 
    { 
        FreedomDatas.clear(); 
        lineDatas.clear(); 
        ellipseDatas.clear(); 
        rectangleDatas.clear(); 
        repaint(); 
    } 
     
    public void saveToFile() 
    { 
        allWriteDatas.clear(); 
        allWriteDatas.add(FreedomDatas); 
        allWriteDatas.add(lineDatas); 
        allWriteDatas.add(ellipseDatas); 
        allWriteDatas.add(rectangleDatas); 
         
        try 
        { 
            BufferedWriter output = new BufferedWriter(new FileWriter("out.dat")); 
             
            for(int s=0;s<allWriteDatas.size();s++) 
            { 
                Vector<Vector<Point>> datas = allWriteDatas.get(s); 
                int ss = datas.size(); 
                output.write(new Integer(ss).toString()); 
                output.newLine(); 
                for(int i=0;i<datas.size();i++) 
                { 
                    Vector<Point> ps = datas.get(i); 
                    int pp = ps.size(); 
                    output.write(new Integer(pp).toString()); 
                    output.newLine(); 
                     
                    for(int j=0;j<ps.size();j++) 
                    { 
                        Point p = ps.get(j); 
                        int x = (int)p.getX(); 
                        int y = (int)p.getY(); 
                        output.write(new Integer(x).toString()); 
                        output.newLine(); 
                        output.write(new Integer(y).toString()); 
                        output.newLine(); 
                    } 
                } 
            } 
            output.close(); 
        } 
        catch(Exception e) 
        { 
            e.printStackTrace(); 
        } 
    } 
     
    public void readFromFile() 
    { 
        FreedomDatas.clear(); 
        lineDatas.clear(); 
        ellipseDatas.clear(); 
        rectangleDatas.clear(); 
        try 
        { 
            BufferedReader input = new BufferedReader(new FileReader("out.dat")); 
            for(int i=0;i<4;i++) 
            { 
                Vector<Vector<Point>> datas = new Vector<Vector<Point>>(); 
                String str = input.readLine(); 
                int size = Integer.parseInt(str); 
                for(int j=0;j<size;j++) 
                { 
                    Vector<Point> ps = new Vector<Point>(); 
                    String st = input.readLine(); 
                    int pp = Integer.parseInt(st); 
                     
                    for(int k=0;k<pp;k++) 
                    { 
                        String sx = input.readLine(); 
                        int x = Integer.parseInt(sx); 
                        String sy = input.readLine(); 
                        int y = Integer.parseInt(sy); 
                        Point p = new Point(x,y); 
                        ps.add(p); 
                    } 
                    datas.add(ps); 
                } 
                allReadDatas.add(datas); 
            } 
 
            Vector<Vector<Point>> clone1 = (Vector<Vector<Point>>) allReadDatas.get(0).clone(); 
            FreedomDatas = clone1; 
            Vector<Vector<Point>> clone2 = (Vector<Vector<Point>>) allReadDatas.get(1).clone(); 
            lineDatas = clone2; 
            Vector<Vector<Point>> clone3 = (Vector<Vector<Point>>) allReadDatas.get(2).clone(); 
            ellipseDatas = clone3; 
            Vector<Vector<Point>> clone4 = (Vector<Vector<Point>>) allReadDatas.get(3).clone(); 
            rectangleDatas = clone4; 
             
            repaint(); 
        } 
        catch (Exception e) 
        { 
            e.printStackTrace(); 
        } 
    } 
} 