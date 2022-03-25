package GameS.Server.bean.Net;

import java.net.InetAddress;

public class Connect_IP {
    private InetAddress mainaddress;
    private InetAddress lateaddress;
    public Connect_IP(){}
    public Connect_IP(InetAddress mainaddress,InetAddress lateaddress){
        this.mainaddress=mainaddress;
        this.lateaddress=lateaddress;
    }
    public InetAddress getLateaddress() {
        return lateaddress;
    }
    public InetAddress getMainaddress() {
        return mainaddress;
    }
    public void setLateaddress(InetAddress lateaddress) {
        this.lateaddress = lateaddress;
    }
    public void setMainaddress(InetAddress mainaddress) {
        this.mainaddress = mainaddress;
    }
    public void free(){
        this.mainaddress=null;
        this.lateaddress=null;
    }
}
