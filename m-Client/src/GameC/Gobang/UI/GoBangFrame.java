package GameC.Gobang.UI;

import GameC.Gobang.main.Client;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;

import javax.swing.*;

/**
 * 五子棋界面类
 * @author DELL
 *
 */
public class GoBangFrame extends JPanel implements GoBangconfig {
    public Graphics g;//定义一支画笔
    public int[][] isAvail = new int [21][21];//定义一个数组用来存储棋子的位置
    /**
     * 游戏的主界面
     */
    public void initUI() {
    	//初始化一个界面，并设置标题大小等属性
    	JFrame jf = new JFrame();
    	jf.setTitle("联机五子棋");//窗口标题“联机五子棋”
    	jf.setSize(800,670);//width,height，窗口大小
    	jf.setLocationRelativeTo(null);//将窗口至于屏幕中央
    	jf.setDefaultCloseOperation(3);//直接关闭应用程序

    	jf.setLayout(new BorderLayout());//设置顶级容器JFrame为框架布局

    	Dimension dim1 = new Dimension(150,0);//设置右半部分的大小
    	Dimension dim3 = new Dimension(630,0);//设置左半部分的大小
    	Dimension dim2 = new Dimension(120,40);//设置右边按钮组件的大小

    	//实现左边的界面，吧GoBangFrame的对象添加到框架布局的中间部分
    	this.setPreferredSize(dim3);//设置下棋界面的大小
    	this.setBackground(Color.LIGHT_GRAY);//设置下棋界面的颜色

    	jf.add(this, BorderLayout.CENTER);//添加到布局的中间区域

    	//实现右边的JPanel界面
    	JPanel jp = new JPanel();
    	jp.setPreferredSize(dim1);//设置JPanel的大小
    	jp.setBackground(Color.white);//设置右边界面颜色为白色
    	jf.add(jp,BorderLayout.EAST);//添加到框架布局的东边部分
    	jp.setLayout(new FlowLayout());//设置JPanel为流式布局

    	//将按钮组件添加到JPanel上
    	//设置按钮数据
    	String[] btnname = {"开始游戏","悔棋","结束游戏"};
    	JButton[] button = new JButton[3];

    	//依次将三个按钮添加
    	for(int i=0;i<btnname.length;i++) {
    		button[i] = new JButton(btnname[i]);
    		button[i].setPreferredSize(dim2);//按钮大小
    		jp.add(button[i]);//添加按钮
    	}

    	//用户信息
    	String[] jlabelname = {"用户id:","用户姓名:","用户段位:","用户积分:"};
    	JLabel[] jlabel = new JLabel[4];
    	//用户信息显示区
    	JTextArea[] jtextarea = new JTextArea[4];

    	//依次添加
    	for(int i=0;i<4;i++) {
    		jlabel[i] = new JLabel(jlabelname[i]);
    		jtextarea[i] = new JTextArea(1,15);//信息显示区域设置
    		jp.add(jlabel[i]);
    		jp.add(jtextarea[i]);
    	}

    	//按钮事件监听
    	ButtonListener bl = new ButtonListener(this);
    	for(int i=0;i<btnname.length;i++) {
    	    button[i].addActionListener(bl);
    	}

    	jf.setVisible(true);//设置窗口可见
    }

    public static int i=5;//Integer.parseInt(Client.socket_c.send_get_Message(get_msg_to_send.get_max_regret()).substring(10));
    //悔棋剩余次数的类
    public int getregretUI() {
    	i--;
    	return i;
    }
    /**
     * 悔棋成功的界面
     */
    public void regretUI1() {
    	//初始化一个界面，并设置标题大小等属性
    	JFrame jf = new JFrame();
    	jf.setTitle("悔棋");//窗口标题“悔棋”
    	jf.setSize(250,170);//width,height，窗口大小
    	jf.setLocationRelativeTo(null);//将窗口至于屏幕中央
    	jf.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);//关闭窗口
    	//在窗口上创建一个标签，显示相应信息
    	JPanel jp = new JPanel();
    	JLabel jl1 = new JLabel(/*"悔棋成功,剩余悔棋次数："+i*/"小朋友下棋认真点哦，不能悔棋的呢");
    	jp.add(jl1);
    	jf.add(jp,BorderLayout.CENTER);//标签置于布局的中间

    	jf.setVisible(true);//设置窗口可见
    }
    /**
     * 悔棋失败
     */
    public void regretUI2() {
    	//初始化一个界面，并设置标题大小等属性
    	JFrame jf = new JFrame();
    	jf.setTitle("悔棋");//窗口标题“悔棋”
    	jf.setSize(250,100);//width,height，窗口大小
    	jf.setLocationRelativeTo(null);//将窗口至于屏幕中央
    	jf.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);//关闭窗口(可以替换为2)
    	//在窗口上创建一个标签，显示相应信息
    	JPanel jp = new JPanel();
    	JLabel jl = new JLabel("小朋友下棋认真点哦，不能悔棋的呢");
    	jp.add(jl);
    	jf.add(jp,BorderLayout.CENTER);

    	jf.setVisible(true);//设置窗口可见
    }
    /**
     * 此处已有棋子,提示界面
     */
    public void againUI() {
    	//初始化一个界面，并设置标题大小等属性
    	JFrame jf = new JFrame();
    	jf.setTitle("error");//窗口标题“error”
    	jf.setSize(250,100);//width,height，窗口大小
    	jf.setLocationRelativeTo(null);//将窗口至于屏幕中央
    	jf.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);//关闭窗口(可以替换为2)
    	//在窗口上创建一个标签，显示相应信息
    	JPanel jp = new JPanel();
    	JLabel jl = new JLabel("此处已有棋子,请在其他位置落子");
    	jp.add(jl);
    	jf.add(jp,BorderLayout.CENTER);

    	jf.setVisible(true);//设置窗口可见
    }
    /**
    *  出现获胜者的情况
	**/
	public void someBody_win(String words){
		//初始化一个界面，并设置标题大小等属性
    	JFrame jf = new JFrame();
    	jf.setTitle(words);//窗口标题“黑方胜”或“白方胜”
    	jf.setSize(250,100);//width,height，窗口大小
    	jf.setLocationRelativeTo(null);//将窗口至于屏幕中央
    	jf.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);//关闭窗口(可以替换为2)
    	//在窗口上创建一个标签，显示相应信息
    	JPanel jp = new JPanel();
    	JLabel jl = new JLabel(words.substring(0,2)+"取得胜利！！！！");
    	String responseStr;
    	if (words.substring(0,2).equals("黑方")){
    		responseStr= Client.socket_c.send_get_Message("change-score--------"+Client.ID+"1");
		}
    	else
    		responseStr= Client.socket_c.send_get_Message("change-score--------"+Client.ID+"0");
    	jp.add(jl);
    	jf.add(jp,BorderLayout.CENTER);

    	jf.setVisible(true);//设置窗口可见
	}
    /**
     * 选择是否结束游戏的的界面
     */
    public void overUI() {
    	//初始化一个界面，并设置标题大小等属性
    	JFrame jf = new JFrame();
    	JPanel jp1 = new JPanel();
    	JPanel jp2 = new JPanel();

    	jf.setTitle("结束游戏");//窗口标题“结束游戏”
    	jf.setSize(250,120);//width,height，窗口大小
    	jf.setLocationRelativeTo(null);//将窗口至于屏幕中央
    	jf.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);//关闭窗口

    	jf.setLayout(new BorderLayout());//设置顶级容器JFrame为框架布局

    	Dimension dim = new Dimension(90,30);//设置按钮组件的大小

    	//在窗口上创建一个标签，显示相应信息
    	JLabel jl = new JLabel("游戏还未分出胜负，确定结束游戏？");
    	jp1.add(jl);
    	jf.add(jp1,BorderLayout.CENTER);

    	//在窗口设置两个按钮“确定”，“取消”
    	JButton jb = new JButton("确定");
    	jp2.add(jb);

    	jb.setPreferredSize(dim);//设置按钮的大小
    	jf.add(jp2,BorderLayout.SOUTH);

    	//按钮事件监听
    	ButtonListener bl = new ButtonListener(this);
    	jb.addActionListener(bl);

    	jf.setVisible(true);//设置窗口可见
    }

    //重写
    public void paint(Graphics g) {
    	super.paint(g);

    	//重绘棋盘
    	g.setColor(Color.black);//棋盘线条颜色
    	for(int i=0;i<row;i++) {
    		g.drawLine(x, y+size*i, x+size*(column-1), y+size*i);
    	}
    	for(int j=0;j<column;j++) {
    		g.drawLine(x+size*j, y, x+size*j, y+size*(row-1));
    	}

    	//重绘出棋子
    	for(int i=0;i<row;i++) {
    		for(int j=0;j<column;j++) {//黑子先走
    			if(isAvail[i][j] == 1) {
    				int countx = size*i+15;
    				int county = size*j+15;
    				g.setColor(Color.black);//棋子颜色，黑色
    				g.fillOval(countx-size/2, county-size/2, size, size);//棋子的（x，y，width，height）
    			}else if(isAvail[i][j] == 2) {
    				int countx = size*i+15;
    				int county = size*j+15;
    				g.setColor(Color.white);//白色
    				g.fillOval(countx-size/2, county-size/2, size, size);
    			}
    		}
    	}
    }
}
