import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Team 19
 */
public class Main {
    public void main() {

        GlobalTime.timerFlag = true;
        //create preProcessQueue to count arrivalTime
        ArrayList<ProcessControlBlock> preQ = new ArrayList<>();
        ProcessQ processQ = new ProcessQ();
        //ask for file
        String file = "src/process.txt";
        //add process to the queue
        ArrayList<ProcessControlBlock> dQ =new ArrayList<>();
        processQ.MakeQ(dQ,file);
        processQ.MakeQ(preQ, file);
        int totalProcessNumber = preQ.size();
        //create ProcessQueue for cpus
        ArrayList<ProcessControlBlock> q = new ArrayList<>();
        //create finish queue
        ArrayList<ProcessControlBlock> finishQ = new ArrayList<>();
        ArrayList<ProcessControlBlock> finishQ1 = new ArrayList<>();
        ArrayList<ProcessControlBlock> finishQ2 = new ArrayList<>();
        MakeFinishQ makeFinishQ = new MakeFinishQ(finishQ, finishQ1, finishQ2,totalProcessNumber);
        Thread mQ = new Thread(makeFinishQ);
        mQ.start();
        //start GlobalTimer
        GlobalTime time = new GlobalTime(finishQ);
        Thread timer = new Thread(time);
        timer.start();
        //create time control thread
        ArrivalTimeControl t1 = new ArrivalTimeControl(preQ, q);
        Thread timeC = new Thread(t1);
        timeC.start();
        //create cpus
        CreateCPU CThread1 = new CreateCPU(q, "CPU1 ", preQ, finishQ1,dQ);
        CreateCPU CThread2 = new CreateCPU(q, "             CPU2 ", preQ, finishQ2,dQ);
        Thread cpu1 = new Thread(CThread1);
        Thread cpu2 = new Thread(CThread2);
        cpu1.start();
        cpu2.start();
        try {
            timer.join();
            mQ.join();
            timeC.join();
            cpu1.join();
            cpu2.join();
        } catch (InterruptedException e) {
        }
        System.out.println(finishQ.get(0).processName+" "+finishQ.get(0).finishTime+" "+finishQ.get(0).arrivalTime+" "+finishQ.get(0).tat+" "+finishQ.get(0).nTAT);
        System.out.println(finishQ.get(1).processName+" "+finishQ.get(1).finishTime+" "+finishQ.get(1).arrivalTime+" "+finishQ.get(1).tat+" "+finishQ.get(1).nTAT);
        System.out.println(finishQ.get(2).processName+" "+finishQ.get(2).finishTime+" "+finishQ.get(2).arrivalTime+" "+finishQ.get(2).tat+" "+finishQ.get(2).nTAT);
        System.out.println(finishQ.get(3).processName+" "+finishQ.get(3).finishTime+" "+finishQ.get(3).arrivalTime+" "+finishQ.get(3).tat+" "+finishQ.get(3).nTAT);
        System.out.println(finishQ.get(4).processName+" "+finishQ.get(4).finishTime+" "+finishQ.get(4).arrivalTime+" "+finishQ.get(4).tat+" "+finishQ.get(4).nTAT);
        System.out.println("process queue is empty");
        System.exit(0);
    }

}
