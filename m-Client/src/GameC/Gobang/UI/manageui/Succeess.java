package GameC.Gobang.UI.manageui;

import javax.swing.*;

public class Succeess {
    String ID;
    String password;
    String confirmpasswd;
    boolean a;
    boolean b;
    //以上都事要返回服务器的值：账户名 密码

    public Succeess(boolean a,boolean b){
        this.a=a;
        this.b=b;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setConfirmpasswd(String confirmpasswd) {
        this.confirmpasswd = confirmpasswd;
    }

    boolean JudgeRegister() throws ClassNotFoundException {

        if(this.ID.equals("")&& a) {
            JOptionPane.showMessageDialog(null, "账号不能为空！", "账号为空",
                    JOptionPane.ERROR_MESSAGE);
            return false;
        }

        if(this.password.equals("")&& b) {
            JOptionPane.showMessageDialog(null, "密码不能为空！", "密码为空",
                    JOptionPane.ERROR_MESSAGE);
            return false;
        }

        if(!this.password.matches("[0-9]+")&&b){
            JOptionPane.showMessageDialog(null, "密码只能为数字！", "密码不为纯数字",
                    JOptionPane.ERROR_MESSAGE);
            return false;
        }

        if(!this.password.equals(this.confirmpasswd)&&b) {
            JOptionPane.showMessageDialog(null, "两次输入的密码不一致!", "密码不一致",
                    JOptionPane.ERROR_MESSAGE);
            return false;
        }

        return true;
    }
}
