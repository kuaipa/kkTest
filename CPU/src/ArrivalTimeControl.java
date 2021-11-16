import java.util.ArrayList;

/**
 * Team 19
 */
public class ArrivalTimeControl implements Runnable {
    private ArrayList<ProcessControlBlock> processControlBlock;
    private ArrayList<ProcessControlBlock> ready;

    public ArrivalTimeControl(ArrayList<ProcessControlBlock> pcb, ArrayList<ProcessControlBlock> rdq) {
        processControlBlock = pcb;
        ready = rdq;
    }

    //manage the arrival time
    public void run() {
        while (processControlBlock.size() > 0) {
            int runningCount=0;
            for (int i = 0; i < processControlBlock.size(); i++) {
                if (processControlBlock.get(i).arrivalTime <= 0) {
                    ready.add(processControlBlock.get(i));
                    processControlBlock.remove(i);
                    i--;
                }
            }
            while (runningCount<20) {
                try {
                    Thread.sleep(50);
                } catch (InterruptedException e) {
                }
                if(Process.pausingFlag==false){
                    runningCount+=1;
                }
            }
            for (int k = 0; k < processControlBlock.size(); k++) {
                processControlBlock.get(k).arrivalTime -= 1;
            }
        }
    }

}
