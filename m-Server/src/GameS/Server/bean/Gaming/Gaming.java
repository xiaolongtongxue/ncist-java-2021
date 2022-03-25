package GameS.Server.bean.Gaming;

import GameS.Server.util.xmlReader;

import java.net.InetAddress;

public class Gaming {
    private final Point[][] point=new Point[21][21];
    private int black_regret= xmlReader.get_max_regret();
    private int white_regret= xmlReader.get_max_regret();
    private Last_step last_step=new Last_step();
    private final InetAddress this_address;
    private InetAddress targetAddress;
//    private int myThing=0;//设置自己手中棋子的颜色暂时没用上
    public Gaming(InetAddress address){
        if (this.white_regret==-1||this.black_regret==-1){
            System.out.println("XML读取出错");
            System.exit(0);
        }
        this.this_address=address;
        for (int i=0;i<21;i++){
            for (int j=0;j<21;j++){
                this.point[i][j]=new Point(i,j);
            }
        }
    }
    //这个函数是落子的时候执行的函数，其中的x、y代表落子的坐标，thing代表落子的颜色
    public void Downzi(int x,int y,int thing){
        this.point[x][y].setThing(thing);
    }
    //悔棋成功则返回true，失败则返回false,同时关于对应的用户，悔棋次数-=1
    public boolean regret(int x,int y,int thing){
        if (thing==1)
            this.white_regret-=1;
        else if(thing==-1)
            black_regret-=1;
        if(white_regret<0||black_regret<0){
            if (white_regret<0)white_regret=0;
            else black_regret=0;
            return false;
        }
        else{
            this.point[x][y].setThing(0);
            return true;
        }
    }
    public Point[][] getPoint(){
        return point;
    }
    public Last_step getLast_step() {
        return last_step;
    }
    public void setTargetAddress(InetAddress targetAddress) {
        this.targetAddress = targetAddress;
    }
    public InetAddress getTargetAddress(){
        return this.targetAddress;
    }
    public InetAddress getThis_address() {
        return this.this_address;
    }
//    public void setMyThing(int myThing) {
//        this.myThing = myThing;
//    }
    //将上一步的信息记录用于悔棋的时候用
    public void Remember(int x, int y, int thing){
        this.last_step.setX(x);
        this.last_step.setY(y);
        this.last_step.setThing(thing);
    }
    //存在上回合信息则返回true，否则返回false
    public boolean getRegret(){
        return this.last_step != null;
    }
    //判断是否已经决定出输赢
    public int GetWin(){
        //
        int win_or_not;
        for (int i=0;i<point.length;i++){
            for (int j=0;j<point[i].length;j++){
                if (point[i][j].getThing()!=0){
                    //当前格子有放棋子
                    win_or_not=Gaming.Win_or_Not(this,i,j,point[i][j].getThing());
                    if (win_or_not==1){
                        //白方胜利
                        return 1;
                    }
                    else if (win_or_not==-1){
                        //黑方胜利
                        return -1;
                    }//还没有分出胜负
                }//遍历到没有放子的棋格，跳过
            }
        }
        return 0;
    }
    private static int Win_or_Not(Gaming game,int x,int y,int Nowthing){
        Point[][] point=game.getPoint();
        boolean up=true,down=true,left=true,right=true;
        boolean left_up=true,left_down=true,right_up=true,right_down=true;
        for (int i=0;i<5;i++){
            /*这里是关于循环体内容的一些内容的注释：
            * 首先关于最外层的if判断语句：首先判断是否有完成五子连珠的资本可能，如果没有，直接断定对应值为false
            * 接下来是关于
            * */
            /*UP*/
            if (y>=4){
                if (up&&point[x][y-i].getThing()!=Nowthing) up=false;
            } else up=false;
            /*DOWN*/
            if (y<=16){
                if (down&&point[x][y+i].getThing()!=Nowthing) down=false;
            } else down=false;
            /*LEFT*/
            if (x>=4){
                if (left&&point[x-i][y].getThing()!=Nowthing) left=false;
            } else left=false;
            /*RIGHT*/
            if (x<=16){
                if (right&&point[x+i][y].getThing()!=Nowthing) right=false;
            } else right=false;
            /*LEFT-UP*/
            if (x>=4&&y>=4){
                if (left_up&&point[x-i][y-i].getThing()!=Nowthing) left_up=false;
            } else left_up=false;
            /*LEFT-DOWN*/
            if (x>=4&&y<=16){
                if (left_down&&point[x-i][y+i].getThing()!=Nowthing) left_down=false;
            } else left_down=false;
            /*RIGHT-UP*/
            if (x<=16&&y>=4){
                if (right_up&&point[x+i][y-i].getThing()!=Nowthing) right_up=false;
            } else right_up=false;
            /*RIGHT-DOWN*/
            if (x<=16&&y<=16){
                if (right_down&&point[x+i][y+i].getThing()!=Nowthing) right_down=false;
            }else right_down=false;
        }
        if (up||down||left||right||left_up||left_down||right_up||right_down)
            return Nowthing;
        return 0;
    }
}

