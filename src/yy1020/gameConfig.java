package yy1020;

import javax.swing.ImageIcon;

/**
 * ��Ϸ���ýӿ�
 * @author yy
 *
 */
public interface gameConfig {
	//��Ϸ����������
	String title = "�����ƶ�С��Ϸ";
	//��Ϸ������Ĵ�С
	int frameX = 1000;
	int frameY = 1000;
	//��Ϸ����С
	int panelX = 950;
	int panelY = 950;
	//��Ϸ�زĴ�С
	int elesize = 50;
	//�����С
	int playersize = 50;
	//------------[��Ϸ�ز�]----------
	//-----��һ��
	ImageIcon icon0 = new ImageIcon("000�հ�.png");
	ImageIcon icon1 = new ImageIcon("001�ݵ�.png");
	ImageIcon icon2 = new ImageIcon("002��ש.png");
	ImageIcon icon3 = new ImageIcon("003����ذ帱��.png");
	ImageIcon icon100 = new ImageIcon("100����.png");
	ImageIcon icon101 = new ImageIcon("101����.png");
	ImageIcon icon102 = new ImageIcon("102����.png");
	ImageIcon icon103 = new ImageIcon("103������.png");
	ImageIcon icon150 = new ImageIcon("150�ҽ�.png");
	
	
	//��ɫ����ͼ
	ImageIcon walk = new ImageIcon("��ɫ����ͼ.png");
	ImageIcon walk1 = new ImageIcon("��ɫͣ��ͼ.png");
	
	//npcͼ
	ImageIcon npc201 = new ImageIcon("201·��1��.png");
	
	//��ͷ
//	ImageIcon shadow = new ImageIcon("��ͷ��Ӱ.png");
	ImageIcon shadow2 = new ImageIcon("��ͷ��Ӱ2.png");
	
	//�Ի���
	ImageIcon talkbox = new ImageIcon("�Ի���.png");
	
}
