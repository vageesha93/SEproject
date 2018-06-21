package GloryClient;

import java.io.IOException;


public abstract class Packet {


    public static enum PacketTypes {
        //packets for all functions
        INVALID(-1), LOGIN(00), DISCONNECT(01),REQUEST(03),START(02),SENDINITIAL(04),GETSCORE(05),READYLEVEL(06),WEAKEST(07),GETFINALSCORE(10);

        private int packetId;

        private PacketTypes(int packetId) {
            this.packetId = packetId;
        }

        public int getId() {
            return packetId;
        }
    }
    public byte packetId;

    public Packet(int packetId) {
        this.packetId = (byte) packetId;
    }


    public abstract void writeData(GameClient client) throws IOException;

    public String readData(byte[] data) {
        String message = new String(data).trim();
        return message.substring(2);
    }

    public static PacketTypes lookupPacket(String packetId) {
        try {
            return lookupPacket(Integer.parseInt(packetId));
        } catch (NumberFormatException e) {
            return PacketTypes.INVALID;
        }
    }

    public static PacketTypes lookupPacket(int id) {
        for (PacketTypes p : PacketTypes.values()) {
            if (p.getId() == id) {
                return p;
            }
        }
        return PacketTypes.INVALID;
    }

    public abstract String getData();

}
