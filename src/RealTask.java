/**
 * @CLassName RealTask
 * @Description TODO
 * @Author BruceChin
 * @Date 2019/7/25 4:04 PM
 * @Version 1.0
 **/

public class RealTask implements Runnable {
    private int sleepTime = 100;
    private int taskID;
    public RealTask(int id){
        taskID = id;
    }

    @Override
    public void run(){
        //System.out.println("Task " + taskID + " is running");

        for(int i = 0; i< 500000; i++){
            double a = Math.random() * 10000;
            double b = Math.random() * 10000;
            double res = a * b;

        }

        //System.out.println("Task " + taskID + " is done");
    }
}
