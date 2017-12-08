package mulThread;

public class Profiler {
    private static final ThreadLocal<Long> TIME_THREADLOCAL = new ThreadLocal<Long>(){
        @Override
        protected Long initialValue() {
            return System.currentTimeMillis();
        }
    };

    public static final void begin() {
        TIME_THREADLOCAL.set(System.currentTimeMillis());
    }

    public static final long end(){
        return System.currentTimeMillis() - TIME_THREADLOCAL.get();
    }


    public static void main(String[] args){
        System.out.println("init: " + Profiler.end() + " mills");
        Profiler.begin();
        SleepUtils.second(5);
        System.out.println("Cost: " + Profiler.end() + " mills");

//        Thread thread1 = new Thread(new Runnable() {
//            @Override
//            public void run() {
//                Profiler.begin();
//                SleepUtils.second(2);
//                System.out.println(Thread.currentThread().getName() + " cost " + Profiler.end() + " mills");
//            }
//        }, "thread-1");
//        thread1.start();
//
//        Thread thread2 = new Thread(new Runnable() {
//            @Override
//            public void run() {
//                Profiler.begin();
//                SleepUtils.second(3);
//                System.out.println(Thread.currentThread().getName() + " cost " + Profiler.end() + " mills");
//            }
//        }, "thread-2");
//        thread2.start();
    }
}
