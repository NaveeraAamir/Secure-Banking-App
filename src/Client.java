import java.util.ArrayList;
import java.util.List;

public class Client {
    private String id;
    private Person personDetails;
    private List<Account> acList;
    public static int count = 0; // Static counter for client IDs

    public Client(Person personDetails) {
        this.id = "CID-" + (++count);
        this.personDetails = personDetails;
        this.acList = new ArrayList<>();
    }

    public void addAccount(Account a) {
        acList.add(a);
    }


    public String getId() { return id; }
    public Person getPersonDetails() { return personDetails; }
    public List<Account> getAcList() { return acList; }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Client ID: ").append(id).append("\n");
        sb.append("Details: ").append(personDetails.toString()).append("\n");
        sb.append("Accounts:\n");
        for (Account a : acList) {
            sb.append("  - ID: ").append(a.getNumber()).append(", Balance: ").append(a.getAmount()).append("\n");
        }
        return sb.toString();
    }
}