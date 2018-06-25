package Interfaces;

import Glory_Schema.GameSounds;
import GloryClient.Packet;
import GloryClient.Packet01Disconnect;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.IOException;


public class FinalScoreBoard extends JFrame implements MouseListener,WindowListener{

    public static JLabel  lblpname1,lblpname2,lblpname3,lblpname4,lblpname5;
    public static JLabel lblpscore1,lblpscore2,lblpscore3,lblpscore4,lblpscore5;
    private JLabel lblback,lblplayagain;
    private ImageIcon imgback;
    private Packet packet;

    public FinalScoreBoard() throws HeadlessException {
        setResizable(false);
        Dimension dimension=new Dimension(950,600);
        setVisible(true);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        Container c=getContentPane();

        setMaximumSize(dimension);
        setSize(dimension);
        setTitle("Glory Game");
        setMinimumSize(dimension);
        setLocationRelativeTo(null);

        addWindowListener(this);
        ImageIcon imgplayagain = new ImageIcon(new ImageIcon("resources/close.png").getImage().getScaledInstance(60, 60, Image.SCALE_SMOOTH));


        lblplayagain=new JLabel();
        lblplayagain.setBounds(800,500,60,60);
        lblplayagain.setIcon(imgplayagain);
        lblplayagain.setBorder(new EmptyBorder(0,0,0,0));
        lblplayagain.addMouseListener(this);

        lblpname1=new JLabel("Please Wait...");
        lblpname1.setBounds(205,275,250,30);
        lblpname1.setFont(new Font("Arial",Font.BOLD,24));
        lblpname1.setHorizontalAlignment(SwingConstants.CENTER);
        lblpname1.setBorder(new EmptyBorder(0,0,0,0));
        lblpname1.setForeground(Color.red);

        lblpscore1=new JLabel("wait...");
        lblpscore1.setBounds(502,275,250,30);
        lblpscore1.setFont(new Font("Arial",Font.BOLD,24));
        lblpscore1.setHorizontalAlignment(SwingConstants.CENTER);
        lblpscore1.setBorder(new EmptyBorder(0,0,0,0));
        lblpscore1.setForeground(Color.red);

        lblpname2=new JLabel();
        lblpname2.setBounds(205,330,250,30);
        lblpname2.setFont(new Font("Arial",Font.BOLD,24));
        lblpname2.setBorder(new EmptyBorder(0,0,0,0));
        lblpname2.setHorizontalAlignment(SwingConstants.CENTER);
        lblpname2.setForeground(Color.magenta);

        lblpscore2=new JLabel();
        lblpscore2.setBounds(502,330,250,30);
        lblpscore2.setFont(new Font("Arial",Font.BOLD,24));
        lblpscore2.setBorder(new EmptyBorder(0,0,0,0));
        lblpscore2.setHorizontalAlignment(SwingConstants.CENTER);
        lblpscore2.setForeground(Color.magenta);

        lblpname3=new JLabel();
        lblpname3.setBounds(205,385,250,30);
        lblpname3.setFont(new Font("Arial",Font.BOLD,24));
        lblpname3.setBorder(new EmptyBorder(0,0,0,0));
        lblpname3.setHorizontalAlignment(SwingConstants.CENTER);
        lblpname3.setForeground(Color.magenta);

        lblpscore3=new JLabel();
        lblpscore3.setBounds(502,385,250,30);
        lblpscore3.setFont(new Font("Arial",Font.BOLD,24));
        lblpscore3.setBorder(new EmptyBorder(0,0,0,0));
        lblpscore3.setHorizontalAlignment(SwingConstants.CENTER);
        lblpscore3.setForeground(Color.magenta);

        lblpname4=new JLabel();
        lblpname4.setBounds(205,440,250,30);
        lblpname4.setFont(new Font("Arial",Font.BOLD,24));
        lblpname4.setBorder(new EmptyBorder(0,0,0,0));
        lblpname4.setHorizontalAlignment(SwingConstants.CENTER);
        lblpname4.setForeground(Color.magenta);

        lblpscore4=new JLabel();
        lblpscore4.setBounds(502,440,250,30);
        lblpscore4.setFont(new Font("Arial",Font.BOLD,24));
        lblpscore4.setBorder(new EmptyBorder(0,0,0,0));
        lblpscore4.setHorizontalAlignment(SwingConstants.CENTER);
        lblpscore4.setForeground(Color.magenta);

        lblpname5=new JLabel();
        lblpname5.setBounds(205,495,250,30);
        lblpname5.setFont(new Font("Arial",Font.BOLD,24));
        lblpname5.setBorder(new EmptyBorder(0,0,0,0));
        lblpname5.setHorizontalAlignment(SwingConstants.CENTER);
        lblpname5.setForeground(Color.magenta);

        lblpscore5=new JLabel();
        lblpscore5.setBounds(502,495,250,30);
        lblpscore5.setFont(new Font("Arial",Font.BOLD,24));
        lblpscore5.setBorder(new EmptyBorder(0,0,0,0));
        lblpscore5.setHorizontalAlignment(SwingConstants.CENTER);
        lblpscore5.setForeground(Color.magenta);


        imgback = new ImageIcon(new ImageIcon("resources/game_over_bg.png").getImage().getScaledInstance(950, 600, Image.SCALE_SMOOTH));
       lblback =new JLabel();
        lblback.setBorder(new EmptyBorder(0,0,0,0));
        lblback.setIcon(imgback);

        c.add(lblplayagain);
        c.add(lblpscore5);
        c.add(lblpscore4);
        c.add(lblpscore3);
        c.add(lblpscore2);
        c.add(lblpscore1);
        c.add(lblpname5);
        c.add(lblpname4);
        c.add(lblpname3);
        c.add(lblpname2);
        c.add(lblpname1);
        c.add(lblback);

         validate();
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        GameSounds.clicksound();
   packet=new Packet01Disconnect(GloryMenu.gloryplayer.getPlayerName());
        try {
            packet.writeData(GloryMenu.gameClient);
        } catch (IOException e1) {
            e1.printStackTrace();
        }

       System.exit(0);
    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {
        GameSounds.hoversound();
        lblplayagain.setBounds(800,503,60,60);
    }

    @Override
    public void mouseExited(MouseEvent e) {
        lblplayagain.setBounds(800,500,60,60);;
    }

    @Override
    public void windowOpened(WindowEvent e) {

    }

    @Override
    public void windowClosing(WindowEvent e) {
        packet=new Packet01Disconnect(GloryMenu.gloryplayer.getPlayerName());
        try {
            packet.writeData(GloryMenu.gameClient);
        } catch (IOException e1) {
            e1.printStackTrace();
        }
    }

    @Override
    public void windowClosed(WindowEvent e) {

    }

    @Override
    public void windowIconified(WindowEvent e) {

    }

    @Override
    public void windowDeiconified(WindowEvent e) {

    }

    @Override
    public void windowActivated(WindowEvent e) {

    }

    @Override
    public void windowDeactivated(WindowEvent e) {

    }
}
