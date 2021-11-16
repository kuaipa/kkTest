import java.util.ArrayList;

/**
 * Team 19
 */
public class ProcessQ {
    // The ProcessQ class has the function MakeQ, where the ArrayList of ProcessControlBlocks and the string are used to create the processes.
    public void MakeQ(ArrayList<ProcessControlBlock> processQ, String s) {
        Process process = new Process();
        process.getter(processQ, s);
    }

}
