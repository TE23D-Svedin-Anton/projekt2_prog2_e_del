// Anton Svedin TE23D Är en föräldrar klass till böcker och tidningar.
package server.com;

public class Publication {
    protected String id;
    protected String title;
    protected boolean isAvailable;

    public Publication(String id, String title, boolean isAvailable) {
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

    @Override
    public String toString() {
        return "Publication [id=" + id + ", Title=" + title + ", IsAvailable=" + isAvailable + "]";
    }

}