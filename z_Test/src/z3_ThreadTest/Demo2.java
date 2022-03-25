package z3_ThreadTest;

public class Demo2 implements Runnable{
    private int counter;
    private String title;

    public Demo2(String title){
        this.title=title;
    }

    @Override
    public void run() {
        while (counter<10){
            counter++;
            System.out.println("Thread线程<"+title+">正在输出："+counter);
            try {
                Thread.sleep((int)(Math.random()*100+100));
            } catch (InterruptedException e) {
                e.printStackTrace();
                System.out.println("线程休眠错误");
            }
        }
    }
}
