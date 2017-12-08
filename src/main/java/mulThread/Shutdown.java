package mulThread;

public class Shutdown {
    public static void main(String[] args){
        Thread countThread = new Thread(new Runner(), "CountThread");
        countThread.start();

        SleepUtils.second(1);
        countThread.interrupt();

        Runner two = new Runner();
        countThread = new Thread(two, "CountThread");
        countThread.start();
        SleepUtils.second(1);
        two.cancel();
    }

    private static class Runner implements Runnable{
        private long i;
        private boolean on = true;

        @Override
        public void run() {
            while (on && !Thread.currentThread().isInterrupted()){
                i++;
            }
            System.out.println("Count i = " + i);
        }

        public void cancel(){
            on = false;
        }
    }
}
