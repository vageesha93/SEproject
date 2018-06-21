package Interfaces;

import Glory_Schema.GameSounds;
import GloryClient.Packet;
import GloryClient.Packet00Login;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.IOException;


public class selectVowels extends JFrame implements MouseListener {


    private JLabel jlblbackground,lblname,lblscore,lblone,lbltwo,lblthree,lblfour,lblfive;
    private Packet packet;

    private ImageIcon imageIcon1,imageIcon2,imageIcon3,imageIcon4,imageIcon5;
    private ImageIcon imageIcon1c,imageIcon2c,imageIcon3c,imageIcon4c,imageIcon5c;

    public selectVowels() throws HeadlessException {
        setResizable(false);
        Container c=getContentPane();
        setVisible(true);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        setSize(950,600);
        setLocationRelativeTo(null);

        Font lblfont=null;

        try {

            lblfont = Font.createFont(Font.TRUETYPE_FONT, new File("resources/astron boy.ttf")).deriveFont(30f);
            GraphicsEnvironment gel = GraphicsEnvironment.getLocalGraphicsEnvironment();
            gel.registerFont(Font.createFont(Font.TRUETYPE_FONT, new File("resources/astron boy.ttf")));


        } catch (FontFormatException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }





        imageIcon1= new ImageIcon(new ImageIcon("resources/1.png").getImage().getScaledInstance(101, 111, Image.SCALE_SMOOTH));
        imageIcon1c= new ImageIcon(new ImageIcon("resources/click 1.png").getImage().getScaledInstance(101, 111, Image.SCALE_SMOOTH));

        lblone=new JLabel();
        lblone.setBounds(100,294,101,111);
        lblone.setIcon(imageIcon1);
        lblone.setBorder(new EmptyBorder(0,0,0,0));
        lblone.addMouseListener(this);

        imageIcon2= new ImageIcon(new ImageIcon("resources/2.png").getImage().getScaledInstance(101, 111, Image.SCALE_SMOOTH));
        imageIcon2c= new ImageIcon(new ImageIcon("resources/click 2.png").getImage().getScaledInstance(101, 111, Image.SCALE_SMOOTH));

        lbltwo=new JLabel();
        lbltwo.setBounds(262,294,101,111);
        lbltwo.setIcon(imageIcon2);
        lbltwo.setBorder(new EmptyBorder(0,0,0,0));
        lbltwo.addMouseListener(this);

        imageIcon3= new ImageIcon(new ImageIcon("resources/3.png").getImage().getScaledInstance(101, 111, Image.SCALE_SMOOTH));
        imageIcon3c= new ImageIcon(new ImageIcon("resources/click 3.png").getImage().getScaledInstance(101, 111, Image.SCALE_SMOOTH));

        lblthree=new JLabel();
        lblthree.setBounds(423,294,101,111);
        lblthree.setIcon(imageIcon3);
        lblthree.setBorder(new EmptyBorder(0,0,0,0));
        lblthree.addMouseListener(this);

        imageIcon4= new ImageIcon(new ImageIcon("resources/4.png").getImage().getScaledInstance(101, 111, Image.SCALE_SMOOTH));
        imageIcon4c= new ImageIcon(new ImageIcon("resources/click 4.png").getImage().getScaledInstance(101, 111, Image.SCALE_SMOOTH));

        lblfour=new JLabel();
        lblfour.setBounds(587,294,101,111);
        lblfour.setIcon(imageIcon4);
        lblfour.setBorder(new EmptyBorder(0,0,0,0));
        lblfour.addMouseListener(this);

        imageIcon5= new ImageIcon(new ImageIcon("resources/5.png").getImage().getScaledInstance(101, 111, Image.SCALE_SMOOTH));
        imageIcon5c= new ImageIcon(new ImageIcon("resources/click 5.png").getImage().getScaledInstance(101, 111, Image.SCALE_SMOOTH));

        lblfive=new JLabel();
        lblfive.setBounds(750,294,101,111);
        lblfive.setIcon(imageIcon5);
        lblfive.setBorder(new EmptyBorder(0,0,0,0));
        lblfive.addMouseListener(this);








        ImageIcon   imageIconback = new ImageIcon(new ImageIcon("resources/vowels.png").getImage().getScaledInstance(950, 600, Image.SCALE_SMOOTH));


        jlblbackground=new JLabel();
        jlblbackground.setBorder(new EmptyBorder(0,0,0,0));
        jlblbackground.setIcon(imageIconback);



        c.add(lblfive);
        c.add(lblfour);
        c.add(lblthree);
        c.add(lbltwo);
        c.add(lblone);
        c.add(jlblbackground);
        validate();



    }

    @Override
    public void mouseClicked(MouseEvent e) {
        GameSounds.clicksound();
        if (e.getComponent()==lblone){
  GamePlay.numvowel=1;
            logintoserver();
            this.dispose();


        }
        if (e.getComponent()==lbltwo){
            GamePlay.numvowel=2;
            logintoserver();
            this.dispose();


        }if (e.getComponent()==lblthree){
            GamePlay.numvowel=3;
            logintoserver();
            this.dispose();


        }if (e.getComponent()==lblfive){
            GamePlay.numvowel=5;
            logintoserver();
            this.dispose();


        }if (e.getComponent()==lblfour){
            GamePlay.numvowel=4;
            logintoserver();
            this.dispose();


        }
    }

    private void logintoserver()  {
        packet = new Packet00Login(00, GloryMenu.gloryplayer.getPlayerName());
        try {
            packet.writeData(GloryMenu.gameClient);
        } catch (IOException e) {
            e.printStackTrace();
        }
        WatingForLoaders watingForLoaders=new WatingForLoaders();
        GloryMenu.gameClient.watingForLoaders=watingForLoaders;
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
        if (e.getComponent()==lblone)
            lblone.setIcon(imageIcon1c);
        if (e.getComponent()==lbltwo)
            lbltwo.setIcon(imageIcon2c);
        if (e.getComponent()==lblthree)
            lblthree.setIcon(imageIcon3c);
        if (e.getComponent()==lblfour)
            lblfour.setIcon(imageIcon4c);
        if (e.getComponent()==lblfive)
            lblfive.setIcon(imageIcon5c);
    }

    @Override
    public void mouseExited(MouseEvent e) {
        if (e.getComponent()==lblone)
            lblone.setIcon(imageIcon1);
        if (e.getComponent()==lbltwo)
            lbltwo.setIcon(imageIcon2);
        if (e.getComponent()==lblthree)
            lblthree.setIcon(imageIcon3);
        if (e.getComponent()==lblfour)
            lblfour.setIcon(imageIcon4);
        if (e.getComponent()==lblfive)
            lblfive.setIcon(imageIcon5);
    }
}
