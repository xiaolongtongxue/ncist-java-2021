package abgame.main;

import abgame.bean.Login;
import abgame.util.XMLReader;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.io.SAXReader;

import java.io.File;

public final class DataInit {
    //管理员的对象
    public static Login login=new Login();
    //document的对象
    public static Document doc=null;
    /*用于初始化读取xml文件，仅执行一次*/
    static {
        SAXReader reader=new SAXReader();
        File file=new File("z_Demo\\Datainit.xml");
        try {
            doc=reader.read(file);
            login=XMLReader.getAdminLogin(doc);//此处的Login不应重复定,义，会导致出现字符为空的错误
        } catch (DocumentException e) {
            e.printStackTrace();
        }
    }
}
