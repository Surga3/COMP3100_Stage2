public class Server {
    private String type;
    private int serverID;
    private String state;
    private int curStartTime;
    private int core;
    private int mem;
    private int disk;

    public Server(String type, int id, String state, int curStartTime, int coreCount, int mem, int disk) {
        setServerType(type);
        setServerServerID(id);
        setServerState(state);
        setServerCurStartTime(curStartTime);
        setServerCoreCount(coreCount);
        setServerMemory(mem);
        setServerDisk(disk);
    }

    public void setServerType(String type) { // setter method for server type
        this.type = type;
    }

    public void setServerServerID(int id) { // setter method for serverID
        this.serverID = id;
    }

    public void setServerState(String state) { // setter method for server state
        this.state = state;
    }

    public void setServerCurStartTime(int curStartTime) { // setter method for current start time
        this.curStartTime = curStartTime;
    }

    public void setServerCoreCount(int coreCount) { // setter method for core
        this.core = coreCount;
    }

    public void setServerMemory(int mem) { // setter method for memory
        this.mem = mem;
    }

    public void setServerDisk(int disk) { // setter method for disk
        this.disk = disk;
    }

    public String getServerType() { // getter method for server type
        return this.type;
    }

    public int getServerServerID() { // getter method for serverID
        return this.serverID;
    }

    public String getServerState() { // getter method for server stae
        return this.state;
    }

    public int getServerCurStartTime() { // getter method for current start time
        return this.curStartTime;
    }

    public int getServerCoreCount() { // getter method for server core count
        return this.core;
    }

    public int getServerMemory() { // getter method for server memory
        return this.mem;
    }

    public int getServerDisk() { // getter method for server disk
        return this.disk;
    }

    public int compareTo(Server c) { // compare function used in the sorting of the servers
        if (this.core - c.core == 0) {
            return c.type.compareTo(this.type);
        }
        return this.core - c.core;
    }

}