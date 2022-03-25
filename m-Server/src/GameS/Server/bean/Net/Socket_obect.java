package GameS.Server.bean.Net;

import GameS.Server.bean.Gaming.Gaming;
import GameS.Server.bean.Gaming.pakcage_to_game;
import GameS.Server.net.Thread_Run;
import GameS.Server.net.standard;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.EOFException;
import java.io.IOException;
import java.net.*;

public class Socket_obect implements Runnable {
    private ServerSocket Server;
    private Socket socket_S;
    private final int port;
    private static Login[] login=new Login[60000];//这是服务器允许的最多单词用户访问量
    private Connect_IP[] connect_ip=new Connect_IP[60000];
    int num;        //服务器自启动以来已经接待过的总机次
    public Socket_obect(int port){
        this.port=port;
    }
    public void begin_Socket(){
        try {
            this.Server=new ServerSocket(this.port);
            System.out.println("服务器启动成功");
        } catch (IOException e) {
            System.out.println("服务器启动失败");
            e.printStackTrace();
            System.exit(0);//服务器启动错误则立即结束程序
        }
        for (int i=0;i<60000;i++){
            login[i]=null;
            connect_ip[i]=new Connect_IP();
        }
    }
    public void setLogin(Login[] login) {
        this.login = login;
    }
    public Login[] getLogin() {
        return login;
    }
    @Override
    public void run() {
        /*
        * 在这里对服务器每单个线程进行说明注解：
        * 每单个线程开始时，会先记录下当前IP接入服务器的序号（整型变量k）
        * 然后在一个客户开始接受服务的时候开始循环接受客户端的报文
        * */
        try {//读取客户端发来的请求语句
            int k=num;
            DataOutputStream out = null;
            InetAddress address=socket_S.getInetAddress();
            int ClientPort=socket_S.getPort();
            Gaming game=new Gaming(address);

            //开始对目前的客户端开始服务
            while (true){
                try {
                    try {//在第一次循环的时候，socket连接是已经创建好了的，后边就需要单独创建了
                        socket_S.connect(new InetSocketAddress(address.toString().substring(1),ClientPort));
                    } catch (SocketException ignored){}
                    //实时更新客户端发来的报文并进行更新判断处理
                    try {//为了防止EOFException错误，在这里使用了try-catch结构
                        String requestseStr=new DataInputStream(socket_S.getInputStream()).readUTF();
                        System.out.println("收到客户端"+login[k].getAddress()+"发来的信息::"+requestseStr);

                        String responseStr;
//                        if (requestseStr.startsWith("play-game-----------")){
//                            login[k].setDoing(standard.getStandard(55));//Ready for begining
//                            boolean _flag=false;
//                            InetAddress target=null;
//                            for (int j=0;j<10;j++){
//                                for (int i=0;i< login.length;i++){
//                                    if (login[i]==null) {break;}//防止空指针错误
//                                    //如果遍历到同样正在匹配的玩家，则将进入同一局
//                                    if (login[i].getDoing().equals(standard.getStandard(55))
//                                            &&
//                                            !login[i].getAddress().toString().equals(login[k].getAddress().toString())
//                                    ){
//                                        target=login[i].getAddress();
//                                        game.setTargetAddress(target);
//                                        _flag=true;
//                                        connect_ip[k]=new Connect_IP(address,target);
//                                    }
//                                    //如果检索数值过大，停止检索
//                                    if (_flag) {
//                                        break;
//                                    }
//                                }
//                                if (_flag) break;
//                                try {//如果匹配不到，系统休眠半秒钟再次遍历
//                                    Thread.sleep(500);
//                                    /*
//                                    * 以下内容更新于2021.7.6,拼死老命干他一炮
//                                    * */
//                                    for (int ii=0;ii<connect_ip.length;ii++){
//                                        if (connect_ip[ii]==null){break;}//防止空指针
//                                        if (connect_ip[ii].getLateaddress()==address){
//                                            target=connect_ip[ii].getMainaddress();
//                                            game.setTargetAddress(target);
//                                            _flag=true;
//                                            connect_ip[k]=new Connect_IP(address,target);
//                                            break;
//                                        }
//                                    }
//                                } catch (InterruptedException e) {
//                                    e.printStackTrace();
//                                }
//                            }
//                            if (_flag){
//                                login[k].setDoing(standard.getStandard(56));//Get player successfully
//                                responseStr=standard.getStandard(17)+target;
//                                //______________Connected to IP::  +  'IP'
//                            }else {
//
//                                login[k].setDoing(standard.getStandard(57));//Get player failed
//                                responseStr=standard.getStandard(18);
//                                //Connected to IP failed
//                            }
//                            System.out.println("向IP::"+address+"回复的信息为————"+responseStr);
//                        }
//                        else {
                        pakcage_to_game pakcageToGame;
                        pakcageToGame=Thread_Run.run__(requestseStr,k,this,game);
                        responseStr=pakcageToGame.getStr();
                        game=pakcageToGame.getGame();
                        this.setLogin(pakcageToGame.getLogin());
//                        }
                        /*
                        * 说句真心话，在这里挺担心关于Login的设定导致线程打架来着....
                        * *//*
                        * 关于switch分析的代码段,即response的得到的函数
                        * 请移步GameS.Server.net.Thread_Run.run__(String requestseStr,int k,Socket_obect socket_obect,Gameing game)
                        * */
                        //向客户端发送response信息
                        out=new DataOutputStream(socket_S.getOutputStream());
                        out.writeUTF(responseStr);

                        //如果是结束的代码的话，结束线程
                        if (responseStr.equals(standard.getStandard(15)))
                            break;
                    } catch (EOFException ignored){}
                } catch (SocketException e){
                    System.out.println("IP::"+address+"强制断开连接");
                    login[k].setDoing(standard.getStandard(48));//Exiting program————Client
                    break;
                }

            }
            assert out != null;
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (NullPointerException ignored){}
    }
    public void For_Servering(){
        for (int i=0;;i++){
            try {
                socket_S=Server.accept();
                /*
                * for (int i=0;;i++){
                * 循环体本来是从这里开始的......
                * 于是就出现了关于同一个用户的子线程疯狂创建的问题......
                * */

                //客户连接时捕获对应ip
                login[i]=new Login(this.Server,this.socket_S,this.port,socket_S.getInetAddress());
                System.out.println("已经成功收到来自"+login[i].getAddress()+"的连接");
                num=i;

                new Thread(this).start();

            } catch (IOException e) {
                System.out.println("111");
                //e.printStackTrace();
            }
        }
    }

    public void close_Socket(){
        try {
            this.socket_S.close();
        } catch (IOException e) {
            System.out.println("套接字关闭异常");
            e.printStackTrace();
        }
    }

}
