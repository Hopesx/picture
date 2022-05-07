package Images;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

public class Images extends JPanel{
	//存放image页面的链表
	public ArrayList<Image_my> imageList = new ArrayList<Image_my>();
	//存放缩略图文件名的链表
	public ArrayList<String> imgList = new ArrayList<String>();
	
	int ordinal = 0;//当前Image页面序号，默认为0
	String imageName;//保存和打开的文件名
	String imageName0;//保存和打开的输入
	String f;//固定路径		
	JFrame frame;//窗体
	//JLabel x;
	
	JPanel thumbnails;
	JScrollPane jsp;
	Image_my nowP;//当前image页面
	
	public Images(JFrame frame) {
		this.setLayout(null);
		this.thumbnails = new JPanel();
		this.frame = frame;
		nowP = new Image_my(frame);
	    this.imageList.add(nowP);
	    //新建Image页面后默认当前页面为新建页面
	    this.ordinal = 0; 
	    //System.out.println(this.ordinal);
		
	    this.refreshPage();//刷新页面
	    
	    
	    this.addMouseListener(new MouseListener() {
			public void mouseClicked(MouseEvent e) {
				// 处理鼠标点击
				
			}
			public void mouseEntered(MouseEvent e) {
				// 处理鼠标移入
			}
			public void mouseExited(MouseEvent e) {
				// 处理鼠标离开
			}
			public void mousePressed(MouseEvent e) {
				// 处理鼠标按下
			}
			public void mouseReleased(MouseEvent e) {
				// 处理鼠标释放
				 if (e.isMetaDown()) {
	                    showPopupMenu(e.getComponent(), e.getX(), e.getY());
	                    //System.out.println("右键");
	                }
			}
		});
	    
	    thumbnails.addMouseListener(new MouseListener() {
			public void mouseClicked(MouseEvent e) {
				// 处理鼠标点击
				int y = e.getY();
				if(ordinal == imageList.size() - 1) {
					//如果原页面为最后一页，则保存原页面
					String fileName =f+imageName0+"\\"+"saveImage" + imgList.size() + ".jpg";
					imageList.get(ordinal).savePic(fileName);
					imgList.add(fileName);
				}
				else {
					//如果原页面不是最后一页，则更新原页面
					String fileName =f+imageName0+"\\"+"saveImage" + ordinal + ".jpg";
					try{
			            File file = new File(fileName);
			            if(file.delete()){
			                System.out.println(file.getName() + " 文件已被删除！");
			            }else{
			                System.out.println("文件删除失败！");
			            }
			        }catch(Exception ev){
			            ev.printStackTrace();
			        }
					imageList.get(ordinal).savePic(fileName);
					System.out.println("更新");
				}
				if((y / 250) < imageList.size()) {
					ordinal = y / 250;
				}
				
				System.out.println(ordinal);
				refreshPage();
			}
			public void mouseEntered(MouseEvent e) {
				// 处理鼠标移入
			}
			public void mouseExited(MouseEvent e) {
				// 处理鼠标离开
			}
			public void mousePressed(MouseEvent e) {
				// 处理鼠标按下
			}
			public void mouseReleased(MouseEvent e) {
				// 处理鼠标释放
			}
		});
  
	}
	
	//获得新建image的名称
	public void getImageName(String imageName1)
	{
		imageName=imageName1;
	}
	public void getImageName0(String imageName11)
	{
		imageName0=imageName11;
	}
	public void getImageName1(String f1)
	{
		f=f1;
	}		
	
	public void addImage() {//新建Image页面
		if(this.ordinal == this.imageList.size() - 1) {
			//如果原页面为最后一页，则保存原页面
			String fileName =f+imageName0+"\\"+"saveImage" + this.imgList.size() + ".jpg";
			this.imageList.get(ordinal).savePic(fileName);
			this.imgList.add(fileName);
		}
		else {
			//如果原页面不是最后一页，则更新原页面
			String fileName =f+imageName0+"\\"+"saveImage" + ordinal + ".jpg";
			try{
	            File file = new File(fileName);
	            if(file.delete()){
	                System.out.println(file.getName() + " 文件已被删除！");
	            }else{
	                System.out.println("文件删除失败！");
	            }
	        }catch(Exception e){
	            e.printStackTrace();
	        }
			this.imageList.get(ordinal).savePic(fileName);
		}
		
		Image_my newp = new Image_my(frame);
		this.nowP = newp;
	    this.imageList.add(newp);
	    //新建Image页面后默认当前页面为新建页面
	    this.ordinal = this.imageList.size() - 1; 
	    //System.out.println(this.ordinal);
	    this.refreshPage();//刷新页面
	}
	
	public void deleteImage() {//删除页面
		this.imageList.remove(ordinal);//删除当前选中页面
		this.imgList.remove(ordinal);
		//删除缩略图
		String fileName =f+imageName0+"\\"+"saveImage" + ordinal + ".jpg";
		try{
            File file = new File(fileName);
            if(file.delete()){
                System.out.println(file.getName() + " 文件已被删除！");
            }else{
                System.out.println("文件删除失败！");
            }
        }catch(Exception e){
            e.printStackTrace();
        }
		if(ordinal >= this.imageList.size()) {
			ordinal = this.imageList.size() - 1;
		}
		this.nowP = this.imageList.get(ordinal);
		this.refreshPage();//刷新页面
	}
	
	public void refreshPage() {//刷新页面
		this.removeAll();//清空 即删除原先Image页面
		thumbnails.removeAll();
		/****缩略图*****/
		//thumbnails = new JPanel();
		thumbnails.setLayout(new GridLayout(0, 1, 0, 10));
		//System.out.println(this.imgList.size());
		//this.labelList.removeAll(labelList);//清空
		for(int i = 0;i < this.imgList.size();i++) {
			ImageIcon image = new ImageIcon(imgList.get(i));
			Image img = image.getImage();
			img = img.getScaledInstance(240, 240, Image.SCALE_DEFAULT);
			image.setImage(img);
			JLabel cl = new JLabel(image);
			//cl.setIcon(image);
			//this.labelList.add(cl);
			thumbnails.add(cl);
			cl.setBounds(0, i*240, 240, 240);
			//System.out.println(i);
		}
		jsp = new JScrollPane(thumbnails);
		jsp.setPreferredSize(new Dimension(250, 540));
		jsp.setBounds(50,35,250,540);//重定位
		this.add(jsp);
		/****缩略图*****/
		
		Image_my now = imageList.get(ordinal);
		now.setPreferredSize(new Dimension(600, 540));
		now.setBackground(Color.white);
		this.add(now);//显示当前选中页面
		//now.setBounds(300,30,600,540);//重定位
		now.paint(now.getGraphics());
		now.setBounds(350,35,650,540);//重定位
		
		
		

	}
	
	 @Override
	 protected void paintComponent(Graphics g) {
		 super.paintComponent(g);
	     ImageIcon image = new ImageIcon("picture/background4.jpg");
	     Image img = image.getImage();
		 img = img.getScaledInstance(1300,700, Image.SCALE_DEFAULT);
		 image.setImage(img);
	     image.paintIcon(this, g, 0, 0);
	 }
	 
	 public void showPopupMenu(Component invoker, int x, int y) {
	        // 创建 弹出菜单 对象
	        JPopupMenu popupMenu = new JPopupMenu();

	        // 创建 一级菜单
	        JMenuItem withdraw = new JMenuItem("撤销");
	        JMenuItem remake = new JMenuItem("重做");
	        

	        // 添加菜单项的点击监听器
	        withdraw.addActionListener(new ActionListener() {
	            @Override
	            public void actionPerformed(ActionEvent e) {
	            	//System.out.println("撤销");
	            	Image_my x = imageList.get(ordinal);
//	            	x = x.backup[1];
	            	x.shapes.copy(x.shapesBackup[2]);
	            	//System.out.println(x.backup[2].shapes.shapeList.size());
	            	x.shapesBackup[2].copy(x.shapesBackup[1]);
	            	x.words.copy(x.wordsBackup[2]);
	            	x.wordsBackup[2].copy(x.wordsBackup[1]);
//	            	imageList.remove(ordinal);
//	            	imageList.add(ordinal,x);
	            	refreshPage();
	            }
	        });
	        remake.addActionListener(new ActionListener() {
	            @Override
	            public void actionPerformed(ActionEvent e) {
	            	imageList.remove(ordinal);
	            	imageList.add(ordinal,new Image_my(frame));
	            	refreshPage();
//	            	Image_my p = imageList.get(ordinal);
//	            	p = new Image_my(frame);
	            }
	        });
	        
	        popupMenu.add(withdraw);
	        popupMenu.add(remake);
	      

	        // 在指定位置显示弹出菜单
	        popupMenu.show(invoker, x, y);
	    }
	 
	//保存所有Image数据到文件txt
	 public void savetxt1() throws IOException{
		 FileOutputStream fos=new FileOutputStream(new File(imageName));
	     OutputStreamWriter osw=new OutputStreamWriter(fos, "UTF-8");
	     BufferedWriter  bw=new BufferedWriter(osw);
	     for(int j=0;j<imageList.size();j++){
	    	 Image_my p =imageList.get(j);
	        	for(int k=0;k<p.shapes.shapeList.size();k++)
	        	{
	        		Shape s=p.shapes.shapeList.get(k);
	        		bw.write(s.type + "/" + s.x + "/" + s.y +"/" + s.x1 
	        				+ "/" + s.y1 + "/" + s.color + "/" + s.fillColor 
	        				+ "/" + s.lineWidth + "\n");
	        	}
	        	for(int k = 0;k < p.words.wordList.size();k++)
	        	{
	        		Word w=p.words.wordList.get(k);
	        		bw.write(w.type + "/" + w.x + "/" + w.y + "/" + w.x1 
	        				+ "/" + w.y1 + "/" + w.color + "/" + w.fillColor
	        				+ "/" + w.familyName + "/" + w.size + "/" + w.style 
	        				+ "/" + w.content + "\n");
	        	
	        	}
	        	bw.write("."+"\n");
	        }
	        bw.close();
	        osw.close();
	        fos.close();	 
				
	}	
		public void opentxt()throws IOException
		{
			int temp = 0;//image页计数
			FileInputStream fis=new FileInputStream(imageName);
		    InputStreamReader isr=new InputStreamReader(fis, "UTF-8");
		    BufferedReader br = new BufferedReader(isr);
		    String line="";
		    Image_my image = new Image_my(this.frame);
		    this.imageList.add(image);
		   	while ((line=br.readLine())!=null) //处理分页
			 {
		   		if(line.equals("."))
		   		{
		   		    //this.nowP.print(getGraphics());
		   			Image_my image1 = new Image_my(this.frame);
		   			this.imageList.add(image1);
		   		    temp++; 
		   		}
		   		else
		   		{
		   		    String[] arrs = new String[11];
		   		    //arrs[]=" ";
	        	    arrs = line.split("/");
				    String type = arrs[0];
				    if(type.equals("word"))//处理文字
				    {
//				    	int r=0,g=0,b=0;//获取颜色的数值
				    	int x = Integer.parseInt(arrs[1]);
		        	    int y = Integer.parseInt(arrs[2]);
		        	    int x1 = Integer.parseInt(arrs[3]);
		        	    int y1 = Integer.parseInt(arrs[4]);
					    String color1 = arrs[5];
					    int r1 = getcolorInt1(color1);
					    int g1 = getcolorInt2(color1);
					    int b1 = getcolorInt3(color1);
					    Color color = new Color(r1,g1,b1);
					    String color2 = arrs[6];
					    int r2 = getcolorInt1(color2);
					    int g2 = getcolorInt2(color2);
					    int b2 = getcolorInt3(color2);
					    Color fillColor = new Color(r2,g2,b2);
//					    w.familyName+" "
//                     +w.size+" "+w.style+" "+w.content
					    String familyname = arrs[7];
					    int size = Integer.parseInt(arrs[8]);
					    int style = Integer.parseInt(arrs[9]);
					    String content = arrs[10];
					    
					    JTextField text = new JTextField(content);
						Font font = new Font(familyname,style,size);
						text.setFont(font);
						Word w = new Word(text,familyname,style,size,color,fillColor,content,x,y,x1,y1);
						//w.show(thumbnails);
//						this.wordList.add(w);
						this.imageList.get(temp).words.wordList.add(w);
				    }
				    else//处理图形
				    {
//				    	int r=0,g=0,b=0;//获取颜色的数值
				    	int x = Integer.parseInt(arrs[1]);
		        	    int y = Integer.parseInt(arrs[2]);
		        	    int x1 = Integer.parseInt(arrs[3]);
		        	    int y1 = Integer.parseInt(arrs[4]);
					    String color1 = arrs[5];
					    int r = getcolorInt1(color1);
					    int g = getcolorInt2(color1);
					    int b = getcolorInt3(color1);
					    Color color = new Color(r,g,b);
					    String color2 = arrs[6];
					    int r2 = getcolorInt1(color2);
					    int g2 = getcolorInt2(color2);
					    int b2 = getcolorInt3(color2);
					    Color fillColor = new Color(r2,g2,b2);
					    Float lineWidth = Float.parseFloat(arrs[7]);
					    //System.out.println(color);
					    Shape s = new Shape(x,y,x1,y1,type,color,fillColor,lineWidth);
					    this.imageList.get(temp).shapes.shapeList.add(s);
				    }
		   		}		   		
			 }
		   	this.imageList.remove(this.imageList.size() - 1);//删去最后一个多余的空白页
		   	for(int i = 0; i < temp;i++) {
		   		//打开缩略图
		   		String fileName = f + imageName0 + "\\" + "saveImage" + i + ".jpg";
		   		this.imgList.add(fileName);
		   		//System.out.println(fileName);
		   	}
		   	for(int j = 0;j < this.imgList.size();j++) {
		   		System.out.println(this.imgList.get(j));
		   	}
		   	this.refreshPage();
	   	     br.close();
	         isr.close();
             fis.close();
		}
		
		public int getcolorInt1(String c)
		{
			String c1=c.substring(0,c.length()-1);
			String[] c2=null;
 	        c2=c1.split("=|,");
// 	        java.awt.Color[r = 0 , g = 0 , b = 0
// 	        System.out.println(c2[0]+" "+c2[1]+" "+c2[2]+" "+c2[3]+" "+c2[4]);
 	        int r=Integer.parseInt(c2[1]);
 	        return r;
 	    
		}
		public int getcolorInt2(String c)
		{
			String c1=c.substring(0,c.length()-1);
			String[] c2=null;
 	        c2=c1.split("=|,");
// 	        java.awt.Color[r = 0 , g = 0 , b = 0
// 	        System.out.println(c2[0]+" "+c2[1]+" "+c2[2]+" "+c2[3]+" "+c2[4]);
 	        int g=Integer.parseInt(c2[3]);
 	        return g;
 	    
	     	}
		public int getcolorInt3(String c)
		{
			String c1=c.substring(0,c.length()-1);
			String[] c2=null;
 	        c2=c1.split("=|,");
// 	        java.awt.Color[r = 0 , g = 0 , b = 0
// 	        System.out.println(c2[0]+" "+c2[1]+" "+c2[2]+" "+c2[3]+" "+c2[4]);
 	        int b=Integer.parseInt(c2[5]);
 	        return b;
		}

}

