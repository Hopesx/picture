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
	
	static int count1;//第几次点击全屏播放
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
    static String f;//获得图片的固定路径
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
    public static void getImageName1(String f1)//获得图片的固定路径
    {
    	f=f1;
    }
    private SimulationImage(){
        super("全屏放映");
        this.draw();
    }
    /**
     * 获取单例类 
     * 用于TimerTask执行时调用同一对象
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
     * 画图方法 将GUI画出来
     * 因为练习图片滚动和JFileChooser
     * 所以GUI比较难看
     */
    public void draw(){
        con=this.getContentPane();
        con.setLayout(new FlowLayout());
        showPic=new JButton("请选择目录");
        con.add(showPic);
        showPic.addActionListener(this);
        picIcon=new JLabel("请选择目录展示图片");
        con.add(picIcon);
        previousPic=new JButton("上一张");
        con.add(previousPic);
        previousPic.addActionListener(this);
        nextPic=new JButton("下一张");
        con.add(nextPic);
        nextPic.addActionListener(this);
        beginPlayPic=new JButton("开始自动播放");
        stopPlayPic=new  JButton("结束播放");
        con.add(beginPlayPic);
        con.add(stopPlayPic);
        beginPlayPic.addActionListener(this);
        stopPlayPic.addActionListener(this);
        this.setLocation(550, 200);
        this.setSize(900,700);
        this.setVisible(true);

    }
    /**
     * 执行自动播放效果 
     * 注意使用的单例类
     * 暂停的话设置单例类静态变量
     * play为false
     */
    public void automatic(){

        TimerTask task = new TimerTask() {  
            
            int count0=-1;//用来停止自动播放
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
        //intevalPeriod为秒数
        long intevalPeriod = 2 * 1000;  
        timer.scheduleAtFixedRate(task, delay, intevalPeriod);
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource().equals(showPic)){
            //设置D盘为默认打开路径
            i=count2;//每次打开都将i重置 方便播放文件
            fileChooser=new JFileChooser(new File(f+pptName));
            /*
             * 设置可以选择文件夹，默认为只允许选择文件
             * 
             *  DIRECTORIES_ONLY 指示仅显示目录。
             *  FILES_AND_DIRECTORIES 指示显示文件和目录
             *  FILES_ONLY 指示仅显示文件。(默认)
             * 
             */
            fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
            //把JFileChooser释放
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
                    picIcon.setText("该目录没有图片哦");
                    picIcon.setIcon(null);
                    setBottonEnabled(false);
                }
                break;
            case JFileChooser.CANCEL_OPTION :
                picIcon.setText("你取消的选择文件");
                picIcon.setIcon(null);
                setBottonEnabled(false);
                break;
            default:
                picIcon.setText("由于错误操作导致意外发生");
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
    //设置按钮不可用
    private void setBottonEnabled(boolean available){
        nextPic.setEnabled(available);
        previousPic.setEnabled(available);
        beginPlayPic.setEnabled(available);
        stopPlayPic.setEnabled(available);
    }
    //判断所选路径是否有图片
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
