package yy1020;


import MySocket.Client;

import javax.swing.JPanel;
/**
 * 刷新游戏面板的线程类
 * @author yy
 *
 */
public class UpdateThread extends Thread{
	JPanel panel;
	JPanel tpanel;
	public static Dsz dsz0;
	public UpdateThread(JPanel panel,JPanel tpanel) {
		this.panel = panel;
		this.tpanel = tpanel;
        dsz0 = new Dsz(Player.x, Player.y, 0);
	}
	public void p2d() {
        dsz0.setX(Player.x);
        dsz0.setY(Player.y);
        dsz0.setNum(0);

		dsz0.bullet=new Bullet[5];
		for(int i=0;i<=4;i++) {
			if(Player.bullet1[i]!=null) {
				dsz0.bullet[i]=new Bullet(Player.bullet1[i].x, Player.bullet1[i].y, Player.bullet1[i].Direct, 2);
			}
		}
		dsz0.health=(int)Player.health.h;
        if((int)Player.health.h<0) {
            dsz0.islive=false;
        }
        else {
            dsz0.islive=true;
        }
	}


	@Override
	public void run() {
		while(true){
			if(mainFrame.tag==1){//如果是在走路状态就刷新游戏地图面板
				panel.repaint();
			}else if(mainFrame.tag==2){//如果是在对话的状态就刷新对话框面板
				tpanel.repaint();
			}
			try {
				Thread.sleep(20);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			p2d();
		}
	}

}
