package GameS.Server.util;

public class gotString {
    public static String get____(String a,char b){
        for (int i=0;i<a.length();i++){
            if (a.charAt(i)==b)
                return a.substring(0,i);
        }
        return a;
    }
}
