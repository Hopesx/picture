package Images;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JTextField;
import javax.swing.text.Document;

import java.util.Scanner;


public class Image_my extends JPanel{
	public Shapes shapes = new Shapes();//图形集合
	public Words words = new Words();//文字集合
	String type = "no";//表示下一次要绘制的类型
	int x,y,x1,y1;//表示鼠标的横纵坐标
	int operation;//操作类型 0表示绘制 1表示移动图形 
	//2表示移动文字 3表示改变图形大小 4表示改变文本框大小 5表示橡皮擦
	Color selectColor = Color.black;//下一次要绘制的图形颜色
	Color fillColor = Color.white;//填充颜色默认为白色
	String familyName = "宋体";//字体类型，默认为宋体
	int style = Font.PLAIN;//字体风格，默认为普通
	int size = 30;//字体大小，默认为30
	float lineWidth = 5;//线宽，默认为5
	Font font;
	String content = " ";//文字内容
	
	JTextField text ;//当前选中文本框
	int nowText;//当前选中文本框
	
	JFrame frame;//窗体
	
	Image_my now;
	
	public Shapes[] shapesBackup = new Shapes[3];//备份，用于撤销，最多撤销两步
	public Words[] wordsBackup = new Words[3];//备份，用于撤销，最多撤销两步
	public Image_my[] backup = new Image_my[3];
	
	public Image_my(JFrame frame) {
		this.frame = frame;
		this.setLayout(null);
		//备份
		shapesBackup[0] = new Shapes();
		shapesBackup[1] = new Shapes();
		shapesBackup[2] = new Shapes();
		wordsBackup[0] = new Words();
		wordsBackup[1] = new Words();
		wordsBackup[2] = new Words();
		
        addMouseListener((MouseListener) new MouseListener() {
			public void mousePressed(MouseEvent e) {
				// 按下
				//获得当前鼠标坐标
				x = e.getX();
				y = e.getY();
				System.out.println("x,y " + x + "," + y);
				
				if(operation != 5) {
					if(shapes.isVertex(x, y) != "X") {
						//如果当前坐标为图形的顶点，操作类型为改变大小
						operation = 3;
					}
					else if(words.isVertex(x, y) != "X") {
						//如果当前坐标为文本框的顶点，操作类型为改变大小
						operation = 4;
					}
					else if(shapes.isEmptyShape(x, y) != -1) {
						//如果当前区域有图形,操作类型为移动图形
						operation = 1;
					}
					else if(words.isEmptyWord(x, y) != -1) {
						//如果当前区域有文字，操作类型为移动文字
						operation = 2;
						nowText = words.isEmptyWord(x, y);
					}
					else {//如果当前坐标没有图形和文字，则操作类型为绘制
						operation = 0;
					}
				}
				
			}
 
			
			public void mouseReleased(MouseEvent e) {
				// 释放
				//获得当前鼠标位置
				mouse(e);
				//System.out.println(backup[2].shapes.shapeList.size());
			}
	
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
			}

			
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub
//				
			}

			
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
		
		});
        
        addMouseMotionListener(new MouseMotionListener() {

			@Override
			public void mouseDragged(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mouseMoved(MouseEvent e) {
				// TODO Auto-generated method stub
				int xx = e.getX();
				int yy = e.getY();
				
				if(operation == 5) {
					//如果操作类型为橡皮擦
					if((shapes.isEmptyShape(xx, yy) != -1)||(words.isEmptyWord(xx, yy) != -1)) {
						Image imageCursor = 
							       Toolkit.getDefaultToolkit().getImage("picture/橡皮擦.png");  	
						frame.setCursor(Toolkit.getDefaultToolkit().createCustomCursor(
							                   imageCursor,  new Point(0, 0), "cursor"));  
					}
					else {
						Cursor normal = new Cursor(Cursor.DEFAULT_CURSOR);
						frame.setCursor(normal);
					}
				}
				else {
					if(!(shapes.isVertex(xx, yy)).equals("X") || !(words.isVertex(xx, yy)).equals("X")) {
						//如果当前位置在图形的顶点处，鼠标光标为十字形
						Cursor cross = new Cursor(Cursor.CROSSHAIR_CURSOR);
						frame.setCursor(cross);
					}
					else if((shapes.isEmptyShape(xx, yy) != -1)||(words.isEmptyWord(xx, yy) != -1)) {
						//如果当前区域有图形或文字,鼠标光标为手状
						Cursor move = new Cursor(Cursor.MOVE_CURSOR);
						frame.setCursor(move);
					}
					else {
						Cursor normal = new Cursor(Cursor.DEFAULT_CURSOR);
						frame.setCursor(normal);
					}
				}
				
			}
        	
        });
        
        
        
       
	}
	
	
	
	@Override
	public void paint(Graphics g) {//重绘
		super.paint(g);
		this.removeAll();
		//this.shapes.shows(g,this);
		for(int i = 0;i < this.shapes.shapeList.size();i++) {
			shapes.shapeList.get(i).show(g);
		}
		System.out.println("word" + this.words.wordList.size());
		for(int i = 0;i < this.words.wordList.size();i++) {
			words.wordList.get(i).show(this);
		}
	}
	
	public void savePic(String fileName) {//保存为图片
		int w = this.getWidth() == 0 ? 600 : this.getWidth();
		int h = this.getHeight() == 0 ? 600 : this.getHeight();
		BufferedImage img = new BufferedImage(w,h, BufferedImage.TYPE_INT_RGB);
//		BufferedImage img=new BufferedImage(
//				this.getWidth(),this.getHeight(),BufferedImage.TYPE_INT_RGB);
		//得到图形对象
		Graphics2D g2d = img.createGraphics();
		this.paint(g2d);
		File f=new File(fileName);
		try {
			ImageIO.write(img, "jpg", f);
		} catch (IOException e) {
			e.printStackTrace();
		}
		//释放图形对象
		g2d.dispose();
	
	}
	
	
	public void mouse(MouseEvent e) {
		x1 = e.getX();
		y1 = e.getY();
		//System.out.println("x1,y1 " + x1 + "," + y1);
		shapesBackup[0].copy(shapesBackup[1]);
		shapesBackup[1].copy(shapesBackup[2]);
		shapesBackup[2].copy(this.shapes);
		wordsBackup[0].copy(wordsBackup[1]);
		wordsBackup[1].copy(wordsBackup[2]);
		wordsBackup[2].copy(this.words);
		//System.out.println("keke" + wordsBackup[2].wordList.size());
		//获得绘图区的作图器
		Graphics2D g = (Graphics2D)getGraphics();
		g.setStroke(new BasicStroke(lineWidth));
		
		if(operation == 5) {
			//如果操作类型为橡皮擦
			int i = this.shapes.isEmptyShape(x, y);
			int j = this.words.isEmptyWord(x, y);
			System.out.println("擦掉");
			if(i != -1) {
				Shape s = this.shapes.shapeList.get(i);
				this.shapes.shapeList.remove(i);
				System.out.println("擦掉图形");
				g.setColor(Color.white);
				g.fillRect(s.x - 5,s.y - 5,s.x1 - s.x + 10,s.y1 - s.y + 10);
			}
			else if(j != -1) {
				Word w = this.words.wordList.get(j);
				this.words.wordList.remove(j);
				g.setColor(Color.white);
				g.fillRect(w.x - 5,w.y - 5,w.x1 - w.x + 10,w.y1 - w.y + 10);
				
			}
		}
		else {
			if(operation == 0) {
				//如果操作类型为绘制
				if(type == "word") {
					content = " ";
					nowText = words.wordList.size();
				}
				
			}
			else if(operation == 3) {
				//如果操作类型为改变大小
				String point = shapes.isVertex(x, y);//获取要改变的图形信息
				System.out.println("move " + point );
				String[] as = point.split(",");
				int i = Integer.parseInt(as[0]);//获取图形在链表中下标
			    //System.out.println("move " + i);
				Shape s = shapes.shapeList.get(i);
			    shapes.shapeList.remove(i);//从链表中删去该图形
			    if(as[1].equals("0")) {
			    	//左上角 改变x,y
			    	x = x1;
			    	y = y1;
			    	x1 = s.x1;
			    	y1 = s.y1;
			    }
			    else if(as[1].equals("1")) {
			    	//左下角 改变x,y1
			    	x = x1;
			    	//y1 = y1;
			    	y = s.y;
			    	x1 = s.x1;
			    }
			    else if(as[1].equals("2")) {
			    	//右下角 改变x1,y1
			    	y = s.y;
			    	x = s.x;
			    }
			    else if(as[1].equals("3")) {
			    	//右上角 改变x1,y
			    	y = y1;
			    	//x1 = x1;
			    	y1 = s.y1;
			    	x = s.x;
			    }
			    type = s.type;
			    //selectColor = s.color;
			    //覆盖原图形
				g.setColor(Color.white);
				g.fillRect(s.x - 5,s.y - 5,s.x1 - s.x + 10,s.y1 - s.y + 10);
			}
			else if(operation == 4) {
				String point = words.isVertex(x, y);//获取要改变的文字信息
				//System.out.println("move " + point );
				String[] as = point.split(",");
				int i = Integer.parseInt(as[0]);//获取文字在链表中下标
			    //System.out.println("move " + i);
				Word w = words.wordList.get(i);
			    words.wordList.remove(i);//从链表中删去该图形
			    if(as[1].equals("0")) {
			    	//左上角 改变x,y
			    	x = x1;
			    	y = y1;
			    	x1 = w.x1;
			    	y1 = w.y1;
			    }
			    else if(as[1].equals("1")) {
			    	//左下角 改变x,y1
			    	x = x1;
			    	//y1 = y1;
			    	y = w.y;
			    	x1 = w.x1;
			    }
			    else if(as[1].equals("2")) {
			    	//右下角 改变x1,y1
			    	y = w.y;
			    	x = w.x;
			    }
			    else if(as[1].equals("3")) {
			    	//右上角 改变x1,y
			    	y = y1;
			    	//x1 = x1;
			    	y1 = w.y1;
			    	x = w.x;
			    }
			    type = w.type;
			    remove(w.text);
			    content = w.content;
			    //selectColor = s.color;
			    //覆盖原图形
				g.setColor(Color.white);
				g.fillRect(w.x - 5,w.y - 5,w.x1 - w.x + 10,w.y1 - w.y + 10);
			}
			else if(operation == 1) {
				//如果操作类型为移动图形
				int i = shapes.isEmptyShape(x, y);//获取要移动的图形在链表中位置
			    Shape s = shapes.shapeList.get(i);
			    shapes.shapeList.remove(i);//从链表中删去该图形
			    int xx = x;
			    int yy = y;
			    x = x1 - (x - s.x);
			    y = y1 - (y - s.y);
			    x1 = x1 + s.x1 - xx;
			    y1 = y1 + s.y1 - yy;
			    type = s.type;
			    //selectColor = s.color;
			    //覆盖原图形
				g.setColor(Color.white);
				g.fillRect(s.x - 5,s.y - 5,s.x1 - s.x + 10,s.y1 - s.y + 10);
			}
			else if(operation == 2){
				//如果操作类型为移动文字
				//System.out.println("移动文字");
				int i = words.isEmptyWord(x, y);//获取要移动的文字在链表中位置
			    Word w = words.wordList.get(i);
			    words.wordList.remove(i);//从链表中删去该文字
			    int xx = x;
			    int yy = y;
			    x = x1 - (x - w.x);
			    y = y1 - (y - w.y);
			    x1 = x1 + w.x1 - xx;
			    y1 = y1 + w.y1 - yy;
			    type = w.type;
			    //selectColor = w.color;
			    content = w.content;
//			    familyName = w.familyName;
//			    style = w.font.getStyle();
//			    size = w.font.getSize();
			    //font = w.font;
			    //覆盖原图形
			    remove(w.text);
				g.setColor(Color.white);
				g.fillRect(w.x - 5,w.y - 5,w.x1 - w.x + 10,w.y1 - w.y + 10);
			} 
			g.setColor(selectColor);
			//根据type的值绘制不同类型的图形
			if(type.equals("line")) {
				//绘制直线
				g.drawLine(x,y, x1, y1);
				this.shapes.addShape(x, y, x1, y1,"line", selectColor,fillColor,lineWidth);
			}
			if(type.equals("rect")) {
				//绘制矩形
				g.drawRect(x,y,x1-x,y1-y);
				g.setColor(fillColor);
				
				g.fillRect(x + (int)lineWidth / 2,y + (int)lineWidth / 2,x1 - x - (int)lineWidth,y1 - y - (int)lineWidth);
				this.shapes.addShape(x, y, x1, y1,"rect", selectColor,fillColor,lineWidth);
			}
			if(type.equals("round")) {
				//绘制圆形
				//使用绘制椭圆的函数，但是保证包括圆形的矩形长和宽一样
				g.drawOval(x, y, x1-x, x1-x);
				g.setColor(fillColor);
				g.fillOval(x,y, x1-x, x1-x);
				this.shapes.addShape(x, y, x1, y+x1-x,"round", selectColor,fillColor,lineWidth);
			}
			if(type.equals("oval")) {
				//绘制椭圆形
				g.drawOval(x, y, x1-x, y1-y);
				g.setColor(fillColor);
				g.fillOval(x, y, x1-x, y1-y);
				this.shapes.addShape(x, y, x1, y1,"oval", selectColor,fillColor,lineWidth);
			}
			if(type.equals("word")) {
				//绘制文字
				text = new JTextField();
				Font font = new Font(familyName,style,size);
				text.setFont(font);
				text.setForeground(selectColor);
				text.setText(content);
				text.setBackground(fillColor);
				add(text);
				text.setBounds(x, y, x1-x, y1-y);
				this.words.addWord(text,familyName,style,size,selectColor,fillColor,content,x,y,x1,y1);
				//System.out.println("颜色" + selectColor);
				
				//nowText = words.wordList.size() - 1;
				//String ss = text.getText();
				//remove(text);
				//g.drawString(ss, size, style);
//				content = text.getText();
//				shapes.addWord(content, familyName, style, size, x, y, x1, y1);
//				System.out.println(content);
				text.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						//保存文本框中内容
						content = text.getText();
						System.out.println(content);
						words.wordList.get(nowText).content = content;
					}

				});

			}
		}

		//savePic();
		//System.out.println("haha" + wordsBackup[2].wordList.size());
	}
	
	 
	
}
