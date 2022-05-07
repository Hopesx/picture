package Images;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JPanel;
import javax.swing.JTextField;

public class Word {//文字类
	JTextField text;
	Color color;
	String content;//文字内容
	String familyName;
	int style;
	int size;
	int x,y,x1,y1;//坐标
	String type = "word";//为了与图形兼容，设置类型为word
	Color fillColor;//填充颜色
	
	
	//构造函数
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
	
	public void show(JPanel p) {//绘制自身
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
