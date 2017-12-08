package mulThread;

public class Interrupted {

    public static void main(String[] args){
        Thread sleepThread = new Thread(new Runnable() {
            @Override
            public void run() {
                while (true){
                    SleepUtils.second(10);
                }
            }
        }, "SleepThread");
        sleepThread.setDaemon(true);
        sleepThread.start();

        Thread busyThread = new Thread(new Runnable() {
            @Override
            public void run() {
                while (true){}
            }
        }, "BusyThread");
        busyThread.setDaemon(true);
        busyThread.start();

        SleepUtils.second(5);

        sleepThread.interrupt();
        busyThread.interrupt();

        System.out.println("sleepThread interrupt is " + sleepThread.isInterrupted());
        System.out.println("busyThread interrupt is " + busyThread.isInterrupted());

        SleepUtils.second(2);

        busyThread.interrupt();
        System.out.println("busyThread interrupt is " + busyThread.isInterrupted());

        SleepUtils.second(2);

    }
}
