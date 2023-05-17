
public class Borrower {
    private String borrowerId;
    private String name;
    private String contactDetails;

    public Borrower(String borrowerId, String name, String contactDetails) {
        this.borrowerId = borrowerId;
        this.name = name;
        this.contactDetails = contactDetails;
    }

    public String getBorrowerId() {
        return borrowerId;
    }

    public void setBorrowerId(String borrowerId) {
        this.borrowerId = borrowerId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getContactDetails() {
        return contactDetails;
    }

    public void setContactDetails(String contactDetails) {
        this.contactDetails = contactDetails;
    }
}
