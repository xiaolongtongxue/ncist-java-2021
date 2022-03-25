package abgame.main;

import abgame.bean.Login;
import abgame.ui.Menu;

//管理员操作类
public class AdminManager {
    public boolean adminOP(){
        for (int i=0;i<DataInit.login.getLogintime();i++) {
            Login login = Menu.getLoginUI();
            boolean b = chkLogin(login);
            if (b) {
                System.out.println("登陆成功");
                return true;
            }
            else {
                System.out.println("账号或密码错误，请重新输入，您还有"+(DataInit.login.getLogintime()-i-1)+"次机会");
                if (DataInit.login.getLogintime()-i-1==0){
                    System.out.println(DataInit.login.getLogintime()+"次机会已用完，系统自动退出");
                    System.exit(0);
                    return false;
                }
            }
        }
        return false;
    }
    public boolean chkLogin(Login login){
        if (login.getLoginName().equals(DataInit.login.getLoginName())
                &&login.getPassword().equals(DataInit.login.getPassword()))
            return true;
        else {
            //System.out.println("用户名或密码错误");
            return false;
        }
    }
}
