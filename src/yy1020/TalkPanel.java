package yy1020;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;

import javax.swing.JPanel;
/**
 * �Ի������
 * @author yy
 *
 */
public class TalkPanel extends JPanel implements gameConfig{
	static NPC npc;
	
	
	public TalkPanel() {
		init();
	}
	
	public void init(){
		this.setBounds(28, 500, 0, 0);
		this.setLayout(null);
		this.setOpaque(false);//�������͸��
	}
	
	
	
	@Override
	public void paint(Graphics g) {
		super.paint(g);
		if(npc!=null){
			g.drawImage(talkbox.getImage(), 0, 0, 630, 130, null);
			g.setColor(Color.BLUE);
			Font font = new Font("����", 600, 25);
			g.setFont(font);
			g.drawString(npc.name+":", 30, 30);
			g.setColor(Color.GREEN);
			g.drawString(npc.talk, 60, 65);
		}
	}
	
	//��ʾ�Ի����
	public void show(){
		this.setPreferredSize(new Dimension(panelX, panelY));
	}
	//���ضԻ����
	public void hide(){
		this.setPreferredSize(new Dimension(0, 0));
	}
	//�õ����ڶԻ���npc
	public static void gettalknpc(int num){
		npc = GetNPC.map.get(num);
	}
	
}
