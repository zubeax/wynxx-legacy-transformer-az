import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;

import com.ibm.ctg.client.ECIRequest;
import com.ibm.ctg.client.JavaGateway;

/**
 * Simple CTG Java client: reads a payload from a text file, sends to CICS program via ECI,
 * prints 80-char reply.
 */
public class CtgOrderClient {
    public static void main(String[] args) throws Exception {
        if (args.length < 5) {
            System.err.println("Usage: java -cp ctgclient.jar;. CtgOrderClient <ctgHost> <ctgPort> <serverName> <programName> <payloadFile> [userid] [password]");
            System.exit(1);
        }
        String host = args[0];
        int port = Integer.parseInt(args[1]);
        String serverName = args[2];
        String program = args[3];
        String payloadFile = args[4];
        String userid = args.length > 5 ? args[5] : null;
        String password = args.length > 6 ? args[6] : null;

        String payload = new String(Files.readAllBytes(Paths.get(payloadFile))).trim();
        byte[] commarea = new byte[4096];
        byte[] payBytes = payload.getBytes("Cp1047"); // EBCDIC default; adjust to your codepage if needed
        System.arraycopy(payBytes, 0, commarea, 0, Math.min(payBytes.length, commarea.length));

        JavaGateway gw = new JavaGateway(host, port);
        try {
            ECIRequest req = new ECIRequest(serverName, program, commarea, commarea.length, ECIRequest.ECI_SYNC, userid, password);
            int rc = gw.flow(req);
            if (rc != ECIRequest.ECI_NO_ERROR) {
                System.err.println("ECI error: rc=" + rc + " cicsRc=" + req.Cics_Rc + " abend=" + req.Abend_Code);
                System.exit(8);
            }
            // reply is first 80 bytes of commarea
            byte[] reply = Arrays.copyOf(req.Commarea, 80);
            String replyStr = new String(reply, "Cp1047").trim();
            System.out.println(replyStr);
        } finally {
            gw.close();
        }
    }
}
