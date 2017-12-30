package yy1020;

import java.awt.Graphics;

/**
 * ��ɫ��
 * @author yy
 *
 */
public class Player extends Thread implements gameConfig{
	static Bullet bullet1[];
	static int bnum=0;
	static Health health=new Health();
	//��ɫ�е������Ϸ����λ��(����Ϸ���ǲ����)
	static int px = panelX/2;
	static int py = panelY/2;
	//��ɫ�е������ŵ�ͼ�е�λ��(�������ʼ�е��λ��һ��Ҫ��һ��Ԫ�����ĵ�λ�ã�Ҫ��Ȼ�����ƶ��ͻ������ - -��)
	static int x = 475;
	static int y = 475;
	//��ɫ��ƫ������ʵ�����ص��ƶ��ؼ��Ĳ���,һ��Ҫ������ʼֵ��Ҫ��Ȼ���߽���ָ��������������Ҵ�������һ�����ϣ�
	static int mx = x+25;
	static int my = y+25;
	//��ɫ�Ĳ���
	static int step = 5;
	//��ɫ�Ƿ��ƶ�
	static boolean up = false;
	static boolean down = false;
	static boolean left = false;
	static boolean right = false;
	//��ɫ�ĳ���    1,2,3,4�ֱ������������(���������ɫ���ƶ�ʱ�ĳ������⣬����Ҫд��npc�Ի�֮��Ĺ����õ���)
	static int towards = 2;
	//��ɫ���ƶ��ۻ��������������������ѭ���ı仯4�Ž�ɫͼƬ����ɶ�̬�ƶ��ģ�
	static int up1 = 0;
	static int down1 = 0;
	static int left1 = 0;
	static int right1 = 0;
	@Override
	public void run() {
		while(true){
			if(bullet1==null) {
				bullet1=new Bullet[5];
			}
			moveUD();
			moveLR();
			try {
				Thread.sleep(20);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	
	/**
	 * ��ɫ�����ƶ��ķ���
	 */
	public void moveUD(){
		if(up){
			//����ס�ϼ�ʱ����up1��1����up1����20ʱ������Ϊ0�����ѭ��
			up1++;
			if(up1>=20){
				up1=0;
			}
			//�����ɫ��ǰλ���Ϸ�������ֵ��Ϊ0����ɫ�Ϸ������嵲�ţ������ﴦ����ǽ�ɫһ�������ڲ����ƶ��������ƶ�������һ��
			if(ReadMapFile.map2[y/elesize-1][x/elesize]!=0||ReadMapFile.map3[y/elesize-1][x/elesize]!=0){
				int y1 = (y/elesize-1)*elesize+elesize/2;
				int x1 = (x/elesize)*elesize+elesize/2;
				if((y-y1)*(y-y1)>=elesize*elesize){
					y=y-step;
					my=my-step;
				}
			}else if(ReadMapFile.map2[y/elesize-1][x/elesize]==0&&ReadMapFile.map3[y/elesize-1][x/elesize]==0){//�Ϸ�û���壬���Լ��������ƶ�
				y=y-step;
				my=my-step;
			}
		}else if(down){
			down1++;
			if(down1>=20){
				down1=0;
			}
			if(ReadMapFile.map2[y/elesize+1][x/elesize]!=0||ReadMapFile.map3[y/elesize+1][x/elesize]!=0){
				int y1 = (y/elesize+1)*elesize+elesize/2;
				int x1 = (x/elesize)*elesize+elesize/2;
				if((y-y1)*(y-y1)>=elesize*elesize){
					y=y+step;
					my=my+step;
				}
			}else if(ReadMapFile.map2[y/elesize+1][x/elesize]==0&&ReadMapFile.map3[y/elesize+1][x/elesize]==0){
				y=y+step;
				my=my+step;
			}
		}
	}
	
	/**
	 * ��ɫ�����ƶ��ķ���
	 */
	public void moveLR(){
		if(left){
			left1++;
			if(left1>=20){
				left1=0;
			}
			if(ReadMapFile.map2[y/elesize][x/elesize-1]!=0||ReadMapFile.map3[y/elesize][x/elesize-1]!=0){
				int y1 = (y/elesize)*elesize+elesize/2;
				int x1 = (x/elesize-1)*elesize+elesize/2;
				if((x-x1)*(x-x1)>=elesize*elesize){
					x=x-step;
					mx=mx-step;
				}
			}else if(ReadMapFile.map2[y/elesize][x/elesize-1]==0&&ReadMapFile.map3[y/elesize][x/elesize-1]==0){
				x=x-step;
				mx=mx-step;
			}
		}else if(right){
			right1++;
			if(right1>=20){
				right1=0;
			}
			if(ReadMapFile.map2[y/elesize][x/elesize+1]!=0||ReadMapFile.map3[y/elesize][x/elesize+1]!=0){
				int y1 = (y/elesize)*elesize+elesize/2;
				int x1 = (x/elesize+1)*elesize+elesize/2;
				if((x-x1)*(x-x1)>=elesize*elesize){
					x=x+step;
					mx=mx+step;
				}
			}else if(ReadMapFile.map2[y/elesize][x/elesize+1]==0&&ReadMapFile.map3[y/elesize][x/elesize+1]==0){
				x=x+step;
				mx=mx+step;
			}
		}
	}
	
	
	public static void draw(Graphics g){
		//�����ɫ�����ƶ���
		if(!up&&!down&&!left&&!right){
			if(towards==1){//�����ɫ�ƶ��������Ϊ��
				g.drawImage(walk1.getImage(), Player.px-elesize/2-15, Player.py-elesize/2-25, Player.px-elesize/2+65, Player.py-elesize/2+55, 0, 96*3, 96, 96*4, null);
			}else if(towards==2){//����ƶ�������
				g.drawImage(walk1.getImage(), Player.px-elesize/2-15, Player.py-elesize/2-25, Player.px-elesize/2+65, Player.py-elesize/2+55, 0, 0, 96, 96, null);
			}else if(towards==3){//����ƶ�������
				g.drawImage(walk1.getImage(), Player.px-elesize/2-15, Player.py-elesize/2-25, Player.px-elesize/2+65, Player.py-elesize/2+55, 0, 96, 96, 96*2, null);
			}else if(towards==4){//����ƶ�������
				g.drawImage(walk1.getImage(), Player.px-elesize/2-15, Player.py-elesize/2-25, Player.px-elesize/2+65, Player.py-elesize/2+55, 0, 96*2, 96, 96*3, null);
			}
		}else{//�����ɫ���ƶ���
			if(up){
				//ͨ��up1��ֵ������������һ��ͼƬ
				if(up1<5){
					g.drawImage(walk.getImage(), Player.px-elesize/2-15, Player.py-elesize/2-25, Player.px-elesize/2+65, Player.py-elesize/2+55, 0, 96*3, 96, 96*4, null);
				}else if(up1<10){
					g.drawImage(walk.getImage(), Player.px-elesize/2-15, Player.py-elesize/2-25, Player.px-elesize/2+65, Player.py-elesize/2+55, 96, 96*3, 96*2, 96*4, null);
				}else if(up1<15){
					g.drawImage(walk.getImage(), Player.px-elesize/2-15, Player.py-elesize/2-25, Player.px-elesize/2+65, Player.py-elesize/2+55, 96*2, 96*3, 96*3, 96*4, null);
				}else{
					g.drawImage(walk.getImage(), Player.px-elesize/2-15, Player.py-elesize/2-25, Player.px-elesize/2+65, Player.py-elesize/2+55, 96*3, 96*3, 96*4, 96*4, null);
				}
			}else if(down){
				if(down1<5){
					g.drawImage(walk.getImage(), Player.px-elesize/2-15, Player.py-elesize/2-25, Player.px-elesize/2+65, Player.py-elesize/2+55, 0, 0, 96, 96, null);
				}else if(down1<10){
					g.drawImage(walk.getImage(), Player.px-elesize/2-15, Player.py-elesize/2-25, Player.px-elesize/2+65, Player.py-elesize/2+55, 96, 0, 96*2, 96, null);
				}else if(down1<15){
					g.drawImage(walk.getImage(), Player.px-elesize/2-15, Player.py-elesize/2-25, Player.px-elesize/2+65, Player.py-elesize/2+55, 96*2, 0, 96*3, 96, null);
				}else{
					g.drawImage(walk.getImage(), Player.px-elesize/2-15, Player.py-elesize/2-25, Player.px-elesize/2+65, Player.py-elesize/2+55, 96*3, 0, 96*4, 96, null);
				}
			}else if(left){
				if(left1<5){
					g.drawImage(walk.getImage(), Player.px-elesize/2-15, Player.py-elesize/2-25, Player.px-elesize/2+65, Player.py-elesize/2+55, 0, 96, 96, 96*2, null);
				}else if(left1<10){
					g.drawImage(walk.getImage(), Player.px-elesize/2-15, Player.py-elesize/2-25, Player.px-elesize/2+65, Player.py-elesize/2+55, 96, 96, 96*2, 96*2, null);
				}else if(left1<15){
					g.drawImage(walk.getImage(), Player.px-elesize/2-15, Player.py-elesize/2-25, Player.px-elesize/2+65, Player.py-elesize/2+55, 96*2, 96, 96*3, 96*2, null);
				}else{
					g.drawImage(walk.getImage(), Player.px-elesize/2-15, Player.py-elesize/2-25, Player.px-elesize/2+65, Player.py-elesize/2+55, 96*3, 96, 96*4, 96*2, null);
				}
				
			}else if(right){
				if(right1<5){
					g.drawImage(walk.getImage(), Player.px-elesize/2-15, Player.py-elesize/2-25, Player.px-elesize/2+65, Player.py-elesize/2+55, 0, 96*2, 96, 96*3, null);
				}else if(right1<10){
					g.drawImage(walk.getImage(), Player.px-elesize/2-15, Player.py-elesize/2-25, Player.px-elesize/2+65, Player.py-elesize/2+55, 96, 96*2, 96*2, 96*3, null);
				}else if(right1<15){
					g.drawImage(walk.getImage(), Player.px-elesize/2-15, Player.py-elesize/2-25, Player.px-elesize/2+65, Player.py-elesize/2+55, 96*2, 96*2, 96*3, 96*3, null);
				}else{
					g.drawImage(walk.getImage(), Player.px-elesize/2-15, Player.py-elesize/2-25, Player.px-elesize/2+65, Player.py-elesize/2+55, 96*3, 96*2, 96*4, 96*3, null);
				}
			}
		}
	}
	
	
	//�õ���ɫ�������е�λ��I
	public static int getI(){
		return (y-(playersize/2))/50;
	}
	//�õ���ɫ�������е�λ��J
	public static int getJ(){
		return (x-(playersize/2))/50;
	}

	public static void shot() {
		// TODO Auto-generated method stub
		for(int i=0;i<=4;i++) {
			if(bullet1[i]!=null&&bullet1[i].isLive==true) {
				if(i==4) {
					bullet1[bnum]=new Bullet(mx, my, towards,1);
					Thread thread=new Thread(bullet1[bnum]);
					thread.start();
					bnum=(bnum+1)%5;
					return;
				}
				continue;
			}
			bullet1[i]=new Bullet(mx, my, towards,1);
			Thread thread=new Thread(bullet1[i]);
			thread.start();
			return;
		}
	}
}
