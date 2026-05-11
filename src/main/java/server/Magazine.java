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

    

}
