package abgame.ui;

import abgame.bean.Login;
import abgame.util.InputHelper;

public class Menu {
    //初始化主菜单界面
    public static int getMainUI(){
        System.out.println("*********************************************");
        System.out.println();
        System.out.println("\t\t愤怒的小鸟");
        System.out.println("1.玩家登录\t2.管理员登录\t0.退出");
        System.out.println();
        System.out.println("*********************************************");
        System.out.println("请选择：");
        return InputHelper.getInt();
    }
    //初始化管理员登陆界面
    public static Login getLoginUI(){
        Login login=new Login();
        System.out.println("*****************");

        System.out.print("请输入用户名");
        login.setLoginName(InputHelper.getString());

        System.out.println("请输入密码");
        login.setPassword(InputHelper.getString());

        return login;
    }
}
