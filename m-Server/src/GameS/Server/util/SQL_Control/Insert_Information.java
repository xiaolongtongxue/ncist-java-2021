package GameS.Server.util.SQL_Control;

import GameS.Server.bean.SQL.DBoperation;

import java.sql.Connection;
import java.sql.SQLException;

public class Insert_Information {
    public static String get_InsertSQL(String table,String[] type,String[][] values){
        StringBuilder sql= new StringBuilder("insert into " + table + " (");
        for (int i=0;i< type.length;i++){
            sql.append(type[i]);
            if (i!=type.length-1)
                sql.append(",");
        }
        sql.append(") values ");
        for (int i=0;i< values.length;i++){
            sql.append("(");
            for (int j=0;j<values[i].length;j++){
                sql.append("'");
                sql.append(values[i][j]);
                if (j!=values[i].length-1){
                    sql.append("',");
                }
            }
            sql.append("')");
            if (i!= values.length-1){
                sql.append(",");
            }
            else
                sql.append(";");
        }
        return sql.toString();
    }
    public static String get_UpdateSQL(String table,String type,String later,String condition){
        return "update "+table+" set "+type+"='"+later+"' where "+condition;
    }
    public static boolean run_insert_withroot(String sql) throws SQLException {
        DBoperation DB=new DBoperation();
        try {
            Connection conn=DB.getConnection_by_root();
            DB.update(conn,sql);
            DB.close(conn);
            return true;
        } catch (ClassNotFoundException e) {
            System.out.println("数据库驱动错误");
            e.printStackTrace();
            return false;
        } catch (SQLException throwables) {
            System.out.println("SQL语句出错---"+sql);
            throwables.printStackTrace();
            return false;
        }
    }
    public static void run_insert_without_root(String conStr,String username,String password,String sql)
    throws SQLException{
        DBoperation DB=new DBoperation();
        try {
            Connection conn= DB.getConnection(conStr,username,password);
            DB.update(conn,sql);
            DB.close(conn);
        } catch (ClassNotFoundException e) {
            System.out.println("数据库驱动错误");
            e.printStackTrace();
        } catch (SQLException throwables) {
            System.out.println("SQL语句出错");
            throwables.printStackTrace();
        }
    }
}
