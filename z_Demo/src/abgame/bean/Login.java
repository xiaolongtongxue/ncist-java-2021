package abgame.bean;

public class Login {
    private String LoginName;
    private String password;
    private int Logintime;

    public void setLoginName(String loginName) {
        LoginName = loginName;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public void setLogintime(int logintime) {
        Logintime = logintime;
    }
    public String getLoginName() {
        return LoginName;
    }
    public String getPassword() {
        return password;
    }
    public int getLogintime() {
        return Logintime;
    }
}
