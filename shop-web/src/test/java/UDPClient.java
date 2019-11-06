import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;

public class UDPClient {

    public static void main(String[] args) throws IOException {
        DatagramSocket datagramSocket=new DatagramSocket();
        String str = "客户端发送数据....";
        byte[] strByte = str.getBytes();
        DatagramPacket dp = new DatagramPacket(strByte, strByte.length, InetAddress.getByName("10.107.218.27"), 8080);
        datagramSocket.send(dp);
    }
}
