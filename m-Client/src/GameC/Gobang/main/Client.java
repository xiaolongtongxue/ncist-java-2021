package GameC.Gobang.main;

import GameC.Gobang.UI.manageui.Logining;
import GameC.Gobang.bean.Socket_C;

public class Client {
    public static String ID;
    public static Socket_C socket_c;
    public static void main(String[] args) {
        //设置服务器端口号，试图连接服务器
        socket_c=new Socket_C(10086);
        //试图连接服务器，连接失败则退出程序
        boolean flag=socket_c.try_connect();
        if (!flag){
            //连接失败
            System.exit(0);
        }
        //运行UI界面
        Logining user=new Logining("五子棋");
        user.run();
        //UI界面关闭
        socket_c.free();
    }
}
