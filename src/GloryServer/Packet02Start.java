package GloryServer;

import java.io.IOException;


public class Packet02Start extends Packet {

    private String roomid;

    public Packet02Start(String username) {
        super(02);
        this.roomid=username;
    }

    @Override
    public void writeData(GameServer server) throws IOException {
        server.sendDataToAllClients(getData());
    }

    @Override
    public String getData() {
        return ("02" + this.roomid);
    }

    public String getUsername() {
        return roomid;
    }
}
