import java.util.ArrayList;
import java.util.List;

// Clasa Factory este responsabilă pentru crearea obiectelor de tip Music (Streamer, Streams, User)
public class Factory {
    public Factory() {
    }

    public Music Create(String tip, String[] date) {
        if (tip.equals("Stream")) {
            // Crearea unui obiect Streams și inițializarea atributelor folosind datele primite
            int streamType = Integer.parseInt(date[0].substring(1, date[0].length() - 1));
            int id = Integer.parseInt(date[1].substring(1, date[1].length() - 1));
            int streamGenre = Integer.parseInt(date[2].substring(1, date[2].length() - 1));
            long noOfStreams = Long.parseLong(date[3].substring(1, date[3].length() - 1));
            int streamerId = Integer.parseInt(date[4].substring(1, date[4].length() - 1));
            long length = Long.parseLong(date[5].substring(1, date[5].length() - 1));
            long dateAdded = Long.parseLong(date[6].substring(1, date[6].length() - 1));
            String name = date[7].substring(1, date[7].length() - 1);

            return new Streams(streamType, id, streamGenre, noOfStreams, streamerId, length, dateAdded, name);

        } else if (tip.equals("Streamer")) {
            // Crearea unui obiect Streamer și inițializarea atributelor folosind datele primite
            int streamerType = Integer.parseInt(date[0].substring(1, date[0].length() - 1));
            int id = Integer.parseInt(date[1].substring(1, date[1].length() - 1));
            String name = date[2].substring(1, date[2].length() - 1);

            return new Streamer(streamerType, id, name);

        }  else if (tip.equals("User")) {
            // Crearea unui obiect User și inițializarea atributelor folosind datele primite
            List<Integer> streams = new ArrayList<>();
            int i = 3;
            while (i < date.length) {
                int streamId = Integer.parseInt(date[i]);
                streams.add(streamId);
                i++;
            }


            int id = Integer.parseInt(date[1]);
            String name = date[2];

            return new User(id, name, streams);

        } else {
            // În cazul în care tipul dat nu corespunde niciunui tip cunoscut, returnăm null
            return null;
        }
    }
}
