package GameC.Gobang.UI.manageui;

import GameC.Gobang.main.Client;
import GameC.Gobang.net.get_msg_to_send;
import GameC.Gobang.net.standard;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Manager extends JFrame implements ActionListener {
    private static final long serialVersionUID = -5159330521192113057L;


    JLabel label1;
    JLabel label2;
    JLabel label3;
    JTextField tf;
    JPasswordField psf;
    JRadioButton rb1;
    JRadioButton rb2;
    JButton bt1;
    public static String Userid;
    String Userkey;


    public Manager() {
        this.setVisible(true);
        this.setSize(350, 200);
        this.setVisible(true);
        this.setLocation(500, 250);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        label1 = new JLabel("登陆界面");
        label2 = new JLabel("账号：");
        label3 = new JLabel("密码：");
        tf = new JTextField();
        psf = new JPasswordField();
        psf.setEchoChar('*');

        /*Userid=tf.getText();//数据库生成的账号
        Userkey=psf.getColumns();//用户自定义的密码,需要传回数据库进行判断
        */
        rb1 = new JRadioButton("记住密码");
        rb2 = new JRadioButton("自动登陆");
        bt1 = new JButton("登录");
        bt1.addActionListener(this);
        //bt2= new JButton("登录");

        // 为指定的 Container 创建 GroupLayout
        GroupLayout layout = new GroupLayout(this.getContentPane());
        this.getContentPane().setLayout(layout);
        /*
        * 这一块的东西，因功能相近我就打包了
        * */
        {
            // 创建GroupLayout的水平连续组，，越先加入的ParallelGroup，优先级级别越高。
            GroupLayout.SequentialGroup hGroup = layout.createSequentialGroup();
            // 添加间隔
            hGroup.addGap(5);
            hGroup.addGroup(layout.createParallelGroup().addComponent(label2).addComponent(label3));
            hGroup.addGap(5);
            hGroup.addGroup(layout.createParallelGroup().addComponent(label1).addComponent(psf).addComponent(rb1)
                    .addComponent(rb2).addComponent(tf).addComponent(bt1));
            hGroup.addGap(5);
            // 设置水平分组
            layout.setHorizontalGroup(hGroup);

            // 创建GroupLayout的垂直连续组，越先加入的ParallelGroup，优先级级别越高。
            GroupLayout.SequentialGroup vGroup = layout.createSequentialGroup();
            vGroup.addGap(10);
            vGroup.addGroup(layout.createParallelGroup().addComponent(label1));
            vGroup.addGap(10);
            vGroup.addGroup(layout.createParallelGroup().addComponent(label2).addComponent(tf));
            vGroup.addGap(5);
            vGroup.addGroup(layout.createParallelGroup().addComponent(label3).addComponent(psf));
            vGroup.addGroup(layout.createParallelGroup().addComponent(rb1));
            vGroup.addGroup(layout.createParallelGroup().addComponent(rb2));
            vGroup.addGroup(layout.createParallelGroup().addComponent(bt1));
            /*vGroup.addGap(100);
            vGroup.addGroup(layout.createParallelGroup(Alignment.TRAILING).addComponent(bt1));
            vGroup.addGroup(layout.createParallelGroup(Alignment.TRAILING).addComponent(bt2));*/
            // 设置垂直组
            layout.setVerticalGroup(vGroup);
        }
    }

    public void actionPerformed(ActionEvent e) {
        Userid=tf.getText();//数据库生成的账号
        Userkey= String.valueOf(psf.getPassword());//用户自定义的密码,需要传回数据库进行判断

        String response= Client.socket_c.send_get_Message(get_msg_to_send.login(Userid,Userkey));

        if (response.equals(standard.getStandard(0))){
            boolean c = false;
            this.setVisible(c);
            String reponseStr=Client.socket_c.send_get_Message("get-information-----"+Userid);

            new view3(reponseStr.substring(20,24),reponseStr.substring(24,30),reponseStr.substring(30));
        } else if (response.equals(standard.getStandard(1))){
            JOptionPane.showMessageDialog(null,
                    "账号或密码错误", "登录失败!",
                    JOptionPane.ERROR_MESSAGE);
        } else {
            System.out.println("Error");
            System.exit(0);
        }
        if(e.getActionCommand().equals("退出游戏")){
            System.exit(0);
            this.setVisible(false);
        }
    }
}

