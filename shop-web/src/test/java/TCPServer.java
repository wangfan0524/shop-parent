import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class TCPServer {

    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket=new ServerSocket(8888);

        Socket accept = serverSocket.accept();

        InputStream inputStream = accept.getInputStream();

        byte [] bytes=new byte[64];

        int len=inputStream.read(bytes);

        String str=new String(bytes,0,len);

        System.out.println(str);

        accept.close();
        serverSocket.close();
    }
}
