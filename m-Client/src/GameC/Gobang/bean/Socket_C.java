package GameC.Gobang.bean;

import GameC.Gobang.util.xmlReader;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class Socket_C {
    private Socket socket_C;
    private DataOutputStream out;
    private DataInputStream in;
    private final String Server_IP;
    int port;
    public Socket_C(int port){
        this.port=port;
        Server_IP= xmlReader.get_Server_IP();
    }
    public boolean try_connect(){
        try {
            socket_C=new Socket(Server_IP,port);
            System.out.println("服务器连接成功");
            return true;
        } catch (IOException e) {
            System.out.println("服务器连接失败");
            e.printStackTrace();
            return false;
        }
    }
    //负责向服务器接发送消息
    public String send_get_Message(String requestStr){
        try {
            out=new DataOutputStream(socket_C.getOutputStream());
            in=new DataInputStream(socket_C.getInputStream());

            out.writeUTF(requestStr);
            System.out.println("向服务器发送消息::"+requestStr+"<<<<<<等待回应中.......");

            String responseStr=in.readUTF();
            System.out.println("收到服务器端发回的消息::"+responseStr);

            return responseStr;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    //下边的方法是为了在退出程序时方便
    public void free(){
        try {
            in.close();
            out.close();
            socket_C.close();
            System.out.println("网页链接断开，程序结束");
        } catch (IOException | NullPointerException e) {
            //e.printStackTrace();
        }
    }
}
