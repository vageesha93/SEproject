package GloryServer;

import java.io.IOException;



/*
create the connection with client
 */
public class Packet00Login extends Packet {

    private String username;

    public Packet00Login(String data) {
        super(00);
        username=data.substring(2,data.length());
    }

    public String getUsername() {
        return username;

    }


    @Override
    public void writeData(GameServer server) throws IOException {
        server.sendDataToAllClients(getData());
    }

    public String getData() {
        return ("00" + this.username);
    }


}
