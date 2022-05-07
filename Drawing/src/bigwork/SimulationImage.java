package bigwork;
import java.awt.Container;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;

import java.util.Timer;
import java.util.TimerTask;
//import javazoom.jl.player.Player;
import java.io.File;
import java.io.FileInputStream;

public class SimulationImage extends JFrame implements ActionListener{
	
	static int count1;//�ڼ��ε��ȫ������
	static int count2;
	static String pptName;
    private  File fileDirectory;
    private JFileChooser fileChooser;
    private Container con;
    private JButton nextPic;
    private JButton previousPic;
    private JButton showPic;
    private JButton beginPlayPic;
    private JButton stopPlayPic;
    private JLabel picIcon;
    private String[] fileName;
    private String parentPath;
    private static boolean play;
    private static SimulationImage playPicture;
    private int i=-1;
    static String f;//���ͼƬ�Ĺ̶�·��
    public static void getImageName(String pptName1)
	{
		 pptName=pptName1;
	}
    public static void getCount1(int Count1)
	{
		 count1=Count1;
	}
    public static void getCount2(int Count2)
	{
		 count2=Count2;
	}
    public static void getImageName1(String f1)//���ͼƬ�Ĺ̶�·��
    {
    	f=f1;
    }
    private SimulationImage(){
        super("ȫ����ӳ");
        this.draw();
    }
    /**
     * ��ȡ������ 
     * ����TimerTaskִ��ʱ����ͬһ����
     * @return Object
     */
    public static Object getInstance(){
    	 if(count1>1)
     	{
         	playPicture.show();
     	}
    	if(playPicture==null)
            playPicture=new SimulationImage();
        return playPicture;
    }
    /**
     * ��ͼ���� ��GUI������
     * ��Ϊ��ϰͼƬ������JFileChooser
     * ����GUI�Ƚ��ѿ�
     */
    public void draw(){
        con=this.getContentPane();
        con.setLayout(new FlowLayout());
        showPic=new JButton("��ѡ��Ŀ¼");
        con.add(showPic);
        showPic.addActionListener(this);
        picIcon=new JLabel("��ѡ��Ŀ¼չʾͼƬ");
        con.add(picIcon);
        previousPic=new JButton("��һ��");
        con.add(previousPic);
        previousPic.addActionListener(this);
        nextPic=new JButton("��һ��");
        con.add(nextPic);
        nextPic.addActionListener(this);
        beginPlayPic=new JButton("��ʼ�Զ�����");
        stopPlayPic=new  JButton("��������");
        con.add(beginPlayPic);
        con.add(stopPlayPic);
        beginPlayPic.addActionListener(this);
        stopPlayPic.addActionListener(this);
        this.setLocation(550, 200);
        this.setSize(900,700);
        this.setVisible(true);

    }
    /**
     * ִ���Զ�����Ч�� 
     * ע��ʹ�õĵ�����
     * ��ͣ�Ļ����õ����ྲ̬����
     * playΪfalse
     */
    public void automatic(){

        TimerTask task = new TimerTask() {  
            
            int count0=-1;//����ֹͣ�Զ�����
            public void run() {
            	count0=count0+1;
            	if(count0>2)
            	{
            		cancel();
            	}
                ((SimulationImage) SimulationImage.getInstance()).NextPicture();
                if(!((SimulationImage)SimulationImage.getInstance()).play){
                    ((SimulationImage) SimulationImage.getInstance()).previousPicture();
                }
                
            } 
        };  
        Timer timer = new Timer();  
        long delay = 0;  
        //intevalPeriodΪ����
        long intevalPeriod = 2 * 1000;  
        timer.scheduleAtFixedRate(task, delay, intevalPeriod);
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource().equals(showPic)){
            //����D��ΪĬ�ϴ�·��
            i=count2;//ÿ�δ򿪶���i���� ���㲥���ļ�
            fileChooser=new JFileChooser(new File(f+pptName));
            /*
             * ���ÿ���ѡ���ļ��У�Ĭ��Ϊֻ����ѡ���ļ�
             * 
             *  DIRECTORIES_ONLY ָʾ����ʾĿ¼��
             *  FILES_AND_DIRECTORIES ָʾ��ʾ�ļ���Ŀ¼
             *  FILES_ONLY ָʾ����ʾ�ļ���(Ĭ��)
             * 
             */
            fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
            //��JFileChooser�ͷ�
            int returnVal =fileChooser.showOpenDialog(this);
            switch (returnVal) {
            case JFileChooser.APPROVE_OPTION:
                fileDirectory=fileChooser.getSelectedFile();

                parentPath=fileDirectory.getAbsolutePath();
                fileName=fileDirectory.list();
                if(hasPicture()){
                    this.NextPicture();
                    setBottonEnabled(true);
                }else{
                    picIcon.setText("��Ŀ¼û��ͼƬŶ");
                    picIcon.setIcon(null);
                    setBottonEnabled(false);
                }
                break;
            case JFileChooser.CANCEL_OPTION :
                picIcon.setText("��ȡ����ѡ���ļ�");
                picIcon.setIcon(null);
                setBottonEnabled(false);
                break;
            default:
                picIcon.setText("���ڴ�������������ⷢ��");
                picIcon.setIcon(null);
                setBottonEnabled(false);
                break;
            
            }
        }else if(e.getSource().equals(nextPic)){
            this.NextPicture();
        }else if(e.getSource().equals(previousPic)){
            this.previousPicture();
        }else if(e.getSource().equals(beginPlayPic)){
            this.automatic();
            play=true;
        }else if(e.getSource().equals(stopPlayPic)){
            play=false;
            dispose();
        }

    }
    //���ð�ť������
    private void setBottonEnabled(boolean available){
        nextPic.setEnabled(available);
        previousPic.setEnabled(available);
        beginPlayPic.setEnabled(available);
        stopPlayPic.setEnabled(available);
    }
    //�ж���ѡ·���Ƿ���ͼƬ
    private boolean hasPicture(){
        for(String s:fileName){
            if(s.matches("(?i).*(.jpg|.png|.gif|.bpm|.jpeg)$"))
                return true;
        }
        return false;
    }
    private void previousPicture(){
        if(i==0){
            i=fileName.length-1;

        }
        while(!fileName[--i].matches("(?i).*(.jpg|.png|.gif|.bpm|.jpeg)$")){
            if(i==-1){
                i=fileName.length;

            }
        }
        System.out.println(i);
        picIcon.setIcon(new ImageIcon(parentPath+"\\"+fileName[i]));
        picIcon.setText("");
    }
    private void NextPicture() {
        if(i==fileName.length-1){
        	dispose();
        	i=-1;
        }
        while(!fileName[++i].matches("(?i).*(.jpg|.png|.gif|.bpm|.jpeg)$")){
            if(i==fileName.length-1){
                dispose();
                i=-1;
            }
        }
        System.out.println(i);
        picIcon.setIcon(new ImageIcon(parentPath+"\\"+fileName[i]));
        picIcon.setText("");
    }
}
