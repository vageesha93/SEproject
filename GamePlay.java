package Interfaces;

import Glory_Schema.*;
import GloryClient.*;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.IOException;


public class GamePlay extends JFrame implements MouseListener{

    Packet packet,packet2;
    String letters = null ;
    private int lettermove=0;
    String wordplayerinsert;
    public boolean playerstop;
    public static int numvowel;


    FunctionElement functionElement;
    LetterValueElement letterValueElement;
    AutoSearchWord autoSearch;
    WordElement wordElement;

    private JLabel jlblbackground, lbl1,lblscore,lbl2,lbl3,lbl4,lbl5,lbl6,lbl7,lbl8,lbl9,lbl10,lbl11,lbl12;
    private JLabel lblrst1,lblrst2,lblrst3,lblrst4,lblrst5,lblrst6,lblrst7,lblrst8,lblrst9,lblrst10,lblrst11,lblrst12;
    private JLabel lblroundnum,lblclear, lblcurrentplayer,lblauto,lblclose;
    public static JLabel player2,player3,player4,player5,lblp2score,lblp3score,lblp4score,lblp5score;
    public static JLabel lblp2init,lblp3init,lblp4init,lblp5init;
    public JLabel  lbltimer;
    private JPanel panel1;

    Font customFont = null;
    Font lblfont=null;
    Font gloryfont;

    ImageIcon imageIconclear,imageIconauto,imageIconsubmit,imageIconclose,imageIconclear2,imageIconauto2,imageIconclose2,imgback;

    public GamePlay() throws HeadlessException {
        setResizable(false);
        Dimension dimension=new Dimension(950,600);
        setVisible(true);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        Container c=getContentPane();

        setMaximumSize(dimension);
        setSize(dimension);
        setMinimumSize(dimension);
        setLocationRelativeTo(null);





        try {
            customFont = Font.createFont(Font.TRUETYPE_FONT, new File("resources/digital-7.ttf")).deriveFont(85f);
            GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
            ge.registerFont(Font.createFont(Font.TRUETYPE_FONT, new File("resources/digital-7.ttf")));

            lblfont = Font.createFont(Font.TRUETYPE_FONT, new File("resources/astron boy video.ttf")).deriveFont(32f);
            GraphicsEnvironment gel = GraphicsEnvironment.getLocalGraphicsEnvironment();
            gel.registerFont(Font.createFont(Font.TRUETYPE_FONT, new File("resources/astron boy video.ttf")));

        } catch (FontFormatException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }


        jlblbackground=new JLabel();
        gloryfont =new Font("Arial",Font.BOLD,34);

         imageIconclear = new ImageIcon(new ImageIcon("resources/btn_clear_new.png").getImage().getScaledInstance(150, 65, Image.SCALE_SMOOTH));
        imageIconclear2= new ImageIcon(new ImageIcon("resources/btn_clear_new_click.png").getImage().getScaledInstance(150, 65, Image.SCALE_SMOOTH));

       addplayerlbl();



        lblclear=new JLabel();
        lblclear.setIcon(imageIconclear);
        lblclear.setBorder(new EmptyBorder(0, 0, 0, 0));
        lblclear.setBounds(325,487,150,65);
        lblclear.setBorder(new EmptyBorder(0,0,0,0));
        lblclear.addMouseListener(this);

         imageIconauto = new ImageIcon(new ImageIcon("resources/btn_auto_new.png").getImage().getScaledInstance(45, 45, Image.SCALE_SMOOTH));
        imageIconauto2 = new ImageIcon(new ImageIcon("resources/btn_auto_new_click.png").getImage().getScaledInstance(45, 45, Image.SCALE_SMOOTH));

        lblauto=new JLabel();
        lblauto.setIcon(imageIconauto);
        lblauto.setBounds(254,252,47,47);
        lblauto.setBorder(new EmptyBorder(0,0,0,0));
        lblauto.addMouseListener(this);

         imageIconclose = new ImageIcon(new ImageIcon("resources/close.png").getImage().getScaledInstance(56, 56, Image.SCALE_SMOOTH));
        imageIconclose2 = new ImageIcon(new ImageIcon("resources/close click.png").getImage().getScaledInstance(56, 56, Image.SCALE_SMOOTH));


        lblclose=new JLabel();
        lblclose.setIcon(imageIconclose);
        lblclose.setBorder(new EmptyBorder(0,0,0,0));
        lblclose.setBounds(850,487,56,56);
        lblclose.addMouseListener(this);

        imageIconsubmit = new ImageIcon(new ImageIcon("resources/player_name.png").getImage().getScaledInstance(135, 47, Image.SCALE_SMOOTH));


        lblcurrentplayer =new JLabel();
        lblcurrentplayer.setIcon(imageIconsubmit);
        lblcurrentplayer.setBounds(660,495,135,47);
        lblcurrentplayer.setHorizontalTextPosition(SwingConstants.CENTER);
        lblcurrentplayer.setForeground(Color.magenta);
        lblcurrentplayer.setFont(new Font("Arial",Font.BOLD,18));
        lblcurrentplayer.setBorder(new EmptyBorder(0,0,0,0));

        addletterlbl();
        addresultlbl();

        lblroundnum=new JLabel("0");
        lblroundnum.setFont(customFont);
        lblroundnum.setForeground(Color.YELLOW);
        lblroundnum.setBounds(559,455,75,75);

        lbltimer=new JLabel("00");
        lbltimer.setFont(customFont.deriveFont(Font.BOLD));
        lbltimer.setForeground(Color.GREEN);
        lbltimer.setHorizontalAlignment(SwingConstants.CENTER);
        lbltimer.setSize(90,75);
        lbltimer.setLocation(45,445);

        lblscore=new JLabel("15000");
        lblscore.setFont(new Font("Arial",Font.BOLD,26));
        lblscore.setForeground(Color.YELLOW);
        lblscore.setBounds(850,19,100,50);

        imgback = new ImageIcon(new ImageIcon("resources/play_screen_bg_new.png").getImage().getScaledInstance(950, 600, Image.SCALE_SMOOTH));


        jlblbackground.setBorder(new EmptyBorder(0,0,0,0));
        jlblbackground.setIcon(imgback);

         addcontainer(c);
        validate();
        glorypaly();

    }

    private void addcontainer(Container c){
        c.add(lblp5init);
        c.add(lblp5score);
        c.add(lblp4init);
        c.add(lblp4score);
        c.add(lblp3score);
        c.add(lblp3init);
        c.add(lblp2init);
        c.add(lblp2score);
        c.add(player5);
        c.add(player4);
        c.add(player3);
        c.add(player2);
        c.add(lblcurrentplayer);
        c.add(lblclose);
        c.add(lblauto);
        c.add(lblclear);
        c.add(lblroundnum);
        c.add(lblrst12);
        c.add(lblrst11);
        c.add(lblrst10);
        c.add(lblrst9);
        c.add(lblrst8);
        c.add(lblrst7);
        c.add(lblrst6);
        c.add(lblrst5);
        c.add(lblrst4);
        c.add(lblrst3);
        c.add(lblrst2);
        c.add(lblrst1);
        c.add(lbl12);
        c.add(lbl11);
        c.add(lbl10);
        c.add(lbl9);
        c.add(lbl8);
        c.add(lbl7);
        c.add(lbl6);
        c.add(lbl5);
        c.add(lbl4);
        c.add(lbl3);
        c.add(lblscore);
        c.add(lbl2);
        c.add(lbl1);
        c.add(lbltimer);
        c.add(jlblbackground);
    }

    private void addplayerlbl(){
        player2=new JLabel();
        player2.setBounds(25,51,100,15);
        player2.setForeground(Color.magenta);
        player2.setBorder(new EmptyBorder(0,0,0,0));

        lblp2score=new JLabel();
        lblp2score.setBounds(75,51,85,15);
        lblp2score.setHorizontalAlignment(SwingConstants.CENTER);
        lblp2score.setForeground(Color.magenta);
        lblp2score.setBorder(new EmptyBorder(0,0,0,0));

        lblp2init=new JLabel();
        lblp2init.setBounds(158,51,85,15);
        lblp2init.setForeground(Color.magenta);
        lblp2init.setHorizontalAlignment(SwingConstants.CENTER);
        lblp2init.setBorder(new EmptyBorder(0,0,0,0));

        player3=new JLabel();
        player3.setBounds(25,80,200,15);
        player3.setForeground(Color.magenta);
        player3.setBorder(new EmptyBorder(0,0,0,0));

        lblp3score=new JLabel();
        lblp3score.setBounds(75,80,85,15);
        lblp3score.setHorizontalAlignment(SwingConstants.CENTER);
        lblp3score.setForeground(Color.magenta);
        lblp3score.setBorder(new EmptyBorder(0,0,0,0));

        lblp3init=new JLabel();
        lblp3init.setBounds(158,80,85,15);
        lblp3init.setForeground(Color.magenta);
        lblp3init.setHorizontalAlignment(SwingConstants.CENTER);
        lblp3init.setBorder(new EmptyBorder(0,0,0,0));


        player4=new JLabel();
        player4.setBounds(25,107,200,15);
        player4.setForeground(Color.magenta);
        player4.setBorder(new EmptyBorder(0,0,0,0));

        lblp4score=new JLabel();
        lblp4score.setBounds(75,107,85,15);
        lblp4score.setHorizontalAlignment(SwingConstants.CENTER);
        lblp4score.setForeground(Color.magenta);
        lblp4score.setBorder(new EmptyBorder(0,0,0,0));

        lblp4init=new JLabel();
        lblp4init.setBounds(158,107,85,15);
        lblp4init.setForeground(Color.magenta);
        lblp4init.setHorizontalAlignment(SwingConstants.CENTER);
        lblp4init.setBorder(new EmptyBorder(0,0,0,0));

        player5=new JLabel();
        player5.setBounds(25,133,200,15);
        player5.setForeground(Color.magenta);
        player5.setBorder(new EmptyBorder(0,0,0,0));

        lblp5score=new JLabel();
        lblp5score.setBounds(75,133,85,15);
        lblp5score.setHorizontalAlignment(SwingConstants.CENTER);
        lblp5score.setForeground(Color.magenta);
        lblp5score.setBorder(new EmptyBorder(0,0,0,0));

        lblp5init=new JLabel();
        lblp5init.setBounds(158,133,85,15);
        lblp5init.setForeground(Color.magenta);
        lblp5init.setHorizontalAlignment(SwingConstants.CENTER);
        lblp5init.setBorder(new EmptyBorder(0,0,0,0));

    }

    @Override
    public void mouseClicked(MouseEvent e) {

        if (SwingUtilities.isLeftMouseButton(e)) {

            if (e.getComponent() == lblclose) {
                packet = new Packet01Disconnect(GloryMenu.gloryplayer.getPlayerName());
                try {
                    packet.writeData(GloryMenu.gameClient);
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
                System.exit(0);
            }
            if (!playerstop) {
                if (e.getComponent() == lblauto && lblauto.isEnabled()) {
                    GameSounds.clicksound();
                    if (GloryMenu.gloryplayer.getAutoUsedcount() < 2) {
                        clear();
                        Autoletteradd();
                    }
                }
                if (e.getComponent() == lblclear && lblclear.isEnabled()) {
                    GameSounds.clicksound();
                    clear();
                }
               if (lblauto.isEnabled()) {
                   if (e.getComponent() == lbl1) {
                       letterMove(lbl1.getText());
                       lbl1.setVisible(false);
                   }
                   if (e.getComponent() == lbl2) {
                       letterMove(lbl2.getText());
                       lbl2.setVisible(false);
                   }
                   if (e.getComponent() == lbl3) {
                       letterMove(lbl3.getText());
                       lbl3.setVisible(false);
                   }
                   if (e.getComponent() == lbl4) {
                       letterMove(lbl4.getText());
                       lbl4.setVisible(false);
                   }
                   if (e.getComponent() == lbl5) {
                       letterMove(lbl5.getText());
                       lbl5.setVisible(false);
                   }
                   if (e.getComponent() == lbl6) {
                       letterMove(lbl6.getText());
                       lbl6.setVisible(false);
                   }
                   if (e.getComponent() == lbl7) {
                       letterMove(lbl7.getText());
                       lbl7.setVisible(false);
                   }
                   if (e.getComponent() == lbl8) {
                       letterMove(lbl8.getText());
                       lbl8.setVisible(false);
                   }
                   if (e.getComponent() == lbl9) {
                       letterMove(lbl9.getText());
                       lbl9.setVisible(false);
                   }
                   if (e.getComponent() == lbl10) {
                       letterMove(lbl10.getText());
                       lbl10.setVisible(false);
                   }
                   if (e.getComponent() == lbl11) {
                       letterMove(lbl11.getText());
                       lbl11.setVisible(false);
                   }
                   if (e.getComponent() == lbl12) {
                       letterMove(lbl12.getText());
                       lbl12.setVisible(false);
                   }
               }
            }
        }

    }

    private void clear(){
        wordplayerinsert=null;
        lettermove=0;
        for (int x=0;x<12;x++){
            letterMove(null);
        }
        lettermove=0;
       clearlettrs();
    }

    private void clearlettrs(){
        lbl1.setVisible(true);
        lbl1.setText(String.valueOf(letters.charAt(0)));
        lbl2.setVisible(true);
        lbl2.setText(String.valueOf(letters.charAt(1)));
        lbl3.setVisible(true);
        lbl3.setText(String.valueOf(letters.charAt(2)));
        lbl4.setVisible(true);
        lbl4.setText(String.valueOf(letters.charAt(3)));
        lbl5.setVisible(true);
        lbl5.setText(String.valueOf(letters.charAt(4)));
        lbl6.setVisible(true);
        lbl6.setText(String.valueOf(letters.charAt(5)));
        lbl7.setVisible(true);
        lbl7.setText(String.valueOf(letters.charAt(6)));
        lbl8.setVisible(true);
        lbl8.setText(String.valueOf(letters.charAt(7)));
        lbl9.setVisible(true);
        lbl9.setText(String.valueOf(letters.charAt(8)));
        lbl10.setVisible(true);
        lbl10.setText(String.valueOf(letters.charAt(9)));
        lbl11.setVisible(true);
        lbl11.setText(String.valueOf(letters.charAt(10)));
        lbl12.setVisible(true);
        //lbl12.setText(String.valueOf(letters.charAt(11)));
    }

    private void Autoletteradd(){
        GloryMenu.gloryplayer.setAutoUsedcount(GloryMenu.gloryplayer.getAutoUsedcount()+1);
        lblauto.setEnabled(false);
        lblclear.setEnabled(false);
        String wordkey=autoSearch.getFoundkey().toUpperCase();
        lettermove=0;
        for (int x=0;x<wordkey.length();x++){
            letterMove(String.valueOf(wordkey.charAt(x)));

        }
    }



    private void letterMove(String letter){
        lettermove++;
        switch (lettermove){
            case 1:lblrst1.setText(letter);break;
            case 2:lblrst2.setText(letter);break;
            case 3:lblrst3.setText(letter);break;
            case 4:lblrst4.setText(letter);break;
            case 5:lblrst5.setText(letter);break;
            case 6:lblrst6.setText(letter);break;
            case 7:lblrst7.setText(letter);break;
            case 8:lblrst8.setText(letter);break;
            case 9:lblrst9.setText(letter);break;
            case 10:lblrst10.setText(letter);break;
            case 11:lblrst11.setText(letter);break;
            case 12:lblrst12.setText(letter);break;
        }
        if (wordplayerinsert!=null){
            wordplayerinsert+=letter;
        }else {
            wordplayerinsert=letter;
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

    if (e.getComponent()==lblauto) {
        GameSounds.hoversound();
        lblauto.setIcon(imageIconauto2);
    }
       if (e.getComponent()==lblclose) {
           GameSounds.hoversound();
           lblclose.setIcon(imageIconclose2);
       }
        if (e.getComponent()==lblclear) {
            GameSounds.hoversound();
            lblclear.setIcon(imageIconclear2);
        }


    }

    @Override
    public void mouseExited(MouseEvent e) {
        if (e.getComponent()==lblauto)
            lblauto.setIcon(imageIconauto);
        if (e.getComponent()==lblclose)
            lblclose.setIcon(imageIconclose);
        if (e.getComponent()==lblclear)
            lblclear.setIcon(imageIconclear);

    }



    private void addletterlbl(){

        lbl12=new JLabel();
        lbl12.setFont(gloryfont);
        lbl12.setForeground(Color.CYAN);
        lbl12.setSize(40,40);
        lbl12.setLocation(880,210);
        lbl12.addMouseListener(this);

        lbl11=new JLabel();
        lbl11.setFont(gloryfont);
        lbl11.setForeground(Color.CYAN);
        lbl11.setSize(40,40);
        lbl11.setLocation(850,195);
        lbl11.addMouseListener(this);

        lbl10=new JLabel();
        lbl10.setFont(gloryfont);
        lbl10.setForeground(Color.CYAN);
        lbl10.setSize(40,40);
        lbl10.setLocation(803,195);
        lbl10.addMouseListener(this);


        lbl9=new JLabel();
        lbl9.setFont(gloryfont);
        lbl9.setForeground(Color.CYAN);
        lbl9.setSize(40,40);
        lbl9.setLocation(751,195);
        lbl9.addMouseListener(this);

        lbl8=new JLabel();
        lbl8.setFont(gloryfont);
        lbl8.setForeground(Color.CYAN);
        lbl8.setSize(40,40);
        lbl8.setLocation(704,195);
        lbl8.addMouseListener(this);


        lbl7=new JLabel();
        lbl7.setFont(gloryfont);
        lbl7.setForeground(Color.CYAN);
        lbl7.setSize(40,40);
        lbl7.setLocation(657,195);
        lbl7.addMouseListener(this);


        lbl6=new JLabel();
        lbl6.setFont(gloryfont);
        lbl6.setForeground(Color.CYAN);
        lbl6.setSize(40,40);
        lbl6.setLocation(610,195);
        lbl6.addMouseListener(this);


        lbl5=new JLabel();
        lbl5.setFont(gloryfont);
        lbl5.setForeground(Color.CYAN);
        lbl5.setSize(40,40);
        lbl5.setLocation(560,195);
        lbl5.addMouseListener(this);


        lbl4=new JLabel();
        lbl4.setFont(gloryfont);
        lbl4.setForeground(Color.CYAN);
        lbl4.setSize(40,40);
        lbl4.setLocation(516,195);
        lbl4.addMouseListener(this);


        lbl3=new JLabel();
        lbl3.setFont(gloryfont);
        lbl3.setForeground(Color.magenta);
        lbl3.setSize(40,40);
        lbl3.setLocation(469,195);
        lbl3.addMouseListener(this);


        lbl2=new JLabel();
        lbl2.setFont(gloryfont);
        lbl2.setForeground(Color.magenta);
        lbl2.setSize(40,40);
        lbl2.setLocation(422,195);
        lbl2.addMouseListener(this);

        lbl1 =new JLabel();
        lbl1.setFont(gloryfont);
        lbl1.setForeground(Color.magenta);
        lbl1.setSize(40,40);
        lbl1.setLocation(372,195);
        lbl1.addMouseListener(this);
    }
  private void addresultlbl(){

      lblrst12=new JLabel();
      lblrst12.setFont(gloryfont);
      lblrst12.setForeground(Color.YELLOW);
      lblrst12.setSize(40,40);
      lblrst12.setLocation(875,370);
      lblrst12.addMouseListener(this);

      lblrst11=new JLabel();
      lblrst11.setFont(gloryfont);
      lblrst11.setForeground(Color.YELLOW);
      lblrst11.setSize(40,40);
      lblrst11.setLocation(850,312);
      lblrst11.addMouseListener(this);

      lblrst10=new JLabel();
      lblrst10.setFont(gloryfont);
      lblrst10.setForeground(Color.YELLOW);
      lblrst10.setSize(40,40);
      lblrst10.setLocation(803,312);
      lblrst10.addMouseListener(this);

      lblrst9=new JLabel();
      lblrst9.setFont(gloryfont);
      lblrst9.setForeground(Color.YELLOW);
      lblrst9.setSize(40,40);
      lblrst9.setLocation(751,312);
      lblrst9.addMouseListener(this);

      lblrst8=new JLabel();
      lblrst8.setFont(gloryfont);
      lblrst8.setForeground(Color.YELLOW);
      lblrst8.setSize(40,40);
      lblrst8.setLocation(704,312);
      lblrst8.addMouseListener(this);

      lblrst7=new JLabel();
      lblrst7.setFont(gloryfont);
      lblrst7.setForeground(Color.YELLOW);
      lblrst7.setSize(40,40);
      lblrst7.setLocation(657,312);
      lblrst7.addMouseListener(this);

      lblrst6=new JLabel();
      lblrst6.setFont(gloryfont);
      lblrst6.setForeground(Color.YELLOW);
      lblrst6.setSize(40,40);
      lblrst6.setLocation(610,312);
      lblrst6.addMouseListener(this);

      lblrst5=new JLabel();
      lblrst5.setFont(gloryfont);
      lblrst5.setForeground(Color.YELLOW);
      lblrst5.setSize(40,40);
      lblrst5.setLocation(560,312);
      lblrst5.addMouseListener(this);

      lblrst4=new JLabel();
      lblrst4.setFont(gloryfont);
      lblrst4.setForeground(Color.YELLOW);
      lblrst4.setSize(40,40);
      lblrst4.setLocation(516,312);
      lblrst4.addMouseListener(this);

      lblrst3=new JLabel();
      lblrst3.setFont(gloryfont);
      lblrst3.setForeground(Color.YELLOW);
      lblrst3.setSize(40,40);
      lblrst3.setLocation(469,312);
      lblrst3.addMouseListener(this);

      lblrst2=new JLabel();
      lblrst2.setFont(gloryfont);
      lblrst2.setForeground(Color.YELLOW);
      lblrst2.setSize(40,40);
      lblrst2.setLocation(422,312);
      lblrst2.addMouseListener(this);

      lblrst1=new JLabel();
      lblrst1.setFont(gloryfont);
      lblrst1.setForeground(Color.YELLOW);
      lblrst1.setSize(40,40);
      lblrst1.setLocation(372,312);
      lblrst1.addMouseListener(this);

  }

private void glorypaly(){

    functionElement=new FunctionElement();
    letterValueElement=new LetterValueElement();
    letters=functionElement.getThreeinitial();
   lblcurrentplayer.setText(GloryMenu.gloryplayer.getPlayerName());
    GloryMenu.gloryplayer.increaseroundnum();
    lblroundnum.setText(String.valueOf(GloryMenu.gloryplayer.getRoundnum()));
    lblscore.setText(String.valueOf(GloryMenu.gloryplayer.getScore()));
    GloryMenu.gloryplayer.setInitial_two(letters);
    packet=new Packet04initsend(GloryMenu.gloryplayer.getPlayerName(),GloryMenu.gloryplayer.getRoomid(),letters,GloryMenu.gloryplayer.getScore());

    try {
        packet.writeData(GloryMenu.gameClient);
    } catch (IOException e) {
        e.printStackTrace();
    }

    lbl1.setText(String.valueOf(letters.charAt(0)));
    lbl2.setText(String.valueOf(letters.charAt(1)));

    letters=letterValueElement.getLetters(letters,numvowel);
    functionElement.putintochar(letters.toLowerCase());
    autoSearch=new AutoSearchWord(letters.toLowerCase());
    autoSearch.searchWord();

    addletters();

    if (!PenaltyTimerElement.IsGive){
        PenaltyTimerElement.CountDownTime=60;
    }else {
        PenaltyTimerElement.IsGive=false;
    }

 CountDownTimer countDownTimer=new CountDownTimer(PenaltyTimerElement.CountDownTime,this);
    countDownTimer.StartTimer();
}

    public void calscore() throws IOException {
        if (wordplayerinsert!=null) {
            if (Dictionary.words.contains(wordplayerinsert.toLowerCase())){
            wordElement = new WordElement();
            wordElement.setPlayerinsertword(wordplayerinsert);
            functionElement.putintoletters(wordElement.getPlayerinsertword());
            functionElement.calculatebouns();
            GloryMenu.gloryplayer.setScore(functionElement.calscore());
                lblscore.setText(String.valueOf(GloryMenu.gloryplayer.getScore()));



            }
        }
        GloryMenu.gameClient.jumpAP=0;
        if (GloryMenu.gloryplayer.getRoundnum()==5){
            new FinalScoreBoard();
            packet=new Packet05Sendscore(GloryMenu.gloryplayer.getRoomid(),GloryMenu.gloryplayer.getPlayerName(),GloryMenu.gloryplayer.getRoundnum(),GloryMenu.gloryplayer.getScore());
            try {
                packet.writeData(GloryMenu.gameClient);
            } catch (IOException e1) {
                e1.printStackTrace();
            }
            this.dispose();
        }else {
            ScoreViewandSetVowels scoreView = new ScoreViewandSetVowels();
            this.dispose();
        }
    }

    private void addletters(){
        int jump=0;
        for (int x=0;x<letters.length();x++){
            jump++;
            letterswitch(jump,x);
        }
    }

    private void letterswitch(int jump,int x){
        switch (jump){
            case 1:lbl1.setText(String.valueOf(letters.charAt(x)));break;
            case 2:lbl2.setText(String.valueOf(letters.charAt(x)));break;
            case 3:lbl3.setText(String.valueOf(letters.charAt(x)));break;
            case 4:lbl4.setText(String.valueOf(letters.charAt(x)));break;
            case 5:lbl5.setText(String.valueOf(letters.charAt(x)));break;
            case 6:lbl6.setText(String.valueOf(letters.charAt(x)));break;
            case 7:lbl7.setText(String.valueOf(letters.charAt(x)));break;
            case 8:lbl8.setText(String.valueOf(letters.charAt(x)));break;
            case 9:lbl9.setText(String.valueOf(letters.charAt(x)));break;
            case 10:lbl10.setText(String.valueOf(letters.charAt(x)));break;
            case 11:lbl11.setText(String.valueOf(letters.charAt(x)));break;

        }
    }


}
