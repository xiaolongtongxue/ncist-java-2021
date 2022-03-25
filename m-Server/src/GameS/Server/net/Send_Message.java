package GameS.Server.net;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;

public class Send_Message {
    public static void sendMessage(InetAddress ip,int port,String msg){
        Socket socket_C;
        DataOutputStream out;
        DataInputStream in;
        String Server_ip=ip.toString().substring(1);   //客户端请求的服务器IP
        String requestStr,responseStr;
        try {
            socket_C=new Socket(Server_ip,port);
            System.out.println("连接客户"+ip+"成功！");
        } catch (IOException e) {
            e.printStackTrace();
            return;
        }
        try {
            out=new DataOutputStream(socket_C.getOutputStream());
            in=new DataInputStream(socket_C.getInputStream());

            requestStr=msg;
            out.writeUTF(requestStr);
            System.out.println("向"+ip+"发送请求信息："+requestStr);

            in.close();
            out.close();
            socket_C.close();
            System.out.println("网页链接断开，结束请求");
        } catch (Exception e){
            e.printStackTrace();
        }
    }
}
