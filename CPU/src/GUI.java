import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.table.JTableHeader;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * @author Kai Kang
 * @date 2021/11/19 7:31 下午
 */
public class GUI extends Thread {
    public static Double currentThroughPut;

    public void run() {
        while (true) {
            while (Process.pausingFlag) {
                new Main().main();
            }
            try {
                sleep(50);
            } catch (InterruptedException ie) {
            }
        }
    }

    GUI() {
        //creat border
        Border border = BorderFactory.createLineBorder(Color.black, 1);

        //create readyQTable
        //set table values
        String[] columName = {"Process name", "Service time"};
        Object[][] readyQ = new Object[4][2];
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 2; j++) {
                switch (j) {
                    case 0:
                        readyQ[i][j] = "N";
                        break;
                    case 1:
                        readyQ[i][j] = "T";
                        break;
                }
            }
        }
        //set readyQ table header
        JTable readyTable = new JTable(readyQ, columName);
        JTableHeader readyTableHeader = readyTable.getTableHeader();
        JLabel leftLabel = new JLabel("Waiting Process");
        leftLabel.setBounds(50, 60, 70, 30);
        //create panel for readyQ table
        JPanel leftPanel = new JPanel();
        leftPanel.setBackground(Color.CYAN);
        leftPanel.setBounds(40, 70, 180, 220);
        leftPanel.add(leftLabel);
        leftPanel.add(readyTableHeader);
        leftPanel.add(readyTable);

        //create right top panel
        JPanel rightTopPanel = new JPanel();
        rightTopPanel.setBounds(230, 50, 150, 30);
        JLabel rightTopLabel = new JLabel("1 time unit is 1s");
        rightTopLabel.setBorder(border);
        rightTopPanel.add(rightTopLabel);

        //create cpu1Label
        JLabel name1 = new JLabel("CPU1 :");
        String[] cpu1 = new String[2];
        cpu1[0] = "process A";
        cpu1[1] = "3";
        JLabel executing1 = new JLabel("exec: " + cpu1[0]);
        JLabel remainingTime1 = new JLabel("remaining time: " + cpu1[1]);
        //create panel for cpu1
        JPanel rightMidPanel = new JPanel();
        rightMidPanel.setBackground(Color.lightGray);
        rightMidPanel.setBounds(230, 80, 150, 100);
        rightMidPanel.setLayout(new GridLayout(3, 1, -10, -10));
        rightMidPanel.setBorder(border);
        rightMidPanel.add(name1);
        rightMidPanel.add(executing1);
        rightMidPanel.add(remainingTime1);

        //Create cpu2label
        JLabel name2 = new JLabel("CPU2 :");
        String[] cpu2 = new String[2];
        cpu2[0] = "process A";
        cpu2[1] = "3";
        JLabel executing2 = new JLabel("exec: " + cpu1[0]);
        JLabel remainingTime2 = new JLabel("remaining time: " + cpu1[1]);
        //create panel for cpu2
        JPanel rightBotPanel = new JPanel();
        rightBotPanel.setBackground(Color.lightGray);
        rightBotPanel.setBounds(230, 190, 150, 100);
        rightBotPanel.setLayout(new GridLayout(3, 1, 10, 0));
        rightBotPanel.setBorder(border);
        rightBotPanel.add(name2);
        rightBotPanel.add(executing2);
        rightBotPanel.add(remainingTime2);

        //create finishQTable
        JTable finishQ = new JTable();
        JLabel currentThroughPut = new JLabel("Current throughput: " + GUI.currentThroughPut + " process/time unit.");
        currentThroughPut.setVerticalAlignment(JLabel.BOTTOM);
        currentThroughPut.setHorizontalAlignment(JLabel.CENTER);
        //create panel for finishQ
        JPanel botPanel = new JPanel();
        botPanel.setBounds(40, 300, 340, 150);
        botPanel.setBackground(Color.PINK);
        botPanel.add(finishQ);
        botPanel.setLayout(new BorderLayout());
        botPanel.add(currentThroughPut);

        //create statLabel
        JLabel systemStatus = new JLabel("Click Start");
        systemStatus.setBounds(230, 20, 70, 30);
        //creat btn
        JButton pause = new JButton("Start");
        pause.setVisible(true);
        pause.setBorderPainted(true);
        pause.setBounds(120, 20, 70, 30);
        pause.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                systemStatus.setText("pausing");
                if (Process.pausingFlag) {
                    pause.setText("pause");
                    systemStatus.setText("running");
                    Process.pausingFlag = false;
                } else {
                    pause.setText("start");
                    systemStatus.setText("pausing");
                    Process.pausingFlag = true;
                }
            }
        });
        //create panel for start,pause and statLabel
        JPanel topPanel = new JPanel();
        topPanel.setBounds(40, 20, 340, 30);
//        topPanel.add(start);
        topPanel.add(pause);
        topPanel.add(systemStatus);

        JFrame frame = new JFrame("GUI");
        frame.add(topPanel);
        frame.add(leftPanel);
        frame.add(rightTopPanel);
        frame.add(rightMidPanel);
        frame.add(rightBotPanel);
        frame.add(botPanel);
        frame.setLayout(null);
        frame.pack();
        frame.setSize(420, 500);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        start();
        while(true){
            currentThroughPut.setText("Current throughput: " + GUI.currentThroughPut + " process/time unit.");
        }
    }

    public static void main(String[] args) {
        new GUI();
    }
}
