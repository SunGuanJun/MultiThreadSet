package mulThread;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Deprecated {

    public static void main(String[] args){
        Thread printThread = new Thread(new Runnable() {
            @Override
            public void run() {
                DateFormat format = new SimpleDateFormat("HH:mm:ss");
                while (true){
                    System.out.println(Thread.currentThread().getName() + "Run at "
                        + format.format(new Date()));
                    SleepUtils.second(1);
                }
            }
        }, "PrintThread");
        DateFormat format = new SimpleDateFormat("HH:mm:ss");
        printThread.setDaemon(true);
        printThread.start();
        SleepUtils.second(3);

        printThread.suspend();
        System.out.println("main suspend printThread at " + format.format(new Date()));
        SleepUtils.second(3);

        printThread.resume();
        System.out.println("main resume printThread at " + format.format(new Date()));
        SleepUtils.second(3);

        printThread.stop();
        System.out.println("main stop printThread at " + format.format(new Date()));
        SleepUtils.second(3);

    }
}
