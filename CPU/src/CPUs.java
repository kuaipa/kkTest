import javax.swing.*;
import java.awt.*;

/**
 * @author Kai Kang
 * @date 2021/11/13 7:55 下午
 */
public class CPUs {
    public static void main(String[] args) {
        JFrame frame=new JFrame("CPUs");
        frame.setLayout(new FlowLayout(FlowLayout.LEFT));


        JButton start = new JButton("start");
        start.setVisible(true);
        start.setBorderPainted(true);

        JButton pause = new JButton("pause");
        start.setVisible(true);
        start.setBorderPainted(true);

        JLabel systemStatus= new JLabel("gggg");
        systemStatus.setSize(30,50);


        frame.add(start);
        frame.add(pause);
        frame.add(systemStatus);
        frame.pack();
        frame.setSize(400,600);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    }
}
