import java.io.*;
import java.util.*;

public class FileHandler {
    private static final String FILE_NAME = "bank_data.txt";

    public static void saveAllData(Bank bank) {
        try (PrintWriter out = new PrintWriter(new FileWriter(FILE_NAME))) {
            for (Client c : bank.getClList()) {
                Person p = c.getPersonDetails();
                for (Account a : c.getAcList()) {
                    String historyStr = String.join(";", a.getHistory());
                    out.println(p.getName() + "|" + p.getCnic() + "|" + p.getPhoneNo() + "|" + a.getAmount() + "|" + historyStr);
                }
            }
        } catch (IOException e) {
            System.err.println("Error saving: " + e.getMessage());
        }
    }

    public static void loadData(Bank bank) {
        File file = new File(FILE_NAME);
        if (!file.exists()) return;
        try (Scanner sc = new Scanner(file)) {
            while (sc.hasNextLine()) {
                String[] data = sc.nextLine().split("\\|");
                if (data.length >= 4) {
                    Person p = new Person(data[0], data[1], data[2]);
                    Client c = bank.addClient(p);
                    Account a = bank.addAccount(Float.parseFloat(data[3]), c);
                    if (data.length == 5) {
                        a.getHistory().clear();
                        String[] histEntries = data[4].split(";");
                        for (String entry : histEntries) a.getHistory().add(entry);
                    }
                }
            }
        } catch (Exception e) { System.err.println("Error loading: " + e.getMessage()); }
    }
}