public class Streams extends Music {
    private int streamType;
    private int ID;
    private int streamGenre;
    private long noOfStreams;
    private int streamerID;
    private long length;
    private long dateAdded;
    private String name;

    public Streams(int streamType, int ID, int streamGenre, long noOfStreams, int streamerID, long length, long dateAdded, String name) {
        this.streamType = streamType;
        this.ID = ID;
        this.streamGenre = streamGenre;
        this.noOfStreams = noOfStreams;
        this.streamerID = streamerID;
        this.length = length;
        this.dateAdded = dateAdded;
        this.name = name;
    }

    public int getStreamType() {
        return this.streamType;
    }

    public void setStreamType(int streamType) {
        this.streamType = streamType;
    }

    public int getID() {
        return this.ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public int getStreamGenre() {
        return this.streamGenre;
    }

    public void setStreamGenre(int streamGenre) {
        this.streamGenre = streamGenre;
    }

    public long getNoOfStreams() {
        return this.noOfStreams;
    }

    public void setNoOfStreams(long noOfStreams) {
        this.noOfStreams = noOfStreams;
    }

    public int getStreamerID() {
        return this.streamerID;
    }

    public void setStreamerID(int streamerID) {
        this.streamerID = streamerID;
    }

    public long getLength() {
        return this.length;
    }

    public void setLength(long length) {
        this.length = length;
    }

    public long getDateAdded() {
        return this.dateAdded;
    }

    public void setDateAdded(long dateAdded) {
        this.dateAdded = dateAdded;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
