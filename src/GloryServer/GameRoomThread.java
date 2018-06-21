package GloryServer;


import java.io.IOException;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

public class GameRoomThread  {

        public static java.util.List<GameSpace> gameroomPlayers =new ArrayList<>();
        static Timer timer;
        private int count=0;
      Packet packet,packet2;

    public void StartTimer(){
        int delay = 60000;
        int period = 60000;
        timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {

            public void run() {
                try {
                    getpalyers();
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
        }, delay, period);

    }

    private void getpalyers() throws IOException {
        for (Player p:GameServer.connectedPlayers) {
            if (p.getRoomid()==0){
                count++;
            }
        }

        if (count>=2){
          GameSpace gameSpace =new GameSpace((gameroomPlayers.size()+1));
            Player[] players=new Player[count]; int temp=0;
            for (Player p:GameServer.connectedPlayers) {
                if (p.getRoomid()==0){
                    p.setRoomid(gameSpace.getGameRoomid());
                    players[temp]=p;
                    temp++;
                }
            }
            gameSpace.setPlayer(players);
            gameroomPlayers.add(gameSpace);
            packet=new Packet02Start(String.valueOf(gameSpace.getGameRoomid()));

            sendDataToAllgameroomClients(gameSpace,packet.getData());
        }
        count=0;

    }



    public void sendDataToAllgameroomClients(GameSpace gameSpace, String data) throws IOException {
        for (Player p : gameSpace.getPlayer()) {
            sendData(data, p.getIpAddress(),p.getPort());
            System.out.println("Connected Players:"+p.getPlayerName());
        }
    }
    public void sendDataToAllgameroomplayer(int gameRoomid,String data) throws IOException {

        for (GameSpace gr:gameroomPlayers){
            if (gr.getGameRoomid()==gameRoomid){
                for (Player player:gr.getPlayer()){
                    sendData(data,player.getIpAddress(),player.getPort());
                    System.out.println("Send Players:"+player.getPlayerName());

                }
            }
        }

    }
    public void sendData(String msg, InetAddress inetAddress,int port) throws IOException {
        byte[] data=msg.getBytes();
        DatagramPacket packet = new DatagramPacket(data, data.length, inetAddress, port);
        try {
            GameServer.socket.send(packet);
        } catch (IOException e) {
            e.printStackTrace();
        };
    }


}
