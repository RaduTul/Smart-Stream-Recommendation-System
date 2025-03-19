import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;


public class ProiectPOO {
    public ProiectPOO() {
    }

    public static void main(String[] args) {
        if (args == null) {
            System.out.println("Nothing to read here");
        } else {
            try {
                Scanner commands;
                Factory instance = new Factory();
                // Citesc cele 3 fisiere primite ca si parametrii in ordine
                // streamers.csv, streams.csv si users.csv
                BufferedReader users = new BufferedReader(new FileReader("src/main/resources/" + args[2]));
                BufferedReader streams = new BufferedReader(new FileReader("src/main/resources/" + args[1]));
                BufferedReader streamers = new BufferedReader(new FileReader("src/main/resources/" + args[0]));

                // Citesc linie cu linie fisierul streamers.csv
                String line1 = streamers.readLine();

                while ((line1 = streamers.readLine()) != null) {
                    // Imparte fiecare linie dupa caractere
                    String[] data = line1.split(",(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)");
                    Music curr = instance.Create("Streamer", data);
                    Singleton.getInstance().addStreamer((Streamer) curr);
                }

                // Citesc linie cu linie fisierul streams.csv
                String line2 = streams.readLine();

                while ((line2 = streams.readLine()) != null) {
                    String[] data = line2.split(",(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)");
                    Music curr = instance.Create("Stream", data);
                    Singleton.getInstance().addStream((Streams) curr);
                }

                // Citesc linie cu linie fisierul users.csv
                String line3 = users.readLine();

                while ((line3 = users.readLine()) != null) {
                    String[] data = line3.split("[,\" ]+");
                    Music curr = instance.Create("User", data);
                    Singleton.getInstance().addUser((User) curr);
                }

                commands = new Scanner(new File("src/main/resources/" + args[3]));

                // Citeste linie cu linie comenzile din commands.txt
                for (; commands.hasNextLine(); ) {
                    // linia curenta
                    String line = commands.nextLine();
                    // imparte datele dupa spatiu
                    String[] data = line.split(" ");
                    // creeaza o comanda noua
                    Command curr = new Execute();
                    // comanda primita in fisier
                    String command = data[1];

                    // comanda este ADD
                    if (command.equals("ADD")) {
                        // executa ADD
                        curr.ADD(data);
                    // comanda este LIST
                    } else if (command.equals("LIST")) {
                        // executa LIST
                        curr.LIST(Integer.parseInt(data[0]));
                    // comanda este DELETE
                    } else if (command.equals("DELETE")) {
                        // executa DELETE
                        curr.DELETE(data);
                    //comanda este LISTEN
                    } else if (command.equals("LISTEN")) {
                        // executa LISTEN
                        curr.LISTEN(data);
                    // comanda este RECOMMEND
                    } else if (command.equals("RECOMMEND")) {
                        // executa RECOMMEND
                        curr.RECOMMEND(data);
                    // comanda este SURPRISE
                    } else if (command.equals("SURPRISE")) {
                        // executa SURPRISE
                        curr.SURPRISE(data);
                    }
                }

                // inchide fisierele deschise
                streamers.close();
                streams.close();
                users.close();
                commands.close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

    }

    public static String displayTime(long totalSeconds) {
        long hours = totalSeconds / (long) 3600;
        long minutes = (totalSeconds % (long) 3600) / (long) 60;
        long seconds = totalSeconds % (long) 60;

        if (hours > (long) 0) {
            return String.format("%02d:%02d:%02d", hours, minutes, seconds);
        } else {
            return String.format("%02d:%02d", minutes, seconds);
        }
    }

    public static String formatDate(long timestamp) {
        LocalDate localDate = Instant.ofEpochSecond(timestamp).atZone(ZoneId.of("GMT")).toLocalDate();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        return formatter.format(localDate);
    }

}
