package z3_ThreadTest;

public class Test2 {
    public static void main(String[] args) {
        Demo2 smt1=new Demo2("Thread1");
        Demo2 smt2=new Demo2("Thread2");
        Demo2 smt3=new Demo2("Thread3");
        Demo2 smt4=new Demo2("Thread4");
        Demo2 smt5=new Demo2("Thread5");
        Demo2 smt6=new Demo2("Thread6");
        Demo2 smt7=new Demo2("Thread7");
        int counter=0;

        Thread thread1=new Thread(smt1);
        Thread thread2=new Thread(smt2);
        Thread thread3=new Thread(smt3);
        Thread thread4=new Thread(smt4);
        Thread thread5=new Thread(smt5);
        Thread thread6=new Thread(smt6);
        Thread thread7=new Thread(smt7);

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
