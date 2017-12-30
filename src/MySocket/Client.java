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
    private static final String Server_IP = "123.206.27.121"; // 服务端IP
    private static final int Server_Port = 610;

    public static Socket client;
    private Writer writer;
    private BufferedReader in;

    //构造函数，与服务器建立连接
    public Client() throws IOException {
        super(Server_IP, Server_Port);
        this.client = this;
        System.out.println("Client[" + client.getLocalPort() + "] 进入");
        UpdateThread.dsz0.setPort(client.getLocalPort());
    }

    /**
     * 启动监听收取消息，循环可以不停的输入消息，将消息发送出去
     * msg为JSON字符串
     * @throws Exception
     */
    public void load() throws IOException, InterruptedException {
        this.writer = new OutputStreamWriter(this.getOutputStream(), "UTF-8");
        new Thread(new ReceiveMsgTask()).start(); //// 启动监听
        
        ClientThread clientThread = new ClientThread(this);
        Thread write = new Thread(clientThread);
        write.start();
    }
    
    //准备发送的消息msg
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
     * 监听服务器发来的消息线程类
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
                        System.out.println("【" + mainFrame.dsz1.x + " " + mainFrame.dsz1.y + " " + mainFrame.dsz1.num + "】");
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
     * 入口
     * @param args
     */
//    public static void main(String[] args) {
//        try {
//            Client client = new Client(); // 启动客户端
////            MoveMsg msg = new MoveMsg(10, 10);
//            client.load();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
}
