package Images;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;

import javax.swing.JPanel;
import javax.swing.JTextField;

public class Shapes {
	//图形和文字集合类
	
	//图形链表
	public ArrayList<Shape> shapeList = new ArrayList<Shape>();
	
	public void addShape(int x,int y,int x1,int y1,String t,Color c,Color fc,float lw) {//添加图形
		//添加图形
		Shape s = new Shape(x,y,x1,y1,t,c,fc,lw);
		this.shapeList.add(s);
	}
	
	
	public int isEmptyShape(int x,int y) {
		//判断当前当前坐标（x,y）是否在其他图形区域内
		//如果当前坐标不被其他图形所占用则返回当前图形下标，否则返回-1
		for(int i = 0;i < this.shapeList.size();i++) {
			Shape s = this.shapeList.get(i);
			if(x >= s.x && x <= s.x1 && y >= s.y && y <= s.y1) {
				//如果当前坐标在当前图形内部
				return i;
			}
		}
		return -1;
	}
	
	public String isVertex(int x,int y) {
		//判断当前鼠标位置是否在包围图形的矩形顶点处
		//如果是则返回“图形的下标，顶点标号” 左上角为0，左下角为1，右下角为2，右上角为4
		//否则返回“X”
		for(int i = 0;i < this.shapeList.size();i++) {
			Shape s = this.shapeList.get(i);
			if(x >= s.x && x <= s.x + 5 && y >= s.y && y <= s.y + 5) {
				//如果在左上角
				return i + ",0";
			}
			else if(x >= s.x && x <= s.x + 5 && y <= s.y1 && y >= s.y1 - 5) {
				//如果在左下角
				return i + ",1";
			}
			else if(x >= s.x1 -5 && x <= s.x1 && y >= s.y1 - 5 && y <= s.y1) {
				//如果在右下角
				return i + ",2";
			}
			else if(x >= s.x1 - 5 && x <= s.x1 && y >= s.y && y <= s.y + 5) {
				//如果在右上角
				return i + ",3";
			}
		}
		return "X";
	}
	
	
	public void shows(Graphics g,JPanel p) {//绘制所有图形和文字
		for(int i = 0;i < shapeList.size();i++) {
			shapeList.get(i).show(g);
		}
		
	}
	
	public void copy(Shapes s) {
		this.shapeList.removeAll(shapeList);
		for(int i = 0;i < s.shapeList.size();i++) {
			Shape b = s.shapeList.get(i);
			Shape a = new Shape(b.x,b.y,b.x1,b.y1,b.type,b.color,b.fillColor,b.lineWidth);
			this.shapeList.add(a);
		}
	}
//	public static void main(String args[]) {
//		Shapes as = new Shapes();
//		Shape a = new Shape(1,2,3,0,"rect",Color.black);
//		as.shapeList.add(a);
//		System.out.println(as.isEmpty(2, 1));
//		
//	}

}

