/**
 * @CLassName RealTask
 * @Description TODO
 * @Author BruceChin
 * @Date 2019/7/25 4:04 PM
 * @Version 1.0
 **/
import org.apache.log4j.Logger;
import org.apache.log4j.LogManager;
public class RealTask implements Runnable {
    private int sleepTime = 100;
    private int taskID;
    private static Logger logger = Logger.getLogger(RealTask.class);
    public RealTask(int id){
        taskID = id;
    }

    @Override
    public void run(){
        logger.info("Task " + taskID + " is running");
        //System.out.println("Task " + taskID + " is running");

        for(int i = 0; i< 500000; i++){
            double a = Math.random() * 10000;
            double b = Math.random() * 10000;
            double res = a * b;

        }
        logger.info("Task " + taskID + " is done");

        //System.out.println("Task " + taskID + " is done");
    }
}
