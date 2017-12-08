package mulThread;

import java.util.concurrent.TimeUnit;

public class SleepUtils {
    /**
     * 休眠n秒，单位：秒
     * @param seconds
     */
    public static final void second(long seconds){
        try {
            TimeUnit.SECONDS.sleep(seconds);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
