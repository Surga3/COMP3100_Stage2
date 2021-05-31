public class Server {
    public String type;
    public int serverID;
    public String state;
    public int curStartTime;
    public int core;
    public int mem;
    public int diskSpace;

    public Server() {

    }

    // Setter methods
    public void setServerType(String type) {
        this.type = type;
    }

    public void setServerServerID(int id) {
        this.serverID = id;
    }

    public void setServerState(String state) {
        this.state = state;
    }

    public void setServerCurStartTime(int curStartTime) {
        this.curStartTime = curStartTime;
    }

    public void setServerCoreCount(int coreCount) {
        this.core = coreCount;
    }

    public void setServerMemory(int mem) {
        this.mem = mem;
    }

    public void setServerDisk(int diskSpace) {
        this.diskSpace = diskSpace;
    }

    // Getter methods
    public String getServerType() {
        return this.type;
    }

    public int getServerServerID() {
        return this.serverID;
    }

    public String getServerState() {
        return this.state;
    }

    public int getServerCurStartTime() {
        return this.curStartTime;
    }

    public int getServerCoreCount() {
        return this.core;
    }

    public int getServerMemory() {
        return this.mem;
    }

    public int getServerDisk() {
        return this.diskSpace;
    }
}