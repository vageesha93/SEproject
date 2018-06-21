package Glory_Schema;

import javax.sound.sampled.*;
import java.io.*;


public class GameSounds {
    static Clip clip;
    public static void backgroundsound(){

        try {

            AudioInputStream inputStream = AudioSystem.getAudioInputStream(new File("resources/Game-Menu.wav"));
          clip  = AudioSystem.getClip();
            clip.open(inputStream);
            clip.loop(Clip.LOOP_CONTINUOUSLY);

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (LineUnavailableException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (UnsupportedAudioFileException e) {
            e.printStackTrace();
        }
    }

    public static void clicksound(){

        try {

            AudioInputStream inputStream = AudioSystem.getAudioInputStream(new File("resources/Mouse_Click.wav"));
            Clip clip1 = AudioSystem.getClip();
            if (!clip1.isOpen())
            clip1.open(inputStream);
            if (!clip1.isRunning())
            clip1.start();


        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (LineUnavailableException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (UnsupportedAudioFileException e) {
            e.printStackTrace();
        }
    }

    public static void hoversound(){

        try {

            AudioInputStream inputStream = AudioSystem.getAudioInputStream(new File("resources/Mouse_hover.wav"));
            Clip clip2 = AudioSystem.getClip();
            if (!clip2.isOpen())
            clip2.open(inputStream);
            if (!clip2.isRunning())
           clip2.start();


        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (LineUnavailableException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (UnsupportedAudioFileException e) {
            e.printStackTrace();
        }

    }

}
