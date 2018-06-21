package GloryClient;




import java.io.IOException;


public class Packet02Start extends Packet  {

    private String roomid;

    public Packet02Start(String username) {
        super(02);
        this.roomid =username;
    }


    @Override
    public void writeData(GameClient client) throws IOException {
        client.sendData(getData());
    }

    @Override
    public String getData() {
        return ("02" + this.roomid);
    }

    public String getRoomid() {
        return roomid;
    }
}
