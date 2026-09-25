public class Person {
    private String name;
    private String cnic;
    private String phoneNo;

    public Person(String name, String cnic, String phoneNo) {
        this.name = name;
        this.cnic = cnic;
        this.phoneNo = phoneNo;
    }


    public String getName() { return name; }
    public String getCnic() { return cnic; }
    public String getPhoneNo() { return phoneNo; }

    @Override
    public String toString() {
        return "Name: " + name + ", CNIC: " + cnic + ", Phone: " + phoneNo;
    }
}