package GameC.Gobang.util;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import java.io.File;

public class xmlReader {
    public static String get_Server_IP(){
        Document doc;
        SAXReader reader=new SAXReader();
        File file=new File("m-Client\\Client_XML\\DataInit.xml");
        try {
            doc=reader.read(file);
            //获取根节点
            Element root=doc.getRootElement();
            //直接获取
            String targetip= root.elementText("targetip");

            return targetip;
        } catch (DocumentException e) {
            e.printStackTrace();
            return "";
        }
    }
}
