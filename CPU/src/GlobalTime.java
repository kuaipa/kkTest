import java.util.ArrayList;

/**
 * Team 19
 */
public class GlobalTime implements Runnable {
    public static int globalTime;
    public static boolean timerFlag;
    private ArrayList<ProcessControlBlock> finishQ;

    public GlobalTime(ArrayList<ProcessControlBlock> pcb) {
        finishQ = pcb;
    }

    @Override
    public void run() {
        while (timerFlag == true) {
            if (Process.pausingFlag == true) {
                try {
                    Thread.sleep(50);
                } catch (InterruptedException e) {
                }
            } else {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                }
                globalTime += 1;
                if (globalTime != 0) {
                    GUI.currentThroughPut = Double.valueOf(finishQ.size()) / Double.valueOf(globalTime);
                }
            }

        }
    }
}
