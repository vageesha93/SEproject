package GloryClient;


import Glory_Schema.PenaltyTimerElement;
import Glory_Schema.Player;
import Interfaces.*;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.ArrayList;
import java.util.List;



public class GameClient  extends Thread{

  public static   List<Player> connectedPlayers=new ArrayList<>();//store all the connected players
  public static  List<String> allPlayersName=new ArrayList<>();
    public static List<GameroomPlayers> gmPlayersList=new ArrayList<>();

    public static boolean clientStop=false;

    public  WatingForLoaders watingForLoaders;
    public int jumpAP =0;

    private InetAddress ipAddress; //store the server ip address
    private DatagramSocket socket;
   private int port;

    public GameClient(String ipAddress,int port) throws IOException {
        this.port=port;
        this.ipAddress = InetAddress.getByName( ipAddress);
        this.socket = new DatagramSocket();
    }

    public void run(){
        clientStop=false;
        while (true) {
            byte[] data = new byte[1024];
            DatagramPacket packet = new DatagramPacket(data, data.length);
            try {
                socket.receive(packet);
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                this.parsePacket(packet.getData(), packet.getAddress(), packet.getPort());
            } catch (IOException e) {
                e.printStackTrace();
            }
            if (clientStop)break;
        }

    }

/*
get the details of data packet  foe each  nessary functions
 */
    private void parsePacket(byte[] data,InetAddress inetAddress,int port) throws IOException {
        String message = new String(data).trim();
        Packet.PacketTypes type = Packet.lookupPacket(message.substring(0, 2));
        Packet packet = null;
        switch (type) {
            default:
            case INVALID:
                break;
            case LOGIN:
                packet = new Packet00Login(message);
                handleLogin((Packet00Login) packet);
                break;
            case DISCONNECT:
                packet = new Packet01Disconnect(message.substring(2,message.length()));
                System.out.println(" " + ((Packet01Disconnect) packet).getUsername() + " has left the game...");
                removeConnection(((Packet01Disconnect) packet));
                break;
           case START:
                packet = new Packet02Start(message.substring(2,message.length()));
                Startcommand(message.substring(2,message.length()));
                break;
            case REQUEST:
               addallplayers(message);
                break;
            case SENDINITIAL:
                getallplayerinit(message);
                break;
            case WEAKEST:
               setweakest();
                break;
            case GETFINALSCORE:
               getFinalScores(message);
                break;
        }
    }

    private void setweakest(){
        System.out.println("I am the weaker");
        PenaltyTimerElement.IsGive=true;
        PenaltyTimerElement.CountDownTime=PenaltyTimerElement.CountDownTime-5;
        ScoreViewandSetVowels.lblweakest.setVisible(true);
    }


    private void Startcommand(String data){
        GloryMenu.gloryplayer.setRoomid(Integer.parseInt(data));
           WatingForLoaders.Watingtimer.cancel();
            watingForLoaders.dispose();

        new GamePlay();


    }

    private void addallplayers(String data){
        allPlayersName.add(data.substring(2,data.length()));
        System.out.println("HI:"+data);
    }



/*
handle all the connected palyer
 */
    private void handleLogin(Packet00Login packet) {
        System.out.println(packet.getUsername()
                + " has joined the game...");
        Player player = new Player(packet.getUsername());
        connectedPlayers.add(player);
    }

    /*
    send the data to server
     */

    public void sendData(String name) throws IOException {
        byte[] data=name.getBytes();
        DatagramPacket packet = new DatagramPacket(data, data.length, ipAddress, 1331);
        try {
            socket.send(packet);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
/*
remove all the disconnected players
 */
    public void removeConnection(Packet01Disconnect packet) throws IOException {
        removePlayer(packet.getUsername());
    }

    public void removePlayer(String username) {
        for (Player player : this.connectedPlayers) {
            if (player.getPlayerName().equals(username)) {
                this.connectedPlayers.remove(player);
                break;
            }

        }

    }

    private void getFinalScores(String msg){
        String tmp=msg.substring(2,msg.length());
        String[] data=tmp.split(":");
        String name=data[0].trim();
        int Score= Integer.parseInt(data[1].trim());
        setplayersFinalScoreToScreen(name,Score);
    }

    private void getallplayerinit(String data){
        boolean pass=false;
        String tmp=data.substring(2,data.length());
        String[] msg=tmp.split(":");
        System.out.println("username: "+msg[0]+"  initial 3 letters: "+msg[1]+"  score: "+msg[2]);
        GameroomPlayers gameroomPlayers=new GameroomPlayers(msg[0]);
        gameroomPlayers.setInit(msg[1]);
        gameroomPlayers.setScore(Integer.parseInt(msg[2]));
        for (GameroomPlayers gmplayer : this.gmPlayersList) {
           if (gmplayer.getName().equals(gameroomPlayers.getName())){
               gmplayer.setScore(gameroomPlayers.getScore());
               gmplayer.setInit(gameroomPlayers.getInit());
           }else {
             pass=true;
           }

        }
        if (!pass){
            gmPlayersList.add(gameroomPlayers);
        }
        if (!gameroomPlayers.getName().trim().equals(GloryMenu.gloryplayer.getPlayerName().trim())) {
            setplayersonScreen(gameroomPlayers);
        }
    }


    private void setplayersFinalScoreToScreen(String name, int score){
        jumpAP++;
        switch (jumpAP){
            case 1:{
                FinalScoreBoard.lblpname1.setText(name);FinalScoreBoard.lblpscore1.setText(String.valueOf(score));
            };break;
            case 2:{
                FinalScoreBoard.lblpname2.setText(name);FinalScoreBoard.lblpscore2.setText(String.valueOf(score));
            };break;
            case 3:{
                FinalScoreBoard.lblpname3.setText(name);FinalScoreBoard.lblpscore3.setText(String.valueOf(score));
            };break;
            case 4:{
                FinalScoreBoard.lblpname4.setText(name);FinalScoreBoard.lblpscore4.setText(String.valueOf(score));
            };break;
            case 5:{
                FinalScoreBoard.lblpname5.setText(name);FinalScoreBoard.lblpscore5.setText(String.valueOf(score));
                jumpAP=0;
            };break;
        }
    }

    private void setplayersonScreen(GameroomPlayers gameroomPlayers){
        jumpAP++;
        switch (jumpAP){
            case 1:{GamePlay.player2.setText(gameroomPlayers.getName());
            GamePlay.lblp2score.setText(String.valueOf(gameroomPlayers.getScore()));
                GamePlay.lblp2init.setText(gameroomPlayers.getInit());
            };break;
            case 2:{
                GamePlay.player3.setText(gameroomPlayers.getName());
                GamePlay.lblp3score.setText(String.valueOf(gameroomPlayers.getScore()));
                GamePlay.lblp3init.setText(gameroomPlayers.getInit());
            };break;
            case 3:{
                GamePlay.player4.setText(gameroomPlayers.getName());
                GamePlay.lblp4score.setText(String.valueOf(gameroomPlayers.getScore()));
                GamePlay.lblp4init.setText(gameroomPlayers.getInit());
            };break;
            case 4:{
                GamePlay.player5.setText(gameroomPlayers.getName());
                GamePlay.lblp5score.setText(String.valueOf(gameroomPlayers.getScore()));
                GamePlay.lblp5init.setText(gameroomPlayers.getInit());
                jumpAP =0;
            };break;
        }
    }

}
