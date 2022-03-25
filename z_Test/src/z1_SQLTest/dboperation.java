package z1_SQLTest;

import java.sql.*;
import java.util.ArrayList;
import java.util.Collections;

public class dboperation {
    public dboperation(){}
    public Connection getConnection()throws ClassNotFoundException,SQLException{
        String sDBDriver="com.mysql.cj.jdbc.Driver";//固定的jar包里边的路径
        String conStr="jdbc:mysql://localhost:3306/game_connected_2021";
        String username="root";
        String password="123456";

        Class.forName(sDBDriver);
        Connection conn=DriverManager.getConnection(conStr,username,password);

        return conn;
    }
    public void update(Connection conn,String sql)throws SQLException{
        Statement st= conn.createStatement();   //根据链接创立关系
        st.executeUpdate(sql);                  //执行对应的sql语句

        st.close();                             //更新结束，连接关闭
    }
    public void select(Connection conn,String sql)throws SQLException{
        ArrayList<Integer> a=new ArrayList<>();
        Statement st=conn.createStatement();
        ResultSet res= st.executeQuery(sql);
        System.out.println(res.getRow());
        while (res.next()){
            String PID=res.getString("PID");
            System.out.println("aaaaaa"+PID.length());
            a.add(Integer.parseInt(PID));
//            String pcn=res.getString("PCN");
////            String opetime=res.getString("OPETIME");
////            String endtime=res.getString("ENDTIME");
//            Timestamp opetime=res.getTimestamp("OPETIME");
//            Timestamp endtime=res.getTimestamp("ENDTIME");
            //System.out.println(opn+" "+pcn+" "+opetime+" "+endtime);
            System.out.println(PID);
            System.out.println(res.getString("pname"));
        }
//        Integer obj= Collections.max(a);
//        System.out.println(obj +1);
        st.close();
    }
    public void close(Connection conn)throws SQLException{
        conn.close();   //整个连接进行关闭操作
    }
}
