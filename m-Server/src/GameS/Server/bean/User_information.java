package GameS.Server.bean;

import GameS.Server.util.SQL_Control.Insert_Information;

import java.sql.SQLException;

public class User_information {
    private final String PID;
    private String Pname;
    private int Score;
    public User_information(String pid, String pname, int score) {
        this.PID = pid;
        this.Pname = pname;
        this.Score = score;
    }
    public String getPID() {
        return PID;
    }
    public String getPname() {
        return Pname;
    }
    public int getScore() {
        return Score;
    }
    public void setPname(String pname) {
        this.Pname = pname;
        String sql= Insert_Information.get_UpdateSQL("game_connected_2021.player","PID",pname,"PID='"+this.PID+"'");
        try {
            Insert_Information.run_insert_withroot(sql);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
    public void setScore(int score) {
        this.Score = score;
        String sql=Insert_Information.get_UpdateSQL("update game_connected_2021.player","score", String.valueOf(score),"PID='"+this.PID+"'");
        try {
            Insert_Information.run_insert_withroot(sql);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}
