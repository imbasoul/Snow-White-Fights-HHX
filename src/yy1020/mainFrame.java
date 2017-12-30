package yy1020;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 * ��Ϸ������
 * @author yy
 *
 */
public class mainFrame extends JFrame implements gameConfig{
	//��Ϸ״̬   1Ϊ���� 2Ϊ�Ի�
	static int tag = 1;
	//��Ϸ���
	JPanel panel;
	//�Ի����
	JPanel tpanel;
	
	Endpanel epanel;
	public static Dsz dsz1;
	public mainFrame() {
		init();
		
	}
	/**
	 * ���ô���
	 */
	public void init(){
		this.setTitle(title);
		this.setSize(frameX, frameY);
		this.setLayout(null);
		this.setDefaultCloseOperation(3);
//		//���ô����ޱ߿�
//		this.setUndecorated(true);
		//�����Ի������
		tpanel = settpanel();
		//������Ϸ���
		panel = setpanel();
		
		epanel=new Endpanel();
		this.add(tpanel);
		this.add(panel);
		
		
		this.setVisible(true);
		//��װ���̼�����
		PanelListenner plis = new PanelListenner();
		this.addKeyListener(plis);
		
		//���������ƶ��߳�
		Player player = new Player();
		player.start();
		
		//����ˢ������߳�
		UpdateThread ut = new UpdateThread(panel,tpanel);
		ut.start();
		
		//�������������
		dsz1=new Dsz(375,375,0);
		Thread thdsz1=new Thread(dsz1);
		thdsz1.start();
	}
	
	/**
	 * ������Ϸ���
	 */
	public JPanel setpanel(){
		JPanel panel = new MyPanel();
		panel.setBounds(18, 5, panelX, panelY);
//		panel.setPreferredSize(new Dimension(panelX, panelY));
		panel.setLayout(null);
		panel.setBackground(Color.black);
		
		return panel;
	}
	/**
	 * ���öԻ����
	 * @return
	 */
	public JPanel settpanel(){
		JPanel tpanel = new TalkPanel();
		return tpanel;
	}
	
	/**
	 * �ڲ���Ϸ����������
	 * @author yy
	 *
	 */
	class PanelListenner extends KeyAdapter{
		//����������
		public void keyPressed(KeyEvent e){
			int code = e.getKeyCode();
			if(tag==1){
				switch (code) {
				case KeyEvent.VK_SPACE:
					Player.shot();
					break;
				case KeyEvent.VK_UP:
					Player.up = true;
					Player.towards = 1;
					break;
				case KeyEvent.VK_DOWN:
					Player.down = true;
					Player.towards = 2;
					break;
				case KeyEvent.VK_LEFT:
					Player.left = true;
					Player.towards = 3;
					break;
				case KeyEvent.VK_RIGHT:
					Player.right = true;
					Player.towards = 4;
					break;
				case KeyEvent.VK_G://�����˶Ի���
					if(Player.towards==1){//��ɫ������
						int num = ReadMapFile.map3[Player.y/elesize-1][Player.x/elesize];
						if(num!=0){//�Ϸ���npc
							if(GetNPC.map.get(num)==null){
								GetNPC.getnpc(num);
								TalkPanel.gettalknpc(num);
							}
							tag = 2;
							tpanel.setBounds(28, 500, 630, 150);
							
							tpanel.repaint();
							System.out.println(1);
						}
					}else if(Player.towards==2){//��ɫ������
						if(ReadMapFile.map3[Player.y/elesize+1][Player.x/elesize]!=0){//�·���npc
							tpanel.setBounds(28, 500, 630, 150);
							tag = 2;//����Ի�ģʽ
							tpanel.repaint();
						}
					}else if(Player.towards==3){//��ɫ������
						if(ReadMapFile.map3[Player.y/elesize][Player.x/elesize-1]!=0){//����npc
							tpanel.setBounds(28, 500, 630, 150);
							tag = 2;
							tpanel.repaint();
						}
					}else if(Player.towards==4){//��ɫ������
						if(ReadMapFile.map3[Player.y/elesize][Player.x/elesize+1]!=0){//�ҷ���npc
							tpanel.setBounds(28, 500, 630, 150);
							tag = 2;
							tpanel.repaint();
						}
					}
					
					break;

				default:
					break;
				}
			}else if(tag==2){
				if(code==KeyEvent.VK_G){
					tag=1;
					tpanel.setBounds(28, 500, 0, 0);
				}
			}
			
		}
		//�������ͷ�
		public void keyReleased(KeyEvent e){
			if(tag==1){
				int code = e.getKeyCode();
				switch (code) {
				case KeyEvent.VK_UP:
					Player.up = false;
					Player.up1 = 0;
					break;
				case KeyEvent.VK_DOWN:
					Player.down = false;
					Player.down1 = 0;
					break;
				case KeyEvent.VK_LEFT:
					Player.left = false;
					Player.left1 = 0;
					break;
				case KeyEvent.VK_RIGHT:
					Player.right = false;
					Player.right1 = 0;
					break;

				default:
					break;
				}
			}
		}
	}
	/**
	 * �Զ����ڲ���Ϸ�����
	 * @author yy
	 *
	 */
	class MyPanel extends JPanel{
		@Override
		public void paint(Graphics g) {
			super.paint(g);
			//�ҵ���ɫ�Աߵ��زģ��������Ҹ�5��
			for(int i=Player.getI()-12;i<=Player.getI()+12;i++){
				for(int j=Player.getJ()-12;j<=Player.getJ()+12;j++){
					//�����һ��û�г���
					if(i>=0&&j>=0&&i<ReadMapFile.map1.length&&j<ReadMapFile.map1[0].length){
						//����һ��Ԫ��
						ImageIcon icon = GetMap.int2icon(ReadMapFile.map1[i][j]);
						g.drawImage(icon.getImage(), (Player.px-elesize/2)+((j-Player.getJ())*elesize)-(Player.mx%elesize), (Player.py-elesize/2)+((i-Player.getI())*elesize)-(Player.my%elesize), elesize, elesize, null);
						//�ڶ���
						if(ReadMapFile.map2[i][j]!=0){
							ImageIcon icon2 = GetMap.int2icon(ReadMapFile.map2[i][j]);
							g.drawImage(icon2.getImage(), (Player.px-elesize/2)+((j-Player.getJ())*elesize)-(Player.mx%elesize), (Player.py-elesize/2)+((i-Player.getI())*elesize)-(Player.my%elesize), elesize, elesize, null);
						}
						//������
						if(ReadMapFile.map3[i][j]!=0){
							ImageIcon icon3 = GetMap.int2npc(ReadMapFile.map3[i][j]);
							g.drawImage(icon3.getImage(), (Player.px-elesize/2)+((j-Player.getJ())*elesize)-(Player.mx%elesize), (Player.py-elesize/2)+((i-Player.getI())*elesize)-(Player.my%elesize), elesize, elesize, null);
						}
						//���Ĳ㣬��Ȧ
//						if(ReadMapFile.map4[i][j]!=0){
//							ImageIcon icon4 = GetMap.int2npc(ReadMapFile.map4[i][j]);
//							g.drawImage(icon4.getImage(), (Player.px-elesize/2)+((j-Player.getJ())*elesize)-(Player.mx%elesize), (Player.py-elesize/2)+((i-Player.getI())*elesize)-(Player.my%elesize), elesize, elesize, null);
//						}
					}
				}
			}
//			g.setColor(Color.black);
//			g.fillRect(0, 0, 50, 650);
//			g.fillRect(0, 0, 650, 50);
//			g.fillRect(600, 0, 50, 650);
//			g.fillRect(0, 600, 650, 50);
			
			Player.draw(g);
			//System.out.format("%d",ReadMapFile.map1[0][0]);  
			for(int i=0;i<=4;i++) {
				if(Player.bullet1[i]!=null&&Player.bullet1[i].isLive==true){
		          //System.out.format("%d   ",i);            
					Player.bullet1[i].paint(g);
					// g.setColor(Color.red);
		            //g.draw3DRect(Player.bullet1[i].x-Player.mx+Player.px,Player.bullet1[i].y-Player.my+Player.py, 20, 20, false);
		        }
			}
			
			Player.health.paint(g);
			if(dsz1!=null&&dsz1.islive==true) {
				dsz1.paint(g);
			}
			if(UpdateThread.dsz0!=null&&(UpdateThread.dsz0.islive==true)) {
				UpdateThread.dsz0.paint(g);
			}
			SmallMap smallmap = new SmallMap();
			smallmap.paint(g);
			//npc
//			g.drawImage(npc1.getImage(), 400, 400, 50, 50, null);
			
			
			//���˵�һ��С�뷨����һ����ɫ��ͼƬ��Ȼ���м��ڿ�һ��Բ������ģ��Ч������ģ���˵���Ұ
			//g.drawImage(shadow2.getImage(), 0, 0, 650, 650, null);
		}
	}
}
