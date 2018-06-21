
package Glory_Schema;




/*
WordElement class use to keep the player inserted word
*/
public class WordElement extends GloryElement {
    
    private String playerinsertword; //keep the player inserted word
    public  static String sysword;
    /*
    get  player inserted word
    */
    public String getPlayerinsertword() {
        return playerinsertword;
    }

    /*
    set  player inserted word..
    */
    public void setPlayerinsertword(String playerinsertword) {
        this.playerinsertword = playerinsertword;
    }
    
    /*
    get length of player inserted word..
    */
    public int getwordlength(){
    
        return playerinsertword.length();
    }
          
        
}
