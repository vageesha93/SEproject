
package Glory_Schema;



import GloryClient.GameClient;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;


public class FunctionElement extends GloryElement {
    
    private final List<Character> wordchar  = new ArrayList<>();  //store random generated word as characters
    
      private final List<Character> letters  = new ArrayList<>();  //store user insert word as characters



    public String getThreeinitial(){
  String letters=null;
        for (int x = 0; x < 3; x++) {
            if (letters == null) {
                letters = String.valueOf(getrandomletter());
            } else {
                boolean recall;
                char a;
                do {
                    recall = false;
                    a = getrandomletter();
                    for (int i = 0; i < letters.length(); i++) {
                        if (letters.charAt(i) == a) {

                            recall = true;
                            break;
                        }
                    }
                } while (recall);
                letters += String.valueOf(a);
            }
        }
 return letters;
    }


      /*
      generate three initial letters
      */
    public char getrandomletter(){
    
        Random r=new Random();
        
        char letter = (char)(r.nextInt(26) + 'A');
        return letter;
    }
    
    /*
    put the random generated word into characters
    */
    public void putintochar(String word){
         for(int x=0;x<word.length();x++)
        wordchar.add(word.charAt(x));
    }
    
    /*
    put the user insert word(input result) into character
    */
     public void putintoletters(String word){
         for(int x=0;x<word.length();x++)
        letters.add(word.charAt(x));
    }
    
    /*
     calculate the bouns and letter value
     */
    public void calculatebouns(){
        bonuses=0;

        LetterValueElement l =new LetterValueElement();
         
        for (char letter : letters) {
         bonuses += l.getValue(letter);
        }

      int size=letters.size();
        switch (size){
            case 2:bonuses=bonuses+150;break;
            case 3:bonuses=bonuses+200;break;
            case 4:bonuses=bonuses+250;break;
            case 5:bonuses=bonuses+300;break;
            case 6:bonuses=bonuses+350;break;
            case 7:bonuses=bonuses+400;break;
            case 8:bonuses=bonuses+450;break;
            case 9:bonuses=bonuses+500;break;
            case 10:bonuses=bonuses+550;break;
            case 11:bonuses=bonuses+600;break;

        }

        
    
    }



    /*
 Calculate the sum of the letter values which are not used by user....
     */
    public int cal_letter_value_not_used(){
        int value = 0,value2 = 0;
         LetterValueElement l =new LetterValueElement();
         for (char letter : wordchar) {
         value += l.getValue(letter);
        }
        for (char letter : letters) {
            value2 += l.getValue(letter);

        }
        value=value-value2;
     return  (value);
    }


    /*
     Calculate the total score as a ratio of used letters vs notused letters
    */
    public int calscore(){
        int letter_value_not_used=cal_letter_value_not_used();
    int score=bonuses-letter_value_not_used;
        return score;
    }
    
    
    
}
