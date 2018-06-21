package Glory_Schema;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class Dictionary  {

    public static final List<String> words=new ArrayList<>(); //store all the english words from dictionary text file


  public void loadDictionary(){
      Runnable r=new Runnable() {
          @Override
          public void run() {
              String[] arr;
              BufferedReader bf= null;
              try {
                  bf = new BufferedReader(new FileReader("resources/english.txt"));
              } catch (FileNotFoundException e) {
                  e.printStackTrace();
              }
              String str;
              try {
                  while ((str = bf.readLine()) != null) {
                      str=str.trim();
                      str=str.substring(0,str.length()-4);
                      words.add(str);

                  }
              } catch (IOException e) {
                  e.printStackTrace();
              }
          }
      };
      Thread thread=new Thread(r);
      thread.start();

  }




}
