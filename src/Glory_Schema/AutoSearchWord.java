package Glory_Schema;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;


public class AutoSearchWord {

    private String word;
    private int wordlength;
    private String foundkey;

    public AutoSearchWord(String word) {
        this.word = word;
        this.wordlength=11;
        this.foundkey=null;

    }

    public void searchWord(){
        Runnable runnable=new Runnable() {
            @Override
            public void run() {
                BufferedReader bf= null;
                try {
                    bf = new BufferedReader(new FileReader("resources/english.txt"));
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
                String str;
                boolean available=true;

                try {

                        while ((str = bf.readLine()) != null) {
                            str = str.trim();
                            str = str.substring(0, str.length() - 4);
                            if (str.length()==wordlength) {
                                int intpass = 0; int[] gotchar=new int[wordlength+1];
                                for (int a = 0; a < 11; a++) {
                                    for (int y = 0; y < wordlength; y++) {
                                            if ((word.charAt(a) == str.charAt(y))) {
                                                //    System.out.println("one: "+word.charAt(a)+" two:"+str.charAt(y)+" pass:"+intpass);
                                                boolean skip=false;
                                                int tmp=y+1;

                                                    if (gotchar[tmp]==tmp)
                                                        skip=true;

                                                if (!skip) {
                                                    intpass++;
                                                    gotchar[tmp]=tmp;
                                                    break;
                                                }
                                            }

                                    }
                                }
                                if ((intpass == wordlength ) &&(available==true)) {
                                            foundkey = str;
                                            available = false;

                                }
                            }

                        }

                        wordlength=wordlength-1;
                  if (foundkey==null) searchWord();
                   // System.out.println("word:"+foundkey);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        };

        Thread thread=new Thread(runnable);
        thread.start();

    }


    public String getFoundkey() {
        return foundkey;
    }
}
