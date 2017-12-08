package mulThread;

public class Join {

    public static void main(String[] args){
        Thread preThread = Thread.currentThread();

        for (int i=0; i<10; i++){
            Thread thread = new Thread(new Domino(preThread), String.valueOf(i));
            thread.start();
            preThread = thread;
        }

        SleepUtils.second(5);
        System.out.println(Thread.currentThread().getName() + " terminate.");
    }

    static class Domino implements Runnable{
        private Thread preThread;

        public Domino(Thread preThread){
            this.preThread = preThread;
        }

        @Override
        public void run() {
            try {
                preThread.join();
            } catch (InterruptedException e){

            }

            System.out.println(Thread.currentThread().getName() + " terminate.");
        }
    }

}
