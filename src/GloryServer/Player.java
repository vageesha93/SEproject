
package GloryServer;


import java.net.InetAddress;


/*
Player class for store all  connected player
*/
public class Player   {
    
    private String playerName; //contains the name
    private int score; //numeric value of the variable
    private int roundnum; //number of round
    private String initial_two;
    private int roomid;
    private InetAddress ipAddress;
    private int port;


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

    public void setRoundnum(int roundnum) {
        this.roundnum = roundnum;
    }

    public InetAddress getIpAddress() {
        return ipAddress;
    }

    public void setIpAddress(InetAddress ipAddress) {
        this.ipAddress = ipAddress;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    /*
            The constructor for Player should take a string denoting the variable name
            */

    public Player(String playerName, InetAddress ipAddress, int port) {
        this.playerName = playerName;
        this.ipAddress = ipAddress;
        this.port = port;
        this.roomid=0;
    }



    /*
     public access method that returns this value as mentioned in assignment
    */
    public int getScore() {
        return score;
    }

    /*
     public set method for changing value as mentioned in assignment  
    */
    public void setScore(int score) {
        this.score = score;
    }

    public String getPlayerName() {
        return playerName;
    }
    
    
    
    
}
