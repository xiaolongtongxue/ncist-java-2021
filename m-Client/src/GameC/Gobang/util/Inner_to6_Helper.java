package GameC.Gobang.util;

public class Inner_to6_Helper {
    public static String inner6(String str){
        if (str.length()>=6)return str;
        else {
            StringBuilder strBuilder = new StringBuilder(str);
            int num=6-strBuilder.length();
            for (int i = 0; i<num; i++)
                strBuilder.append("_");
            str = strBuilder.toString();
            return str;
        }
    }
}
