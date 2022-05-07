package Images;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.IOException;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;


public class Interface extends JFrame implements ActionListener{
	
	String f="jpg";//�̶�·��

	FileDialog op,sv;
	Button btn2,btn3;
	JMenuItem open,create,save;//���ļ����½��ļ��������ļ�
	JMenuItem createP,deleteP,chooseColor,fillColors;//�½�pptҳ��ɾ��pptҳ����ɫ�������ɫ
	JMenuItem rect,round,oval,line,word;//���Ρ�Բ�Ρ���Բ�Ρ�ֱ�ߡ�����
	JMenuItem playBackHead,playBackThis;//ȫ�����ţ��ӵ�ǰҳ����
	JMenuItem songTi,fangSong,youYuan,weiRuan,huPo;//��������:���壬���Σ���Բ��΢���źڣ���������
	JButton eraser;//��Ƥ��
	JRadioButton plain,bold,italic,boldItalic;//������:��ͨ���Ӵ֣�б�壬��б��
	JSlider fontSize,lineWidth;//�����С���߿�
	ButtonGroup fontStyle;//������
	Images workbench;//����̨
	Color selectColor,fillColor;//ѡ�����ɫ
	JPopupMenu withdraw;//���������ֻ�ܻس�����
	static int count1=0;//�жϵڼ��ν���ȫ������
	static int count2=-1;
	String pptName;//������ļ���
	String pptName0;//�򿪵��ļ���
	String pptName1,pptName2;
	Interface(){//���캯��
		super("Drawing");
		setLayout(new BorderLayout ());
		//setBackground(Color.red);
		setSize(1100,700);
		
		JMenuBar menuBar = new JMenuBar();
		
		//�˵��ָ���
		JSeparator sep0 = new JSeparator();
	    sep0.setForeground(new Color(172,182,194));
	    sep0.setBackground(new Color(172,182,194));
		menuBar.add(sep0);
		
		/****�ļ�*****/
	    JMenu file = new JMenu("�ļ�(F)");
	    file.setMnemonic('F');// �������Ƿ�ΪF������ALT + F ���Դ����ò˵�
	    file.setBackground(new Color(172,182,194));
	    file.setForeground(new Color(218,219,221));
	    open = new JMenuItem("��");
	    create = new JMenuItem("�½��ļ�");
	    save = new JMenuItem("����");
	    file.add(open);
	    file.addSeparator();// ���ò˵��ָ���
	    file.add(create);
	    file.addSeparator();// ���ò˵��ָ���
	    file.add(save);
	    menuBar.add(file);
	    /****�ļ�*****/
	    
	    /****��ʼ*****/
	    JMenu start = new JMenu("��ʼ(T)");
	    start.setMnemonic('T');// �������Ƿ�ΪF������ALT + F ���Դ����ò˵�
	    start.setForeground(new Color(218,219,221));
	    createP = new JMenuItem("�½�ҳ��");//�½��õ�Ƭҳ��
	    deleteP = new JMenuItem("ɾ��ҳ��");//ɾ���õ�Ƭҳ��
	    start.add(createP);
	    start.addSeparator();// ���ò˵��ָ���
	    start.add(deleteP);
	    //start.add(font);
	    menuBar.add(start);
	    /****��ʼ*****/
	    
	    /****����*****/
	    JMenu insert = new JMenu("����(S)");
	    insert.setMnemonic('S');// �������Ƿ�ΪS������ALT + S ���Դ����ò˵�
	    insert.setForeground(new Color(218,219,221));
	    JMenu shape = new JMenu("��״");
	    rect = new JMenuItem("����");
	    round = new JMenuItem("Բ��");
	    oval = new JMenuItem("��Բ��");
	    word = new JMenuItem("����");//
	    line = new JMenuItem("��");
	    shape.add(rect);
	    shape.add(round);
	    shape.add(oval);
	    shape.add(line);
	    insert.add(shape);
	    insert.addSeparator();// ���ò˵��ָ���
	    insert.add(word);
	    menuBar.add(insert);
	    /****����*****/
	    
	    /****��ɫ****/
	    JMenu colors = new JMenu("��ɫ(Y)");
	    colors.setMnemonic('Y');// �������Ƿ�ΪY������ALT + Y ���Դ����ò˵�
	    colors.setForeground(new Color(218,219,221));
	    chooseColor = new JMenuItem("��ɫ");
	    fillColors = new JMenuItem("�����ɫ");
	    colors.add(chooseColor);
	    colors.addSeparator();// ���ò˵��ָ���
	    colors.add(fillColors);
	    menuBar.add(colors);
	    /****��ɫ****/
	    
	    /****����****/ 
	    JMenu play = new JMenu("����(P)");
	    play.setMnemonic('P');// �������Ƿ�ΪP������ALT + P ���Դ����ò˵�
	    play.setForeground(new Color(218,219,221));
	    playBackHead = new JMenuItem("��ͷ����");
//	    playBackThis = new JMenuItem("�ӵ�ǰҳ����");
	    play.add(playBackHead);
//	    play.add(playBackThis);
	    menuBar.add(play);
	    /****����****/
	    
	    /****����****/
	    JMenu fonts = new JMenu("����(W)");
	    fonts.setMnemonic('W');// �������Ƿ�ΪW������ALT + W ���Դ����ò˵�
	    fonts.setForeground(new Color(218,219,221));
	    
	    fontSize = new JSlider(10,50);
	    fontSize.setSize(10,menuBar.getHeight());
	    fontSize.setMajorTickSpacing(5);//�������
	    fontSize.setMinorTickSpacing(1);
	    fontSize.setPaintLabels(true);
	    fontSize.setPaintTicks(true);
	    JMenu fontType = new JMenu("��������");
	    fontStyle = new ButtonGroup();
	    songTi = new JMenuItem("����");
	    fangSong = new JMenuItem("����");
	    youYuan = new JMenuItem("��Բ");
	    weiRuan = new JMenuItem("΢���ź�");
	    huPo = new JMenuItem("��������");
	    plain = new JRadioButton("��ͨ",true);
	    bold = new JRadioButton("�Ӵ�");
	    italic = new JRadioButton("б��");
	    boldItalic = new JRadioButton("��б��");
	    fontType.add(songTi);
	    fontType.addSeparator();// ���ò˵��ָ���
	    fontType.add(fangSong);
	    fontType.addSeparator();// ���ò˵��ָ���
	    fontType.add(youYuan);
	    fontType.addSeparator();// ���ò˵��ָ���
	    fontType.add(weiRuan);
	    fontType.addSeparator();// ���ò˵��ָ���
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
	    JLabel label0 = new JLabel("������");
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
	    JLabel label1 = new JLabel("�����С");
	    label1.setForeground(new Color(235,203,174));
	    menuBar.add(label1);
	    fontSize.setForeground(new Color(235,203,174));
	    fontSize.setBackground(new Color(79,82,97));
	    menuBar.add(fontSize);
	    /****����****/

	    /****�߿�****/
	    lineWidth = new JSlider(0,10);
	    lineWidth.setSize(10,menuBar.getHeight());
	    lineWidth.setMajorTickSpacing(5);//�������
	    lineWidth.setMinorTickSpacing(1);
	    lineWidth.setPaintLabels(true);
	    lineWidth.setPaintTicks(true);
	    lineWidth.setForeground(new Color(235,203,174));
	    lineWidth.setBackground(new Color(79,82,97));
	    JLabel label2 = new JLabel("�߿�");
	    label2.setForeground(new Color(235,203,174));
	    JSeparator sep4 = new JSeparator(JSeparator.VERTICAL);
	    sep4.setForeground(new Color(172,182,194));
	    sep4.setBackground(new Color(172,182,194));
	    menuBar.add(sep4);
	    menuBar.add(label2);
	    menuBar.add(lineWidth);
	    /****�߿�****/
	    
	    /****��Ƥ��***/
	    JSeparator sep5 = new JSeparator(JSeparator.VERTICAL);
	    sep5.setForeground(new Color(172,182,194));
	    sep5.setBackground(new Color(172,182,194));
	    menuBar.add(sep5);
	    ImageIcon image = new ImageIcon("picture/eraser.jpg");
	    Image img = image.getImage();
		img = img.getScaledInstance(20,20, Image.SCALE_DEFAULT);
		image.setImage(img);
	    eraser = new JButton("��Ƥ��",image);
	    eraser.setForeground(new Color(218,219,221));
	    eraser.setBackground(new Color(79,82,97));
	    menuBar.add(eraser);
	    /****��Ƥ��***/
	    
	    JSeparator sep3 = new JSeparator();
	    sep3.setForeground(new Color(172,182,194));
	    sep3.setBackground(new Color(172,182,194));
		menuBar.add(sep3);
		menuBar.setBackground(new Color(79,82,97));
	    setJMenuBar(menuBar);// ���ò˵�����ʹ�����ַ�ʽ���ò˵������Բ�ռ�ò��ֿ�
	    op=new FileDialog(this,"��",FileDialog.LOAD);
		sv=new FileDialog(this,"����",FileDialog.SAVE);
	    
		save.addActionListener(this);
		open.addActionListener(this);
		
		/*****Images������****/
		workbench = new Images(this);
		//workbench.setPreferredSize(new Dimension(30, 15));
		//workbench.setBackground("Drawing\\picture.jpg");
		add(workbench);
		workbench.setBounds(0,0,1300,700);//�ض�λ������
		
		/*****Images������****/

		addWindowListener(
				new WindowAdapter(){
					public void windowClosing(WindowEvent e){
						setVisible(false);
						System.exit(0);
					}
				}
		);
		//��ɫ ������
		chooseColor.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
            	Color color = JColorChooser.showDialog(null, "��ѡ��߿���ɫ", selectColor);
    			selectColor = color;		//Ĭ����ɫ���ó�ѡ�����ɫ
    			//��ѡ�����ɫ���ص���ť"ѡ����ɫ"�ı�����
    			chooseColor.setBackground(selectColor);
    			workbench.imageList.get(workbench.ordinal).selectColor = selectColor;
            }
        });
		//�����ɫ������
		fillColors.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
            	Color color = JColorChooser.showDialog(null, "��ѡ�������ɫ", selectColor);
    			fillColor = color;		//Ĭ����ɫ���ó�ѡ�����ɫ
    			//��ѡ�����ɫ���ص���ť"ѡ����ɫ"�ı�����
    			fillColors.setBackground(fillColor);
    			workbench.imageList.get(workbench.ordinal).fillColor = fillColor;
            }
        });
       
		//ֱ��
	    line.addActionListener(new ActionListener() {
	    	@Override
			public void actionPerformed(ActionEvent e) {
				//�ı��ͼ����type������ֵΪ"line"
	    		workbench.imageList.get(workbench.ordinal).type = "line";
	    		workbench.imageList.get(workbench.ordinal).operation = 0;
			}
			
		});
	  //����
		rect.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				//�ı��ͼ����type������ֵΪ"rect"
				workbench.imageList.get(workbench.ordinal).type = "rect";
				workbench.imageList.get(workbench.ordinal).operation = 0;
			}
			
		});
		
		//Բ
		round.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				//�ı��ͼ����type������ֵΪ"round"
				workbench.imageList.get(workbench.ordinal).type = "round";
				workbench.imageList.get(workbench.ordinal).operation = 0;
			}
			
		});
		
		//��Բ
		oval.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				//�ı��ͼ����type������ֵΪ"oval"
				workbench.imageList.get(workbench.ordinal).type = "oval";
				workbench.imageList.get(workbench.ordinal).operation = 0;
			}
			
		});
		
		//����
		word.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				//�ı��ͼ����type������ֵΪ"word"
				workbench.imageList.get(workbench.ordinal).type = "word";
				workbench.imageList.get(workbench.ordinal).operation = 0;
			}
		});		
		
		//�½�ҳ��
		createP.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				//����Imageҳ��
				workbench.addImage();
				//System.out.println(workbench.ordinal);
			}
			
		});		
		
		//ɾ��ҳ��
		deleteP.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				//ɾ��Imageҳ��
				workbench.deleteImage();
			}
		});	
		
		//�������plain,bold,italic,boldItalic
		//�������
		bold.addItemListener(new ItemListener() {

			@Override
			public void itemStateChanged(ItemEvent e) {
				// TODO Auto-generated method stub
				if(bold.isSelected()) {
					workbench.imageList.get(workbench.ordinal).style = Font.BOLD;
				}
			}
			
		});

		//����б��
		italic.addItemListener(new ItemListener() {

			@Override
			public void itemStateChanged(ItemEvent e) {
				// TODO Auto-generated method stub
				if(italic.isSelected()) {
					workbench.imageList.get(workbench.ordinal).style = Font.ITALIC;
				}
			}
			
		});
		
		//�����б��
		boldItalic.addItemListener(new ItemListener() {

			@Override
			public void itemStateChanged(ItemEvent e) {
				// TODO Auto-generated method stub
				if(boldItalic.isSelected()) {
					workbench.imageList.get(workbench.ordinal).style = Font.BOLD + Font.ITALIC;
				}
			}
			
		});
		
		//������ͨ
		plain.addItemListener(new ItemListener() {

			@Override
			public void itemStateChanged(ItemEvent e) {
				// TODO Auto-generated method stub
				if(plain.isSelected()) {
					workbench.imageList.get(workbench.ordinal).style = Font.PLAIN;
				}
			}
			
		});
		
		//�������� songTi,fangSong,youYuan,weiRuan,huPo
		
		songTi.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				//�������Ϊ����
				workbench.imageList.get(workbench.ordinal).familyName = "����";
			}
		});	
		
		fangSong.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				//�������Ϊ����
				workbench.imageList.get(workbench.ordinal).familyName = "����";
			}
		});	
		
		youYuan.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				//�������Ϊ��Բ
				workbench.imageList.get(workbench.ordinal).familyName = "��Բ";
			}
		});	
		
		weiRuan.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				//�������Ϊ΢���ź�
				workbench.imageList.get(workbench.ordinal).familyName = "΢���ź�";
			}
		});	
		
		huPo.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				//�������Ϊ��������
				workbench.imageList.get(workbench.ordinal).familyName = "��������";
			}
		});	
		
		
		//�����С
		fontSize.addChangeListener(new ChangeListener() {
			 public void stateChanged(ChangeEvent evt) {
				 workbench.imageList.get(workbench.ordinal).size = fontSize.getValue();     
		     }
		});
		
		//�߿�
		lineWidth.addChangeListener(new ChangeListener() {
			 public void stateChanged(ChangeEvent evt) {
				 workbench.imageList.get(workbench.ordinal).lineWidth = lineWidth.getValue();     
		     }
		});
		
		//��
		open.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//workbench.imageList.removeAll(workbench.imageList);//���
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
		
		
		//�½��ļ�
		create.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				//workbench.imageList.removeAll(workbench.imageList);//���
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
		
		//�����ļ�
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
		
		//��ͷ����
		playBackHead.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				//��ͷ����
				count1=count1+1;
				count2=-1;
				bigwork.SimulationImage.getImageName1(f);
				bigwork.SimulationImage.getCount1(count1);
				bigwork.SimulationImage.getCount2(count2);
				bigwork.SimulationImage.getImageName(pptName0);
				bigwork.SimulationImage.getInstance();
			}
		});			
			
		//��Ƥ��
		eraser.addChangeListener(new ChangeListener() {
			 public void stateChanged(ChangeEvent evt) {
				 workbench.imageList.get(workbench.ordinal).operation = 5;
				 //System.out.println( "���� " + workbench.imageList.get(workbench.ordinal).operation);     
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
