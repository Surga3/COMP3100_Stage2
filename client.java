import java.io.*;
import java.net.*;
import java.util.*;

public class client {

    // Variable names for easier calls
    private static final String ADDRESS = "127.0.0.1";
    private static final int PORT = 50000;
    private static final String REDY = "REDY\n";
    private static final String HELO = "HELO\n";
    private static final String OK = "OK\n";
    private static final String NONE = "NONE\n";
    private static final String AUTH = "AUTH carlos\n";
    private static final String WHITE_SPACE = " ";
    private static final String QUIT = "QUIT";
    private static DataInputStream din;
    private static DataOutputStream dout;

    // Used for the algorithm in handleGetCapable
    public static Server shortestStartTime;
    public static int currentStartTime = -1;

    // Handshaking steps establish connection with the server
    private static void handshake(final DataInputStream din, final DataOutputStream dout) {
        try {
            // first step
            dout.write(HELO.getBytes());
            String reply = din.readLine();
            System.out.println("Server says: " + reply);

            // second step
            dout.write(AUTH.getBytes());
            reply = din.readLine();
            System.out.println("Server says: " + reply);
        } catch (final IOException e) {
            System.out.println(e);
        }
    }

    // Message sender function that sends important instructions to the server
    public static void msgSender(final String message, final DataOutputStream dout) {
        try {
            dout.write(message.getBytes());
            dout.flush();
        } catch (final IOException e) {
            System.out.println(e);
        }
    }

    // Message reciever function to recieve data that the server has given
    public static String msgReciever(final DataInputStream in) throws IOException {
        final String reply = in.readLine();
        System.out.println("Received");
        return reply;
    }

    // Listing of JOBN to determine the current jobs needed to be scheduled
    private static List<String> parseJOBNMessage(final String serverReply) {
        final List<String> info = new ArrayList<>();
        final String[] splitInfo = serverReply.split(WHITE_SPACE);
        info.add(splitInfo[2]);
        info.add(splitInfo[4]);
        info.add(splitInfo[5]);
        info.add(splitInfo[6]);
        return info;
    }

    // This function will start the job scheduling after it finds a cable server
    private static String createSCHDString(final String jobId, final String serverID, final String serverType) {
        return "SCHD" + WHITE_SPACE + jobId + WHITE_SPACE + serverType + WHITE_SPACE + serverID;
    }

    // This function will ask which servers are capable to run the job and the goal
    // is to find the one with the shortest start time to
    // to minimise turnaroun time
    private static String[] handleGetCapable(final String coreCount, final String memory, final String disk,
            final DataInputStream din, final DataOutputStream dout) throws IOException {

        String[] ans = new String[2];

        final String getCapable = "GETS Capable" + coreCount + memory + disk;

        // Will reply with data __ __
        dout.write(getCapable.getBytes());
        String reply = din.readLine();

        // Show the all capable servers
        dout.write(OK.getBytes());
        reply = din.readLine();

        // Algorithm
        String[] array = reply.split(reply, '\n');

        List<Server> tempServers = new ArrayList<>();

        // Assignment of data gathered from server reply to custom server list.
        for (String server : array) {
            String[] indiServer = server.split(" ");
            Server tempIndividualServer = new Server();
            tempIndividualServer.setServerType(indiServer[0]);
            tempIndividualServer.setServerServerID(Integer.parseInt(indiServer[1]));
            tempIndividualServer.setServerState(indiServer[2]);
            tempIndividualServer.setServerCurStartTime(Integer.parseInt(indiServer[3]));
            tempIndividualServer.setServerCoreCount(Integer.parseInt(indiServer[4]));
            tempIndividualServer.setServerMemory(Integer.parseInt(indiServer[5]));
            tempIndividualServer.setServerDisk(Integer.parseInt(indiServer[6]));
            tempServers.add(tempIndividualServer);
        }

        // If the servers start time is shorter than the current start time, then assign
        // it to that server. So the job
        // Can be dont quicker
        for (Server serverToInspect : tempServers) {
            if (currentStartTime > serverToInspect.getServerCurStartTime()) {
                shortestStartTime = serverToInspect; // ShortestStartTime refers to the server
                currentStartTime = serverToInspect.getServerCurStartTime();
            }
        }

        ans[1] = Integer.toString(shortestStartTime.serverID);
        ans[0] = shortestStartTime.type;

        return ans;

    }

    /*
     * 
     * MAIN RUNNING OF THE CODE
     * 
     */
    public static void main(final String[] args) {

        try {
            // initialise socket
            final Socket s = new Socket(ADDRESS, PORT);
            din = new DataInputStream(s.getInputStream());
            dout = new DataOutputStream(s.getOutputStream());

            // Handshake
            handshake(din, dout);

            // Controls of incoming messages
            dout.write(REDY.getBytes());
            final String reply = din.readLine();
            System.out.println("Server says: " + reply);

            // Begin the schduling of jobs
            while (!reply.equals(NONE)) {
                // parse the incoming message from ds-server
                final List<String> parsedInfo = parseJOBNMessage(reply);
                // parseInfo 0> jobId
                // 1>> core
                // 2>> mem
                // 3>> disk

                // Gets the return values of capable servers and assign begin to schedule it to
                // the most cable server
                String[] systemDetails = handleGetCapable(parsedInfo.get(1), parsedInfo.get(2), parsedInfo.get(3), din,
                        dout);
                if (parsedInfo.get(0) == "JOBN") {
                    msgSender(createSCHDString(parsedInfo.get(0), systemDetails[1], systemDetails[0]), dout);
                } else if (parsedInfo.get(0) == "JCPL") {
                    msgSender(REDY, dout);
                } else if (parsedInfo.get(0) == "OK") {
                    msgSender(REDY, dout);
                }

            }

            // Termination of connections
            msgSender(QUIT, dout);
            dout.close();
            s.close();

        } catch (final IOException e) {
            System.out.println(e);

        }

    }

}