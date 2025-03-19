import java.util.ArrayList;


public class Singleton {
    private static Singleton instance = null;
    private final ArrayList<Streamer> streamerList = new ArrayList<>();
    private final ArrayList<Streams> streamList = new ArrayList<>();
    private final ArrayList<User> userList = new ArrayList<>();

    private Singleton() {
    }

    public static Singleton getInstance() {
        if (instance == null) {
            instance = new Singleton();
        }

        return instance;
    }

    public ArrayList<Streamer> getStreamerList() {
        return this.streamerList;
    }

    public ArrayList<Streams> getStreamList() {
        return this.streamList;
    }

    public ArrayList<User> getUserList() {
        return this.userList;
    }

    public void addStreamer(Streamer item) {
        this.streamerList.add(item);
    }

    public void addStream(Streams item) {
        this.streamList.add(item);
    }

    public void addUser(User item) {
        this.userList.add(item);
    }


}
