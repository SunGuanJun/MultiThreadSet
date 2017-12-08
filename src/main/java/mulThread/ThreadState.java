package mulThread;

public class ThreadState {

    public static void main(String[] args){
        new Thread(new TimeWaiting(), "TimeWaitingThread").start();
        new Thread(new Waiting(), "WaitingThread").start();
        new Thread(new Blocked(), "BlockedThread-1").start();
        new Thread(new Blocked(), "BlockedThread-2").start();
    }

    /**
     * 一直睡眠的线程
     */
    static class TimeWaiting implements Runnable{
        @Override
        public void run() {
            while (true){
                SleepUtils.second(100);
            }
        }
    }

    /**
     * 该线程在Waiting.class上
     */
    static class Waiting implements Runnable{
        @Override
        public void run() {
            while (true){
                synchronized (Waiting.class){
                    try {
                        Waiting.class.wait();
                    } catch (InterruptedException e){
                        e.printStackTrace();
                    }
                }
            }
        }
    }

    /**
     * 该线程在Blocked.class上加锁后，不会释放该锁
     */
    static class Blocked implements Runnable{
        @Override
        public void run() {
            synchronized (Blocked.class){
                while (true){
                    SleepUtils.second(100);
                }
            }
        }
    }
}
