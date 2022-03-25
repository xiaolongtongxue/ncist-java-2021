package GameS.Server.net;

import GameS.Server.bean.Net.Socket_obect;

public class Socket__ {
    //新建套接字，开放本地端口port
    public static Socket_obect createSocketobj(int port){
        return new Socket_obect(port);
    }
    public static void Server_Begin(Socket_obect sock_ob){
        sock_ob.begin_Socket();
    }
    public static void running(Socket_obect sock_ob){
        sock_ob.For_Servering();
    }
    public static void Server_End(Socket_obect sock_ob){
        sock_ob.close_Socket();
        System.out.println("服务器已关闭，感谢您的使用");
    }


}
