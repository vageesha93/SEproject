package Glory_Schema;


import Interfaces.GamePlay;

import java.awt.*;
import java.io.IOException;
import java.util.Timer;
import java.util.TimerTask;




public class CountDownTimer {

    static int interval;
    static Timer timer;
   private int secs;
    GamePlay gamePlay;

    public CountDownTimer(int secs, GamePlay gamePlay) {
        this.secs = secs;
        this.gamePlay = gamePlay;
    }

    public void StartTimer(){
     int delay = 1000;
     int period = 1000;
     timer = new Timer();
     interval = secs;
     timer.scheduleAtFixedRate(new TimerTask() {

         public void run() {
             int x=setInterval();
             if (x==40)
                 gamePlay.lbltimer.setForeground(Color.YELLOW);
             if (x==20)gamePlay.lbltimer.setForeground(Color.RED);
            gamePlay.lbltimer.setText(""+x);

             if (x==0){
                 gamePlay.playerstop=true;
                 try {
                     gamePlay.calscore();
                 } catch (IOException e) {
                     e.printStackTrace();
                 }
             }
         }
     }, delay, period);

 }

    private int setInterval() {
        if (interval == 1)
            timer.cancel();
        return --interval;
    }

}
