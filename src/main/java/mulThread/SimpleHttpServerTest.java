package mulThread;

public class SimpleHttpServerTest {
    public static void main(String[] args) throws Exception{
        Thread thread1 = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("server start...");
                try {
                    SimpleHttpServer.start();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }, "thread1");

        Thread thread2 = new Thread(new Runnable() {
            @Override
            public void run() {
                SleepUtils.second(10);
                try {
                    SimpleHttpServer.shutdown();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                System.out.println("server stop...");
            }
        }, "thread2");

        thread1.start();
        thread2.start();

    }

}
