import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;

public class UDPTimeClient {

    public static void main(String[] args) {

        try {
            DatagramSocket socketUDP = new DatagramSocket();
            byte[] message = "time".getBytes();
            InetAddress serverHost = InetAddress.getByName("localhost");
            int serverPort = 6789;

            // Construimos un datagrama para enviar el mensaje al servidor
            DatagramPacket sendDatagram = new DatagramPacket(message, message.length, serverHost, serverPort);

            socketUDP.send(sendDatagram);

            // Construimos el DatagramPacket que contendrá la respuesta
            byte[] buffer = new byte[1000];
            DatagramPacket answerDatagram = new DatagramPacket(buffer, buffer.length);
            socketUDP.receive(answerDatagram);

            String received = new String(answerDatagram.getData()).substring(0, answerDatagram.getLength());
            System.out.print("Hora: ");
            System.out.println(received);

            socketUDP.close();

        } catch (SocketException e) {
            System.out.println("Socket: " + e.getMessage());
        } catch (IOException e) {
            System.out.println("IO: " + e.getMessage());
        }
    }
}