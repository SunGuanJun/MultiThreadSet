package mulThread;

public class ThreadPoolTest {

    public static void main(String[] args){
        DefaultThreadPool<Job> pool = new DefaultThreadPool<Job>();
        for (int i=0; i<100; i++){
            Job1 job1 = new Job1();
            job1.setName("job-" + i);
            pool.execute(job1);
        }
    }

    static class Job1 implements Job {
        String jobName;

        @Override
        public void run() {
            System.out.println(Thread.currentThread().getName() + " " + jobName + " running...");
        }

        public void setName(String name){
            jobName = name;
        }
    }

}
