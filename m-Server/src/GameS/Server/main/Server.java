package GameS.Server.main;

import GameS.Server.bean.Net.Socket_obect;
import GameS.Server.net.Socket__;

public class Server {
    public static void main(String[] args) {
        //端口号设置10086
        Socket_obect socket_obect=Socket__.createSocketobj(10086);
        //启动套接字服务
        Socket__.Server_Begin(socket_obect);
        //执行详细操作
        Socket__.running(socket_obect);
        //关闭套接字服务
        Socket__.Server_End(socket_obect);
    }
}
