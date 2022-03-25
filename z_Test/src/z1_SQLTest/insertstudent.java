package z1_SQLTest;

import java.sql.Connection;
import java.sql.SQLException;

public class insertstudent {
    public static void insert(){
        String sql;
        sql= //"create database TEST1";
            //"create table student(name char(6),age int,grade float);";
            //"insert into student(name,age,grade)values('Tom1',10,90),('John1',11,91)";
            //"select OPN,PCN,OPETIME,ENDTIME from opm";
            //"delete from z_end_work.computer where PCN='C037'";
            //"select PID from game_connected_2021.player where PID='111111'";
            //"insert into game_connected_2021.player(PID,pname,score,WinNum,DefeatNUM) values('000001','Tom','1200','0','0')";
            "select PID,pname from game_connected_2021.player where (PID='Tom' or pname='Tom') and password='123456';";

        dboperation db=new dboperation();
        try {
            Connection con=db.getConnection();
            db.select(con,sql);
//            db.update(con,sql);
            db.close(con);
        } catch (ClassNotFoundException e) {
            System.out.println("数据库驱动不存在");
            e.printStackTrace();
        } catch (SQLException throwables) {
            System.out.println("数据库操作错误");
            throwables.printStackTrace();
        }

    }
}
