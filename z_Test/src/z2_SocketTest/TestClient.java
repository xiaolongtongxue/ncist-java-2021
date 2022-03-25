package z2_SocketTest;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class TestClient {
    public static void main(String[] args) {
        Socket socket_C;
        DataOutputStream out;
        DataInputStream in;
        String Server_ip="127.0.0.1";   //客户端请求的服务器IP
        int port=10086;                 //客户端请求的服务器端口
        String requestStr,responseStr;
        try {
            socket_C=new Socket(Server_ip,port);
            System.out.println("连接服务器成功！");
        } catch (IOException e) {
            e.printStackTrace();
            return;
        }
        try {
            out=new DataOutputStream(socket_C.getOutputStream());
            in=new DataInputStream(socket_C.getInputStream());

            requestStr="这是一个来自卑微客户端的请求信息";
            out.writeUTF(requestStr);
            System.out.println("客户端发送请求信息："+requestStr);

            responseStr=in.readUTF();
            System.out.println("客户端收到来自服务端的请求信息："+responseStr);

            in.close();
            out.close();
            socket_C.close();
            System.out.println("网页链接断开，结束请求");
        } catch (Exception e){
            e.printStackTrace();
        }
    }
}
