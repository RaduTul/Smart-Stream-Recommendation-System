import java.time.Instant;
import java.util.*;

class Execute extends Command {
    Execute() {
    }

    // Metoda pentru adăugarea unui nou stream
    public void ADD(String[] data) {
        String name = "";

        // Concatenarea numelui melodiei din elementele rămase ale comenzii
        for (int i = 6; i < data.length; i++) {
            name += data[i] + " ";
        }
        name = name.trim(); // Eliminarea spațiului final

        int noOfStreams = 0;
        // Obținerea timestamp-ului în secunde de la epoch-ul Unix
        Instant instant = Instant.now();
        long dateAdded = instant.getEpochSecond();

        // Crearea unui nou obiect Streams și adăugarea acestuia în lista de stream-uri
        Streams currStream = new Streams(Integer.parseInt(data[2]), Integer.parseInt(data[3]), Integer.parseInt(data[4]),
                noOfStreams, Integer.parseInt(data[0]), Integer.parseInt(data[5]), dateAdded, name);
        Singleton.getInstance().addStream(currStream);
    }

    // Metoda pentru afișarea listelor de stream-uri asociate unui anumit ID
    public void LIST(int Id) {
        ArrayList<Streamer> curr1 = Singleton.getInstance().getStreamerList();
        ArrayList<Streams> curr2 = Singleton.getInstance().getStreamList();
        ArrayList<User> curr3 = Singleton.getInstance().getUserList();
        String output1 = "";
        String output2 = "";

        // Parcurgere lista de streameri pentru afișarea stream-urilor asociate unui streamer cu ID-ul dat
        for (Streamer streamer : curr1) {
            if (streamer.getID() == Id) {
                output2 += "[";
                // Parcurgere lista de stream-uri și creare output pentru stream-urile asociate streamerului
                for (Streams stream : curr2) {
                    if (stream.getStreamerID() == streamer.getID()) {
                        String date = ProiectPOO.formatDate(stream.getDateAdded());
                        String length = ProiectPOO.displayTime(stream.getLength());
                        output2 += "{\"id\":\"" + stream.getID() + "\",\"name\":\"" +
                                stream.getName() + "\",\"streamerName\":\"" +
                                streamer.getName() + "\",\"noOfListenings\":\"" +
                                stream.getNoOfStreams() + "\",\"length\":\"" + length +
                                "\",\"dateAdded\":\"" + date + "\"},";
                    }
                }
                if (output2.length() > 0) {
                    output2 = output2.substring(0, output2.length() - 1);
                }
                output2 += "]";
                System.out.println(output2);
            }
        }

        // Parcurgere lista de utilizatori pentru afișarea stream-urilor asociate unui utilizator cu ID-ul dat
        for (User user : curr3) {
            if (user.getID() == Id) {
                output1 += "[";
                // Parcurgere lista de ID-uri de stream-uri ale utilizatorului și creare output pentru acestea
                for (Integer currStreamID : user.getStreams()) {
                    for (Streams currStream : curr2) {
                        if (currStreamID == currStream.getID()) {
                            for (Streamer currStreamer : curr1) {
                                if (currStream.getStreamerID() == currStreamer.getID()) {
                                    String date = ProiectPOO.formatDate(currStream.getDateAdded());
                                    String length = ProiectPOO.displayTime(currStream.getLength());
                                    output1 += "{\"id\":\"" + currStream.getID() + "\",\"name\":\"" +
                                            currStream.getName() + "\",\"streamerName\":\"" +
                                            currStreamer.getName() + "\",\"noOfListenings\":\"" +
                                            currStream.getNoOfStreams() + "\",\"length\":\"" + length +
                                            "\",\"dateAdded\":\"" + date + "\"},";
                                }
                            }
                        }
                    }
                }
                if (output1.length() > 0) {
                    output1 = output1.substring(0, output1.length() - 1);
                }
                output1 += "]";
                System.out.println(output1);
            }
        }
    }

    // Metoda pentru ștergerea unui stream
    public void DELETE(String[] data) {
        ArrayList<Streams> curr = Singleton.getInstance().getStreamList();
        // Parsarea datelor primite în comanda de tip DELETE
        int streamerId = Integer.parseInt(data[0]);
        int streamId = Integer.parseInt(data[2]);
        Iterator<Streams> iterator = curr.iterator();

        try {
            while (iterator.hasNext()) {
                Streams currStream = iterator.next();
                if (streamId == currStream.getID() && streamerId == currStream.getStreamerID()) {
                    iterator.remove();
                    break;
                }
            }
        } catch (ConcurrentModificationException e) {
            System.out.println("A apărut ConcurrentModificationException: " + e.getMessage());
        }
    }

    // Metoda pentru ascultarea unui stream
    public void LISTEN(String[] data) {
        ArrayList<Streams> curr1 = Singleton.getInstance().getStreamList();
        ArrayList<User> curr2 = Singleton.getInstance().getUserList();
        int userId = Integer.parseInt(data[0]);
        int streamId = Integer.parseInt(data[2]);

        Streams listener = null;

        // Găsirea stream-ului pe care utilizatorul îl ascultă
        for (Streams currStream : curr1) {
            if (streamId == currStream.getID()) {
                listener = currStream;
                break;
            }
        }

        if (listener != null) {
            // Creșterea numărului de ascultări pentru stream-ul respectiv
            long newNoOfStreams = listener.getNoOfStreams() + (long) 1;
            listener.setNoOfStreams(newNoOfStreams);
        }

        // Adăugarea stream-ului ascultat în lista de stream-uri a utilizatorului
        for (User currUser : curr2) {
            if (userId == currUser.getID()) {
                currUser.getStreams().add(streamId);
            }
        }
    }

    // Metoda pentru recomandarea unor stream-uri utilizatorului
    public void RECOMMEND(String[] data) {
        User curr1 = new User();
        ArrayList<Streams> curr2 = new ArrayList<>();
        ArrayList<Streamer> curr3 = new ArrayList<>();
        ArrayList<Streamer> currStreamerList = Singleton.getInstance().getStreamerList();
        ArrayList<Streams> currStreamList = Singleton.getInstance().getStreamList();
        ArrayList<User> currUserList = Singleton.getInstance().getUserList();
        String output = "";
        int userId = Integer.parseInt(data[0]);

        // Parcurgere lista de utilizatori pentru a găsi utilizatorul cu ID-ul dat
        for (User currUser : currUserList) {
            if (userId == currUser.getID()) {
                curr1 = currUser;
                // Parcurgere lista de ID-uri de stream-uri ale utilizatorului pentru a identifica stream-urile ascultate
                for (Integer currStreamId : currUser.getStreams()) {
                    // Parcurgere lista de stream-uri pentru a identifica streamerii asociati stream-urilor ascultate
                    for (Streams currStream : currStreamList) {
                        if (currStreamId == currStream.getID()) {
                            int StreamerID = currStream.getStreamerID();
                            // Parcurgere lista de streameri pentru a adăuga streamerii unici în lista de recomandări
                            for (Streamer currStreamer : currStreamerList) {
                                if (currStreamer.getID() == StreamerID && !curr3.contains(currStreamer)) {
                                    curr3.add(currStreamer);
                                }
                            }
                        }
                    }
                }
                break;
            }
        }

        // Parcurgere stream-eri pentru a identifica stream-urile asociate streamerilor din lista de recomandări
        for (Streamer currStreamer : curr3) {
            for (Streams currStream : currStreamList) {
                if (currStream.getStreamerID() == currStreamer.getID() &&
                        !curr2.contains(currStream) &&
                        !curr1.getStreams().contains(currStream.getID())) {
                    curr2.add(currStream);
                }
            }
        }

        // Sortarea listei de stream-uri recomandate în funcție de numărul de ascultări
        Collections.sort(curr2, Comparator.comparingLong(Streams::getNoOfStreams));
        int i = 5;
        output += "[";

        // Crearea output-ului pentru primele 5 stream-uri recomandate
        for (Streamer currStreamer : currStreamerList) {
            for (Streams currStream : curr2) {
                if (currStream.getStreamerID() == currStreamer.getID() && i != 0) {
                    String length = ProiectPOO.displayTime(currStream.getLength());
                    String date = ProiectPOO.formatDate(currStream.getDateAdded());
                    output += "{\"id\":\"" + currStream.getID() + "\",\"name\":\"" +
                            currStream.getName() + "\",\"streamerName\":\"" +
                            currStreamer.getName() + "\",\"noOfListenings\":\"" +
                            currStream.getNoOfStreams() + "\",\"length\":\"" +
                            length + "\",\"dateAdded\":\"" + date + "\"},";
                    i--;
                }
            }
        }

        if (output.length() > 0) {
            output = output.substring(0, output.length() - 1); // Eliminarea virgulei finale
        }
        output += "]";
        System.out.println(output);
    }

    public void SURPRISE(String[] data) {}
}

