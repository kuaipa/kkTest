import java.util.ArrayList;

/**
 * Team 19
 */
public class CreateCPU implements Runnable {
    // Variables of Create CPU include the processControlBlock,
    private ArrayList<ProcessControlBlock> processControlBlock;
    // the name of the CPU,
    private String pret;
    // and the pre, post, and final queue data.
    private ArrayList<ProcessControlBlock> pre;
    private ArrayList<ProcessControlBlock> finishQ;
    private ArrayList<ProcessControlBlock> dataQ;
    public CreateCPU(ArrayList<ProcessControlBlock> pcb, String pretxt, ArrayList<ProcessControlBlock> preQ, ArrayList<ProcessControlBlock> fQ,ArrayList<ProcessControlBlock> dQ) {
        processControlBlock = pcb;
        pret = pretxt;
        pre = preQ;
        finishQ = fQ;
        dataQ =dQ;
    }

    //each process will be executed 1s
    public void run() {
        while (pre.size() > 0) {
            int runningCount = 0;
            if (processControlBlock.size() > 0) {
                ProcessControlBlock p0 = processControlBlock.get(0);
                processControlBlock.remove(p0);
                System.out.println(pret + "running process:" + p0.processName);
                System.out.println(pret + "running time: 0s");
                while (runningCount < 20) {
                    try {
                        Thread.sleep(50);
                    } catch (InterruptedException e) {
                    }
                    if (Process.pausingFlag == false) {
                        runningCount += 1;
                    }
                }
                p0.serviceTime--;
                System.out.println(pret + "running time: 1s");
                if (p0.serviceTime > 0) {
                    processControlBlock.add(p0);
                } else {
                    p0.finishTime = GlobalTime.globalTime;
                    for(int k =0;k<dataQ.size();k++){
                        if(dataQ.get(k).processName .contains( p0.processName)){
                            p0.serviceTime=dataQ.get(k).serviceTime;
                            p0.arrivalTime =dataQ.get(k).arrivalTime;
                        }
                    }
                    p0.tat= p0.finishTime-p0.arrivalTime;
                    p0.nTAT=Double.valueOf(p0.tat)/Double.valueOf(p0.serviceTime);
                    finishQ.add(p0);

                }
            } else {
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                }
            }
        }
        while (processControlBlock.size() > 0) {
            int runningCount = 0;
            ProcessControlBlock p0 = processControlBlock.get(0);
            processControlBlock.remove(p0);
            System.out.println(pret + "running process:" + p0.processName);
            for (int i = 0; i < processControlBlock.size(); i++) {
            }

            System.out.println(pret + "running time: 0s");
            while (runningCount < 20) {
                try {
                    Thread.sleep(50);
                } catch (InterruptedException e) {
                }
                if (Process.pausingFlag == false) {
                    runningCount += 1;
                }
            }
            p0.serviceTime--;
            System.out.println(pret + "running time: 1s");
            if (p0.serviceTime > 0) {
                processControlBlock.add(p0);
            } else {
                p0.finishTime = GlobalTime.globalTime;
                for(int k =0;k<dataQ.size();k++){
                    if(dataQ.get(k).processName .contains( p0.processName)){
                        p0.serviceTime=dataQ.get(k).serviceTime;
                        p0.arrivalTime =dataQ.get(k).arrivalTime;
                    }
                }
                p0.tat= p0.finishTime-p0.arrivalTime;
                p0.nTAT=Double.valueOf(p0.tat)/Double.valueOf(p0.serviceTime);
                finishQ.add(p0);

            }
        }

    }
}
