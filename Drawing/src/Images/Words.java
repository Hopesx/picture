package Images;

import java.awt.Color;
import java.awt.Font;
import java.util.ArrayList;

import javax.swing.JTextField;

public class Words {
	public ArrayList<Word> wordList = new ArrayList<Word>();
	
	public void addWord(JTextField text,String familyName,int style,int size,Color color,Color fillColor,String content,int x,int y,int x1,int y1) {
		//�������
		Word w = new Word(text,familyName,style,size,color,fillColor,content,x,y,x1,y1);
		this.wordList.add(w);
	}
	
	public int isEmptyWord(int x,int y) {
		//�жϵ�ǰ��ǰ���꣨x,y���Ƿ�����������������
		//�����ǰ���겻������������ռ���򷵻ص�ǰ�������������±꣬���򷵻�-1
		for(int i = 0;i < this.wordList.size();i++) {
			Word w = this.wordList.get(i);
			if(x >= w.x - 10 && x <= w.x1 +10  && y >= w.y - 10 && y <= w.y1 + 10) {
				//�����ǰ�����ڵ�ǰ�����ڲ�
				return i;
			}
		}
		return -1;
	}
	
	public String isVertex(int x,int y) {
		//�жϵ�ǰ���λ���Ƿ����ı���ľ��ζ��㴦
		//������򷵻ء��ı�����±꣬�����š� ���Ͻ�Ϊ0�����½�Ϊ1�����½�Ϊ2�����Ͻ�Ϊ4
		//���򷵻ء�X��
		for(int i = 0;i < this.wordList.size();i++) {
			Word s = this.wordList.get(i);
			if(x >= s.x - 5 && x <= s.x   && y >= s.y - 5 && y <= s.y) {
				//��������Ͻ�
				return i + ",0";
			}
			else if(x >= s.x - 5 && x <= s.x  && y <= s.y1 + 10 && y >= s.y1 + 5) {
				//��������½�
				return i + ",1";
			}
			else if(x >= s.x1 + 5 && x <= s.x1 + 10 && y >= s.y1 + 5 && y <= s.y1 + 10) {
				//��������½�
				return i + ",2";
			}
			else if(x >= s.x1 + 5 && x <= s.x1 + 10 && y >= s.y - 5 && y <= s.y ) {
				//��������Ͻ�
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
