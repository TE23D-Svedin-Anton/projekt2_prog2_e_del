package server;

public class SuspendedUser {

    private String id;
    private String customerId;

    public SuspendedUser(String id, String customerId) {
        this.id = id;
        this.customerId = customerId;
    }

    public String getId() {
        return id;
    }

    public String getcustomerId() {
        return customerId;
    }

    @Override
    public String toString() {
        return "SuspendedUser [id=" + id + ", userId=" + customerId + "]";
    }
}
