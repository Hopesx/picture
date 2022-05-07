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
	//ͼ�κ����ּ�����
	
	//ͼ������
	public ArrayList<Shape> shapeList = new ArrayList<Shape>();
	
	public void addShape(int x,int y,int x1,int y1,String t,Color c,Color fc,float lw) {//���ͼ��
		//���ͼ��
		Shape s = new Shape(x,y,x1,y1,t,c,fc,lw);
		this.shapeList.add(s);
	}
	
	
	public int isEmptyShape(int x,int y) {
		//�жϵ�ǰ��ǰ���꣨x,y���Ƿ�������ͼ��������
		//�����ǰ���겻������ͼ����ռ���򷵻ص�ǰͼ���±꣬���򷵻�-1
		for(int i = 0;i < this.shapeList.size();i++) {
			Shape s = this.shapeList.get(i);
			if(x >= s.x && x <= s.x1 && y >= s.y && y <= s.y1) {
				//�����ǰ�����ڵ�ǰͼ���ڲ�
				return i;
			}
		}
		return -1;
	}
	
	public String isVertex(int x,int y) {
		//�жϵ�ǰ���λ���Ƿ��ڰ�Χͼ�εľ��ζ��㴦
		//������򷵻ء�ͼ�ε��±꣬�����š� ���Ͻ�Ϊ0�����½�Ϊ1�����½�Ϊ2�����Ͻ�Ϊ4
		//���򷵻ء�X��
		for(int i = 0;i < this.shapeList.size();i++) {
			Shape s = this.shapeList.get(i);
			if(x >= s.x && x <= s.x + 5 && y >= s.y && y <= s.y + 5) {
				//��������Ͻ�
				return i + ",0";
			}
			else if(x >= s.x && x <= s.x + 5 && y <= s.y1 && y >= s.y1 - 5) {
				//��������½�
				return i + ",1";
			}
			else if(x >= s.x1 -5 && x <= s.x1 && y >= s.y1 - 5 && y <= s.y1) {
				//��������½�
				return i + ",2";
			}
			else if(x >= s.x1 - 5 && x <= s.x1 && y >= s.y && y <= s.y + 5) {
				//��������Ͻ�
				return i + ",3";
			}
		}
		return "X";
	}
	
	
	public void shows(Graphics g,JPanel p) {//��������ͼ�κ�����
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

