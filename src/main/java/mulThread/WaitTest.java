package mulThread;

public class WaitTest {
    static Object lock = new Object();

    public static void main(String[] args){
        Thread thread1 = new Thread(new Wait(), "thread-1");
        thread1.start();

        Thread thread2 = new Thread(new Wait(), "thread-2");
        thread2.start();

        Thread thread3 = new Thread(new Wait(), "thread-3");
        thread3.start();

        Thread thread4 = new Thread(new Wait(), "thread-4");
        thread4.start();

        Thread thread5 = new Thread(new Wait(), "thread-5");
        thread5.start();

        Thread thread6 = new Thread(new Wait(), "thread-6");
        thread6.start();

        Thread thread10 = new Thread(new Runnable() {
            @Override
            public void run() {
                synchronized (lock){
                    System.out.println(Thread.currentThread().getName() + " get the lock...");
                    lock.notify();
                    System.out.println(Thread.currentThread().getName() + " notify all...");
                }
            }
        }, "thread10");
        thread10.start();

    }

    static class Wait implements Runnable{
        @Override
        public void run() {
            synchronized (lock){
                System.out.println(Thread.currentThread().getName() + " get the lock...");
                try {
                    System.out.println(Thread.currentThread().getName() + " release the lock...");
                    lock.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(Thread.currentThread().getName() + " get the lock again...");
            }
        }
    }
}
