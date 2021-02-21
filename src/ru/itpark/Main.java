import java.io.IOException;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class Main {
    public static void main(String[] args) {
        try (
                ServerSocket serverSocket = new ServerSocket(9999); // ctrl + alt + v
        ) {
            while (true) {
                try (
                        Socket socket = serverSocket.accept(); // пришёл клиент
                ) {
                    OutputStream outputStream = socket.getOutputStream();
                    String message = "Hello world";

                    outputStream.write((
                            "HTTP/1.1 200 OK\r\n" +
                                    "Content-Type: text/plain\r\n" +
                                    "Content-Length: " + message.length() + "\r\n" +
                                    "Connection: close\r\n" +
                                    "\r\n" +
                                    message
                    ).getBytes());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}