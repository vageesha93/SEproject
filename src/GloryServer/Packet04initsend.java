package GloryServer;



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
    public void writeData(GameServer server) throws IOException {
        server.sendDataToAllClients(getData());
    }

    @Override
    public String getData() {
        return ("04"+playrname+":"+init+":"+score);
    }
}
