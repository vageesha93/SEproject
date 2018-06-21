package GloryClient;

import java.io.IOException;


public class Packet04initsend extends Packet {

      private String playrname;
        private int roomid;
        private String init;
       private int score;

    public Packet04initsend( String playrname, int roomid, String init, int score) {
        super(04);
        this.playrname = playrname;
        this.roomid = roomid;
        this.init = init;
        this.score = score;
    }

    @Override
    public void writeData(GameClient client) throws IOException {
        client.sendData(getData());
    }

    @Override
    public String getData() {
        return ("04"+playrname+":"+roomid+":"+init+":"+score);
    }
}
