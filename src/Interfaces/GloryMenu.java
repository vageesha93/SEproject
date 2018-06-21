package Interfaces;

import Glory_Schema.GameSounds;
import Glory_Schema.Player;
import GloryClient.GameClient;
import GloryClient.Packet;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.*;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;

public class GloryMenu extends JFrame implements MouseListener {


    private JLabel jlblbackground, lblplay, lblexit, lblhelp, textBoxTransparan;
    TextBoxTransparan txtplayname;

    private ImageIcon imageIconback, imageIconplay, imageIconpexit, imageIconhelp, imageIconhelp2, imageIconplay2, imageIconpexit2;

    public static Player gloryplayer;
    Packet packet;
    public static GameClient gameClient ;

    static GraphicsDevice device = GraphicsEnvironment
            .getLocalGraphicsEnvironment().getScreenDevices()[0];

    public GloryMenu() throws HeadlessException {
        setResizable(false);
        Dimension dimension = new Dimension();
        dimension.setSize(950, 600);
       Container  c = getContentPane();
        setVisible(true);
        setLocationRelativeTo(null);
        setMaximumSize(dimension);
        setSize(dimension);
        setMinimumSize(dimension);



        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        //    device.setFullScreenWindow(this);


        Font lblfont = null;
        Font customFont = null;
        try {
            customFont = Font.createFont(Font.TRUETYPE_FONT, new File("resources/Future TimeSplitters.otf")).deriveFont(90f);
            GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
            ge.registerFont(Font.createFont(Font.TRUETYPE_FONT, new File("resources/Future TimeSplitters.otf")));

            lblfont = Font.createFont(Font.TRUETYPE_FONT, new File("resources/astron boy.ttf")).deriveFont(30f);
            GraphicsEnvironment gel = GraphicsEnvironment.getLocalGraphicsEnvironment();
            gel.registerFont(Font.createFont(Font.TRUETYPE_FONT, new File("resources/astron boy.ttf")));


        } catch (FontFormatException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }


        txtplayname = new TextBoxTransparan();
        txtplayname.setBounds(510, 225, 295, 50);
        txtplayname.setFont(lblfont);
        txtplayname.setForeground(Color.white);
        txtplayname.setFocusable(true);


        textBoxTransparan = new JLabel();
        textBoxTransparan.setForeground(Color.white);
        textBoxTransparan.setBounds(150, 75, 400, 100);
        //textBoxTransparan.setText("Glory Game");
        textBoxTransparan.setFont(customFont);

        imageIconback = new ImageIcon(new ImageIcon("resources/username.png").getImage().getScaledInstance(950, 600, Image.SCALE_SMOOTH));

        jlblbackground = new JLabel();
        jlblbackground.setBorder(new EmptyBorder(0, 0, 0, 0));
        jlblbackground.setIcon(imageIconback);

        imageIconplay = new ImageIcon(new ImageIcon("resources/Play.png").getImage().getScaledInstance(150, 65, Image.SCALE_SMOOTH));
        imageIconplay2 = new ImageIcon(new ImageIcon("resources/Playclick.png").getImage().getScaledInstance(150, 65, Image.SCALE_SMOOTH));

        lblplay = new JLabel();
        lblplay.setIcon(imageIconplay);
        lblplay.setLocation(559, 484);
        lblplay.setSize(150, 65);
        lblplay.addMouseListener(this);

        //imageIconpexit = new ImageIcon(new ImageIcon("/resources/Exitclick.png").getImage().getScaledInstance(150, 65, Image.SCALE_SMOOTH));
        imageIconpexit2 = new ImageIcon(new ImageIcon("resources/Exitclick.png").getImage().getScaledInstance(150, 65, Image.SCALE_SMOOTH));
        imageIconpexit = new ImageIcon(new ImageIcon("resources/Exit1.png").getImage().getScaledInstance(150, 65, Image.SCALE_SMOOTH));


        lblexit = new JLabel();
        lblexit.setSize(150, 65);
        lblexit.setLocation(750, 484);
        lblexit.setIcon(imageIconpexit);
        lblexit.addMouseListener(this);

        imageIconhelp = new ImageIcon(new ImageIcon("resources/Help.png").getImage().getScaledInstance(150, 65, Image.SCALE_SMOOTH));
        imageIconhelp2 = new ImageIcon(new ImageIcon("resources/Helpclick.png").getImage().getScaledInstance(150, 65, Image.SCALE_SMOOTH));

        lblhelp = new JLabel();
        lblhelp.setBorder(new EmptyBorder(0, 0, 0, 0));
        lblhelp.setIcon(imageIconhelp);
        lblhelp.setForeground(Color.green);
        lblhelp.setBounds(54, 484, 150, 65);
        lblhelp.setFont(lblfont);
        lblhelp.addMouseListener(this);


        c.add(lblhelp);
        c.add(txtplayname);
        c.add(textBoxTransparan);
        c.add(lblexit);
        c.add(lblplay);
        c.add(jlblbackground);
        validate();
        try {
            gameClient = new GameClient("localhost",1331);
            gameClient.run();
        } catch (IOException e1) {
            e1.printStackTrace();
        }

    }




    public static void main(String args[]) {


    }

    @Override
    public void mouseClicked(MouseEvent e) {
        GameSounds.clicksound();
        if (e.getComponent() == lblplay  ) {


          try {
               gameClient.sendData("03");
           } catch (IOException e1) {
               e1.printStackTrace();
           }
           if(!txtplayname.getText().isEmpty()) {
               if (!GameClient.allPlayersName.contains(txtplayname.getText())) {
                   gloryplayer = new Player(txtplayname.getText());
                       this.dispose();
                     new selectVowels();

               }else {
                   JOptionPane.showMessageDialog(this,"Name should be unique");
               }
        }
    }

    else if(e.getComponent()==lblexit)
    {

        System.exit(0);

    }else if (e.getComponent()==lblhelp){
            URI uri = null;
            try {
                uri = new URL("https://www.google.lk/").toURI();
            } catch (URISyntaxException e1) {
                e1.printStackTrace();
            } catch (MalformedURLException e1) {
                e1.printStackTrace();
            }
            Desktop desktop = Desktop.isDesktopSupported() ? Desktop.getDesktop() : null;
            if (desktop != null && desktop.isSupported(Desktop.Action.BROWSE))
                try {
                    desktop.browse(uri);
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
        }
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
        if (e.getComponent()==lblplay){
            lblplay.setIcon(imageIconplay2);
        }
        if (e.getComponent()==lblexit)
            lblexit.setIcon(imageIconpexit2);
        if (e.getComponent()==lblhelp)
            lblhelp.setIcon(imageIconhelp2);
    }

    @Override
    public void mouseExited(MouseEvent e) {
        if (e.getComponent()==lblplay){
            lblplay.setIcon(imageIconplay);
        }
        if (e.getComponent()==lblexit)
            lblexit.setIcon(imageIconpexit);
        if (e.getComponent()==lblhelp)
            lblhelp.setIcon(imageIconhelp);
    }



}
