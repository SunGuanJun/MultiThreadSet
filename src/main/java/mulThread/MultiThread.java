package mulThread;

import java.lang.management.ManagementFactory;
import java.lang.management.ThreadInfo;
import java.lang.management.ThreadMXBean;

public class MultiThread {
    private String b;

    public static void main(String[] args){
        ThreadMXBean threadMXBean = ManagementFactory.getThreadMXBean();
        ThreadInfo[] threadInfos = threadMXBean.dumpAllThreads(false, false);
        for (ThreadInfo threadInfo : threadInfos){
            System.out.println("[" + threadInfo.getThreadId() + "]" + threadInfo.getThreadName());
        }

        Demo demo = new Demo();
        demo.a = "dd";
    }

    public String getB() {
        return b;
    }

    public void setB(String b) {
        this.b = b;
    }

    static class Demo{
        private String a;

        public void test(){
            Demo demo = new Demo();
            MultiThread multiThread = new MultiThread();
            multiThread.b = "sd";
        }
    }
}
