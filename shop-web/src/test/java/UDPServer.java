import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;

public class UDPServer {

    public static void main(String[] args) throws IOException {
        //创建socket,指定监听8080端口
        DatagramSocket datagramSocket=new DatagramSocket(8080);
        //创建一个缓存区
        byte [] buf=new byte[1024];
        //创建数据包
        DatagramPacket datagramPacket=new DatagramPacket(buf,buf.length);
        //通过socket接收消息，将消息写入到数据包，这里会阻塞，知道接收到消息
        datagramSocket.receive(datagramPacket);

        String str =new String(datagramPacket.getData());
        System.out.println(str);
    }
}
