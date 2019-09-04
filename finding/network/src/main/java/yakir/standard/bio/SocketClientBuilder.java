package yakir.standard.bio;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import yakir.utils.Inet;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.Objects;

/**
 * SocketClientBuilder
 *
 * @author yakir <a href="yakirchen.github.io">yakirchen.github.io</a> on 04/04/2019 18:42.
 */
public class SocketClientBuilder {

    private static final Logger log = LogManager.getLogger(SocketClientBuilder.class);

    private Socket socket;
    private String response;

    public SocketClientBuilder build(int serverPort) {
        try {
            socket = new Socket();
            socket.setKeepAlive(true); //
            socket.setOOBInline(true); // 发送心跳包
            socket.setTcpNoDelay(true);
            socket.setSoTimeout(Inet.TIMEOUT);
            socket.connect(new InetSocketAddress(Inet.LOCAL_V4, serverPort));
        } catch (IOException e) {
            log.error("Socket Client Build Exp", e);
        }
        return this;
    }

    public SocketClientBuilder msg(String msg) {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(
                new BufferedInputStream(socket.getInputStream())));
             PrintWriter writer = new PrintWriter(new BufferedWriter(new OutputStreamWriter(
                     new BufferedOutputStream(socket.getOutputStream()))))) {
            writer.write(msg.concat("\n"));
            writer.flush();
            String reponse;
            while (true) {
                reponse = reader.readLine();
                if (Objects.nonNull(reponse) && !reponse.isEmpty()) {
                    log.info("Server Reponse: [{}]", reponse);
                    if (reponse.equalsIgnoreCase("ok")) {
                        break;
                    } else {
                        response = String.copyValueOf(reponse.toCharArray());
                        writer.println("exit\n");
                        writer.flush();
                    }
                }
            }
        } catch (IOException e) {
            log.error("Socket Client Send Msg Exp", e);
        } finally {
            if (Objects.nonNull(socket) && !socket.isClosed()) {
                try {
                    socket.close();
                } catch (IOException e) {
                    log.error("close socket Exp", e);
                }
                socket = null;
            }
        }

        return this;
    }

    public String response() {
        log.info("Socket Response {}", response);
        return response;
    }
}
