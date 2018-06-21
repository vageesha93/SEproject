package Interfaces;

import Glory_Schema.GameSounds;
import Glory_Schema.PenaltyTimerElement;
import GloryClient.Packet;
import GloryClient.Packet05Sendscore;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.IOException;


public class ScoreViewandSetVowels extends JFrame implements MouseListener  {


    private JLabel jlblbackground,lblname,lblscore,lblone,lbltwo,lblthree,lblfour,lblfive;
  private   Packet packet;
    public static JLabel lblweakest;

    private ImageIcon imageIcon1,imageIcon2,imageIcon3,imageIcon4,imageIcon5;
    private ImageIcon imageIcon1c,imageIcon2c,imageIcon3c,imageIcon4c,imageIcon5c;

    public ScoreViewandSetVowels() throws HeadlessException {
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

        ImageIcon   imageIconscoe = new ImageIcon(new ImageIcon("resources/GrayRoundedButton.png").getImage().getScaledInstance(350, 60, Image.SCALE_SMOOTH));


        lblname=new JLabel("Score: ");
        lblname.setBorder(new EmptyBorder(0,0,0,0));
        lblname.setBounds(575,495,350,60);
        lblname.setFont(lblfont);
        lblname.setForeground(Color.YELLOW);
        lblname.setVerticalTextPosition(SwingConstants.CENTER);
        lblname.setHorizontalTextPosition(SwingConstants.CENTER);
        lblname.setIcon(imageIconscoe);

         imageIcon1= new ImageIcon(new ImageIcon("resources/1.png").getImage().getScaledInstance(101, 111, Image.SCALE_SMOOTH));
        imageIcon1c= new ImageIcon(new ImageIcon("resources/click 1.png").getImage().getScaledInstance(101, 111, Image.SCALE_SMOOTH));

        lblone=new JLabel();
        lblone.setBounds(100,294,101,111);
        lblone.setIcon(imageIcon1);
        lblone.setBorder(new EmptyBorder(0,0,0,0));
        lblone.addMouseListener(this);

        lblweakest=new JLabel("**You are the weakest player. There will be a 5 second punishment**");
        lblweakest.setBounds(50,420,810,50);
        lblweakest.setFont(lblfont);
        lblweakest.setVisible(false);
        lblweakest.setForeground(Color.magenta);
        lblweakest.setBorder(new EmptyBorder(0,0,0,0));


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


        lblscore=new JLabel();
        lblscore.setBorder(new EmptyBorder(0,0,0,0));
        lblscore.setForeground(Color.YELLOW);
        lblscore.setHorizontalTextPosition(SwingConstants.CENTER);
        lblscore.setVerticalTextPosition(SwingConstants.CENTER);
        lblscore.setBounds(25,495,350,60);
        lblscore.setIcon(imageIconscoe);
        lblscore.setFont(lblfont);






     ImageIcon   imageIconback = new ImageIcon(new ImageIcon("resources/vowels.jpg").getImage().getScaledInstance(950, 600, Image.SCALE_SMOOTH));


        jlblbackground=new JLabel();
        jlblbackground.setBorder(new EmptyBorder(0,0,0,0));
        jlblbackground.setIcon(imageIconback);


        c.add(lblweakest);
        c.add(lblfive);
        c.add(lblfour);
        c.add(lblthree);
        c.add(lbltwo);
        c.add(lblone);
        c.add(lblscore);
        c.add(lblname);
        c.add(jlblbackground);
        validate();

        lblname.setText(lblname.getText()+String.valueOf(GloryMenu.gloryplayer.getScore()));
        lblscore.setText(GloryMenu.gloryplayer.getPlayerName());

        System.out.println("My weak:"+PenaltyTimerElement.CountDownTime+"Is : "+PenaltyTimerElement.IsGive);
        packet=new Packet05Sendscore(GloryMenu.gloryplayer.getRoomid(),GloryMenu.gloryplayer.getPlayerName(),GloryMenu.gloryplayer.getRoundnum(),GloryMenu.gloryplayer.getScore());
        try {
            packet.writeData(GloryMenu.gameClient);
        } catch (IOException e1) {
            e1.printStackTrace();
        }


    }

    @Override
    public void mouseClicked(MouseEvent e) {
        GameSounds.clicksound();
        if (e.getComponent()==lblone){
        GamePlay.numvowel=1;
        }
        if (e.getComponent()==lbltwo){
            GamePlay.numvowel=2;

        }if (e.getComponent()==lblthree){
            GamePlay.numvowel=3;
        }if (e.getComponent()==lblfive){
            GamePlay.numvowel=4;
        }if (e.getComponent()==lblfour){
            GamePlay.numvowel=5;
        }

     WatingForLoaders watingForLoaders=new WatingForLoaders();
        GloryMenu.gameClient.watingForLoaders=watingForLoaders;
        try {
            GloryMenu.gameClient.sendData("06"+GloryMenu.gloryplayer.getRoomid());
        } catch (IOException e1) {
            e1.printStackTrace();
        }
        this.dispose();
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
