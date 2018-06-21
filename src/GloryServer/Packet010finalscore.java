package GloryServer;

import java.io.IOException;


public class Packet010finalscore extends Packet {

    private String name;
    private int score;


    public Packet010finalscore( String name, int score) {
        super(10);
        this.name = name;
        this.score = score;
    }

    @Override
    public void writeData(GameServer server) throws IOException {
     server.sendDataToAllClients(getData());
    }

    @Override
    public String getData() {
        return ("10"+name+":"+score);
    }
}
