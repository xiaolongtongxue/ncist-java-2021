package GameC.Gobang.UI.manageui;

import GameC.Gobang.UI.GoBangFrame;
import GameC.Gobang.main.Client;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

class view3 extends JFrame implements ActionListener {
    JFrame frame1 = new JFrame("账号界面");
    public JPanel east1;
    public JPanel center1;
    public JPanel center2;
    public JPanel center3;
    public JButton button1;
    public JButton button3;
    public String Username;

    public view3 (String part_name,String Userid,String username){
        Client.ID=Userid;
        frame1=new JFrame();

        this.Username=username;

        Container contentPane1 = frame1.getContentPane();
        contentPane1.setLayout(new

        BorderLayout());
        frame1.setBounds(500,250,400,300);
        frame1.setVisible(true);
        JPanel east1 = new JPanel();
        JLabel label1 = new JLabel("账号|" +Userid+"                                                                ");
        JLabel label2 = new JLabel("昵称|"+username+"                                                             ");
        JLabel label3 = new JLabel("当前段位|"+part_name+"                                                     ");
        east1.add(label1);
        east1.add(label2);
        east1.add(label3);
        frame1.add(east1,BorderLayout.CENTER);
        JPanel center1 = new JPanel();
        JPanel center2 = new JPanel();
        JPanel center3 = new JPanel();
        JButton button_ = new JButton("修改密码");
        JButton button1 = new JButton("开始游戏");
        JButton button3 = new JButton("退出游戏");
        JButton button4 = new JButton("修改网名");
        center1.add(button_);
        center1.add(button1);
        center1.add(button3);
        center1.add(button4);
        button_.addActionListener(this);
        button1.addActionListener(this);
        button3.addActionListener(this);
        button4.addActionListener(this);
        frame1.add(center1,BorderLayout.SOUTH);
    }//登录成功

    public void actionPerformed(ActionEvent e) {
        if(e.getActionCommand().equals("退出游戏")){
            System.exit(0);
            this.setVisible(false);
        }
        else if(e.getActionCommand().equals("开始游戏")){
//            this.setVisible(false);
            //跳转游戏部分;
            GoBangFrame gf = new GoBangFrame();//初始化一个五子棋的对象
    	    gf.initUI();//调用方法初始化界面
        }
        else if (e.getActionCommand().equals("修改密码")){
            new change_password();
            this.setVisible(false);
        }
        else if (e.getActionCommand().equals("修改网名")){
            new change_information();
            this.setVisible(false);
        }
    }
}
