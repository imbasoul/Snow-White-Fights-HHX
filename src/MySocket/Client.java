package MySocket;

import com.alibaba.fastjson.JSON;
import yy1020.Dsz;
import yy1020.UpdateThread;
import yy1020.mainFrame;

import java.io.*;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Timer;

public class Client extends Socket {
    private static final String Server_IP = "123.206.27.121"; // �����IP
    private static final int Server_Port = 610;

    public static Socket client;
    private Writer writer;
    private BufferedReader in;

    //���캯�������������������
    public Client() throws IOException {
        super(Server_IP, Server_Port);
        this.client = this;
        System.out.println("Client[" + client.getLocalPort() + "] ����");
        UpdateThread.dsz0.setPort(client.getLocalPort());
    }

    /**
     * ����������ȡ��Ϣ��ѭ�����Բ�ͣ��������Ϣ������Ϣ���ͳ�ȥ
     * msgΪJSON�ַ���
     * @throws Exception
     */
    public void load() throws IOException, InterruptedException {
        this.writer = new OutputStreamWriter(this.getOutputStream(), "UTF-8");
        new Thread(new ReceiveMsgTask()).start(); //// ��������
        
        ClientThread clientThread = new ClientThread(this);
        Thread write = new Thread(clientThread);
        write.start();
    }
    
    //׼�����͵���Ϣmsg
    public void writeToServer() throws IOException{
    	if(UpdateThread.dsz0 == null){
            System.out.println("dsz0 Fuck!!");
            return;
        }
        String msg = JSON.toJSONString(UpdateThread.dsz0.toMsg());
        if(msg == null) System.out.println("msg Fuck!!!");
        writer.write(msg);
        writer.write("\n");
        writer.flush();
    }

    /**
     * ������������������Ϣ�߳���
     */
    class ReceiveMsgTask implements Runnable{
        private BufferedReader reader;

        @Override
        public void run() {
            try {
                this.reader = new BufferedReader(new InputStreamReader(client.getInputStream(), "UTF-8"));
                while(true){
                    String reply = reader.readLine();
                    Msg tmp = JSON.parseObject(reply, Msg.class);
                    if(tmp.getPort() == UpdateThread.dsz0.getPort()) continue;
                    else{
//                        mainFrame.dsz1 = new Dsz();
                        mainFrame.dsz1.fromMsg(tmp);
                        System.out.println("��" + mainFrame.dsz1.x + " " + mainFrame.dsz1.y + " " + mainFrame.dsz1.num + "��");
//                        System.out.println(reply); //test
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                try {
                    reader.close();
                    writer.close();
                    client.close();
                    in.close();
                } catch (Exception e) {

                }
            }
        }
    }

    /**
     * ���
     * @param args
     */
//    public static void main(String[] args) {
//        try {
//            Client client = new Client(); // �����ͻ���
////            MoveMsg msg = new MoveMsg(10, 10);
//            client.load();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
}
