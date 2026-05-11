package server.com;

public class Publication {
    protected String id;
    protected String title;
    protected boolean isAvailable;
    
    public Publication(String id, String title, boolean isAvailable){
        this.id = id;
        this.title = title;
        this.isAvailable = isAvailable;
    }

    public String getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public boolean isAvailable() {
        return isAvailable;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setAvailable(boolean isAvailable) {
        this.isAvailable = isAvailable;
    }

    @Override
    public String toString() {
        return "Publication [ID=" + id + ", Title=" + title + ", IsAvailable=" + isAvailable + "]";
    }

    
}
