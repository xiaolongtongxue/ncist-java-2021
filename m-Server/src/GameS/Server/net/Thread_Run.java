package GameS.Server.net;

import GameS.Server.bean.Gaming.Gaming;
import GameS.Server.bean.Gaming.Last_step;
import GameS.Server.bean.Gaming.pakcage_to_game;
import GameS.Server.bean.Net.Login;
import GameS.Server.bean.Net.Socket_obect;
import GameS.Server.util.SQL_Control.Insert_Information;
import GameS.Server.util.SQL_Control.select_information;
import GameS.Server.util.SQL_Control.way_of_select;
import GameS.Server.util.gotString;
import GameS.Server.util.xmlReader;

import java.net.InetAddress;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Thread_Run {
    /*
    * 在下边的方法中，为了方便整理代码，在switch-case结构中为了方便IDEA缩进
    * 在原有的case-default结构后边加上了大括号的结构。
    * 无其他用途仅用来方便整理
    * * * * * * * * * * * * * * * * * * *
    * 该函数的返回值为对应操作
    * */
    public static pakcage_to_game run__(String requestseStr, int k, Socket_obect socket_obect,Gaming game) {
        Login[] login = socket_obect.getLogin();
        InetAddress address=login[k].getAddress();
        String response;
        //关于发回的请求报文的字符串，开头20位应当为请求的名字，不足的用"-"补齐，后边再添加多需要具体传参的属性
        //          再后边需要添加登录设置的姓名/PID，尾部用"_"补齐，后边加上密码
        //          例如：login-request-------Tom___123456
        //               login-request-------0000001123456
        switch (requestseStr.substring(0, 20)) {
            case "login-request-------":{
                login[k].setDoing(standard.getStandard(30));//Loginging
                String id_=gotString.get____(requestseStr.substring(20,26),'_');
                String passWord_=requestseStr.substring(26);
                String[] type___=new String[2];
                type___[0]="PID";type___[1]="pname";
                try {
                    ResultSet res_= select_information.RunSQL(
                            select_information.get_selectSQL(
                                    "game_connected_2021.player",type___,
                                    "(PID='"+id_+"' or "+"pname='"+id_+"') and password='"+passWord_+"';"
                            )
                    );
                    assert res_ != null;
                    //关于对比是否正确的案例，请移步GameS.Server.util.SQL_Control.way_of_select.Gettrue_false
                    if (!way_of_select.Gettrue_false(res_, id_, "pname", "PID")){
                        System.out.println("账号或密码出错，来自IP:"+address);
                        login[k].setDoing(standard.getStandard(32));//Login Failed
                        response=standard.getStandard(1);
                        //Error on id or password
                    } else {
                        System.out.println("输入正确，允许登录，来自IP:"+address);
                        login[k].setDoing(standard.getStandard(31));//Login Successfully
                        response=standard.getStandard(0);
                        //Correct to login
                    }
                } catch (SQLException throwables) {
                    System.out.println("账号或密码出错，来自IP:"+address);
                    login[k].setDoing(standard.getStandard(32));//Login Failed
                    response=standard.getStandard(1);
                    throwables.printStackTrace();
                    //Error on id or password
                }
                break;
            }
            //在此处添加注解：'register'后边的应当是只有PID加上注册用的用户名！！并且后边也应当用_补齐至6位，后边加上密码
            // 如：“register------------Tom___123456”
            case "register------------":{
                login[k].setDoing(standard.getStandard(33));//registering
                //在点击提交按钮后，信息应当传递到这里
                String Pname = gotString.get____(requestseStr.substring(20,26),'_') ;
                String Password_=requestseStr.substring(26);
                if (Password_.length()>16){
                    System.out.println("密码过长----来自IP::"+address);
                    login[k].setDoing(standard.getStandard(34));//Password too long to fail
                    response=standard.getStandard(2);
                    //Password too long
                    break;
                }
                //还需要有一个函数用来获取新用户的ID
                String new_id = way_of_select.get_new_ID();
                int BeginScore = 1200;
                int Num = 0;
                String[] type_ = {"PID","pname","score","WinNum","DefeatNum","password"};
                String[][] value = {{
                    new_id, Pname,String.valueOf(BeginScore),String.valueOf(Num),
                    String.valueOf(Num),Password_
                }};
                try {
                    //添加成功未弹出异常
                    if(Insert_Information.run_insert_withroot(
                            Insert_Information.get_InsertSQL(
                                    "game_connected_2021.player", type_, value
                            )
                        )
                    ){
                        /*
                         * 以后有时间的话可以在这里加上一段查询数据库来对用户添加行为进行确认成功的判断条件
                         * */
                        System.out.println("新用户" + new_id + "已添加成功，信息来自IP："+address);
                        login[k].setDoing(standard.getStandard(35));//Register Successfully
                        response=standard.getStandard(4);
                        //Insert Successfully
                    } else {
                        System.out.println("该用户名已被占用或SQL错误---"+Insert_Information.get_InsertSQL(
                                "game_connected_2021.player", type_, value
                        ));
                        login[k].setDoing(standard.getStandard(36));//Used ID_name to fail
                        response=standard.getStandard(3);
                        //Used ID or SQLError
                    }
                } catch (SQLException e) {
                    System.out.println("该用户名已被占用或SQL错误---"+Insert_Information.get_InsertSQL(
                                "game_connected_2021.player", type_, value
                        ));
                    login[k].setDoing(standard.getStandard(36));//Used ID_name to fail
                    response=standard.getStandard(3);
                    //Used ID or SQLError
                }
                break;
            }
            //在此处添加注释，'get-information'后边的应当是PID/pname，如"get-information-----Tom"、"get-information-----000001"
            //回馈的语句里边应当包含：PID、pname和段位信息，在这里进行段位信息的计算
            case "get-information-----":{
                String id_name=requestseStr.substring(20);
                String ID = "",PName = "",Score="";
                String[] type__=new String[3];
                type__[0]="PID";type__[1]="pname";type__[2]="score";
                String condition_1="PID='"+id_name+"'";
                String condition_2="pname='"+id_name+"'";
                try {
                    ResultSet res__=select_information.RunSQL(
                            select_information.get_selectSQL(
                                    "game_connected_2021.player",type__,
                                    condition_1
                            )
                    );
                    assert res__ != null;
                    if (res__.getRow()==0){//如果输入的是名字而不是ID
                        ResultSet res___=select_information.RunSQL(
                                select_information.get_selectSQL(
                                        "game_connected_2021.player",type__,
                                        condition_2
                                )
                        );
                        assert res___ != null;
                        while (res___.next()){//这里循环必须有，不然可能会报错java.sql.SQLException: Before start of result set
                            ID=res___.getString("PID");
                            PName=res___.getString("pname");
                            Score=res___.getString("score");
                        }
                    }
                    else {//如果输入的是ID而不是名字
                        while (res__.next()){
                            ID=res__.getString("PID");
                            PName=res__.getString("pname");
                            Score=res__.getString("score");
                        }
                    }
                    ResultSet _res_=select_information.RunSQL(
                            select_information.get_selectSQL(
                                    "game_connected_2021.part"
                            )
                    );
//                    System.out.println(ID);
//                    System.out.println(PName);
//                    System.out.println(Score);
                    int score= Integer.parseInt(Score);
                    String truename="";
                    while (true){
                        assert _res_ != null;
                        if (!_res_.next()) break;
                        int minnum= Integer.parseInt(_res_.getString("MinScore"));
                        int maxnum;
                        try {
                            maxnum= Integer.parseInt(_res_.getString("MaxScore"));
                        } catch (NumberFormatException e){
                            maxnum=100000;
                        }
                        String name=_res_.getString("Part_Name");
                        if (score>=minnum&&score<maxnum){
                            truename=name;
                            break;
                        }
                    }
                    response=standard.getStandard(21)+truename+ID+PName;
                } catch (SQLException e) {
                    e.printStackTrace();
                    response=standard.getStandard(22);
                }
                break;
            }
            //在此处添加注解：'change-information'后边的应当是只有PID加上重置后的用户名！！
            //如：“change-information--100001Tom”
            case "change-information--":{
                login[k].setDoing(standard.getStandard(37));//changing information
                //在点击确认修改之后，数据应当传到这里
                String PID = requestseStr.substring(20, 26);
                String newPname = requestseStr.substring(26);
                try {
                    Insert_Information.run_insert_withroot(
                        Insert_Information.get_UpdateSQL(
                            "game_connected_2021.player", "pname", newPname, "PID='" + PID + "';"
                        )
                    );
                    System.out.println("用户" + PID + "信息更新完成，用户IP：" + login[k].getAddress());
                    login[k].setDoing(standard.getStandard(38));//change successfully
                    response=standard.getStandard(5);
                    //Update Successfully
                } catch (SQLException e) {
                    System.out.println("用户名已被占用或SQL错误---"+Insert_Information.get_UpdateSQL(
                            "game_connected_2021.player", "pname", newPname, "PID='" + PID + "';"
                        ));
                    login[k].setDoing(standard.getStandard(39));//Used ID_name to change failed
                    response=standard.getStandard(3);
                    //Used ID or SQLError
                }
                break;
            }
            //此处添加注释：由于修改密码的要求较为特殊，在这里要求在这后边添加"用户的PID"+"修改密码"+"确认密码"
            //如"change-password-----000001741258963741258963"修改密码为：741258963
            case "change-password-----":{
                login[k].setDoing(standard.getStandard(40));//Chaning Password
                //如果修改密码和确认密码位数一个奇数一个偶数，则必定错误
                if ((requestseStr.length()-26)%2!=0){
                    System.out.println("确认密码输入错误，用户IP::"+address);
                    login[k].setDoing(standard.getStandard(41));//Correctpassword Error to fail
                    response=standard.getStandard(6);
                    //Confirm password error
                }
                else {
                    String PID_ = requestseStr.substring(20, 26);
                    int len=requestseStr.length()-26;
                    String changepassword=requestseStr.substring(26,26+len/2);
                    String confirmpassword=requestseStr.substring(26+len/2);
                    if (changepassword.equals(confirmpassword)){
                        try {
                            Insert_Information.run_insert_withroot(
                                Insert_Information.get_UpdateSQL(
                                      "game_connected_2021.player","password",changepassword,"PID='"+PID_+"';"
                                )
                            );
                            System.out.println("密码修改成功，用户IP::"+address);
                            login[k].setDoing(standard.getStandard(42));//change password successfully
                            response=standard.getStandard(7);
                            //Password modified successfully
                        } catch (SQLException e){
                            System.out.println("密码修改失败或SQL错误--"+Insert_Information.get_UpdateSQL(
                                      "game_connected_2021.player","password",changepassword,"PID='"+PID_+"';"
                                ));
                            login[k].setDoing(standard.getStandard(43));//change password failed by SQL
                            response=standard.getStandard(8);
                            //Password modified failed
                        }
                    }
                    else {
                        System.out.println("密码错误");
                        login[k].setDoing(standard.getStandard(41));//Correctpassword Error to fail
                        response=standard.getStandard(16);
                        //Password error
                    }
                }
                break;
            }
            //
            //
//            case "play-game-----------":{
//                login[k].setDoing(standard.getStandard(55));//Ready for begining
//                boolean _flag=false;
//                InetAddress target=null;
//                for (int j=0;j<10;j++){
//                    for (int i=0;i< login.length;i++){
//                        if (login[i]==null) break;//防止空指针错误
//                        //如果遍历到同样正在匹配的玩家，则将进入同一局
//                        if (login[i].getDoing().equals(standard.getStandard(55))
//                                &&
//                                !login[i].getAddress().toString().equals(login[k].getAddress().toString())
//                        ){
//                            target=login[i].getAddress();
//                            game.setTargetAddress(target);
//                            _flag=true;
//                        }
//                        //如果检索数值过大，停止检索
//                        if (_flag) {
//                            break;
//                        }
//                    }
//                    if (_flag) break;
//                    try {//如果匹配不到，系统休眠半秒钟再次遍历
//                        Thread.sleep(500);
//                    } catch (InterruptedException e) {
//                        e.printStackTrace();
//                    }
//                }
//                if (_flag){
//                    login[k].setDoing(standard.getStandard(56));//Get player successfully
//                    response=standard.getStandard(17)+target;
//                    //______________Connected to IP::  +  'IP'
//                }else {
//                    login[k].setDoing(standard.getStandard(57));//Get player failed
//                    response=standard.getStandard(18);
//                    //Connected to IP failed
//                }
//                break;
//            }
            //
            //
            case "play-game-----------":{
                login[k].setDoing(standard.getStandard(58));//Begin playing
                response=standard.getStandard(19)+game.getRegret();
                //Allow to begin
                break;
            }
            //
            //返回一共可以的悔棋次数
            case "ask-regret----------":{
                response= standard.getStandard(20) + xmlReader.get_max_regret();
                break;
            }
            //此处添加注释：用于在分出胜负之后修改数据库中的部分信息。
            //例如：change-score--------0000011（其中的'000001'表示用户ID，后边的1表示胜利）
            case "change-score--------":{
                String targetID=requestseStr.substring(20,26);//1代表胜利，0代表失败
                String end=requestseStr.substring(26);//1代表胜利，0代表失败
                //如果胜利了的话
                try {
                    int winnum_before = 0,defeat_before=0,score_before=0;
                    ResultSet __res_=select_information.RunSQL(
                            select_information.get_selectSQL(
                                    "game_connected_2021.player",
                                    new String[]{"WinNum","DefeatNum","score"},
                                    "PID='"+targetID+"';"
                            )
                    );
                    while (true){
                        assert __res_ != null;
                        if (!__res_.next()) break;
                        winnum_before=Integer.parseInt(__res_.getString("WinNum"));
                        defeat_before=Integer.parseInt(__res_.getString("DefeatNum"));
                        score_before=Integer.parseInt(__res_.getString("score"));
                    }
                    if (end.equals(String.valueOf(1))){
                        System.out.println(Insert_Information.get_UpdateSQL(
                                        "game_connected_2021.player","WinNum",String.valueOf(winnum_before+1),
                                        "PID='"+targetID+"';"
                                ));
                        Insert_Information.run_insert_withroot(
                                Insert_Information.get_UpdateSQL(
                                        "game_connected_2021.player","WinNum",String.valueOf(winnum_before+1),
                                        "PID='"+targetID+"';"
                                )
                        );
                        Insert_Information.run_insert_withroot(
                                Insert_Information.get_UpdateSQL(
                                        "game_connected_2021.player","score",String.valueOf(score_before+100),
                                        "PID='"+targetID+"';"
                                )
                        );
                    } else {
                        Insert_Information.run_insert_withroot(
                                Insert_Information.get_UpdateSQL(
                                        "game_connected_2021.player","WinNum",String.valueOf(defeat_before+1),
                                        "PID='"+targetID+"';"
                                )
                        );
                        if (score_before<=1200)
                            Insert_Information.run_insert_withroot(
                                Insert_Information.get_UpdateSQL(
                                        "game_connected_2021.player","score","1200",
                                        "PID='"+targetID+"';"
                                )
                        );
                    }
                    response=standard.getStandard(23);//Update score successfully
                } catch (SQLException e) {
                    response=standard.getStandard(24);//Update score failed
                    e.printStackTrace();
                }
                break;
            }
            //
            //悔棋
            case "regret--------------":{
                login[k].setDoing(standard.getStandard(44));//regreting
                //悔棋
                boolean flag = game.getRegret();
                if (flag) {
                    Last_step last_step = game.getLast_step();
                    //悔棋操作执行中
                    boolean flag1 = game.regret(last_step.getX(), last_step.getY(), last_step.getThing());
                    if (flag1) {
                        System.out.println("悔棋成功，用户IP::"+address);
                        login[k].setDoing(standard.getStandard(46));//regreting successfully
                        response=standard.getStandard(12);
                        //regret successfully
                    } else {//此时的情况是悔棋次数已用完
                        System.out.println("悔棋次数已用完，用户IP::"+address);
                        login[k].setDoing(standard.getStandard(47));//regreting times over
                        response=standard.getStandard(13);
                        //regret Over
                    }
                } else {//此时的情况时还没有落子，上一步的存储空间为空
                    System.out.println("请先落子，用户IP::"+address);
                    login[k].setDoing(standard.getStandard(45));//Dowing first please
                    response=standard.getStandard(14);
                    //Please Downzi First
                }
                break;
            }
            //这里没啥好讲的，程序直接退出就完事
            //
            case "exit----------------":{
                login[k].setDoing(standard.getStandard(48));//Exiting program————Client
                response=standard.getStandard(15);
                //Exit
                break;
            }
            /*
            * 这里边的传入参数格式要求较为严格，如"luozi-06-16-white"或"luozi-01-06-black"
            * 其实这里的情况也就指的是落子的情况
            * */
            default:{   // if (requestseStr.equals("luozi----- x- y -type")){、
                login[k].setDoing(standard.getStandard(49));//Downing zi
                int x = Integer.parseInt(requestseStr.substring(10,12));
                int y = Integer.parseInt(requestseStr.substring(13,15));
                String thing = requestseStr.substring(16);    //截掉前边的，从第十四位开始读取type对象

                int type = 0;
                if (thing.equals("black")) {
                    //落子是黑子
                    game.Downzi(x, y, -1);
                    login[k].setDoing(standard.getStandard(50));//Dowing Black
                }
                else if (thing.equals("white")) {
                    //落子是白子
                    game.Downzi(x, y, 1);
                    login[k].setDoing(standard.getStandard(51));//Dowing White
                }
                game.Remember(x, y, type);
                int end = game.GetWin();
                if (end == 1) {
                    //白方胜
                    login[k].setDoing(standard.getStandard(52));//White part Wining
                    response=standard.getStandard(9);//White Win
                } else if (end == -1) {
                    //黑方胜
                    login[k].setDoing(standard.getStandard(53));//Black part Wining
                    response=standard.getStandard(10);//Black Win
                } else {
                    login[k].setDoing(standard.getStandard(54));//Continue
                    response=standard.getStandard(11);//Continue Running
                }
                break;
            }
        }
        /*将全部重置之后的login对象返回给*/
        socket_obect.setLogin(login);
        System.out.println("向IP::"+address+"回复的信息为————"+response);
        return new pakcage_to_game(game,response,login);
    }
}
