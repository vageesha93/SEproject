package GloryServer;

import java.io.IOException;


/*
disconnect the client from server
 */
public class Packet01Disconnect extends Packet {

    private String username;

    public Packet01Disconnect(String username) {
        super(01);
        this.username=username;
    }

    @Override
    public void writeData(GameServer server) throws IOException {
        server.sendDataToAllClients(getData());
    }

    @Override
    public String getData() {
        return ("01" + this.username);
    }




    public String getUsername() {
        return username;
    }
}
