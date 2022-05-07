package Images;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;

public class Shape{//ͼ��
	//����ͼ�ζ��Ծ��εĿ�������ʾ
	public int x,y;//�������Ͻ�����
	public int x1,y1;//���ε����½�����
	public String type;//ͼ�����ͣ�����rect��Բ��round����Բ��oval��ֱ��line��
	public Color color;//������ɫ
	public Color fillColor;//�����ɫ
	public float lineWidth;//�߿�
	
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
	
	public void show(Graphics g) {//��������
		Graphics2D g1 = (Graphics2D) g;
		g1.setColor(color);
		g1.setStroke(new BasicStroke(lineWidth));
		//����type��ֵ���Ʋ�ͬ���͵�ͼ��
		if(type.equals("line")) {
			//����ֱ��
			g1.drawLine(x,y, x1, y1);
			
		}
		if(type.equals("rect")) {
			//���ƾ���
			g1.drawRect(x,y,x1-x,y1-y);
			g1.setColor(fillColor);
			g1.fillRect(x + (int)lineWidth / 2,y + (int)lineWidth / 2,x1 - x - (int)lineWidth,y1 - y - (int)lineWidth);
			
		}
		if(type.equals("round")) {
			//����Բ��
			//ʹ�û�����Բ�ĺ��������Ǳ�֤����Բ�εľ��γ��Ϳ�һ��
			g1.drawOval(x, y, x1-x, x1-x);
			g1.setColor(fillColor);
			g1.fillOval(x, y, x1-x, x1-x);
		}
		if(type.equals("oval")) {
			//������Բ��
			g.drawOval(x, y, x1-x, y1-y);
			g1.setColor(fillColor);
			g1.fillOval(x, y, x1-x, y1-y);
			
		}	
	}
}
