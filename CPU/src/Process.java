import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;

/**
 * Team 19
 */
public class Process {
    public static boolean pausingFlag = false;
    //get information of each process and set them into an ArrayList
    public void getter(ArrayList<ProcessControlBlock> pcb, String fileAddress) {
        File file = new File(fileAddress);
        int k = 0;
        int[] P = new int[3];
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
            String s = null;
            String name = null;
            int count = 0;
            boolean firstLine = true;
            while ((s = bufferedReader.readLine()) != null) {
                if (firstLine == true) {
                    firstLine = false;
                } else {
                    String ids = "";
                    for (int i = 0; i < s.length(); i++) {
                        if (s.toCharArray()[i] != 44) {
                            ids = ids + s.toCharArray()[i];
                        } else if (count == 0) {
                            name = ids;
                            ids = "";
                            count += 1;
                        } else if ((s.toCharArray()[i] == 44) || (i == s.length() - 1)) {
                            P[k] = Integer.parseInt(ids);
                            ids = "";
                            k += 1;
                        }
                    }
                    pcb.add(new ProcessControlBlock(name, P[0], P[1],0,0, 0,false,99.99));
                    k = 0;
                    count = 0;
                }
            }
            bufferedReader.close();
        } catch (Exception e) {
        }
    }
}
