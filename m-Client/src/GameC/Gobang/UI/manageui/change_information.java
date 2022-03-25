package GameC.Gobang.UI.manageui;

import GameC.Gobang.main.Client;
import GameC.Gobang.net.get_msg_to_send;
import GameC.Gobang.net.standard;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class change_information extends JFrame implements ActionListener {
    JLabel rigister1;
    JLabel passwordStr;
    JTextField password;
    JButton rg1;

    public change_information() {
        this.setVisible(true);
        this.setSize(250, 220);
        this.setVisible(true);
        this.setLocation(500, 250);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        rigister1 = new JLabel("昵称修改界面");
        passwordStr = new JLabel("新名字：");
        password = new JTextField();

        rg1 = new JButton("确认修改");
        rg1.addActionListener( this);

        // 为指定的 Container 创建 GroupLayout
        GroupLayout layout = new GroupLayout(this.getContentPane());
        this.getContentPane().setLayout(layout);
        {
            // 创建GroupLayout的水平连续组，，越先加入的ParallelGroup，优先级级别越高。
            GroupLayout.SequentialGroup hGroup = layout.createSequentialGroup();

            // 添加间隔
            hGroup.addGap(5);
            hGroup.addGroup(layout.createParallelGroup().addComponent(passwordStr));
            hGroup.addGap(5);
            hGroup.addGroup(layout.createParallelGroup().addComponent(rigister1).addComponent(password)
                    .addComponent(rg1));
            hGroup.addGap(5);

            // 设置水平分组
            layout.setHorizontalGroup(hGroup);

            // 创建GroupLayout的垂直连续组，越先加入的ParallelGroup，优先级级别越高。
            GroupLayout.SequentialGroup vGroup = layout.createSequentialGroup();
            vGroup.addGap(10);
            vGroup.addGroup(layout.createParallelGroup().addComponent(rigister1));
            vGroup.addGap(10);
            vGroup.addGroup(layout.createParallelGroup().addComponent(passwordStr).addComponent(password));
            vGroup.addGap(10);
            vGroup.addGroup(layout.createParallelGroup().addComponent(rg1));
            vGroup.addGap(40);

            // 设置垂直组
            layout.setVerticalGroup(vGroup);
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String passwd = password.getText();         //输入的密码

        Succeess succeess = new Succeess(false,false);
        succeess.setID("");
        succeess.setPassword(passwd);

        try {
            if(succeess.JudgeRegister()){
                String response= Client.socket_c.send_get_Message(get_msg_to_send.change_information(Client.ID,passwd));
                if (response .equals(standard.getStandard(5))){
                    JOptionPane.showMessageDialog(null, "昵称修改成功,请重新登录");
                    System.out.println("change information Successfully");
                    this.setVisible(false);
                    new Manager();
//                    System.exit(0);
                }
                else{
                    System.out.println("Used ID");
                    JOptionPane.showMessageDialog(null, "密码过长，请重新设置（不得超过16位）", "异常",
                        JOptionPane.ERROR_MESSAGE);
                }
            }
        } catch (ClassNotFoundException throwables) {   //SQL异常被抹去了，没那个必要，不可能抛出的
            throwables.printStackTrace();
        }
    }
}
