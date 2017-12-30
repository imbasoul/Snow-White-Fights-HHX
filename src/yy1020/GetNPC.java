package yy1020;

import java.util.HashMap;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class GetNPC {
	//������npc������map3�����е�ֵһһ��Ӧ������hashmap
	static HashMap<Integer, NPC> map = new HashMap<Integer, NPC>();
	
	public static void getnpc(int num){
		//���xml�ļ�·��
		String numstr = String.valueOf(num);
		String path = "npc\\npc"+numstr+".xml";
		try{
			//�õ�����xml��������
			DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
			//ͨ����������õ�����������
			DocumentBuilder builder = factory.newDocumentBuilder();
			//�������������xml�ļ����õ�һ��doc�ļ�
			Document doc = builder.parse(path);
			setNPC(doc,num);
		}catch(Exception e){
			e.printStackTrace();
		}
		
	}
	
	/**
	 * ����doc�ļ�����������npc��
	 * @param doc
	 */
	public static void setNPC(Node doc,int num){
		String name = "";
		int hp=0;
		int level=0;
		int exp=0;
		int money=0;
		int islive=0;
		String talk="";
		Node node = (Node) doc.getFirstChild();
		System.out.println(node.getNodeName());
		NodeList nodes = node.getChildNodes();
		for(int i=0;i<nodes.getLength();i++){
			Node n = nodes.item(i);
			String str = n.getNodeName();
			if(n instanceof Element){
				if(str.equals("name")){
					name = n.getTextContent();
				}else if(str.equals("hp")){
					hp = Integer.parseInt(n.getTextContent());
				}else if(str.equals("level")){
					level = Integer.parseInt(n.getTextContent());
				}else if(str.equals("exp")){
					exp = Integer.parseInt(n.getTextContent());
				}else if(str.equals("money")){
					money = Integer.parseInt(n.getTextContent());
				}else if(str.equals("islive")){
					islive = Integer.parseInt(n.getTextContent());
				}else if(str.equals("talk")){
					talk = n.getTextContent();
				}
				
			}
		}
		NPC npc = new NPC(name, hp, level, exp, money, islive, talk);
		//�����ɵ����npc�����num����hashmap
		map.put(num, npc);
		System.out.println(npc.name);
	}
}
