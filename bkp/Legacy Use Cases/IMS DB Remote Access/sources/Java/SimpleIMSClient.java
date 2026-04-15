
// SimpleIMSClient.java
import com.ibm.ims.connect.*;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.charset.StandardCharsets;

public class SimpleIMSClient {
    public static void main(String[] args) throws Exception {
        String host = "your.ims.host.name";
        int    port = 9999;                  // matches TCPIP(PORTID=) or SSLPORT
        String datastoreName = "IMSPLEX1";   // matches DATASTORE(TMEMBER=)
        String tranCode = "ECHO";
        String userText = "Hello from Java";

        ConnectionFactory cf = new ConnectionFactory();
        cf.setHostName(host);
        cf.setPortNumber(port);
        cf.setClientId("JAVADEMO");

        try (Connection conn = cf.getConnection()) {
            TmInteraction ti = (TmInteraction) conn.createInteraction();
            ti.setImsDatastoreName(datastoreName);
            ti.setInteractionTypeDescription(ApiProperties.INTERACTION_TYPE_DESC_SENDRECV);

            byte[] trn = String.format("%-8s", tranCode).getBytes(StandardCharsets.US_ASCII);
            byte[] dat = userText.getBytes(StandardCharsets.US_ASCII);

            int segLen = 4 + trn.length + dat.length;
            ByteBuffer bb = ByteBuffer.allocate(segLen).order(ByteOrder.BIG_ENDIAN);
            bb.putShort((short) segLen);  // LL
            bb.putShort((short) 0);       // ZZ
            bb.put(trn);
            bb.put(dat);

            InputMessage in = new InputMessage(bb.array());
            OutputMessage out = (OutputMessage) ti.doInteraction(in);

            byte[] reply = out.getMessageBytes();
            if (reply.length >= 4) {
                int rlen = ((reply[0] & 0xff) << 8) | (reply[1] & 0xff);
                int dataLen = Math.min(rlen - 4, reply.length - 4);
                String text = new String(reply, 4, Math.max(0, dataLen), StandardCharsets.US_ASCII);
                System.out.println("IMS replied: " + text);
            } else {
                System.out.println("Empty or invalid reply");
            }
        }
    }
}
