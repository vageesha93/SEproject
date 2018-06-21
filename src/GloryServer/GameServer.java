package GloryServer;



import java.io.*;
import java.net.*;
import java.util.ArrayList;
import java.util.List;



public class GameServer  extends Thread {

//   static GameServer gameServer;
//  staticGameRoomThread gameRoomThread;


    public static DatagramSocket socket;

    static boolean stopme = false; //stop the server client disconnected
    public static List<Player> connectedPlayers = new ArrayList<>(); //store all the conneced players

    Packet packet;

    public GameServer() {
        try {
            this.socket = new DatagramSocket(1331);
        } catch (SocketException e) {
            e.printStackTrace();
        }
    }

    public void run() {
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
        }

    }

    /*
    get the details of data packet give necessary function
     */
    private void parsePacket(byte[] data, InetAddress address, int port) throws IOException {
        String message = new String(data).trim();
        Packet.PacketTypes type = Packet.lookupPacket(message.substring(0, 2));
        Packet packet = null;
        switch (type) {
            default:
            case INVALID:
                break;
            case LOGIN:
                packet = new Packet00Login(message);
                System.out.println("Address:[" + address + ":" + port + "] "
                        + ((Packet00Login) packet).getUsername() + " has connected...");
                Player player = new Player(((Packet00Login) packet).getUsername(),address,port);
                this.addConnection(player, (Packet00Login) packet);
                break;
            case DISCONNECT:
                packet = new Packet01Disconnect(message.substring(2,message.length()));
                System.out.println(" " + ((Packet01Disconnect) packet).getUsername() + " has left...");
                this.removeConnection((Packet01Disconnect) packet);
                break;
            case START:
                packet = new Packet02Start(message);
                this.readytostart(((Packet02Start) packet).getUsername());
                packet.writeData(this);
                break;
            case REQUEST:
                sendallplayername(address,port);
                break;
            case SENDINITIAL:
               recieveinit(message);
                break;
            case GETSCORE:
                SetScore(message);
                break;
            case READYLEVEL:
               setnextRound(message);
                break;

        }
    }


    private void setnextRound(String data) throws IOException {
        int roundnumid= Integer.parseInt(data.substring(2,data.length()).trim());

        for (GameSpace gr: GameRoomThread.gameroomPlayers){
            if (gr.getGameRoomid()==roundnumid){
                System.out.println("OWWW GOOO");
                gr.setAsknextround((gr.getAsknextround()+1));

                if (gr.getAsknextround()==gr.getPlayer().length){
                    packet=new Packet02Start(String.valueOf(gr.getGameRoomid()));
                    sendDataToAllgameroomClients(gr,packet.getData());
                    gr.setAsknextround(0);
                }

                }
            }
    }

    public void sendDataToAllgameroomClients(GameSpace gameSpace, String data) throws IOException {
        for (Player p : gameSpace.getPlayer()) {
            sendData(data, p.getIpAddress(),p.getPort());
            System.out.println("Connected Players:"+p.getPlayerName());
        }
    }

    private void SetScore(String data){
        String tmp=data.substring(2,data.length());
        String[] sco=tmp.split(":");
        String name=sco[0].trim();
        int roomid=Integer.parseInt(sco[1].trim());
        int roundnum=Integer.parseInt(sco[2].trim());
        int score=Integer.parseInt(sco[3].trim());
        for (GameSpace gr: GameRoomThread.gameroomPlayers){
            if (gr.getGameRoomid()==roomid){

                for (Player player:gr.getPlayer()){

                    if (player.getPlayerName().equals(name))
                    {
                     player.setScore(score);
                        player.setRoundnum(roundnum);

                        if (gr.getRoundnum()<roundnum)
                            gr.setRoundnum(roundnum);
                        System.out.println("Score has been come ");
                        break;
                    }

                }
                break;
            }
        }
     for (GameSpace gr:GameRoomThread.gameroomPlayers){
         if (gr.getGameRoomid()==roomid){
             gr.setAllScorehasCome((gr.getAllScorehasCome()+1));

             if (gr.getAllScorehasCome()==gr.getPlayer().length){
                 if (gr.getRoundnum()==5) {
                     calfinalscore(gr);
                     gr.setAllScorehasCome(0);
                     break;
                 }else {

                     calhightAndWeak(roomid);
                     gr.setAllScorehasCome(0);
                     break;
                 }
             }
         }
     }

    }


    private void  calfinalscore(GameSpace gr){
        Player[] players=gr.getPlayer();
        for (int i=0;i<players.length;i++)
        for (int x=1;x<players.length-i;x++){
            if (players[x-1].getScore()>players[x].getScore()){
                Player tmp;
                tmp=players[x-1];
                players[x-1]=players[x];
                players[x]=tmp;
            }
        }
        for (Player player:gr.getPlayer()) {
            for (int z = players.length-1; z >=0; z--) {
               packet=new Packet010finalscore(players[z].getPlayerName(),players[z].getScore());
                try {
                    sendData(packet.getData(),player.getIpAddress(),player.getPort());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        GameRoomThread.gameroomPlayers.remove(gr);
    }

    private void calhightAndWeak(int roomid){
        int minscore=Integer.MAX_VALUE;
        for (GameSpace gr:GameRoomThread.gameroomPlayers){
            if (gr.getGameRoomid()==roomid){
                for (Player player:gr.getPlayer()){
                    if (player.getScore()<minscore){
                        minscore=player.getScore();
                    }
                }
                for (Player player:gr.getPlayer()){
                    if (player.getScore()==minscore){
                        try {
                            sendData("07",player.getIpAddress(),player.getPort());
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }
                break;
            }
        }
    }

    private void recieveinit(String data) throws IOException {
        String tmp=data.substring(2,data.length());
        String[] init=tmp.split(":");
        Packet packet=new Packet04initsend(init[0],Integer.parseInt(init[1]),init[2],Integer.parseInt(init[3]));
        int id=Integer.parseInt(init[1].trim());
        String name=init[0].trim();
        String initvalue=init[2].trim();
        int score=Integer.parseInt(init[3].trim());
        for (GameSpace gr: GameRoomThread.gameroomPlayers){
            if (gr.getGameRoomid()==id){

                for (Player player:gr.getPlayer()){

                   if (player.getPlayerName().equals(name))
                   {
                       player.setInitial_two(initvalue);
                       player.setScore(score);
                   }

                }
            }
        }

        for (GameSpace gr:GameRoomThread.gameroomPlayers){
            if (gr.getGameRoomid()==id){

                for (Player player:gr.getPlayer()){

                  sendData(packet.getData(),player.getIpAddress(),player.getPort());

                }
            }
        }

    }


    private void sendallplayername(InetAddress inetAddress,int port) throws IOException {
        for (Player p:connectedPlayers){
            sendData("03"+p.getPlayerName(),inetAddress,port);
        }
    }

    /*
    connect the client to server and added player class
     */
    public void addConnection(Player player, Packet00Login packet) throws IOException {
        boolean alreadyConnected = false;
        for (Player p : this.connectedPlayers) {
            if (player.getPlayerName().equalsIgnoreCase(p.getPlayerName())) {
                if (p.getIpAddress() == null) {
                    p.setIpAddress(player.getIpAddress());
                }
                if (p.getPort() == -1) {
                    p.setPort(player.getPort());
                }
                alreadyConnected = true;
            }
        }
        if (!alreadyConnected) {
            this.connectedPlayers.add(player);
            sendDataToAllClients(packet.getData());
        }
    }
/*
send the data to client
 */
   public void sendData(String msg,InetAddress ipAddress, int port) throws IOException {
       byte[] data=msg.getBytes();
       DatagramPacket packet = new DatagramPacket(data, data.length, ipAddress, port);
       try {
           this.socket.send(packet);
       } catch (IOException e) {
           e.printStackTrace();
       }
        }

/*
send  the data to all the connected client;
 */
    public void sendDataToAllClients(String data) throws IOException {
        for (Player p : connectedPlayers) {
            sendData(data, p.getIpAddress(),p.getPort());
            System.out.println("Connected Players:"+p.getPlayerName());
        }
    }



    public static void main(String args[]) throws IOException, ClassNotFoundException {
        GameServer   gameServer=new GameServer();
        gameServer.start();
        GameRoomThread gameRoomThread=new GameRoomThread();
        gameRoomThread.StartTimer();
    }

    /*
    disconnted client remove from player list
     */
    public void removeConnection(Packet01Disconnect packet) throws IOException {
        removePlayer(packet.getUsername());
       packet.writeData(this);
    }

    public void removePlayer(String username) {
        for (Player player : this.connectedPlayers) {
            if (player.getPlayerName().equals(username.trim())) {
                this.connectedPlayers.remove(player);
                break;
            }

        }

    }

    /*
    record all the player ready to play
     */
    public void readytostart(String username){
        for (Player player : this.connectedPlayers) {
            if (player.getPlayerName().equals(username)) {

                break;
            }

        }

    }




}
