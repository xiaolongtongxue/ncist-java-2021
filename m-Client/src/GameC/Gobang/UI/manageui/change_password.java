package GameC.Gobang.UI.manageui;

import GameC.Gobang.main.Client;
import GameC.Gobang.net.get_msg_to_send;
import GameC.Gobang.net.standard;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class change_password extends JFrame implements ActionListener {
    JLabel rigister1;
    JLabel idStr;
    JLabel passwordStr;
    JLabel confirmStr;
    JTextField id;
    JPasswordField password;
    JPasswordField confirm;
    JButton rg1;

    public change_password() {
        this.setVisible(true);
        this.setSize(250, 220);
        this.setVisible(true);
        this.setLocation(500, 250);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        rigister1 = new JLabel("密码修改界面");
        passwordStr = new JLabel("密码：");
        confirmStr = new JLabel("确认密码");
        id = new JTextField();
        password = new JPasswordField();

        confirm = new JPasswordField();
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
            hGroup.addGroup(layout.createParallelGroup().addComponent(passwordStr).addComponent(confirmStr));
            hGroup.addGap(5);
            hGroup.addGroup(layout.createParallelGroup().addComponent(rigister1).addComponent(password).
                    addComponent(confirm)
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
            vGroup.addGroup(layout.createParallelGroup().addComponent(confirmStr).addComponent(confirm));
            vGroup.addGap(10);
            vGroup.addGroup(layout.createParallelGroup().addComponent(rg1));
            vGroup.addGap(40);

            // 设置垂直组
            layout.setVerticalGroup(vGroup);
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String passwd = new String(password.getPassword());         //输入的密码
        String confirmpasswd = new String(confirm.getPassword());   //二次输入的确认密码

        Succeess succeess = new Succeess(false,true);
        succeess.setID("");
        succeess.setPassword(passwd);
        succeess.setConfirmpasswd(confirmpasswd);

        try {
            if(succeess.JudgeRegister()){
                String response= Client.socket_c.send_get_Message(get_msg_to_send.change_password(Client.ID,passwd,confirmpasswd));
                if (response .equals(standard.getStandard(7))){
                    JOptionPane.showMessageDialog(null, "密码修改成功,请重新登录");
                    System.out.println("Register Successfully");
                    this.setVisible(false);
                    new Manager();
                }
                else{
                    System.out.println("Password too long");
                    JOptionPane.showMessageDialog(null, "密码过长，请重新设置（不得超过16位）", "异常",
                        JOptionPane.ERROR_MESSAGE);
                }
            }
        } catch (ClassNotFoundException throwables) {   //SQL异常被抹去了，没那个必要，不可能抛出的
            throwables.printStackTrace();
        }
    }
}
