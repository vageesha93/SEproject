package GloryClient;


import java.io.IOException;


public class Packet01Disconnect extends Packet {

    private String username;

    public Packet01Disconnect(String username) {
        super(01);
        this.username=username;
    }

    @Override
    public void writeData(GameClient client) throws IOException {
     client.sendData(getData());
    }

    @Override
    public String getData() {
        return ("01" + this.username);
    }

    public String getUsername() {
        return username;
    }
}
