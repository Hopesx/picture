package Images;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;

public class Shape{//图形
	//所有图形都以矩形的框架坐标表示
	public int x,y;//矩形左上角坐标
	public int x1,y1;//矩形的右下角坐标
	public String type;//图形类型（矩形rect、圆形round、椭圆形oval、直线line）
	public Color color;//线条颜色
	public Color fillColor;//填充颜色
	public float lineWidth;//线宽
	
	Shape(int x,int y,int x1,int y1,String t,Color c,Color fc,float lw){
		this.x = x;
		this.y = y;
		this.x1 = x1;
		this.y1 = y1;
		this.type = t;
		this.color = c;
		this.fillColor =fc;
		this.lineWidth = lw;
	}
	
	Shape(){
	}
	
	public void show(Graphics g) {//绘制自身
		Graphics2D g1 = (Graphics2D) g;
		g1.setColor(color);
		g1.setStroke(new BasicStroke(lineWidth));
		//根据type的值绘制不同类型的图形
		if(type.equals("line")) {
			//绘制直线
			g1.drawLine(x,y, x1, y1);
			
		}
		if(type.equals("rect")) {
			//绘制矩形
			g1.drawRect(x,y,x1-x,y1-y);
			g1.setColor(fillColor);
			g1.fillRect(x + (int)lineWidth / 2,y + (int)lineWidth / 2,x1 - x - (int)lineWidth,y1 - y - (int)lineWidth);
			
		}
		if(type.equals("round")) {
			//绘制圆形
			//使用绘制椭圆的函数，但是保证包括圆形的矩形长和宽一样
			g1.drawOval(x, y, x1-x, x1-x);
			g1.setColor(fillColor);
			g1.fillOval(x, y, x1-x, x1-x);
		}
		if(type.equals("oval")) {
			//绘制椭圆形
			g.drawOval(x, y, x1-x, y1-y);
			g1.setColor(fillColor);
			g1.fillOval(x, y, x1-x, y1-y);
			
		}	
	}
}
