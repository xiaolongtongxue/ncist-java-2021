package GameC.Gobang.net;

import GameC.Gobang.util.Inner_to6_Helper;

public class get_msg_to_send {
    public static String login(String id,String password){
        id= Inner_to6_Helper.inner6(id);
        return "login-request-------"+id+password;
    }
    public static String register(String pname,String password){
        pname=Inner_to6_Helper.inner6(pname);
        return "register------------"+pname+password;
    }
    public static String change_information(String pid,String pname){
        return "change-information--"+pid+pname;
    }
    public static String change_password(String pid,String newpassword,String confirmpassword){
        return "change-password-----"+pid+newpassword+confirmpassword;
    }
    public static String regret(){
        return "regret--------------";
    }
    public static String exit(){
        return "exit----------------";
    }
    public static String play_game(){
        return "play-game-----------";
    }
    public static String luozi(int x,int y,int thing){
        String type,x1,y1;
        if (thing==1)
            type="white";
        else if (thing==-1)
            type="black";
        else
            //如果返回值为空，则证明客户端传参错误
            return null;
        if (x<10) x1="0"+x;
        else x1=String.valueOf(x);
        if (y<10) y1="0"+y;
        else y1=String.valueOf(y);

        return "luozi-----"+x1+"-"+y1+"-"+type;
    }
    public static String get_max_regret(){
        return "ask-regret----------";
    }
}
