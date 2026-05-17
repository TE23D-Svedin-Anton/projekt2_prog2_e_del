// Anton Svedin TE23D Detta är en klass för objektet bok.
package server;

public class Magazine extends Publication {

    private int issueNumber;
    private String category;
    private int publisherYear;

    public Magazine(String id, String title, int issueNumber, String category, int publisherYear, boolean isAvailable) {
        super(id, title, isAvailable);
        this.issueNumber = issueNumber;
        this.category = category;
        this.publisherYear = publisherYear;
    }

    public int getIssueNumber() {
        return issueNumber;
    }

    public String getCategory() {
        return category;
    }

    public int getPublisherYear() {
        return publisherYear;
    }

    @Override
    public String toString() {
        return "Magazine [id=" + id + ", title=" + title + ", issueNumber=" + issueNumber + ", isAvailable="
                + isAvailable + ", category=" + category + ", publisherYear=" + publisherYear + "]";
    }

}