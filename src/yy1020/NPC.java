package yy1020;

public class NPC {
	//NPC������
	String name;
	//Ѫ��
	int hp;
	//�ȼ�
	int level;
	//����
	int exp;
	//��Ǯ
	int money;
	//�Ƿ���� 1���� 0����
	int islive;
	//�Ի�
	String talk = "���Ǵ�ɵ�ƣ�";
	
	public NPC(String name,int hp,int level,int exp,int money,int islive,String talk) {
		this.name = name;
		this.hp = hp;
		this.level = level;
		this.exp = exp;
		this.money = money;
		this.islive = islive;
		this.talk = talk;
	}
}
