package GameS.Server.bean.Gaming;

public class Point {
    private final int x;
    private final int y;
    private int thing;
    //在此注明，此处的thing只可能为三个值：-1、0和1，其中-1代表黑子放下，0代表无子放之，1代表白子放置
    public Point(int x,int y){
        this.x=x;
        this.y=y;
        this.thing=0;
    }
    public int getX() {
        return x;
    }
    public int getY() {
        return y;
    }
    public int getThing() {
        return thing;
    }
    public void setThing(int thing) {
        this.thing = thing;
    }
}
