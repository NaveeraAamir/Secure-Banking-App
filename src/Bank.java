import java.util.ArrayList;
import java.util.List;

public class Bank {
    private String name;
    private List<Client> clList;
    private List<Account> acList;

    public Bank(String name) {
        this.name = name;
        this.clList = new ArrayList<>();
        this.acList = new ArrayList<>();
    }

    public Client addClient(Person p) {
        Client c = new Client(p);
        clList.add(c);
        return c;
    }

    public Account addAccount(float amount, Client c) {
        Account a = new Account(amount, c);
        acList.add(a);
        c.addAccount(a);
        return a;
    }

    public Account searchAccount(String id) {
        for (Account a : acList) {
            if (a.getNumber().equals(id)) return a;
        }
        return null;
    }

    public boolean removeClient(String id) {
        Client foundClient = null;
        for (Client c : clList) {
            if (c.getId().equals(id)) {
                foundClient = c;
                break;
            }
        }
        if (foundClient != null) {
            acList.removeAll(foundClient.getAcList());
            clList.remove(foundClient);
            return true;
        }
        return false;
    }


    public Client searchCustomerDetail(String CNIC) {
        for (Client c : clList) {
            if (c.getPersonDetails().getCnic().equals(CNIC)) return c;
        }
        return null;
    }


    public List<Client> getClList() { return clList; }
}