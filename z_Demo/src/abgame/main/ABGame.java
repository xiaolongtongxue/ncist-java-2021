package abgame.main;

import abgame.ui.Menu;

public class ABGame {
    public static void main(String[] args) {
        boolean b=true;
        while (b) {
            AdminManager am = new AdminManager();
            int c = Menu.getMainUI();
            switch (c) {
                case 1:
                    System.out.println("玩家登录界面");
                    break;
                case 2:
//                    System.out.println("管理员登录界面");
                    b=am.adminOP();
                    break;
                case 0:
                    System.out.println("感谢使用");
                    System.exit(0);
                    break;
                default:
                    System.out.println("请输入正确的选择");
                    break;
            }
        }
    }
}
