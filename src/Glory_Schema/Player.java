
package Glory_Schema;



/*
Player is the subclass of GloryElement that represents instances of variables
*/
public class Player extends GloryElement {
    
    private String playerName; //contains the name string
    private int score; //numeric value of the variable
    private int roundnum; //number of round
    private int rpv; //round pass value
    private String initial_two;
    private int roomid;
    private int autoUsedcount;

    public int getAutoUsedcount() {
        return autoUsedcount;
    }

    public void setAutoUsedcount(int autoUsedcount) {
        this.autoUsedcount = autoUsedcount;
    }

    public void setRpv(int rpv) {
        this.rpv = rpv;
    }

    public void setRoundnum(int roundnum) {
        this.roundnum = roundnum;
    }



    public int getRoomid() {
        return roomid;
    }

    public void setRoomid(int roomid) {
        this.roomid = roomid;
    }

    public String getInitial_two() {
        return initial_two;
    }

    public void setInitial_two(String initial_two) {
        this.initial_two = initial_two;
    }

    public int getRoundnum() {
        return roundnum;
    }

    public void increaseroundnum() {
        roundnum++;
        if (roundnum>1 && roundnum<=5){
            rpv=rpv+10;
            setRoundScore();
        }
    }

    /*
        The constructor for Player should take a string denoting the variable name
        */
    public Player(String playerName) {
        this.playerName = playerName;
        rpv=50;
        score=150;
        autoUsedcount=0;
    }



    public void setRoundScore(){
        score=score+rpv;
    }


    public int getScore() {
        return score;
    }


    public void setScore(int score) {
        this.score += score;
    }

    public String getPlayerName() {
        return playerName;
    }
    
    
    
    
}
