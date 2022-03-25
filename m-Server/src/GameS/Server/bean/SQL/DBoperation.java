package GameS.Server.bean.SQL;

import GameS.Server.util.xmlReader;

import java.sql.*;

public class DBoperation {
    public DBoperation(){}
    public Connection getConnection_by_root()throws ClassNotFoundException, SQLException{
        final String sDBDriver="com.mysql.cj.jdbc.Driver";
        String conStr="jdbc:mysql://localhost:3306/game_connected_2021";//填放数据库的链接
        String username;
        String password;
        String[] aa = xmlReader.get_sql_message();
        username=aa[0];
        password=aa[1];
        System.out.println(username);
        System.out.println(password);

        Class.forName(sDBDriver);

        return DriverManager.getConnection(conStr,username,password);
    }
    public Connection getConnection(String conStr,String username,String password)throws ClassNotFoundException,SQLException{
        Class.forName("com.mysql.cj.jdbc.Driver");

        return DriverManager.getConnection(conStr,username,password);
    }
    //方便删除、修改和添加语句，即delete语句、update语句以及insert语句都可以使用
    public void update(Connection conn,String sql)throws SQLException{
        Statement st=conn.createStatement();
        st.executeUpdate(sql);

        st.close();
    }
    //方便执行查询语句，select语句可执行，返回内容用ResultSet存储
    public ResultSet select(Connection conn,String sql)throws SQLException{
        Statement st=conn.createStatement();
        ResultSet result= st.executeQuery(sql);

        st.close();
        return result;
        /*
        * 关于result的解析方法如下：
        * while (res.next()){
        *       String（对象格式，如String） x=res.getString("（这里放的是表上的实体名）");
        *       ......
        * }
        * */
    }
    public void close(Connection conn)throws SQLException{
        conn.close();
    }
}
