package GloryClient;


import java.io.IOException;


public class Packet00Login extends Packet {

    private String username;

    public Packet00Login(String data) {
        super(00);
        username=data.substring(2,data.length());
    }

    public Packet00Login(int packetId, String username) {
        super(packetId);
        this.username = username;
    }

    public String getUsername() {
        return username;

    }


    @Override
    public void writeData(GameClient client) throws IOException {
        client.sendData(getData());
    }

    public String getData() {
        return ("00" + this.username);
    }


}
