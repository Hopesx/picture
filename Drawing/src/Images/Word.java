package Images;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JPanel;
import javax.swing.JTextField;

public class Word {//������
	JTextField text;
	Color color;
	String content;//��������
	String familyName;
	int style;
	int size;
	int x,y,x1,y1;//����
	String type = "word";//Ϊ����ͼ�μ��ݣ���������Ϊword
	Color fillColor;//�����ɫ
	
	
	//���캯��
	public Word(JTextField text,String familyName,int style,int size,Color color,Color fillColor,String content,int x,int y,int x1,int y1) {
		this.text =text;
		this.x = x;
		this.y = y;
		this.x1 = x1;
		this.y1 = y1;
		this.color = color;
		this.fillColor = fillColor;
		//this.font = new Font(familyName,style,size);
		this.content = content;
		this.familyName = familyName;
		this.style = style;
		this.size = size;
	}
	
	public void show(JPanel p) {//��������
		this.text = new JTextField();
		Font font = new Font(familyName,style,size);
		this.text.setFont(font);
		text.setForeground(this.color);
		text.setText(this.content);
		text.setBackground(this.fillColor);
		p.add(this.text);
		text.setBounds(x, y, x1 - x, y1 - y);
	}

}
