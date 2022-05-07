package Images;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.IOException;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;


public class Interface extends JFrame implements ActionListener{
	
	String f="jpg";//固定路径

	FileDialog op,sv;
	Button btn2,btn3;
	JMenuItem open,create,save;//打开文件、新建文件、保存文件
	JMenuItem createP,deleteP,chooseColor,fillColors;//新建ppt页、删除ppt页、颜色、填充颜色
	JMenuItem rect,round,oval,line,word;//矩形、圆形、椭圆形、直线、文字
	JMenuItem playBackHead,playBackThis;//全屏播放，从当前页播放
	JMenuItem songTi,fangSong,youYuan,weiRuan,huPo;//字体类型:宋体，仿宋，幼圆，微软雅黑，华文琥珀
	JButton eraser;//橡皮擦
	JRadioButton plain,bold,italic,boldItalic;//字体风格:普通，加粗，斜体，粗斜体
	JSlider fontSize,lineWidth;//字体大小、线宽
	ButtonGroup fontStyle;//字体风格
	Images workbench;//工作台
	Color selectColor,fillColor;//选择的颜色
	JPopupMenu withdraw;//撤销，最多只能回撤三步
	static int count1=0;//判断第几次进行全屏播放
	static int count2=-1;
	String pptName;//保存的文件名
	String pptName0;//打开的文件名
	String pptName1,pptName2;
	Interface(){//构造函数
		super("Drawing");
		setLayout(new BorderLayout ());
		//setBackground(Color.red);
		setSize(1100,700);
		
		JMenuBar menuBar = new JMenuBar();
		
		//菜单分隔栏
		JSeparator sep0 = new JSeparator();
	    sep0.setForeground(new Color(172,182,194));
	    sep0.setBackground(new Color(172,182,194));
		menuBar.add(sep0);
		
		/****文件*****/
	    JMenu file = new JMenu("文件(F)");
	    file.setMnemonic('F');// 设置助记符为F，按下ALT + F 可以触发该菜单
	    file.setBackground(new Color(172,182,194));
	    file.setForeground(new Color(218,219,221));
	    open = new JMenuItem("打开");
	    create = new JMenuItem("新建文件");
	    save = new JMenuItem("保存");
	    file.add(open);
	    file.addSeparator();// 设置菜单分隔符
	    file.add(create);
	    file.addSeparator();// 设置菜单分隔符
	    file.add(save);
	    menuBar.add(file);
	    /****文件*****/
	    
	    /****开始*****/
	    JMenu start = new JMenu("开始(T)");
	    start.setMnemonic('T');// 设置助记符为F，按下ALT + F 可以触发该菜单
	    start.setForeground(new Color(218,219,221));
	    createP = new JMenuItem("新建页面");//新建幻灯片页面
	    deleteP = new JMenuItem("删除页面");//删除幻灯片页面
	    start.add(createP);
	    start.addSeparator();// 设置菜单分隔符
	    start.add(deleteP);
	    //start.add(font);
	    menuBar.add(start);
	    /****开始*****/
	    
	    /****插入*****/
	    JMenu insert = new JMenu("插入(S)");
	    insert.setMnemonic('S');// 设置助记符为S，按下ALT + S 可以触发该菜单
	    insert.setForeground(new Color(218,219,221));
	    JMenu shape = new JMenu("形状");
	    rect = new JMenuItem("矩形");
	    round = new JMenuItem("圆形");
	    oval = new JMenuItem("椭圆形");
	    word = new JMenuItem("文字");//
	    line = new JMenuItem("线");
	    shape.add(rect);
	    shape.add(round);
	    shape.add(oval);
	    shape.add(line);
	    insert.add(shape);
	    insert.addSeparator();// 设置菜单分隔符
	    insert.add(word);
	    menuBar.add(insert);
	    /****插入*****/
	    
	    /****颜色****/
	    JMenu colors = new JMenu("颜色(Y)");
	    colors.setMnemonic('Y');// 设置助记符为Y，按下ALT + Y 可以触发该菜单
	    colors.setForeground(new Color(218,219,221));
	    chooseColor = new JMenuItem("颜色");
	    fillColors = new JMenuItem("填充颜色");
	    colors.add(chooseColor);
	    colors.addSeparator();// 设置菜单分隔符
	    colors.add(fillColors);
	    menuBar.add(colors);
	    /****颜色****/
	    
	    /****播放****/ 
	    JMenu play = new JMenu("播放(P)");
	    play.setMnemonic('P');// 设置助记符为P，按下ALT + P 可以触发该菜单
	    play.setForeground(new Color(218,219,221));
	    playBackHead = new JMenuItem("从头播放");
//	    playBackThis = new JMenuItem("从当前页播放");
	    play.add(playBackHead);
//	    play.add(playBackThis);
	    menuBar.add(play);
	    /****播放****/
	    
	    /****字体****/
	    JMenu fonts = new JMenu("字体(W)");
	    fonts.setMnemonic('W');// 设置助记符为W，按下ALT + W 可以触发该菜单
	    fonts.setForeground(new Color(218,219,221));
	    
	    fontSize = new JSlider(10,50);
	    fontSize.setSize(10,menuBar.getHeight());
	    fontSize.setMajorTickSpacing(5);//主间隔度
	    fontSize.setMinorTickSpacing(1);
	    fontSize.setPaintLabels(true);
	    fontSize.setPaintTicks(true);
	    JMenu fontType = new JMenu("字体类型");
	    fontStyle = new ButtonGroup();
	    songTi = new JMenuItem("宋体");
	    fangSong = new JMenuItem("仿宋");
	    youYuan = new JMenuItem("幼圆");
	    weiRuan = new JMenuItem("微软雅黑");
	    huPo = new JMenuItem("华文琥珀");
	    plain = new JRadioButton("普通",true);
	    bold = new JRadioButton("加粗");
	    italic = new JRadioButton("斜体");
	    boldItalic = new JRadioButton("粗斜体");
	    fontType.add(songTi);
	    fontType.addSeparator();// 设置菜单分隔符
	    fontType.add(fangSong);
	    fontType.addSeparator();// 设置菜单分隔符
	    fontType.add(youYuan);
	    fontType.addSeparator();// 设置菜单分隔符
	    fontType.add(weiRuan);
	    fontType.addSeparator();// 设置菜单分隔符
	    fontType.add(huPo);
	    fontStyle.add(plain);
	    fontStyle.add(bold);
	    fontStyle.add(italic);
	    fontStyle.add(boldItalic);
	    plain.setForeground(new Color(229,229,224));
	    plain.setBackground(new Color(79,82,97));
	    bold.setForeground(new Color(229,229,224));
	    bold.setBackground(new Color(79,82,97));
	    italic.setForeground(new Color(229,229,224));
	    italic.setBackground(new Color(79,82,97));
	    boldItalic.setForeground(new Color(229,229,224));
	    boldItalic.setBackground(new Color(79,82,97));
	  
	    fonts.add(fontType);
	    menuBar.add(fonts);
	    JSeparator sep1 = new JSeparator(JSeparator.VERTICAL);
	    sep1.setForeground(new Color(172,182,194));
	    sep1.setBackground(new Color(172,182,194));
	    menuBar.add(sep1);
	    JLabel label0 = new JLabel("字体风格");
	    label0.setForeground(new Color(235,203,174));
	    menuBar.add(label0);
	    menuBar.add(plain);
	    menuBar.add(bold);
	    menuBar.add(italic);
	    menuBar.add(boldItalic);
	    JSeparator sep2 = new JSeparator(JSeparator.VERTICAL);
	    sep2.setForeground(new Color(172,182,194));
	    sep2.setBackground(new Color(172,182,194));
	    menuBar.add(sep2);
	    JLabel label1 = new JLabel("字体大小");
	    label1.setForeground(new Color(235,203,174));
	    menuBar.add(label1);
	    fontSize.setForeground(new Color(235,203,174));
	    fontSize.setBackground(new Color(79,82,97));
	    menuBar.add(fontSize);
	    /****字体****/

	    /****线宽****/
	    lineWidth = new JSlider(0,10);
	    lineWidth.setSize(10,menuBar.getHeight());
	    lineWidth.setMajorTickSpacing(5);//主间隔度
	    lineWidth.setMinorTickSpacing(1);
	    lineWidth.setPaintLabels(true);
	    lineWidth.setPaintTicks(true);
	    lineWidth.setForeground(new Color(235,203,174));
	    lineWidth.setBackground(new Color(79,82,97));
	    JLabel label2 = new JLabel("线宽");
	    label2.setForeground(new Color(235,203,174));
	    JSeparator sep4 = new JSeparator(JSeparator.VERTICAL);
	    sep4.setForeground(new Color(172,182,194));
	    sep4.setBackground(new Color(172,182,194));
	    menuBar.add(sep4);
	    menuBar.add(label2);
	    menuBar.add(lineWidth);
	    /****线宽****/
	    
	    /****橡皮擦***/
	    JSeparator sep5 = new JSeparator(JSeparator.VERTICAL);
	    sep5.setForeground(new Color(172,182,194));
	    sep5.setBackground(new Color(172,182,194));
	    menuBar.add(sep5);
	    ImageIcon image = new ImageIcon("picture/eraser.jpg");
	    Image img = image.getImage();
		img = img.getScaledInstance(20,20, Image.SCALE_DEFAULT);
		image.setImage(img);
	    eraser = new JButton("橡皮擦",image);
	    eraser.setForeground(new Color(218,219,221));
	    eraser.setBackground(new Color(79,82,97));
	    menuBar.add(eraser);
	    /****橡皮擦***/
	    
	    JSeparator sep3 = new JSeparator();
	    sep3.setForeground(new Color(172,182,194));
	    sep3.setBackground(new Color(172,182,194));
		menuBar.add(sep3);
		menuBar.setBackground(new Color(79,82,97));
	    setJMenuBar(menuBar);// 设置菜单栏，使用这种方式设置菜单栏可以不占用布局空
	    op=new FileDialog(this,"打开",FileDialog.LOAD);
		sv=new FileDialog(this,"保存",FileDialog.SAVE);
	    
		save.addActionListener(this);
		open.addActionListener(this);
		
		/*****Images工作区****/
		workbench = new Images(this);
		//workbench.setPreferredSize(new Dimension(30, 15));
		//workbench.setBackground("Drawing\\picture.jpg");
		add(workbench);
		workbench.setBounds(0,0,1300,700);//重定位工作区
		
		/*****Images工作区****/

		addWindowListener(
				new WindowAdapter(){
					public void windowClosing(WindowEvent e){
						setVisible(false);
						System.exit(0);
					}
				}
		);
		//颜色 监听器
		chooseColor.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
            	Color color = JColorChooser.showDialog(null, "请选择边框颜色", selectColor);
    			selectColor = color;		//默认颜色设置成选择的颜色
    			//讲选择的颜色返回到按钮"选择颜色"的背景上
    			chooseColor.setBackground(selectColor);
    			workbench.imageList.get(workbench.ordinal).selectColor = selectColor;
            }
        });
		//填充颜色监听器
		fillColors.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
            	Color color = JColorChooser.showDialog(null, "请选择填充颜色", selectColor);
    			fillColor = color;		//默认颜色设置成选择的颜色
    			//讲选择的颜色返回到按钮"选择颜色"的背景上
    			fillColors.setBackground(fillColor);
    			workbench.imageList.get(workbench.ordinal).fillColor = fillColor;
            }
        });
       
		//直线
	    line.addActionListener(new ActionListener() {
	    	@Override
			public void actionPerformed(ActionEvent e) {
				//改变绘图区的type变量的值为"line"
	    		workbench.imageList.get(workbench.ordinal).type = "line";
	    		workbench.imageList.get(workbench.ordinal).operation = 0;
			}
			
		});
	  //矩形
		rect.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				//改变绘图区的type变量的值为"rect"
				workbench.imageList.get(workbench.ordinal).type = "rect";
				workbench.imageList.get(workbench.ordinal).operation = 0;
			}
			
		});
		
		//圆
		round.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				//改变绘图区的type变量的值为"round"
				workbench.imageList.get(workbench.ordinal).type = "round";
				workbench.imageList.get(workbench.ordinal).operation = 0;
			}
			
		});
		
		//椭圆
		oval.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				//改变绘图区的type变量的值为"oval"
				workbench.imageList.get(workbench.ordinal).type = "oval";
				workbench.imageList.get(workbench.ordinal).operation = 0;
			}
			
		});
		
		//文字
		word.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				//改变绘图区的type变量的值为"word"
				workbench.imageList.get(workbench.ordinal).type = "word";
				workbench.imageList.get(workbench.ordinal).operation = 0;
			}
		});		
		
		//新建页面
		createP.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				//新增Image页面
				workbench.addImage();
				//System.out.println(workbench.ordinal);
			}
			
		});		
		
		//删除页面
		deleteP.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				//删除Image页面
				workbench.deleteImage();
			}
		});	
		
		//字体监听plain,bold,italic,boldItalic
		//字体粗体
		bold.addItemListener(new ItemListener() {

			@Override
			public void itemStateChanged(ItemEvent e) {
				// TODO Auto-generated method stub
				if(bold.isSelected()) {
					workbench.imageList.get(workbench.ordinal).style = Font.BOLD;
				}
			}
			
		});

		//字体斜体
		italic.addItemListener(new ItemListener() {

			@Override
			public void itemStateChanged(ItemEvent e) {
				// TODO Auto-generated method stub
				if(italic.isSelected()) {
					workbench.imageList.get(workbench.ordinal).style = Font.ITALIC;
				}
			}
			
		});
		
		//字体粗斜体
		boldItalic.addItemListener(new ItemListener() {

			@Override
			public void itemStateChanged(ItemEvent e) {
				// TODO Auto-generated method stub
				if(boldItalic.isSelected()) {
					workbench.imageList.get(workbench.ordinal).style = Font.BOLD + Font.ITALIC;
				}
			}
			
		});
		
		//字体普通
		plain.addItemListener(new ItemListener() {

			@Override
			public void itemStateChanged(ItemEvent e) {
				// TODO Auto-generated method stub
				if(plain.isSelected()) {
					workbench.imageList.get(workbench.ordinal).style = Font.PLAIN;
				}
			}
			
		});
		
		//字体类型 songTi,fangSong,youYuan,weiRuan,huPo
		
		songTi.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				//将字体改为宋体
				workbench.imageList.get(workbench.ordinal).familyName = "宋体";
			}
		});	
		
		fangSong.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				//将字体改为仿宋
				workbench.imageList.get(workbench.ordinal).familyName = "仿宋";
			}
		});	
		
		youYuan.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				//将字体改为幼圆
				workbench.imageList.get(workbench.ordinal).familyName = "幼圆";
			}
		});	
		
		weiRuan.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				//将字体改为微软雅黑
				workbench.imageList.get(workbench.ordinal).familyName = "微软雅黑";
			}
		});	
		
		huPo.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				//将字体改为华文琥珀
				workbench.imageList.get(workbench.ordinal).familyName = "华文琥珀";
			}
		});	
		
		
		//字体大小
		fontSize.addChangeListener(new ChangeListener() {
			 public void stateChanged(ChangeEvent evt) {
				 workbench.imageList.get(workbench.ordinal).size = fontSize.getValue();     
		     }
		});
		
		//线宽
		lineWidth.addChangeListener(new ChangeListener() {
			 public void stateChanged(ChangeEvent evt) {
				 workbench.imageList.get(workbench.ordinal).lineWidth = lineWidth.getValue();     
		     }
		});
		
		//打开
		open.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//workbench.imageList.removeAll(workbench.imageList);//清空
				workbench.getImageName1(f);
				op.setVisible(true);
				File f1=new File(op.getDirectory(),op.getFile());
				pptName=op.getDirectory()+op.getFile();
				pptName1=op.getDirectory();
				pptName2=op.getFile();
				pptName0=pptName2.substring(0,pptName2.length()-4);
				workbench.getImageName(pptName);
				workbench.getImageName0(pptName0);
				try {
					workbench.opentxt();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		
		
		//新建文件
		create.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				//workbench.imageList.removeAll(workbench.imageList);//清空
	        	sv.setVisible(true);
	        	try{
	        		workbench.getImageName1(f);
					File f1=new File(sv.getDirectory()+sv.getFile());
					pptName1=sv.getDirectory();
					pptName2=sv.getFile();
					pptName0=pptName2.substring(0,pptName2.length()-4);
					for(int i=0;i<100;i++)
					{
						File file=new File(f+pptName0);
						if(!file.exists()&&!file.isDirectory())
						{
							file.mkdirs();
							break;
						}
					}
					pptName=pptName1+pptName2;
					workbench.getImageName(pptName);
					workbench.getImageName0(pptName0);
//					workbench.savetxt1();
				}
				catch (Exception e2)
				{
					e2.printStackTrace();
				}
			}
			
		});	
		
		//保存文件
		save.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					workbench.getImageName(pptName);
					workbench.savetxt1();
					workbench.addImage();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
			
		});		
		
		//从头播放
		playBackHead.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				//从头播放
				count1=count1+1;
				count2=-1;
				bigwork.SimulationImage.getImageName1(f);
				bigwork.SimulationImage.getCount1(count1);
				bigwork.SimulationImage.getCount2(count2);
				bigwork.SimulationImage.getImageName(pptName0);
				bigwork.SimulationImage.getInstance();
			}
		});			
			
		//橡皮擦
		eraser.addChangeListener(new ChangeListener() {
			 public void stateChanged(ChangeEvent evt) {
				 workbench.imageList.get(workbench.ordinal).operation = 5;
				 //System.out.println( "操作 " + workbench.imageList.get(workbench.ordinal).operation);     
		     }
		});
		
		setLayout(null);
		setVisible(true);
	}

	public static void main(String args[]) {
		Interface a = new Interface();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}

}
