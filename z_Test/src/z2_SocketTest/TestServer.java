package z2_SocketTest;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

public class TestServer {
    public static void main(String[] args) {
        ServerSocket server;
        Socket socket_S;
        DataOutputStream out;
        DataInputStream in;
        InetAddress iaddress;
        int port=10086;
        String requestStr,responseStr;
        try {
            server=new ServerSocket(port);
            System.out.println("服务器启动成功，等待用户请求");
        } catch (IOException e) {
            e.printStackTrace();
            return;//出现错误直接结束程序
        }
        try {
            try {
                socket_S=server.accept();
                iaddress=socket_S.getInetAddress();
                System.out.println("收到用户建立连接请求，客户端地址："+iaddress);
                
                in=new DataInputStream(socket_S.getInputStream());
                out=new DataOutputStream(socket_S.getOutputStream());
                
                requestStr=in.readUTF();
                System.out.println("服务端收到请求消息："+requestStr);
                
                responseStr="这边已收到请求消息："+requestStr;
                out.writeUTF(responseStr);
                System.out.println("服务器端返回相应信息："+responseStr);
                
                in.close();
                out.close();
                socket_S.close();
                System.out.println("服务结束");
            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (Exception e){
            e.printStackTrace();
        }
    }
}
