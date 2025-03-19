import java.util.List;

public class User extends Music {
    private int ID;
    private String name;
    private List<Integer> streams;

    public User() {
    }

    public User(int ID, String name, List<Integer> streams) {
        this.ID = ID;
        this.name = name;
        this.streams = streams;
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

    public List<Integer> getStreams() {
        return this.streams;
    }

    public void setStreams(List<Integer> streams) {
        this.streams = streams;
    }

}
