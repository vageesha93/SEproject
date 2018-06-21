
package Glory_Schema;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;



/*
LetterValueElement for access the letter value
*/
public class LetterValueElement extends GloryElement {
    
    
      private final List<Character> consonants  = new ArrayList<>(
      Arrays.asList('B', 'C', 'D', 'F', 'G', 'H', 'J', 'K', 'L', 'M', 'N', 'P', 'Q', 'R', 'S', 'T', 'V', 'W', 'X', 'Y', 'Z')
  );//store all the consonants letters
     

  private final List<Character> vowels = new ArrayList<>(
      Arrays.asList(
          'A', 'E', 'I', 'O', 'U'
      )
  );//store all the vowels


    private final char[][] Scrabble_letter={ //Scrabble letter values
            {'1','L', 'S' ,'U', 'N' ,'R', 'T' ,'O' ,'A' ,'I' ,'E'},
            {'2','G','D'},
            {'3','B','C','M','P'},
            {'4','F', 'H', 'V', 'W', 'Y'},
            {'5','K'},
            {'8','J', 'X'},
            {'0','Q', 'Z'}}; //value 0 set to 10 because char can not store 10 use function to get the correct value




    public char undome(char a){
        char tmp;
        if (consonants.contains(a)){
            tmp=getrandomconsonants();
        }else {
            tmp=getrandomvowels();
        }


        return tmp;
    }
     
  /*
   find the value of each letter
   */
  public int getValue(char letter){
      letter=Character.toUpperCase(letter);
      int value=0;
      for (int i=0; i<(Scrabble_letter.length); i++ ) {
          for (int j=0;j<Scrabble_letter[i].length;j++)
              if (Scrabble_letter[i][j]==letter){
                  value=Integer.parseInt(String.valueOf(Scrabble_letter[i][0]));
                  if (value==0)value=9;
                  return value;
              }
      }


      return value;
  }


    public String getLetters(String letters,int getNumVowels){
        String tmp=letters;
        for (int x = 0; x < getNumVowels; x++) {

              char  a = getrandomvowels();

            tmp += String.valueOf(a);
        }


        for (int x = 0; x < (8 - getNumVowels); x++) {

              char  a = getrandomconsonants();

            tmp += String.valueOf(a);
        }

        return tmp;
    }

  
   /*
   generate random consonats letter 
   */
  public char getrandomconsonants(){
      Random ranchar =new Random();
      int value=ranchar.nextInt(21)+0;
      char letter=consonants.get(value);

      return letter;
  }
  
  
  /*
  generate random vowels letter 
  */
   public char getrandomvowels(){
      Random ranchar =new Random();
      int value=ranchar.nextInt(5)+0;
      char letter=vowels.get(value);

      return letter;
  }


}
