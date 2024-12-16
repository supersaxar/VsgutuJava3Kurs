package DorzhievZhargalB7621.A;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.io.*;
import java.net.*;
import java.util.*;

public class ClientHandlerTest {

    @Test
    void testSendMessage() throws IOException {
        Socket mockSocket = new Socket() {
            @Override
            public InputStream getInputStream() throws IOException {
                return new ByteArrayInputStream("test message".getBytes());
            }

            @Override
            public OutputStream getOutputStream() throws IOException {
                return new ByteArrayOutputStream();
            }
        };
        List<ClientHandler> clients = new ArrayList<>();
        ClientHandler handler = new ClientHandler(mockSocket, clients);
        assertDoesNotThrow(() -> handler.sendMessage("Test message"));
    }

    @Test
    void testClientConnect() {
        assertDoesNotThrow(() -> new Socket("localhost", 12345));
    }
}