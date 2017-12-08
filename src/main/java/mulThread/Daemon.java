package mulThread;

public class Daemon {
    public static void main(String[] args){
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("DaemonThread start");
                try {
                    SleepUtils.second(10);
                } finally {
                    System.out.println("DaemonThread finally run");
                }
            }
        }, "DaemonThread");
        thread.setDaemon(true);
        thread.start();
//        SleepUtils.second(1);
    }

}
