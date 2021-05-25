import java.io.*;
import java.net.*;
import java.util.*;

/*
Client (C): Sends HELO to the server (S)
Server (S): Sends OK to the C
C: Sends AUTH BLANK to S
S: Prints Welcome and sends OK
/*
/*
read ds-system.xml
*/


/*
while(){
}
C: Sends REDY to Server
S: String reply = JOBN <infomation>
JOBN submitTime id estRuntime core mem disk
>> (1) Prase the reply message
String[] server info = reply.split(WHITE_SPACE);
*/


/*
javac *.java
./ds-server -n -c <CONFIG> in one terminal 
java Test
*/


/*
New line char >>>
./ds-server -c <CONFIG_FILE> -n
*/

public class Test {
    private static final String ADDRESS = "127.0.0.1";
    private static final int PORT = 50000;
    private static final String REDY = "REDY\n";
    private static final String HELO = "HELO\n";
    private static final String OK = "OK\n";
    private static final String NONE = "NONE\n";
    private static final String AUTH = "AUTH BLANK\n";
    private static final String DSSYSTEM_FILE_ADDRESs = "ds-system.xml";
    private static final String WHITE_SPACE = " ";
    private static final String GETS_CAPABLE = "GETS Capable";
    private static Socket socket;
    private static DataInputStream din;
    private static DataOutputStream dout;

    private static void handshake(DataInputStream din, DataOutputStream dout) {
        try {
            // first step
            dout.write(HELO.getBytes());
            String reply = din.readLine();
            System.out.println("Server says: " + reply);

            // second step
            dout.write(AUTH.getBytes());
            reply = din.readLine();
            System.out.println("Server says: " + reply);
        } catch (IOException e) {
            Logger.getLogger(Test.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private static List<Server> parseDSSystemXML(String fileAddress){
        List<Server> dsServerList = new ArrayList<>(); //create a Server class
        //do the parsing here
        //create job class, complete it with the reqired attributes like getter and setter functions
        //create Server class, complete it with the required attributes like getter and setter functions 
        //optional >> implement comparable for server class or job class
        //easily sort those classes based on any attributes you want based collections java class
        return dsServerList;
    }

    private static List<String> parseJOBNMessage(String serverReply){
        List<String> info = new ArrayList<>();
        String[] splitInfo = serverReply.split(WHITE_SPACE);
        info.add(splitInfo[2]);
        info.add(splitInfo[4]);
        info.add(splitInfo[5]);
        info.add(splitInfo[6]);
        return info;
    }

    private static String createSCHDString(String jobId, int serverId, String serverType){
        return "SCHD" +WHITE_SPACE + jobId + WHITE_SPACE + serverType + WHITE_SPACE + serverId;
    }

    private static String[] hangleGetCapable(int coreCount, int memory, int disk, DataInputStream din, DataOutputStream dout){
        /*
        *******************************************************************************************************************
        String GETS Capable core mem disk 
        send it to ds-server 
        parse the incoming message
        data #lines #length 
        List <server> temporaryServers;
        for(<lines){
            read message from server 
            parse and add it to the temporaryservers
        }
        look for the best capable server within temporaryservers
        String sId, sType;
        String[] ans = new String[2];
        ans[0]= sId;
        ans[1]= stype;
        return ans;
        *******************************************************************************************************************
        */

    }

    public static void main(String[] args) {

        try { 
            // initialise socket
            socket = new Socket(ADDRESS,PORT);
            din = new DataInputStream(socket.getInputStream());
            dout = new DataOutputStream(socket.getOutputStream());

            // Handshake
            handshake(din, dout);

            //ds-system.xml is available 
            List<Server> dsServers = parseDSSystemXML(ADDRESS);
            // to find out the server at the ds-sim server sde,
            //GETS Capable 

            // Controls of incoming messages
            dout.write(REDY.getBytes());
            String reply = din.readLine();
            System.out.println("Server says: "+reply);
            while(!reply.equals(NONE)){
                //parse the incoming message from ds-server
                List<String> parsedInfo = parseJOBNMessage(reply);
                //parseInfo 0> jobId
                //1>> core
                //2>> mem
                //3>> disk 
//****************************************************************************************************************

                //call handleGetscapable
                //create schd decision based on the return value
                //send redy to the ds-server 
 


//*****************************************************************************************************************


        //        Server server = dsServers.get(0); // first server of the list will need to get changed, will be from gets capable
        //        int serverId = 0;
        //        String jobId = parsedInfo.get(0);
        //        createSCHDString(jobId, 0, server.getServerType());
        //        dout.write(REDY.getBytes());
        //        reply = din.readLine();
        //        System.out.println("Server says: "+ reply)

                //schedulin decsion taking into acount jobID, serverD, serverType
                //sending redy to server

            }


        } catch (IOException e) {
            Logger.getLogger(Test.class.getName()).log(Level.SEVERE, null, e);

        }

    }

}