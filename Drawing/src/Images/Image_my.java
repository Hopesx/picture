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
	public Shapes shapes = new Shapes();//ͼ�μ���
	public Words words = new Words();//���ּ���
	String type = "no";//��ʾ��һ��Ҫ���Ƶ�����
	int x,y,x1,y1;//��ʾ���ĺ�������
	int operation;//�������� 0��ʾ���� 1��ʾ�ƶ�ͼ�� 
	//2��ʾ�ƶ����� 3��ʾ�ı�ͼ�δ�С 4��ʾ�ı��ı����С 5��ʾ��Ƥ��
	Color selectColor = Color.black;//��һ��Ҫ���Ƶ�ͼ����ɫ
	Color fillColor = Color.white;//�����ɫĬ��Ϊ��ɫ
	String familyName = "����";//�������ͣ�Ĭ��Ϊ����
	int style = Font.PLAIN;//������Ĭ��Ϊ��ͨ
	int size = 30;//�����С��Ĭ��Ϊ30
	float lineWidth = 5;//�߿�Ĭ��Ϊ5
	Font font;
	String content = " ";//��������
	
	JTextField text ;//��ǰѡ���ı���
	int nowText;//��ǰѡ���ı���
	
	JFrame frame;//����
	
	Image_my now;
	
	public Shapes[] shapesBackup = new Shapes[3];//���ݣ����ڳ�������೷������
	public Words[] wordsBackup = new Words[3];//���ݣ����ڳ�������೷������
	public Image_my[] backup = new Image_my[3];
	
	public Image_my(JFrame frame) {
		this.frame = frame;
		this.setLayout(null);
		//����
		shapesBackup[0] = new Shapes();
		shapesBackup[1] = new Shapes();
		shapesBackup[2] = new Shapes();
		wordsBackup[0] = new Words();
		wordsBackup[1] = new Words();
		wordsBackup[2] = new Words();
		
        addMouseListener((MouseListener) new MouseListener() {
			public void mousePressed(MouseEvent e) {
				// ����
				//��õ�ǰ�������
				x = e.getX();
				y = e.getY();
				System.out.println("x,y " + x + "," + y);
				
				if(operation != 5) {
					if(shapes.isVertex(x, y) != "X") {
						//�����ǰ����Ϊͼ�εĶ��㣬��������Ϊ�ı��С
						operation = 3;
					}
					else if(words.isVertex(x, y) != "X") {
						//�����ǰ����Ϊ�ı���Ķ��㣬��������Ϊ�ı��С
						operation = 4;
					}
					else if(shapes.isEmptyShape(x, y) != -1) {
						//�����ǰ������ͼ��,��������Ϊ�ƶ�ͼ��
						operation = 1;
					}
					else if(words.isEmptyWord(x, y) != -1) {
						//�����ǰ���������֣���������Ϊ�ƶ�����
						operation = 2;
						nowText = words.isEmptyWord(x, y);
					}
					else {//�����ǰ����û��ͼ�κ����֣����������Ϊ����
						operation = 0;
					}
				}
				
			}
 
			
			public void mouseReleased(MouseEvent e) {
				// �ͷ�
				//��õ�ǰ���λ��
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
					//�����������Ϊ��Ƥ��
					if((shapes.isEmptyShape(xx, yy) != -1)||(words.isEmptyWord(xx, yy) != -1)) {
						Image imageCursor = 
							       Toolkit.getDefaultToolkit().getImage("picture/��Ƥ��.png");  	
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
						//�����ǰλ����ͼ�εĶ��㴦�������Ϊʮ����
						Cursor cross = new Cursor(Cursor.CROSSHAIR_CURSOR);
						frame.setCursor(cross);
					}
					else if((shapes.isEmptyShape(xx, yy) != -1)||(words.isEmptyWord(xx, yy) != -1)) {
						//�����ǰ������ͼ�λ�����,�����Ϊ��״
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
	public void paint(Graphics g) {//�ػ�
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
	
	public void savePic(String fileName) {//����ΪͼƬ
		int w = this.getWidth() == 0 ? 600 : this.getWidth();
		int h = this.getHeight() == 0 ? 600 : this.getHeight();
		BufferedImage img = new BufferedImage(w,h, BufferedImage.TYPE_INT_RGB);
//		BufferedImage img=new BufferedImage(
//				this.getWidth(),this.getHeight(),BufferedImage.TYPE_INT_RGB);
		//�õ�ͼ�ζ���
		Graphics2D g2d = img.createGraphics();
		this.paint(g2d);
		File f=new File(fileName);
		try {
			ImageIO.write(img, "jpg", f);
		} catch (IOException e) {
			e.printStackTrace();
		}
		//�ͷ�ͼ�ζ���
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
		//��û�ͼ������ͼ��
		Graphics2D g = (Graphics2D)getGraphics();
		g.setStroke(new BasicStroke(lineWidth));
		
		if(operation == 5) {
			//�����������Ϊ��Ƥ��
			int i = this.shapes.isEmptyShape(x, y);
			int j = this.words.isEmptyWord(x, y);
			System.out.println("����");
			if(i != -1) {
				Shape s = this.shapes.shapeList.get(i);
				this.shapes.shapeList.remove(i);
				System.out.println("����ͼ��");
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
				//�����������Ϊ����
				if(type == "word") {
					content = " ";
					nowText = words.wordList.size();
				}
				
			}
			else if(operation == 3) {
				//�����������Ϊ�ı��С
				String point = shapes.isVertex(x, y);//��ȡҪ�ı��ͼ����Ϣ
				System.out.println("move " + point );
				String[] as = point.split(",");
				int i = Integer.parseInt(as[0]);//��ȡͼ�����������±�
			    //System.out.println("move " + i);
				Shape s = shapes.shapeList.get(i);
			    shapes.shapeList.remove(i);//��������ɾȥ��ͼ��
			    if(as[1].equals("0")) {
			    	//���Ͻ� �ı�x,y
			    	x = x1;
			    	y = y1;
			    	x1 = s.x1;
			    	y1 = s.y1;
			    }
			    else if(as[1].equals("1")) {
			    	//���½� �ı�x,y1
			    	x = x1;
			    	//y1 = y1;
			    	y = s.y;
			    	x1 = s.x1;
			    }
			    else if(as[1].equals("2")) {
			    	//���½� �ı�x1,y1
			    	y = s.y;
			    	x = s.x;
			    }
			    else if(as[1].equals("3")) {
			    	//���Ͻ� �ı�x1,y
			    	y = y1;
			    	//x1 = x1;
			    	y1 = s.y1;
			    	x = s.x;
			    }
			    type = s.type;
			    //selectColor = s.color;
			    //����ԭͼ��
				g.setColor(Color.white);
				g.fillRect(s.x - 5,s.y - 5,s.x1 - s.x + 10,s.y1 - s.y + 10);
			}
			else if(operation == 4) {
				String point = words.isVertex(x, y);//��ȡҪ�ı��������Ϣ
				//System.out.println("move " + point );
				String[] as = point.split(",");
				int i = Integer.parseInt(as[0]);//��ȡ�������������±�
			    //System.out.println("move " + i);
				Word w = words.wordList.get(i);
			    words.wordList.remove(i);//��������ɾȥ��ͼ��
			    if(as[1].equals("0")) {
			    	//���Ͻ� �ı�x,y
			    	x = x1;
			    	y = y1;
			    	x1 = w.x1;
			    	y1 = w.y1;
			    }
			    else if(as[1].equals("1")) {
			    	//���½� �ı�x,y1
			    	x = x1;
			    	//y1 = y1;
			    	y = w.y;
			    	x1 = w.x1;
			    }
			    else if(as[1].equals("2")) {
			    	//���½� �ı�x1,y1
			    	y = w.y;
			    	x = w.x;
			    }
			    else if(as[1].equals("3")) {
			    	//���Ͻ� �ı�x1,y
			    	y = y1;
			    	//x1 = x1;
			    	y1 = w.y1;
			    	x = w.x;
			    }
			    type = w.type;
			    remove(w.text);
			    content = w.content;
			    //selectColor = s.color;
			    //����ԭͼ��
				g.setColor(Color.white);
				g.fillRect(w.x - 5,w.y - 5,w.x1 - w.x + 10,w.y1 - w.y + 10);
			}
			else if(operation == 1) {
				//�����������Ϊ�ƶ�ͼ��
				int i = shapes.isEmptyShape(x, y);//��ȡҪ�ƶ���ͼ����������λ��
			    Shape s = shapes.shapeList.get(i);
			    shapes.shapeList.remove(i);//��������ɾȥ��ͼ��
			    int xx = x;
			    int yy = y;
			    x = x1 - (x - s.x);
			    y = y1 - (y - s.y);
			    x1 = x1 + s.x1 - xx;
			    y1 = y1 + s.y1 - yy;
			    type = s.type;
			    //selectColor = s.color;
			    //����ԭͼ��
				g.setColor(Color.white);
				g.fillRect(s.x - 5,s.y - 5,s.x1 - s.x + 10,s.y1 - s.y + 10);
			}
			else if(operation == 2){
				//�����������Ϊ�ƶ�����
				//System.out.println("�ƶ�����");
				int i = words.isEmptyWord(x, y);//��ȡҪ�ƶ���������������λ��
			    Word w = words.wordList.get(i);
			    words.wordList.remove(i);//��������ɾȥ������
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
			    //����ԭͼ��
			    remove(w.text);
				g.setColor(Color.white);
				g.fillRect(w.x - 5,w.y - 5,w.x1 - w.x + 10,w.y1 - w.y + 10);
			} 
			g.setColor(selectColor);
			//����type��ֵ���Ʋ�ͬ���͵�ͼ��
			if(type.equals("line")) {
				//����ֱ��
				g.drawLine(x,y, x1, y1);
				this.shapes.addShape(x, y, x1, y1,"line", selectColor,fillColor,lineWidth);
			}
			if(type.equals("rect")) {
				//���ƾ���
				g.drawRect(x,y,x1-x,y1-y);
				g.setColor(fillColor);
				
				g.fillRect(x + (int)lineWidth / 2,y + (int)lineWidth / 2,x1 - x - (int)lineWidth,y1 - y - (int)lineWidth);
				this.shapes.addShape(x, y, x1, y1,"rect", selectColor,fillColor,lineWidth);
			}
			if(type.equals("round")) {
				//����Բ��
				//ʹ�û�����Բ�ĺ��������Ǳ�֤����Բ�εľ��γ��Ϳ�һ��
				g.drawOval(x, y, x1-x, x1-x);
				g.setColor(fillColor);
				g.fillOval(x,y, x1-x, x1-x);
				this.shapes.addShape(x, y, x1, y+x1-x,"round", selectColor,fillColor,lineWidth);
			}
			if(type.equals("oval")) {
				//������Բ��
				g.drawOval(x, y, x1-x, y1-y);
				g.setColor(fillColor);
				g.fillOval(x, y, x1-x, y1-y);
				this.shapes.addShape(x, y, x1, y1,"oval", selectColor,fillColor,lineWidth);
			}
			if(type.equals("word")) {
				//��������
				text = new JTextField();
				Font font = new Font(familyName,style,size);
				text.setFont(font);
				text.setForeground(selectColor);
				text.setText(content);
				text.setBackground(fillColor);
				add(text);
				text.setBounds(x, y, x1-x, y1-y);
				this.words.addWord(text,familyName,style,size,selectColor,fillColor,content,x,y,x1,y1);
				//System.out.println("��ɫ" + selectColor);
				
				//nowText = words.wordList.size() - 1;
				//String ss = text.getText();
				//remove(text);
				//g.drawString(ss, size, style);
//				content = text.getText();
//				shapes.addWord(content, familyName, style, size, x, y, x1, y1);
//				System.out.println(content);
				text.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						//�����ı���������
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
