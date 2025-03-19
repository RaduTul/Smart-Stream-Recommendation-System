public class Streamer extends Music {
    private int streamerType;
    private int ID;
    private String name;

    public Streamer(int streamerType, int ID, String name) {
        this.streamerType = streamerType;
        this.ID = ID;
        this.name = name;
    }

    public int getStreamerType() {
        return this.streamerType;
    }

    public void setStreamerType(int streamerType) {
        this.streamerType = streamerType;
    }

    public int getID() {
        return this.ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
