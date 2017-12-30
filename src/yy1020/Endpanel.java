package yy1020;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

public class Endpanel extends JPanel implements gameConfig{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public Endpanel() {
		init();
	}
	
	public void init(){
		this.setBounds(28, 500, 0, 0);
		this.setLayout(null);
		this.setOpaque(false);//设置面板透明
	}
	
	@Override
	public void paint(Graphics g) {
		super.paint(g);
		ImageIcon bg=new ImageIcon("img\\endbg.png");
		g.drawImage(bg.getImage(), 0, 0, frameX, frameY, null);
		/*
			g.setColor(Color.BLUE);
			Font font = new Font("黑体", 600, 25);
			g.setFont(font);
			g.drawString(npc.name+":", 30, 30);
			g.setColor(Color.GREEN);
			g.drawString(npc.talk, 60, 65);*/
		
	}

}
