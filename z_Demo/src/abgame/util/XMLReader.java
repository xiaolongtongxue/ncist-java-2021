package abgame.util;

import abgame.bean.Login;
import org.dom4j.Document;
import org.dom4j.Element;

public class XMLReader {
    public static Login getAdminLogin(Document doc){
        //获取根节点
        Element root=doc.getRootElement();
        //获取admin-loginname的值
        String loginname= root.element("admin").elementText("loginname");
        //获取admin-password的值
        String password= root.element("admin").elementText("password");
        String logintimes= root.element("admin").elementText("lodintimes");

        Login admin=new Login();
        admin.setLoginName(loginname);
        admin.setPassword(password);
        admin.setLogintime(Integer.parseInt(logintimes));


        return admin;
    }
}
