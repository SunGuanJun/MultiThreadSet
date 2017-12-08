package mulThread;

import java.text.SimpleDateFormat;
import java.util.Date;

public class WaitNotify {
    static boolean flag = true;
    static Object lock = new Object();

    public static void main(String[] args){
        Thread waitThread = new Thread(new Wait(), "waitThread");
        waitThread.start();
        SleepUtils.second(1);
        Thread notifyThread = new Thread(new Notify(), "notifyThread");
        notifyThread.start();
    }

    static class Wait implements Runnable{
        @Override
        public void run() {
            // 加锁，拥有lock的Monitor
            synchronized (lock){
                // 当条件不满足时，继续wait，同时释放了lock的锁
                while (flag){
                    try {
                        System.out.println(Thread.currentThread() + "flag is true. wait@ " +
                                new SimpleDateFormat("HH:mm:ss").format(new Date()));
                        // 想要回到这个同步块，不仅要notify，还要持有lock的锁
                        lock.wait();
                    }catch (InterruptedException e){

                    }
                }

                // 条件满足时，完成工作
                System.out.println(Thread.currentThread() + " flag is false. running@ "
                    + new SimpleDateFormat("HH:mm:ss").format(new Date()));
            }
        }
    }

    static class Notify implements Runnable{
        @Override
        public void run() {
            // 加锁，拥有lock的Monitor
            synchronized (lock){
                // 获取lock的锁，然后进行通知，通知时不会释放lock的锁，
                // 直到当前线程释放了lock后，WaitThread才能从wait方法中返回
                System.out.println(Thread.currentThread() + " hold lock. notify @"
                    + new SimpleDateFormat("HH:mm:ss").format(new Date()));

                lock.notifyAll();
                flag = false;
                SleepUtils.second(5);
            }

//            SleepUtils.second(1);

            synchronized (lock){
                System.out.println(Thread.currentThread() + " hold lock again. sleep@ "
                    + new SimpleDateFormat("HH:mm:ss").format(new Date()));
                SleepUtils.second(5);
            }
        }
    }
}
