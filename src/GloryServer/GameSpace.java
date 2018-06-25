package GloryServer;


public class  GameSpace {

    private int gameRoomid;
    private Player[] player;
    private int highestscore;
    private int allScorehasCome;
    private int roundnum;
   private int asknextround;

    public GameSpace(int gameRoomid) {
        this.gameRoomid = gameRoomid;
        asknextround=0;
        allScorehasCome=0;
    }

    public int getAsknextround() {
        return asknextround;
    }

    public void setAsknextround(int asknextround) {
        this.asknextround = asknextround;
    }

    public int getRoundnum() {
        return roundnum;
    }

    public void setRoundnum(int roundnum) {
        this.roundnum=roundnum;
    }

    public void incRoundnum(){
        this.roundnum++;
    }

    public Player[] getPlayer() {
        return player;
    }

    public void setPlayer(Player[] player) {
        this.player = player;
    }

    public int getHighestscore() {
        return highestscore;
    }

    public void setHighestscore(int highestscore) {
        this.highestscore = highestscore;
    }

    public int getAllScorehasCome() {
        return allScorehasCome;
    }

    public void setAllScorehasCome(int allScorehasCome) {
        this.allScorehasCome = allScorehasCome;
    }

    public int getGameRoomid() {
        return gameRoomid;
    }
}
