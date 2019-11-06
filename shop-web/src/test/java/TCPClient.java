import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;
import java.util.Scanner;

public class TCPClient {

    public static void main(String[] args) throws IOException {
        Socket s = new Socket("10.107.218.27", 8888);

        OutputStream outputStream = s.getOutputStream();

        

        outputStream.write("我是客户端".getBytes());

        s.close();
    }
}
