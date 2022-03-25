package abgame.util;


import java.util.InputMismatchException;
import java.util.Scanner;

public class InputHelper {
    public static int getInt(){
        int a = 0;
        boolean flag=true;
        while (flag){
            try {//这里的sc必须在循环体里边定义，在外边定义的话可能会出现死循环
                Scanner sc=new Scanner(System.in);
                a= sc.nextInt();
                flag=false;
            } catch (InputMismatchException e){
                System.out.println("请输入正确的数字类型");
            }
        }
        return a;
    }
    public static String getString(){
        Scanner sc=new Scanner(System.in);
        return sc.nextLine();
    }
}
