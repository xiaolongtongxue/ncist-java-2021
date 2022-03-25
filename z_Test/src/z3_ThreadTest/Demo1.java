package z3_ThreadTest;

public class Demo1 extends Thread{
    private int counter=0;
    private String title;
    public Demo1(String title){
        this.title=title;
    }
    public void run(){
        while (counter<10){
            counter++;
            System.out.println("Thread线程<"+title+">正在输出："+counter);
            try {
                sleep((int)(Math.random()*100+100));
            } catch (InterruptedException e) {
                e.printStackTrace();
                System.out.println("线程休眠错误");
            }
        }
    }
}
