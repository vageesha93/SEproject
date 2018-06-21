package Interfaces;

import GloryClient.Packet;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.*;
import java.util.Timer;


public class WatingForLoaders extends JFrame {

    Packet packet;

    static int interval;
    public   static Timer Watingtimer;
   private int secs;


    private JLabel lblback,lblanim;
    private ImageIcon imageIconback,imageAnim;
    public WatingForLoaders() {
        setResizable(false);
        Dimension dimension=new Dimension(950,534);
        setVisible(true);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        Container c=getContentPane();
        setLocationRelativeTo(null);
        setMaximumSize(dimension);
        setSize(dimension);
        setMinimumSize(dimension);

        //lblask=new JLabel();
        Font   customFont = null;
        try {

            customFont = Font.createFont(Font.TRUETYPE_FONT, new File("resources/digital-7.ttf")).deriveFont(85f);
        GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();

            ge.registerFont(Font.createFont(Font.TRUETYPE_FONT, new File("resources/digital-7.ttf")));
        } catch (FontFormatException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        imageIconback = new ImageIcon(new ImageIcon("resources/waiting.jpg").getImage().getScaledInstance(950, 534, Image.SCALE_SMOOTH));

        lblback=new JLabel();
        lblback.setIcon(imageIconback);

        imageAnim = new ImageIcon(new ImageIcon("resources/anim.png").getImage().getScaledInstance(50, 50, Image.SCALE_SMOOTH));


        int[] pos={321,385,450,515,579,579,515,450,385,321};

        lblanim=new JLabel();
        lblanim.setIcon(imageAnim);
        lblanim.setSize(50, 50);
        lblanim.setLocation(pos[0], 227);
        int delay = 1000;
        int period = 1000;
        Watingtimer = new Timer();
        interval=60;
        Watingtimer.scheduleAtFixedRate(new TimerTask() {

            public void run() {
                int x =setInterval();
                int i;
                i=x%10;
                lblanim.setLocation(pos[i], 227);
                if(x==0)
                    interval=60;
            }
        },delay,period);

        c.add(lblanim);
        c.add(lblback);

        validate();
    }

    private int setInterval() {
        return --interval;
    }



}
