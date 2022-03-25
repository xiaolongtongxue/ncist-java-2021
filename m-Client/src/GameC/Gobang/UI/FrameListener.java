package GameC.Gobang.UI;

import GameC.Gobang.main.Client;
import GameC.Gobang.net.get_msg_to_send;
import GameC.Gobang.net.standard;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

/**
 * 实现对GoBangFrame下棋界面的监听接口处理
 */
public class FrameListener implements GoBangconfig,ActionListener, MouseListener{
    public GoBangFrame gf;
    public int turn = 1;//判断当前轮到了谁，1表示黑子，2表示白子

    public void setGraphics(GoBangFrame gf) {
    	this.gf = gf;
    }

    public void mouseClicked(MouseEvent e) {
    	int x = e.getX();
    	int y = e.getY();
    	//计算棋子要落在棋盘哪个交叉点上
    	int countx = (x/30)*30+15;
    	int county = (y/30)*30+15;
    	Graphics g = gf.getGraphics();

    	if(gf.isAvail[(countx-15)/30][(county-15)/30]!=0) {
    		gf.againUI();
    	}else {
    		//当前位置可以落子，先计算棋盘上棋子在数组中相应的位置
    		int colu = (countx-15)/30;
    		int ro = (county-15)/30;

    		if(turn==1) {
    			//先设置颜色
    			g.setColor(Color.black);
    			//落子
    			g.fillOval(countx-size/2, county-size/2, size, size);
    			//设置当前位置已经有棋子了，棋子为黑子
    			gf.isAvail[colu][ro]=1;
    			turn++;
    		}else {
    			g.setColor(Color.white);
    			g.fillOval(countx-size/2, county-size/2, size, size);
    			//设置当前位置已经有棋子了，棋子为白子
    			gf.isAvail[colu][ro]=2;
    			turn--;
    		}

    		int a;
    		if (turn==2)a=-1;
    		else a=1;
			String response=Client.socket_c.send_get_Message(get_msg_to_send.luozi(colu,ro,a));
			if (response.equals(standard.getStandard(10))){
				//黑方胜利
				gf.someBody_win("黑方胜");

			} else if (response.equals(standard.getStandard(9))){
				//白方胜利
				gf.someBody_win("白方胜");
			}  //尚未分出胜负

		}
    }
    //返回坐标的
    public Point point(int colu,int ro) {
    	Point p;
    	p = new Point(colu, ro);
    	return p;
    }
    //自定义棋子坐标
    public void mouseClicked(int colu,int ro) {
    	//计算棋子要落在棋盘哪个交叉点上
    	int countx = colu*30+15;
    	int county = ro*30+15;
    	Graphics g = gf.getGraphics();

    	if(gf.isAvail[(countx-15)/30][(county-15)/30]!=0) {
    		gf.againUI();
    	}else {
    		if(turn==1) {
    			//先设置颜色
    			g.setColor(Color.black);
    			//落子
    			g.fillOval(countx-size/2, county-size/2, size, size);
    			//设置当前位置已经有棋子了，棋子为黑子
    			gf.isAvail[colu][ro]=1;
    			turn++;
    		}else {
    			g.setColor(Color.white);
    			g.fillOval(countx-size/2, county-size/2, size, size);
    			//设置当前位置已经有棋子了，棋子为白子
    			gf.isAvail[colu][ro]=2;
    			turn--;
    		}
    	}
    }

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub

	}


}