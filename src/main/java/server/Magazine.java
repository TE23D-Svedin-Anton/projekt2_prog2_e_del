package server;

import server.com.Publication;

public class Magazine extends Publication{

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

    public void setIssueNumber(int issueNumber) {
        this.issueNumber = issueNumber;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public int getPublisherYear() {
        return publisherYear;
    }

    public void setPublisherYear(int publisherYear) {
        this.publisherYear = publisherYear;
    }

    @Override
    public String toString() {
        return "Magazine [id=" + id + ", title=" + title + ", issueNumber=" + issueNumber + ", isAvailable="
                + isAvailable + ", category=" + category + ", publisherYear=" + publisherYear + "]";
    }

    

    

}
