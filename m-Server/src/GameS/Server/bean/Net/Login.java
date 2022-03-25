package GameS.Server.bean.Net;

import GameS.Server.net.standard;

import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

public class Login {
    private final ServerSocket Server;
    private final Socket socket;
    private final int port;
    private final InetAddress address;
    private String doing;
    private boolean Login_type=false;   //表示登陆状态，在线为true，反之为false
    public Login(ServerSocket server,Socket socket,int port,InetAddress address){
        this.Server=server;
        this.socket=socket;
        this.port=port;
        this.address=address;
    }
    public InetAddress getAddress(){
        return this.address;
    }
    public void setDoing(String doing) {
        this.doing = doing;
        //如果返回值表示登陆成功的话
        if (doing.equals(standard.getStandard(31))){
            this.Login_type=true;
        }
        //如果返回值表示用户退出程序的话
        else if (doing.equals(standard.getStandard(48))){
            this.Login_type=false;
        }
    }
    public String getDoing() {
        return doing;
    }
}
