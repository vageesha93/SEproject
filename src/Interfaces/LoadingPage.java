package Interfaces;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.util.*;
import java.util.Timer;


public class LoadingPage extends JFrame {

    static int interval;
    private Timer timer;
    private JLabel lblback;
    public LoadingPage() throws HeadlessException, InterruptedException {

        setResizable(false);

        Dimension dimension=new Dimension(950,534);
        setVisible(true);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        Container c=getContentPane();
        setLocationRelativeTo(null);
        setMaximumSize(dimension);
        setSize(dimension);
        setMinimumSize(dimension);

     // ImageIcon  imageIconback = new ImageIcon(new ImageIcon("resources/Splash75.gif").getImage().getScaledInstance(950, 534, Image.SCALE_SMOOTH));

        lblback=new JLabel();
        lblback.setBorder(new EmptyBorder(0,0,0,0));
        lblback.setIcon(new ImageIcon("resources/Splash75.png"));
        lblback.setSize(dimension);


        int delay = 1000;
        int period = 1000;
        timer = new java.util.Timer();
        interval=60;
        timer.scheduleAtFixedRate(new TimerTask() {

            public void run() {

               if (setInterval()==55){
                   timer.cancel();
                   close();
                   new GloryMenu();

               }

            }
        },delay,period);


        c.add(lblback);

        validate();

    }

    private int setInterval() {
        return --interval;
    }

    private void close(){
        this.dispose();
    }

}
