package server;

public class SuspendedUser {

    private String id;
    private String userId;

    public SuspendedUser(String id, String userId) {
        this.id = id;
        this.userId = userId;
    }

    public String getId() {
        return id;
    }

    public String getUserId() {
        return userId;
    }

    @Override
    public String toString() {
        return "SuspendedUser [id=" + id + ", userId=" + userId + "]";
    }
}
