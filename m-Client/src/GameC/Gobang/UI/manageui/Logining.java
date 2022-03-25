package GameC.Gobang.UI.manageui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Logining implements ActionListener {
    private final long seriaVersionUID=1L;
    private JFrame mainFrame;
    //按钮登陆界面，显示信息
    private JPanel top;
    private JLabel Labeltop;
    private JButton BtnLogin;
    //注册界面
    private JPanel middle;
    private JButton BtnRegister;
    //退出界面
    private JPanel bottom;
    private JButton BtnQuit;

    public Logining(String title){
        mainFrame=new JFrame(title);
    }
    public void run(){
        mainFrame.setBounds(500,250,350,350);
        mainFrame.setVisible(true);
        mainFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        mainFrame.setSize(350,300);

        JLogin();
        JRegister();
        JQuit();
        Jpicture();
        mainFrame.validate();

    }

    //登录
    public void JLogin(){
        top=new JPanel();
        Labeltop=new JLabel("联机五子棋");
        Labeltop.setFont(new Font("宋体",Font.PLAIN,14));
        //BtnLogin=new JButton("登录");
        top.add(Labeltop);
        //top.add(BtnLogin);
        mainFrame.add(top, BorderLayout.NORTH);
        //BtnLogin.addActionListener(this);
    }
//注册
    public void JRegister(){
        middle=new JPanel();
        BtnRegister=new JButton("注册");
        BtnLogin=new JButton("登录");
        middle.add(BtnLogin);
        middle.add(BtnRegister);
        mainFrame.add(middle,BorderLayout.CENTER);
        BtnRegister.addActionListener(this);
        BtnLogin.addActionListener(this);
    }
    //退出
    public void JQuit(){
        bottom=new JPanel();
        BtnQuit=new JButton("退出");
        bottom.add(BtnQuit);
        mainFrame.add(bottom,BorderLayout.SOUTH);
        BtnQuit.addActionListener(this);
    }

    public void Jpicture(){
        JPanel titlePanel = new JPanel();
        ImageIcon icon = new ImageIcon("m-Client/image/GoBang.png");
        JLabel titleLabel = new JLabel();
        titlePanel.setBounds(0,0,180,180);
        icon.setImage(icon.getImage().getScaledInstance(titlePanel.getWidth(),titlePanel.getHeight(),Image.SCALE_DEFAULT));//设置图像大小
        titleLabel.setIcon(icon);
        titlePanel.add(titleLabel);
        mainFrame.add(titlePanel,BorderLayout.NORTH);

    }
    public void actionPerformed(ActionEvent e) {
        String inputText=e.getActionCommand();
        switch (inputText) {
            case "登录": {
                boolean a = false;
                mainFrame.setVisible(a);//如果点击了登录按钮，则关闭本窗口并跳转到下一界面

                Manager manager = new Manager();
                break;
            }
            case "注册": {
                boolean a = false;
                mainFrame.setVisible(a);//注册按钮同理

                Rigister rigister = new Rigister();
                break;
            }
            case "退出":
                System.exit(0);
        }

    }
}
