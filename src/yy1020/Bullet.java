package yy1020;

import java.awt.Checkbox;
import java.awt.Graphics;

import javax.swing.ImageIcon;

public class Bullet implements Runnable{
    int x;
    int y;
    int Direct; 
    int speed = 20;
    int tag;
    boolean isLive = true;
    public Bullet(int x,int y,int Direct,int tag){
        this.x=x;
        this.y=y;
        this.Direct = Direct;
        this.tag=tag;
    }
    
    
    public int getI(){
		return (y-(20/2))/50;
	}
	//�õ���ɫ�������е�λ��J
	public int getJ(){
		return (x-(20/2))/50;
	}
	public void paint(Graphics g) {
    	ImageIcon fireball=new ImageIcon("img\\fireball"+Direct+".png");
    	switch(Direct) {
    	case 1:
    		g.drawImage(fireball.getImage(), x-Player.x+Player.px-40, y-Player.y+Player.py-75, 36, 75, null);
    		break;
    	case 2:	
    		g.drawImage(fireball.getImage(), x-Player.x+Player.px-40, y-Player.y+Player.py, 36, 75, null);
    		break;
    	case 3:	
    		g.drawImage(fireball.getImage(), x-Player.x+Player.px-75, y-Player.y+Player.py-25, 75, 36, null);
    		break;
    	case 4:	
    		g.drawImage(fireball.getImage(), x-Player.x+Player.px-25, y-Player.y+Player.py-25, 75, 36, null);
    		break;
    	}
    	//g.drawImage(fireball.getImage(), x-Player.x+Player.px-25, y-Player.y+Player.py-25, 75, 36, null);
    }
	public void check(Dsz dsz1) {
		if(dsz1.islive==true&&dsz1.getI()==getI()&&dsz1.getJ()==getJ()) {
        	dsz1.health-=10;
        	isLive = false;
        }
	}
	public void check() {
		if(Player.getI()+1==getI()&&Player.getJ()+1==getJ()) {
        	Player.health.de(10.0);
        	isLive = false;
        }
	}
    @Override
    public void run() {
        while(true){
            try {
                Thread.sleep(20);
            } catch (Exception e) {
                // TODO: handle exception
            }
            switch(Direct){
            case 1: 
                y-=speed;
                break;
            case 2:
                y+=speed;
                break;
            case 3:
                x-=speed;
                break;
            case 4:
                x+=speed;
                break;
            default:
                break;
            }
            if(tag==1&&mainFrame.dsz1.islive==true&&mainFrame.dsz1.getI()+1==getI()&&mainFrame.dsz1.getJ()==getJ()) {
            	mainFrame.dsz1.health-=10;
            	isLive = false;
                break;
            }
//          System.out.println(""+x+" "+y);
            //�ӵ���ʱ����
            if(x<0||x>7000||y<0||y>7000){
                isLive = false;
                break;
            }
        }
    }
}