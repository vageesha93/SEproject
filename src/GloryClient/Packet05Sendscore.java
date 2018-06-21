package GloryClient;

import java.io.IOException;


public class Packet05Sendscore extends Packet {

    private int roomid;
    private String name;
    private int roundnum;
    private int score;

    public Packet05Sendscore(int roomid, String name, int roundnum, int score) {
        super(05);
        this.roomid = roomid;
        this.name = name;
        this.roundnum = roundnum;
        this.score = score;
    }

    @Override
    public void writeData(GameClient client) throws IOException {
        client.sendData(getData());
    }

    @Override
    public String getData() {
        return ("05"+name+":"+roomid+":"+roundnum+":"+score);
    }
}
