package GameC.Gobang.UI;

import GameC.Gobang.main.Client;
import GameC.Gobang.net.get_msg_to_send;
import GameC.Gobang.net.standard;
import GameC.Gobang.util.Inner_to6_Helper;
import javafx.scene.control.Slider;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;

/**
 * 设置按钮监听方法ButtonListener类
 * @author DELL
 *
 */
public class ButtonListener implements GoBangconfig,ActionListener{
    public GoBangFrame gf;
    JFrame jf = new JFrame();

    public ButtonListener(GoBangFrame gf) {
    	this.gf=gf;//获取左半部分的画报
    }

    //当界面发生操作时进行处理
    public void actionPerformed(ActionEvent e) {
    	//获取当前被点击按钮的内容，判断是不是“开始游戏”这个按钮
		switch (e.getActionCommand()) {
			case "开始游戏":{
				System.out.println("开始游戏");
				String response = Client.socket_c.send_get_Message(get_msg_to_send.play_game());
				if (!response.startsWith(standard.getStandard(19)))
					System.exit(0);
				String respnseStr_toregret = Client.socket_c.send_get_Message(get_msg_to_send.get_max_regret()).substring(10);
				GoBangFrame.i = Integer.parseInt(respnseStr_toregret);
				//如果是开始游戏的按钮，在为左半部分设置监听方法
				FrameListener fl = new FrameListener();
				fl.setGraphics(gf);//获取画笔对象

				gf.addMouseListener(fl);
				//判断是否为“悔棋”
				break;
			}
			case "悔棋":{
				System.out.println("悔棋");

				if (gf.getregretUI() >= 0) {//悔棋次数大于零，成功
					gf.regretUI1();
				} else if (gf.getregretUI() < 0) {//悔棋次数小于零，失败
					gf.regretUI2();
				}
				//判断是否为结束游戏
				break;
			}
			case "结束游戏":{
				gf.overUI();//结束页面

				System.out.println("结束游戏");
				Client.socket_c.send_get_Message(get_msg_to_send.exit());
				System.exit(0);
			}
		}
    }
}
