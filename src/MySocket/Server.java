package MySocket;

import com.alibaba.fastjson.JSON;
import yy1020.Dsz;
import yy1020.mainFrame;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

public class Server extends ServerSocket {
    private static final int Server_Port = 230;

    //����һ������Ϊ5���̳߳�
    private static ExecutorService service = Executors.newFixedThreadPool(50);

    private static CopyOnWriteArrayList<String> userList = new CopyOnWriteArrayList<String>();
    private static CopyOnWriteArrayList<Task> threadList =  new CopyOnWriteArrayList<Task>();
    private static BlockingQueue<String> msgQueue = new ArrayBlockingQueue<String>(50);

    public Server() throws IOException {
        super(Server_Port);
    }

    /**
     * ������ͻ��˷�����Ϣ���̣߳�ʹ���̴߳���ÿ���ͻ��˷�������Ϣ
     *
     * @throws Exception
     */
    public void load() throws Exception {
        new Thread(new PushMsgTask()).start(); // ������ͻ��˷�����Ϣ���߳�

        while (true) {
            // server���Խ�������Socket����������server��accept����������ʽ��
            Socket socket = this.accept();
            /**
             * ���ǵķ���˴���ͻ��˵�����������ͬ�����еģ� ÿ�ν��յ����Կͻ��˵����������
             * ��Ҫ�ȸ���ǰ�Ŀͻ���ͨ����֮������ٴ�����һ���������� ���ڲ����Ƚ϶������»�����Ӱ���������ܣ�
             * Ϊ�ˣ����ǿ��԰�����Ϊ���������첽������ͻ���ͨ�ŵķ�ʽ
             */
            // ÿ���յ�һ��Socket�ͽ���һ���µ��߳���������
            service.execute(new Task(socket, socket.getPort()));
            Dsz dsz1 = new Dsz(375,375,1);
            dsz1.setPort(socket.getPort());
            msgQueue.put(JSON.toJSONString(dsz1));
        }
    }

    /**
     * ����Ϣ������ȡ��Ϣ���ٷ��͸����пͻ���
     */
    class PushMsgTask implements Runnable{
        @Override
        public void run() {
            while(true){
                String msg = null;
                try {
                    msg = msgQueue.take();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                if(msg != null){
                    for(Task thread : threadList){
                        thread.sendMsg(msg);
                    }
                }
            }
        }
    }

    /**
     * ����ͻ��˷�������Ϣ�߳���
     */
    class Task implements Runnable{
        private Socket socket;
        private BufferedReader reader;
        private Writer writer;
        private String userName;
        private int port;

        /**
         * ���캯��<br>
         * ����ͻ��˵���Ϣ
         * @throws Exception
         */
        public Task(Socket socket, int port) {
            this.socket = socket;
            this.port = port;
            this.userName = String.valueOf(socket.getPort());
            try {
                this.reader = new BufferedReader(new InputStreamReader(socket.getInputStream(), "UTF-8"));
                this.writer = new OutputStreamWriter(socket.getOutputStream(), "UTF-8");
            } catch (IOException e) {
                e.printStackTrace();
            }
            userList.add(this.userName);
            threadList.add(this);
//            pushMsg("��" + this.userName + "��������Ϸ��");
            System.out.println("Form Client[port:" + socket.getPort() + "] "
                    + this.userName + "��������Ϸ");
        }

        @Override
        public void run() {
            try {
                while(true){
                    String msg = reader.readLine();
                    if(msg==null) System.out.println("Server.Task.run.msg Fuck");
                    Msg tmp = JSON.parseObject(msg, Msg.class); //test
                    if(msg==null) System.out.println("Server.Task.run.tmp Fuck");
                    System.out.println("��ң�" + tmp.getPort()); //test
                    pushMsg(msg);
                }
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                try {
                    writer.close();
                    reader.close();
                    socket.close();
                } catch (Exception e) {

                }
                userList.remove(userName);
                threadList.remove(this);
//                pushMsg("��" + userName + "�˳�����Ϸ��");
                System.out.println("Form Client[port:" + socket.getPort() + "] "
                        + userName + "�˳�����Ϸ");
            }
        }

        /**
         * ׼�����͵���Ϣ�������
         *
         * @param msg
         */
        private void pushMsg(String msg){
            try {
                msgQueue.put(msg);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        private void sendMsg(String msg){
            try {
                // ����Ϣ���͸������ͻ���
                Msg sendMsg = JSON.parseObject(msg, Msg.class);
                if(sendMsg == null) System.out.println("Fuck!!!!!");
                if(sendMsg.getPort() != this.port){
//                    System.out.println(msg);
                    writer.write(msg);
                    writer.write("\n");
                    writer.flush();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        try {
            Server server = new Server();
            server.load();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
