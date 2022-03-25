package z3_ThreadTest;

public class Test1 {
    public static void main(String[] args) {
        Demo1 thread1=new Demo1("Thread1");
        Demo1 thread2=new Demo1("Thread2");
        Demo1 thread3=new Demo1("Thread3");
        Demo1 thread4=new Demo1("Thread4");
        Demo1 thread5=new Demo1("Thread5");
        Demo1 thread6=new Demo1("Thread6");
        Demo1 thread7=new Demo1("Thread7");
        int counter=0;
        thread1.start();
        thread2.start();
        thread3.start();
        thread4.start();
        thread5.start();
        thread6.start();
        thread7.start();
        while (counter<10){
            counter++;
            System.out.println("主线程正在输出："+counter);
            try {
                Thread.sleep((int)(Math.random()*100+100));
            } catch (InterruptedException e) {
                e.printStackTrace();
                System.out.println("线程休眠错误");
            }
        }
    }
}
