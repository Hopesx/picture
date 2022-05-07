package Images;

import java.awt.Color;
import java.awt.Font;
import java.util.ArrayList;

import javax.swing.JTextField;

public class Words {
	public ArrayList<Word> wordList = new ArrayList<Word>();
	
	public void addWord(JTextField text,String familyName,int style,int size,Color color,Color fillColor,String content,int x,int y,int x1,int y1) {
		//添加文字
		Word w = new Word(text,familyName,style,size,color,fillColor,content,x,y,x1,y1);
		this.wordList.add(w);
	}
	
	public int isEmptyWord(int x,int y) {
		//判断当前当前坐标（x,y）是否在其他文字区域内
		//如果当前坐标不被其他文字所占用则返回当前文字在链表中下标，否则返回-1
		for(int i = 0;i < this.wordList.size();i++) {
			Word w = this.wordList.get(i);
			if(x >= w.x - 10 && x <= w.x1 +10  && y >= w.y - 10 && y <= w.y1 + 10) {
				//如果当前坐标在当前文字内部
				return i;
			}
		}
		return -1;
	}
	
	public String isVertex(int x,int y) {
		//判断当前鼠标位置是否在文本框的矩形顶点处
		//如果是则返回“文本框的下标，顶点标号” 左上角为0，左下角为1，右下角为2，右上角为4
		//否则返回“X”
		for(int i = 0;i < this.wordList.size();i++) {
			Word s = this.wordList.get(i);
			if(x >= s.x - 5 && x <= s.x   && y >= s.y - 5 && y <= s.y) {
				//如果在左上角
				return i + ",0";
			}
			else if(x >= s.x - 5 && x <= s.x  && y <= s.y1 + 10 && y >= s.y1 + 5) {
				//如果在左下角
				return i + ",1";
			}
			else if(x >= s.x1 + 5 && x <= s.x1 + 10 && y >= s.y1 + 5 && y <= s.y1 + 10) {
				//如果在右下角
				return i + ",2";
			}
			else if(x >= s.x1 + 5 && x <= s.x1 + 10 && y >= s.y - 5 && y <= s.y ) {
				//如果在右上角
				return i + ",3";
			}
		}
		return "X";
	}
	
	//JTextField text,String familyName,int style,int size,Color color,String content,int x,int y,int x1,int y1
	public void copy(Words w) {
		this.wordList.removeAll(wordList);
		for(int i = 0;i < w.wordList.size();i++) {
			Word b = w.wordList.get(i);
			Word a = new Word(b.text,b.familyName,b.style,b.size,b.color,b.fillColor,b.content,b.x,b.y,b.x1,b.y1);
			this.wordList.add(a);
		}
	}
}
