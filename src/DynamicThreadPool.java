import com.sun.management.OperatingSystemMXBean;

import java.lang.management.ManagementFactory;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.*;
import org.apache.log4j.Logger;
import org.apache.log4j.LogManager;

/**
 * @CLassName DynamicThreadPool
 * @Description modify number of threads in the thread pool according to the utilization of host machine CPU
 * @Author lianke.qin@gmail.com
 * @Date 2019/7/25 2:44 PM
 * @Version 1.0
 **/
public class DynamicThreadPool<Job extends Runnable>{
    int numProcessors;
    double procUtil;//usage percentage of all processors
    int minThreads;
    int maxThreads;
    int curThreads;
    int updatePeriod;//run updateWorkers per period
    public ThreadPoolExecutor threadPool;
    OperatingSystemMXBean bean;
    Timer timer;
    public DynamicThreadPool(int minthread, int maxthread, BlockingQueue queue){
        bean = (com.sun.management.OperatingSystemMXBean) ManagementFactory.getOperatingSystemMXBean();
        numProcessors = bean.getAvailableProcessors();
        procUtil = bean.getSystemCpuLoad();
        minThreads = minthread;
        maxThreads = maxthread;
        curThreads = minThreads;
        timer = new Timer();
        timer.schedule(new ThreadTask(), 100, 1000);

        threadPool = new ThreadPoolExecutor(minThreads, maxThreads, 1000, TimeUnit.MILLISECONDS, queue);
    }

    public void printInfo(){

    }
    public void updateWorkers(){

        procUtil = bean.getSystemCpuLoad();
        if(curThreads != minThreads + (int)((maxThreads - minThreads) * (1 - procUtil))) {
            //reduce number of setCorePoolSize calls
            curThreads = minThreads + (int) ((maxThreads - minThreads) * (1 - procUtil));
            threadPool.setCorePoolSize(curThreads);
        }
        System.out.println("CPU util " + procUtil + " curWorkers " + threadPool.getCorePoolSize());
    }

    public void execute(Job j){
        threadPool.execute(j);
    }

    public long getTaskCount(){
        return 0;
        //return threadPool.getTaskCount();
    }

    public void shutdown(){
        timer.cancel();
        threadPool.shutdown();
        System.out.println("Dynamic thread pool shutdown");
    }

    public class ThreadTask extends TimerTask{
        public ThreadTask(){

        }

        @Override
        public void run(){
            updateWorkers();
        }
    }

    public static void main(String[] args){
        Logger logger = LogManager.getLogger("ThreadPool");
        BlockingQueue queue = new ArrayBlockingQueue<>(1000);
        DynamicThreadPool test = new DynamicThreadPool(1,1, queue);
        for(int i = 0; i < 1000; i++){
            //System.out.println(test.threadPool.getTaskCount());
            try{
                Thread.sleep(10);
                test.execute(new RealTask(i));
                logger.info("thread" + i +" init");
            }catch (Exception e){
                e.printStackTrace();
            }

        }
        //test.shutdown();
    }

}
