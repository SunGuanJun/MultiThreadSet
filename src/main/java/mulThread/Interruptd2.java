package mulThread;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

public class Interruptd2 {
    public static void main(String[] args){
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                DateFormat format = new SimpleDateFormat("HH:mm:ss");
                while (true){
                    try {
                        TimeUnit.SECONDS.sleep(5);
                    } catch (InterruptedException e) {
                        System.out.println("I'm interrupted at " + format.format(new Date()));
                    }
                    System.out.println(format.format(new Date()));
                }
            }
        }, "Interrupted2");
        thread.setDaemon(true);
        thread.start();

        System.out.println("interrupted status " + thread.isInterrupted());
        SleepUtils.second(7);
        thread.interrupt();
        System.out.println("interrupted status " + thread.isInterrupted());

        SleepUtils.second(6);
        System.out.println("interrupted status " + thread.isInterrupted());


    }
}
