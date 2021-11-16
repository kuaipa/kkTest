import java.util.ArrayList;

/**
 * Team 19
 */
public class MakeFinishQ implements Runnable {
    private ArrayList<ProcessControlBlock> fQ;
    private ArrayList<ProcessControlBlock> c1;
    private ArrayList<ProcessControlBlock> c2;
    private int n;

    public MakeFinishQ(ArrayList<ProcessControlBlock> finishQ, ArrayList<ProcessControlBlock> cpuQ1, ArrayList<ProcessControlBlock> cpuQ2, int totalProcessNumber) {
        fQ = finishQ;
        c1 = cpuQ1;
        c2 = cpuQ2;
        n = totalProcessNumber;
    }

    public void run() {
        while (GlobalTime.timerFlag == true) {
            if (fQ.size() < n) {
                if (c1.size() > 0 && c2.size() == 0) {
                    ProcessControlBlock p1 = c1.get(0);
                    fQ.add(p1);
                    c1.remove(0);
                } else if (c1.size() == 0 && c2.size() > 0) {
                    ProcessControlBlock p2 = c2.get(0);
                    fQ.add(p2);
                    c2.remove(0);
                } else if (c1.size() > 0 && c2.size() > 0) {
                    ProcessControlBlock p1 = c1.get(0);
                    ProcessControlBlock p2 = c2.get(0);
                    if (p1.finishTime <= p2.finishTime) {
                        fQ.add(p1);
                        c1.remove(0);
                    } else {
                        fQ.add(p2);
                        c2.remove(0);
                    }
                } else {
                    try {
                        Thread.sleep(50);
                    } catch (InterruptedException e) {
                    }
                }
            } else {
                GlobalTime.timerFlag = false;
            }
        }
    }

}
