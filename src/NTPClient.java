import org.apache.commons.net.ntp.NTPUDPClient;
import org.apache.commons.net.ntp.TimeInfo;
import java.net.InetAddress;

public class NTPClient {

    public static void main(String[] args) throws Exception {
        // Replace "pool.ntp.org" with the desired NTP server address
        String serverAddress = "pool.ntp.org";

        // Create an NTP UDP client instance
        NTPUDPClient client = new NTPUDPClient();

        // **Convert the string address to an InetAddress object:**
        InetAddress inetAddress = InetAddress.getByName(serverAddress);

        // Connect to the server and retrieve time information
        TimeInfo timeInfo = client.getTime(serverAddress);

        // Check if the response is valid
        if (timeInfo.getMessage() != null) {
            long transmitTimestamp = timeInfo.getMessage().getTransmitTimeStamp().getTime();
            long receiveTimestamp = timeInfo.getMessage().getReceiveTimeStamp().getTime();
            long offset = transmitTimestamp - receiveTimestamp;

            System.out.println("Time offset from server: " + offset + " milliseconds");
        } else {
            System.out.println("Failed to retrieve time from server.");
        }
    }
}
