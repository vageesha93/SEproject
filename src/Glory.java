import Glory_Schema.*;

import Interfaces.*;





public class Glory {
//.
    public static void main(String args[]) {

       Dictionary dictionary = new Dictionary();
           dictionary.loadDictionary();




       GameSounds.backgroundsound();


        try {
            new LoadingPage();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        //  new ScoreViewandSetVowels();
//-------------------------------------------------------------

     //   new WatingForLoaders();
   // new FinalScoreBoard();



    }

}
