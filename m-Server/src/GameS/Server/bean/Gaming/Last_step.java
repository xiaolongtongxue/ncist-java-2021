package GameS.Server.bean.Gaming;

public class Last_step {
    private int x;
    private int y;
    private int thing;
    public Last_step(){}
    public Last_step(int x,int y,int thing){
        this.x=x;this.y=y;
        this.thing=thing;
    }

    public int getY() {
        return y;
    }

    public int getX() {
        return x;
    }

    public int getThing() {
        return thing;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public void setThing(int thing) {
        this.thing = thing;
    }
}
